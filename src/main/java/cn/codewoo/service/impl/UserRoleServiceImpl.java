package cn.codewoo.service.impl;

import cn.codewoo.constant.Constant;
import cn.codewoo.entity.SysUserRole;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.mapper.SysUserRoleMapper;
import cn.codewoo.service.IUserRoleService;
import cn.codewoo.service.RedisService;
import cn.codewoo.utils.CommonUtils;
import cn.codewoo.utils.TokenSetting;
import cn.codewoo.vo.req.UserOwnRoleEditReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserRoleServiceImpl
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/17 11:27 上午
 * @Version 1.0
 **/
@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired(required = false)
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenSetting tokenSetting;

    @Override
    public int batchUserRole(List<SysUserRole> list) {
        if (list.isEmpty()){
            return 0;
        }
        int row = userRoleMapper.batchInsert(list);
        return row;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class, BusinessException.class})
    public int editOwnRole(UserOwnRoleEditReqVO vo) {
        //先删除关联表中的关联记录
        int deleteRow = userRoleMapper.deleteByUserId(vo.getUserId());
        if (deleteRow <= 0){
            throw new BusinessException(BaseResponseCodeImpl.SYS_ERROR);
        }
        //插入新的关联记录
        List<SysUserRole> list = new ArrayList<>();
        vo.getRoleIds().forEach(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(CommonUtils.generateUUID());
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRole.setCreateTime(new Date());
            list.add(sysUserRole);
        });
        int row = batchUserRole(list);
        if (row < 0){
            throw new BusinessException(BaseResponseCodeImpl.SYS_ERROR);
        }

        //标记用户，需要去主动刷新token
//        redisService.set(Constant.JWT_REFRESH_KEY+vo.getUserId(),vo.getUserId(),tokenSetting.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
        String key = Constant.JWT_REFRESH_KEY + vo.getUserId();
        String value = vo.getUserId();
        redisService.set(key,value,tokenSetting.getAccessTokenExpireTime().toMillis(),TimeUnit.MILLISECONDS);
        return row;
    }
}
