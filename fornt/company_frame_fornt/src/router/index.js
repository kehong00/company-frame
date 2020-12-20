import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "@/views/Login/Login";
import Token from "@/views/token/Token";
import PermissionManager from "@/views/PermissionManager/PermissionManager";
import Main from "@/views/Main";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Main',
        component: Main,
        children: [
            {
                path: "/index",
                name: '首页',
                component: () => import('@/views/Home/Home')
            },
            {
                path: "/system/permission/index",
                name: '菜单权限管理',
                component: () => import('@/views/PermissionManager/PermissionManager')
            },
            {
                path: "/system/dept/index",
                name: '部门管理',
                component: () => import('@/views/DeptManager/DeptManager')
            },
            {
                path: "/system/role/index",
                name: '角色管理',
                component: () => import('@/views/RoleManager/RoleManager')
            }
            ,{
                path: "/system/user/index",
                name: '用户管理',
                component: () => import('@/views/UserManager/UserManager')
            }
            ,{
                path: "/system/log/index",
                name: '日志管理',
                component: () => import('@/views/SysLogManager/SysLogManager')
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/token',
        name: 'Token',
        component: Token
    },
    {
        path: '/permissions',
        name: 'Permissions',
        component: PermissionManager
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
