var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

/* 昵称 */
$("#doc-ipt-name-1").blur(function() {
    var formText = $("#doc-ipt-name-1").val();

    if (formText=="") {
        $("#form-name").removeClass();
        $("#form-name").addClass("am-form-group");
        $("#form-name").addClass("am-form-warning");

        $("#form-text-error-name").html("请告诉我你的名字叭");
        $("#form-text-error-name").css("margin-left", "300px");
        $("#form-text-error-name").css("display", "block");

        $("#icon-name-success").css("display", "none");
    } else if (formText.length >= 15) {
        $("#form-name").removeClass();
        $("#form-name").addClass("am-form-group");
        $("#form-name").addClass("am-form-warning");

        $("#form-text-error-name").html("您的名字好长好长鸭，请控制在15个字符内！");
        $("#form-text-error-name").css("margin-left", "110px");
        $("#form-text-error-name").css("display", "block");

        $("#icon-name-success").css("display", "none");
    } else if (formText.match(/\s/g,"")) {
        $("#form-name").removeClass();
        $("#form-name").addClass("am-form-group");
        $("#form-name").addClass("am-form-warning");

        $("#form-text-error-name").html("您的名字中不能含有空格的哟~");
        $("#form-text-error-name").css("margin-left", "240px");
        $("#form-text-error-name").css("display", "block");

        $("#icon-name-success").css("display", "none");
    } else {
        $("#form-name").removeClass();
        $("#form-name").addClass("am-form-group");
        $("#form-name").addClass("am-form-success");

        $("#form-text-error-name").html("");
        $("#form-text-error-name").css("display", "none");

        $("#icon-name-success").css("display", "block");
    }
});

/* 密码 */
$("#doc-ipt-pwd-1").blur(function() {
    var formText = $("#doc-ipt-pwd-1").val();

    if (formText=="") {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("不能不能没有密码鸭！");
        $("#form-text-error-password").css("margin-left", "284px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else if (formText.length < 6) {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("您的密码好短好短鸭，请大于6个字符！");
        $("#form-text-error-password").css("margin-left", "140px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else if (formText.length > 16) {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("您的密码好长好长鸭，请控制在16个字符内！");
        $("#form-text-error-password").css("margin-left", "110px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else if (formText.match(/\s/g,"")) {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("密码中有空格哪行鸭！");
        $("#form-text-error-password").css("margin-left", "284px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-success");

        $("#form-text-error-password").html("");
        $("#form-text-error-password").css("display", "none");

        $("#icon-password-success").css("display", "block");
    }
});

/* 手机 */
$("#doc-ipt-phone-1").blur(function() {
    var formText = $("#doc-ipt-phone-1").val();

    if (formText=="") {
        $("#form-phone").removeClass();
        $("#form-phone").addClass("am-form-group");
        $("#form-phone").addClass("am-form-warning");

        $("#form-text-error-phone").html("哼！竟然不给我联系方式！");
        $("#form-text-error-phone").css("margin-left", "240px");
        $("#form-text-error-phone").css("display", "block");

        $("#icon-phone-success").css("display", "none");
    }else if (!formText.match(/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/)) {
        $("#form-phone").removeClass();
        $("#form-phone").addClass("am-form-group");
        $("#form-phone").addClass("am-form-warning");

        $("#form-text-error-phone").html("这是什么！KFC外卖的电话吗！");
        $("#form-text-error-phone").css("margin-left", "200px");
        $("#form-text-error-phone").css("display", "block");

        $("#icon-phone-success").css("display", "none");
    } else {
        $("#form-phone").removeClass();
        $("#form-phone").addClass("am-form-group");
        $("#form-phone").addClass("am-form-success");

        $("#form-text-error-phone").html("");
        $("#form-text-error-phone").css("display", "none");

        $("#icon-phone-success").css("display", "block");
    }
});

/* 验证码 */
var countdown=60;
function settime(val) {
    if (countdown == 0) {
        val.removeAttribute("disabled");
        $("#msg_btn").html("获取验证码");
        countdown = 60;
        return false;
    } else {
        val.setAttribute("disabled", true);
        $("#msg_btn").html("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function() {
        settime(val);
    },1000);
}

/* 性别 */
var $sex = $('[name="gender"]');
$sex.on('change', function() {
    $("#form-sex-judge").css("display", "none");
});

/* 注册点击 */
var sex;
$("#registerFormBtn").click(function() {

});
