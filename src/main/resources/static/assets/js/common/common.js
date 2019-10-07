/**
 * 获取当前用户信息
 */
function getCurrentUserInfo(){
    var userInfo;
    var getUserInfoRequestUrl = "/usercontroller/getuserinfo";
    $.ajax({
        url:getUserInfoRequestUrl,
        type:'GET',
        async:false,
        success:function(data){
            /**
             * 犯了一个低级错误
             * 在if语句块中return，返回值并不能作为getCurrentUserInfo方法的返回值
             * 只能作为ajax的success回调函数的返回值，所以存在一个index.js获取不到返回值的问题
             * 需要把userInfo放到Ajax外部进行返回。
             */
            if(data.successFlag==true){
                userInfo =  data.userInfo;
            }else{
                console.info(data.message)
            }
        }
    });
    return userInfo;
}

/**
 * 点击切换当前验证码
 * @param img
 */
function changeCaptcha(img){
    img.src="../captcha/kaptcha.jpg?"+Math.floor(Math.random()*100);
}
