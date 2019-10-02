$(function(){
    var registerUrl = "/usercontroller/registeruser";
    var username=$("#username").val();
    var password=$("#password").val();
    var email=$("#email").val();
    var captcha = $("#captcha").val();
    var userProfile = $("#userprofile")[0].files[0];
    isNull(username);
    function isNull(item) {
        item.append("<small class='form-text'>We'll never share your email with anyone else.</small>");
    }
});