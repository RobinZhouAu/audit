/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-23
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
var AppManagerUtil = function () {
    return {
        setTimeId: "",
        bsCallCs: function (appId,type,login,refresh,toboard,feedback) {
            if(Global.userId == undefined || Global.userId == ""){
                Global.userId = 'admin';
                Global.userName = 'admin';
                Global.token = 'dayang';
            }
            if(type == undefined || type == ""){
                type = 'online';
            }
            if(login == undefined || login == ""){
                login = 'true';
            }
            if(refresh == undefined || refresh == ""){
                refresh = 'true';
            }
            if(toboard == undefined || toboard == ""){
                toboard = 'true';
            }
            if(feedback == undefined || feedback == ""){
                feedback = 'false';
            }
            appId = appId.toUpperCase();
            var app = "";
            var BsCallCs = "dyitcre://";
            var userInfo = "&userId=" + Global.userId + '&username=' + Global.userName + '&token=' + Global.token
                + '&type=' + type + '&login=' + login + '&refresh=' + refresh + '&toboard=' + toboard
                + '&feedback= ' + feedback + '&param=';
            var url = "";
            if (appId == "QUICKCUT") {
                //appId = 1;
            	app=1;
                url = BsCallCs + "app=1" + userInfo;
            } else if (appId == "LEOVIDEO") {
//                appId = 2;
//                url = BsCallCs + "app=" + appId + userInfo;
            	app=2;
                url = BsCallCs + "app=2" + userInfo;
            } else if (appId == "CLOUDEDIT") {
//                appId = 3;
//                url = BsCallCs + "app=" + appId + userInfo;
            	app=3;
                url = BsCallCs + "app=3" + userInfo;
            } else if (appId == "LAUNCHER") {
//                appId = 3;
//                url = BsCallCs + "app=" + appId + userInfo;
            	app=4;
                url = BsCallCs + "app=4" + userInfo;
            } else if (appId == "INGEST") {
            	app=5;
                url = BsCallCs + "app=5" + userInfo;
            } else if (appId == "QC") {
            	app=6;
                url = BsCallCs + "app=6" + userInfo;
            }else {
            	app=0;
                url = BsCallCs + "app=0" + userInfo + "&appname=" + appId;
            }

            if (AppManagerUtil.setTimeId != "") {
                clearTimeout(AppManagerUtil.setTimeId);
            }
            AppManagerUtil.setTimeId =
                window.setTimeout(function(){

                    $.ajax({
                        type : "post",
                        /*async: false,*/
                        cache:false,
                        data:"appId=1",
//                       url : "http://localhost:8043/urcwebservice30/controller/jsonp?appId=" + appId,
                        url : "http://localhost:5656/?appId=" + /*appId*/app,
                        timeout: 2000,
                        dataType : "jsonp",//数据类型为jsonp
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success : function(data){
                            if(data.status == 1){

                                bootbox.dialog({
                                    //message: '当前C/S程序未安装，请' + '<a class="downloadFile" href="downloadFile?file=agent">下载安装</a>',
                                    message: '当前C/S程序启动遇到问题，请检查本机当前C/S程序!',
                                    title: "提示信息",
                                    buttons: {
                                        main: {
                                            label: "关闭",
                                            className: "blue",
                                            callback: function () {
                                                //alert("Primary button");
                                            }
                                        }
                                    }
                                });

                            } else {
                                //bootbox.alert("启动当前CS应用时出现，请重新检查!");
                                return false;
                            }

                        },
                        error:function(data){
                        	/**
                        	 * 由于jsonp返回的json格式有误
                        	 * 现以XMLHttpRequest的readyState作为标识
                        	 */
                        	var readyState = data.readyState;
//                        	XMLHttpRequest.readyState
//                        	0 － （未初始化）还没有调用send()方法
//                        	1 － （载入）已调用send()方法，正在发送请求
//                        	2 － （载入完成）send()方法执行完成，已经接收到全部响应内容
//                        	3 － （交互）正在解析响应内容
//                        	4 － （完成）响应内容解析完成，可以在客户端调用了
                        	if(readyState==0){
	                            bootbox.dialog({
	                                message: 'BSCallCSAgent程序未安装或者未启动，请' + '<a class="downloadFile" href="downloadFile?file=agent">下载安装</a>' + '或者重新启动!',
	                                title: "提示信息",
	                                buttons: {
	                                    main: {
	                                        label: "关闭",
	                                        className: "blue",
	                                        callback: function () {
	                                            //alert("Primary button");
	                                        }
	                                    }
	                                }
	                            });
                        	}else if(readyState==4){
	                            bootbox.dialog({
	                                message: appId+'程序未安装或者未启动，请' + '<a class="downloadFile" href="downloadFile?file=agent&appId='+appId+'"'+'>下载安装</a>' + '或者重新启动!',
	                                title: "提示信息",
	                                buttons: {
	                                    main: {
	                                        label: "关闭",
	                                        className: "blue",
	                                        callback: function () {
	                                            //alert("Primary button");
	                                        }
	                                    }
	                                }
	                            });
                        }
                      }
                    });

                    AppManagerUtil.setTimeId = "";
                },2000);

            //window.location.href = "dyitcre://app=2&userId=mam&pass=mam";
            window.open(url, "hiddenFrame");
        },

        empty: null
    };
}();
