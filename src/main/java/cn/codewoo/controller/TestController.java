package cn.codewoo.controller;

import cn.codewoo.entity.SysUser;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.TestReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/swagger")
@Api(tags = "swagger测试接口")
public class TestController {
    @GetMapping("/test")
    @ApiOperation("测试")
    public DataResult<SysUser> test(){
        SysUser user = new SysUser();
        user.setUsername("kehong");
        user.setPassword("111111");
        user.setRealName("kehong");
        int i = 1/0;
        return DataResult.getDataResult(BaseResponseCodeImpl.SUCCESS,user);
    }

    @GetMapping("/type")
    public DataResult testBusinessException(@RequestParam String type){
        if (!"1".equals(type)){
            throw new BusinessException(BaseResponseCodeImpl.DATA_ERROR);
        }
        return DataResult.getDataResult(BaseResponseCodeImpl.SUCCESS,type);
    }

    @PostMapping("/test/valid")
    @ApiOperation(value = "校验器测试")
    public DataResult<TestReqVO> testValid(@RequestBody @Valid TestReqVO vo){
        return DataResult.getDataResult(BaseResponseCodeImpl.SUCCESS,vo);
    }
}
