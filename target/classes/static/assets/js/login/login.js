$(function(){
    var loginRequestUrl = "/usercontroller/login";
    var rightFlag = true;
    $("#login").bind("click",function () {
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
                        alert(data.message);
                        window.location.href="/frontend/index";
                    }else{
                        alert(data.message);
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