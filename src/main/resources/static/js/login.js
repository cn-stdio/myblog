var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

/* 手机 */
function phoneCheck(formText) {
        if (formText == "") {
            $("#form-phone").removeClass();
            $("#form-phone").addClass("am-form-group");
            $("#form-phone").addClass("am-form-warning");

            $("#form-text-error-phone").html("哼！竟然不给我联系方式！");
            $("#form-text-error-phone").css("margin-left", "165px");
            $("#form-text-error-phone").css("display", "block");

            $("#icon-phone-success").css("display", "none");
        } else if (!formText.match(/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/)) {
            $("#form-phone").removeClass();
            $("#form-phone").addClass("am-form-group");
            $("#form-phone").addClass("am-form-warning");

            $("#form-text-error-phone").html("这是什么！不要乱写鸭！");
            $("#form-text-error-phone").css("margin-left", "180px");
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
}

$("#doc-ipt-3").blur(function () {
    var formText = $("#doc-ipt-3").val();
    phoneCheck(formText);
});

/* 密码 */
function passwordCheck(formText) {
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

            $("#form-text-error-password").html("介么短的密码是肿么被放进来的！");
            $("#form-text-error-password").css("margin-left", "113px");
            $("#form-text-error-password").css("display", "block");

            $("#icon-password-success").css("display", "none");
        } else if (formText.length > 16) {
            $("#form-password").removeClass();
            $("#form-password").addClass("am-form-group");
            $("#form-password").addClass("am-form-warning");

            $("#form-text-error-password").html("您确定您的密码有辣么辣么长吗！");
            $("#form-text-error-password").css("margin-left", "113px");
            $("#form-text-error-password").css("display", "block");

            $("#icon-password-success").css("display", "none");
        } else if (formText.match(/\s/g,"")) {
            $("#form-password").removeClass();
            $("#form-password").addClass("am-form-group");
            $("#form-password").addClass("am-form-warning");

            $("#form-text-error-password").html("密码中肿么可能有空格！");
            $("#form-text-error-password").css("margin-left", "180px");
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
}

$("#doc-ipt-pwd-2").blur(function() {
    var formText = $("#doc-ipt-pwd-2").val();
    passwordCheck(formText);
});

function toVaild() {
    var formText = $("#doc-ipt-3").val();
    phoneCheck(formText);

    var formText = $("#doc-ipt-pwd-2").val();
    passwordCheck(formText);

    if($("#form-phone").hasClass("am-form-success") &&
        $("#form-password").hasClass("am-form-success")) {
        return true;
    } else {
        return false;
    }
}

$("#doc-ipt-3").focus(function () {
    $(".error-alert").css("display", "none");
});
$("#doc-ipt-pwd-2").focus(function () {
    $(".error-alert").css("display", "none");
});
