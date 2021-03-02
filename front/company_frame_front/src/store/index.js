import Vue from 'vue'
import Vuex from 'vuex'
import tab from './tab'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        //从本地获取token，没有则为''
        accessToken: localStorage.getItem("accessToken") || '',
        selectMenuKey: "",
        refreshToken: localStorage.getItem("refreshToken") || '',
        menuList: [],
        userInfo: {}
    },
    mutations: {
        SET_TOKEN: ((state, token) => {
            state.accessToken = token;
        }),
        SET_SELECT_KEY: ((state, payload) => {
            state.selectMenuKey = payload;
        }),
        SET_REFRESHTOKEN: ((state, refreshToken) => {
            state.refreshToken = refreshToken;
        }),
        SET_MENULIST: (((state, payload) => {
            state.menuList = payload;
        })),
        SET_USERINFO: ((state, payload) => {
            state.userInfo = payload;
        })
    },
    actions: {
        setToken(context,token){
            context.commit("SET_TOKEN",token);
        },
        clearToken(context){
            context.commit("SET_TOKEN","");
        },
        setRefreshToken(context,refreshToken){
            context.commit("SET_REFRESHTOKEN",refreshToken);
        },
        clearRefreshToken(context){
            context.commit("SET_REFRESHTOKEN","");
        },
        setSelectMenuKey(context,key){
            context.commit("SET_SELECT_KEY",key);
        },
        setUserInfo(context,key){
            context.commit("SET_USERINFO",key);
        },
        setMenuList(context,key){
            context.commit("SET_MENULIST",key);
        }
    },
    modules: {
        tab
    }
})
