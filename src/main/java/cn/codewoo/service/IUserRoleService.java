package cn.codewoo.service;


import cn.codewoo.entity.SysUserRole;
import cn.codewoo.vo.req.UserOwnRoleEditReqVO;

import java.util.List;

/**
 * @author kehong
 * 用户角色关联表服务接口
 */
public interface IUserRoleService {
    /**
     * 批量添加用户角色关联记录
     * @param list
     * @return
     */
    int batchUserRole(List<SysUserRole> list);

    /**
     * 编辑用户角色信息
     * @param vo
     * @return
     */
    int editOwnRole(UserOwnRoleEditReqVO vo);
}
