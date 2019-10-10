$(function(){
    //初始化Toastr组件
    initToastrConfig();
    var loginRequestUrl = "/usercontroller/login";
    $("#login").bind("click",function () {
        var rightFlag = true;
        var username = $("#username").val();
        var password = $("#password").val();
        var captcha = $("#captcha").val();
        if(username==null||username==""){
            isNull($("#username"));
            rightFlag=false;
        }
        if(password==null||password==""){
            isNull($("#password"));
            rightFlag=false;
        }
        if(rightFlag){
            var userInfo = {};
            userInfo.userName = username;
            userInfo.password = password;
            var formData = new FormData();
            formData.append("userInfo",JSON.stringify(userInfo));
            formData.append("captcha",captcha);
            $.ajax({
                url:loginRequestUrl,
                type:'POST',
                data:formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function(data){
                    if(data.successFlag==true){
                        toastr.success(data.message,"提示");
                        window.location.href="/frontend/index";
                    }else{
                        toastr.error(data.message,"提示");
                        $("#captchaImg").click();
                    }
                }
            });
        }
    });

    function isNull(item){
        item.parent().children("#after").remove();
        item.parent().addClass("has-danger");
        item.after("<small id='after' class='form-text'>"+item.attr('placeholder')+"不能为空</small>");
    }
});