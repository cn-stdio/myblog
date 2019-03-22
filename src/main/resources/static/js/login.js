var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

/* 手机 */
$("#doc-ipt-3").blur(function() {

    var formText = $("#doc-ipt-3").val();

    if (formText=="") {
        $("#form-phone").removeClass();
        $("#form-phone").addClass("am-form-group");
        $("#form-phone").addClass("am-form-warning");

        $("#form-text-error-phone").html("哼！竟然不给我联系方式！");
        $("#form-text-error-phone").css("margin-left", "165px");
        $("#form-text-error-phone").css("display", "block");

        $("#icon-phone-success").css("display", "none");
    }else if (!formText.match(/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/)) {
        $("#form-phone").removeClass();
        $("#form-phone").addClass("am-form-group");
        $("#form-phone").addClass("am-form-warning");

        $("#form-text-error-phone").html("这是什么！KFC外卖的电话吗！");
        $("#form-text-error-phone").css("margin-left", "125px");
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

/* 密码 */
$("#doc-ipt-pwd-2").blur(function() {
    var formText = $("#doc-ipt-pwd-2").val();

    if (formText=="") {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("不能不能没有密码鸭！");
        $("#form-text-error-password").css("margin-left", "200px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else if (formText.length < 6) {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("您的密码好短好短鸭，请大于6个字符！");
        $("#form-text-error-password").css("margin-left", "75px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else if (formText.length > 16) {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("您的密码好长好长鸭，请控制在16个字符内！");
        $("#form-text-error-password").css("margin-left", "25px");
        $("#form-text-error-password").css("display", "block");

        $("#icon-password-success").css("display", "none");
    } else if (formText.match(/\s/g,"")) {
        $("#form-password").removeClass();
        $("#form-password").addClass("am-form-group");
        $("#form-password").addClass("am-form-warning");

        $("#form-text-error-password").html("密码中有空格哪行鸭！");
        $("#form-text-error-password").css("margin-left", "200px");
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