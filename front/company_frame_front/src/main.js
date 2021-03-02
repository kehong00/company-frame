import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI)

Vue.config.productionTip = false


//配置路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('accessToken');
  console.log('main中获取的token: ' + token)
  if (to.matched.some(res => res.meta.requiresAuth)) {
    if (token){
      console.log('拦截后放行');
      next();
    }else {
      next({path: '/login'})
    }
  }else {
    console.log('没有被拦截')
    next();
  }
})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
