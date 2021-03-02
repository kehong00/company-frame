import axios from 'axios'
import router from "@/router";
import store from '@/store'
import { MessageBox, Message } from "element-ui";  // 引入
import { Base64 } from 'js-base64';
/*axios.defaults.baseURL = "http://localhost:9990"
axios.interceptors.response.use((resp) =>{
    const code = resp.data.code;
    if (code == 4010004 || code == 4010003){
        this.$route.push("/login");
    }else if (code == 4010005){
        localStorage.setItem("accessToken",resp.data.data);
        this.$store.dispatch("setToken",resp.data.data);
    }
});*/
const service = axios.create({
    baseURL: "http://localhost:9990"
});
/*service.interceptors.response.use((resp) =>{
    const code = resp.data.code;
    if (code == 4010004 || code == 4010003){
        this.$route.push("/login");
    }else if (code == 4010005){
        localStorage.setItem("accessToken",resp.data.data);
        this.$store.dispatch("setToken",resp.data.data);
    }
})*/
service.interceptors.response.use(
    (resp) => {
        console.log(resp);
        const code = resp.data.code;
        if (code == 4010004 || code == 4010003){
            console.log(code)
            Message.error(resp.data.msg);
            router.push("/login");
            return;
        }else if (code == 4010005){
            /*localStorage.setItem("accessToken",resp.data.data);
            store.dispatch("setToken",resp.data.data);*/
            console.log("token过期")
            //刷新token
            //base64加密refreshToken
            // const encode = Base64.encode(store.state.refreshToken);
            service.get("/api/pub/refreshtoken", {
                params: {
                    refreshToken: store.state.refreshToken
                }
            }).then(resp =>{
                console.log("交换token")
                //判断是否成功交换token
                if (resp.data.code == 0){
                    //交换成功，更新token
                    localStorage.setItem("accessToken",resp.data.data);
                    store.dispatch("setToken",resp.data.data);
                    console.log("交换成功")
                }else{
                    /*router.push("/login");
                    return;*/
                    console.log("交换失败")
                }
            })
            // return Promise.resolve(resp);
        }else{
            console.log(resp.data.code);
            return Promise.resolve(resp);
        }

    }
)
// const service = axios;

export default service;
