import axios from '../../request'
import id from "element-ui/src/locale/lang/id";

//获取支付宝登录url
export const getAlipayLoginUrl = () => axios.get("/api/pub/user/alipay/login_url");

//普通登录方式登录接口
export const usernamePasswordLoginApi = (username,pwd) => axios.post("/api/pub/user/login",{
    username,
    pwd,
    type: "1"
});

//退出登录
export const loginOutApi = (accessToken,refreshToken) => axios.get("/api/auth/loginout",{
    params: {
        token: accessToken,
        refreshToken: refreshToken
    }
})

//获取权限树形列表，包含按钮
export const getPermissionTreeInBtn = (token) => axios.get("/api/auth/permission/tree/all",{
    headers: {
        token
    }
})

//获取权限树形列表，不包含按钮
export const getPermissionTreeExBtn = (token) => axios.get("/api/auth/v2/permission/tree",{
    headers: {
        token
    }
})

//获取用户菜单列表
export const getUserMenuTreeApi = (token) => axios.get("/api/auth/home",{
    headers: {
        token
    }
})

//获取菜单权限详情
export const getPermissionMenuApi = (id) => axios.get("/api/auth/v2/permission/detail",{
    params: {
        id
    }
})

//获取所有权限列表
export const getAllPermissionListApi = (token) => axios.get("/api/auth/permission/list",{
    headers: {
        token
    }
})

//编辑权限接口
export const editPermissionApi = (token,form) => axios.post("/api/auth/v2/permission/edit",{
    id: form.id,
    name: form.name,
    code: form.code,
    perms: form.perms,
    url: form.url,
    method: form.method,
    type: form.type,
    orderNum: form.orderNum,
    pid: form.pid,
    status: form.status
},{
    headers: {token}
})

//根据权限id获取权限信息
export const getPermissionInfoApibak = (token,id) => axios.get("/api/pub/permission/info",{
    //参数列表
    params:{ id: id,token: token},
})

export const getPermissionInfoApi = (token,id) => axios.get("/api/pub/permission/info",{
    params: {
        id: id
    },
    headers: {
        token: token
    }
})

export const addPermissionApi = (token,form) => axios.post("/api/auth/permission/add",{
    name: form.name,
    code: form.code,
    perms: form.perms,
    url: form.url,
    method: form.method,
    type: form.type,
    orderNum: form.orderNum,
    pid: form.pid,
    status: form.status
},{
    headers: {
        token: token
    }
})

//删除指定的权限记录
export const deletePermissionApi = (token,id) => axios.delete("/api/auth/v2/permission/del/" + id,{
    headers: {
        token
    }
})

//获取部门树形结构数据
export const deptTreeApi = (token) => axios.get("/api/auth/dept/tree",{
    headers: {
        token: token
    }
})

//根据id获取部门信息
export const deptInfoApi = (token,id) => axios.get("/api/auth/dept/info",{
    params: {
        id: id
    },
    headers: {
        token: token
    }
})

//编辑部门信息
export const deptEditApi = (token,form) => axios.post("/api/auth/v2/dept/edit",{
    id: form.id,
    name: form.name,
    pid: form.pid,
    managerName: form.managerName,
    phone: form.phone,
    status: form.status
},{
    headers: {
        token: token
    }
})

export const deptAddApi = (token,form) => axios.post("/api/auth/v2/dept/add",{
    name: form.name,
    pid: form.pid,
    managerName: form.managerName,
    phone: form.phone,
    status: form.status
},{
    headers: {
        token: token
    }
})

//获取全部用户列表
export const userListAllApi = (token,form) => axios.post("/api/auth/v1/user/list",{
    pageNum: form.pageNum,
    pageSize: form.pageSize,
    username: form.username,
    userId: form.userId,
    status: form.status,
    startTime: form.startTime,
    endTime: form.endTime,
    nickName: form.nickName
},{
    headers: {
        token
    }
})

//新增用户接口
export const userAddApi = (token,form) => axios.post("/api/auth/v2/user/add",{
    username: form.username,
    password: form.password,
    phone: form.phone,
    deptId: form.deptId,
    realName: form.realName,
    nickName: form.nickName,
    email: form.email,
    sex: form.sex,
    status: form.status,
    createWhere: form.createWhere
},{
    headers: {
        token: token
    }
})

//删除用户接口
export const userDeleteApi = (token,userList) => axios.post("/api/auth/v2/user/delete",{
    userIds: userList
},{
    headers: {
        token: token
    }
})

/**
 * 获取用户拥有角色和可赋予角色列表
 * @param token
 * @param userId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const userOwnRoleApi = (token,userId) => axios.get("/api/user/role/" + userId,{
    headers: {
        token: token
    }
})

/**
 * 编辑用户拥有角色
 * @param token
 * @param userId 用户id
 * @param userRoleList 用户拥有权限id列表
 * @returns {Promise<AxiosResponse<any>>}
 */
export const userOwnRoleEditApi = (token,userId,userRoleList) => axios.post("/api/user/role/edit",{
    userId: userId,
    roleIds: userRoleList
})


//分页查询角色记录
export const roleListPageApi = (token,form) => axios.post("/api/auth/role/page",{
    pageNum: form.pageNum,
    pageSize: form.pageSize,
    roleId: form.roleId,
    roleName: form.roleName,
    status: form.status,
    startTime: form.startTime,
    endTime: form.endTime
},{
    headers: {
        token: token
    }
})

//获取所有角色列表
export const roleListAllApi = (token) => axios.get("/api/auth/role/list",{
    headers: {
        token: token
    }
})

//获取角色拥有的权限
export const rolePermissionListApi = (token,roleId) => axios.get("/api/auth/role/permission/list",{
    params: {
        roleId: roleId
    },
    headers: {
        token: token
    }
})

//添加角色
export const roleAddApi = (token,form) => axios.post("/api/auth/v2/role/add",{
    name: form.roleName,
    description: form.description,
    status: form.status,
    permissionIds: form.permissionIds
},{
    headers: {
    token: token
}})

//根据角色id获取角色回显信息
export const roleInfoApi = (token,id) => axios.get("/api/auth/role/info/" + id,{
    headers: {
        token: token
    }
})

//编辑角色
export const roleEditApi = (token,form) => axios.post("/api/auth/v2/role/edit",{
    id: form.roleId,
    name: form.roleName,
    description: form.description,
    status: form.status,
    permissionIds: form.permissionIds
},{
    headers: {
        token: token
    }
})


/**
 * 系统日志分页查询
 * @param token
 * @param form
 * @returns {Promise<AxiosResponse<any>>}
 */
export const sysLogPageApi = (token,form) => axios.post("/api/auth/api/log/page",{
    pageNum: form.pageNum,
    pageSize: form.pageSize,
    userId: form.userId,
    username: form.username,
    action: form.action,
    startTime: form.startTime,
    endTime: form.endTime
},{
    headers: {
        token: token
    }
    }
)

/**
 * 删除日志记录
 * @param token
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const sysLogDeleteApi = (token,id) => axios.delete("/api/auth/log/delete/" + id,{
    headers: {
        token: token
    }
})

/**
 * 批量删除日志记录
 * @param token
 * @param ids
 * @returns {Promise<AxiosResponse<any>>}
 */
export const sysLogBatchDeleteApi = (token,ids) => axios.post("/api/auth/log/batch_del",{
    ids: ids
},{
    headers: {
        token: token
    }
})


/**
 * 获取个人中心回显数据
 * @param token
 * @returns {Promise<AxiosResponse<any>>}
 */
export const userInfoApi = (token) => axios.get("/api/auth/user/personal",{
    headers: {
        token: token
    }
})
