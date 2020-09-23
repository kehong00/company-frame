/*CoreUtil*/
/*工具类，类似java静态工具类*/
var CoreUtil = (function () {
    var coreUtil = {};
    /*ajax请求*/
    coreUtil.sendAjax = function (url, params, ft, method, async,contentType,headers) {
        var roleSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        layui.jquery.ajax({
            url: url,
            cache: false,
            async: async == undefined ? true : async,
            data: params,
            type: method == undefined ? "POST" : method,
            contentType: contentType == undefined ? 'application/json; charset=UTF-8': contentType ,
            dataType: "json",
            beforeSend: function(request){
                console.log('传入的header是：' + headers + '\n获取的accessToken： ' + CoreUtil.getData('accessToken'));
                if (headers == undefined || headers == null){
                    request.setRequestHeader('authorization',CoreUtil.getData('accessToken'));
                }else if (headers){
                    request.setRequestHeader('authorization',CoreUtil.getData('accessToken'));
                    request.setRequestHeader('refreshToken',CoreUtil.getData('refreshToken'));
                }else{

                }
            },
            success: function (res) {
                top.layer.close(roleSaveLoading);
                if (typeof ft == "function") {
                    if(res.code==4010001){ //凭证过期重新登录
                        layer.msg("凭证过期请重新登录")
                        top.window.location.href="/index/login"
                    }else if(res.code==0) {
                        if(ft!=null&&ft!=undefined){
                            ft(res);
                        }
                    } else if(res.code==4030001){
                        if(ft!=null&&ft!=undefined){
                            noAuthorityFt(res);
                        }
                    }else {
                        layer.msg(res.msg)
                    }

                }
            },error:function (XMLHttpRequest, textStatus, errorThrown) {
                top.layer.close(roleSaveLoading);
                if(XMLHttpRequest.status==404){
                    top.window.location.href="/index/404";
                }else{
                    layer.msg("服务器好像除了点问题！请稍后试试");
                }
            }
        });
    };
    //在本地存放数据
    coreUtil.setData = function (key,value) {
        layui.sessionData('LocalData',{
            key: key,
            value: value
        })
    };
    //获取本地存放的数据
    coreUtil.getData = function (key){
        let localData = layui.sessionData('LocalData');
        return localData[key];
    };

    /*格式化时间格式*/
    coreUtil.formattime=function (val) {
        if(val==null||val==undefined){
            return "";
        }
        var date=new Date(val);
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var hh=date.getHours();
        hh=hh>9?hh:('0'+hh);
        var mm=date.getMinutes();
        mm=mm>9?mm:('0'+mm);
        var ss=date.getSeconds();
        ss=ss>9?ss:('0'+ss);
        var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
        return time;
    };
    return coreUtil;
})(CoreUtil, window);