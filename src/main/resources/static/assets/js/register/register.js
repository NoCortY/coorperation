$(function(){
    //初始化Toastr组件
    initToastrConfig();
    //注册用户
    var registerUrl = "/usercontroller/registeruser";
    //获取验证码
    var getCaptchaUrl = "/captchacontroller/getcaptcha";
    //查看验证码状态
    var getCaptchaStatusUrl = "/captchacontroller/iscaptchaexist";
    //邮箱正则
    var reg_email = new RegExp("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
    //密码正则
    var reg_password = new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")
    //手机号正则
    var reg_telenum = new RegExp("^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$");
    /*防刷新：检测是否存在cookie*/
    if($.cookie("captcha")){
        var count = $.cookie("captcha");
        var btn = $('#getcaptcha');
        btn.val(count+'秒后可重新获取').attr('disabled',true).css('cursor','not-allowed');
        var resend = setInterval(function(){
            count--;
            if (count > 0){
                btn.val(count+'秒后可重新获取').attr('disabled',true).css('cursor','not-allowed');
                $.cookie("captcha", count, {path: '/', expires: (1/86400)*count});
            }else {
                clearInterval(resend);
                btn.val("获取验证码").removeClass('disabled').removeAttr('disabled style');
            }
        }, 1000);
    }
    $("#getcaptcha").bind("click",function (e) {
       var userEmail = $("#email").val();
       if(userEmail==null||userEmail==""){
           isNull($("#email"));
           return;
       }
       var formData = new FormData();
       formData.append("userEmail",userEmail);
       $.ajax({
           url:getCaptchaUrl,
           type:'POST',
           data:formData,
           contentType:false,
           processData:false,
           cache:false,
           success:function(data){
               if(data.successFlag==true){
                   toastr.success(data.message,"提示");
                   var count = 60;
                   var btn = $('#getcaptcha');
                   btn.val(count+'秒后可重新获取').attr('disabled',true).css('cursor','not-allowed');
                   //设置间隔事件
                   var resend = setInterval(function(){
                       count--;
                       if (count > 0){
                           btn.val(count+"秒后可重新获取");
                           $.cookie("captcha", count, {path: '/', expires: (1/86400)*count});
                       }else {
                           clearInterval(resend);
                           btn.val("获取验证码").removeAttr('disabled style');
                       }
                   }, 1000);
                   btn.attr('disabled',true).css('cursor','not-allowed');
               }else{
                   toastr.error(data.message,"提示");
               }
           }
       });
    });
    $("#register").bind("click",function(e){
        //标识：输入是否正确且不为空
        var rightFlag = true;
        var username=$("#username").val();
        var password=$("#password").val();
        var email=$("#email").val();
        var captcha = $("#captcha").val();
        //验证码是否已经发送
        var captchaFlag = false;
        var userProfile = $("#userprofile")[0].files[0];
        //同步ajax，必须同步执行，先验证是否已经发送了验证码。
        $.ajax({
            url:getCaptchaStatusUrl,
            type:'POST',
            data:{userEmail:email},
            async:false,
            success:function(data){
                if(data.successFlag==true)
                    captchaFlag=true;
            }
        });

        if(username=="") {
            isNull($("#username"));
            rightFlag=false;
        }
        if(email=="") {
            isNull($("#email"));
            rightFlag=false;
        } else if(!reg_email.test(email)) {
            isError($("#email"));
            rightFlag=false;
        }
        if(password=="") {
            isNull($("#password"));
            rightFlag=false;
        } else if(!reg_password.test(password)) {
            isError($("#password"));
            rightFlag=false;
        }
        if(captchaFlag==false){
            toastr.warning("请先获取验证码","提示");
            rightFlag = false;
        }else if(captchaFlag==true&&(captcha==""||captcha==null)){
            toastr.warning("请输入验证码","提示");
            rightFlag = false;
        }
        if(rightFlag){
            var userInfo = {};
            userInfo.userName = username;
            userInfo.userEmail = email;
            userInfo.password = password;
            var formData = new FormData();
            formData.append("userInfo",JSON.stringify(userInfo));
            formData.append("captcha",captcha);
            formData.append("userProfile",userProfile);
            $.ajax({
                url:registerUrl,
                type:'POST',
                data:formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function (data) {
                    if(data.successFlag == true) {
                        toastr.success(data.message,"提示");
                        window.location.href = "/frontend/index";
                    }else{
                        toastr.error(data.message,"提示");
                    }
                }
            });
        }
    });

    /**
     * 如果输入的格式不符合要求
     * @param item
     */
    function isError(item){
        item.parent().children("#after").remove();
        item.parent().addClass("has-danger");
        item.after("<small id='after' class='form-text'>"+item.attr('placeholder')+"格式错误</small>");
    }
    /**
     * 如果输入为空
     * @param item
     */
    function isNull(item){
        item.parent().children("#after").remove();
        item.parent().addClass("has-danger");
        item.after("<small id='after'  class='form-text'>"+item.attr('placeholder')+"不允许为空</small>");
    }
});