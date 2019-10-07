/**
 * 获取当前用户信息
 */
function getCurrentUserInfo(){
    var getUserInfoRequestUrl = "/usercontroller/getuserinfo";

    $.ajax({
        url:getUserInfoRequestUrl,
        type:'GET',
        success:function(data){
            if(data.successFlag==true){
                console.log(data.message);
                return data.userInfo;
            }else{
                console.log(data.message);
            }
        }
    });

}

/**
 * 点击切换当前验证码
 * @param img
 */
function changeCaptcha(img){
    img.src="../captcha/kaptcha.jpg?"+Math.floor(Math.random()*100);
}
