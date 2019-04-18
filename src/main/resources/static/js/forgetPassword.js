var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

/* 手机 */
function phoneCheck(formText) {
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

        $("#form-text-error-phone").html("这是什么！不要乱写鸭！");
        $("#form-text-error-phone").css("margin-left", "260px");
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
$("#doc-ipt-phone-1").blur(function() {
    var formText = $("#doc-ipt-phone-1").val();
    phoneCheck(formText);
});

/* 验证码输入检测 */
function captchaCheck(formText) {
    if (formText=="") {
        $("#form-captcha").removeClass();
        $("#form-captcha").addClass("am-form-group");
        $("#form-captcha").addClass("am-form-warning");

        $("#form-text-error-captcha").html("验证码都不写！空手套白狼吗！");
        $("#form-text-error-captcha").css("margin-left", "-70px");
        $("#form-text-error-captcha").css("margin-top", "10px");
        $("#form-text-error-captcha").css("display", "block");

        $("#icon-captcha-success").css("display", "none");
    } else {
        $("#form-captcha").removeClass();
        $("#form-captcha").addClass("am-form-group");
        $("#form-captcha").addClass("am-form-success");

        $("#form-text-error-captcha").html("");
        $("#form-text-error-captcha").css("display", "none");

        $("#icon-captcha-success").css("display", "block");
    }
}
$("#doc-ipt-captcha-1").blur(function () {
    var formText = $("#doc-ipt-captcha-1").val();
    captchaCheck(formText);
});

/* 验证码 */
var countdown=60;
function settime(val) {
    if (countdown == 0) {
        val.removeAttribute("disabled");
        $("#msg-btn").html("获取验证码");
        countdown = 60;
        return false;
    } else {
        val.setAttribute("disabled", true);
        $("#msg-btn").html("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function() {
        settime(val);
    },1000);
}

$("#msg-btn").click(function () {
    if($("#form-phone").hasClass("am-form-success")) {
        var flagPhone = 0;

        var phone = $("input[name=phone]").val();
        console.log(phone);
        $.ajax(
            {
                type:"post",
                url:"/phoneExistCheck",
                dataType:"json",
                async:false,
                data:{
                    phone: phone
                },
                success:function(data){
                    if(data['msg']=="success") {
                        flagPhone = 1;
                    } else {
                        $("#form-phone").removeClass();
                        $("#form-phone").addClass("am-form-group");
                        $("#form-phone").addClass("am-form-warning");

                        $("#form-text-error-phone").html(data['msg']);
                        $("#form-text-error-phone").css("margin-left", "250px");
                        $("#form-text-error-phone").css("display", "block");

                        $("#icon-phone-success").css("display", "none");
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });

        if(flagPhone == 1) {
            settime(this);
            $.ajax(
                {
                    type:"post",
                    url:"/sendIdentifyCode",
                    dataType:"json",
                    data:{
                        phone: phone,
                        type: "back"
                    },
                    success:function(data){
                        if(data['msg']=="success") {
                            alert("手机验证码成功发送！(๑•ㅂ•́)و✧");
                        } else {
                            alert("手机验证码发送失败！w(゜Д゜)w ");
                        }
                    },
                    error:function(){
                        alert("手机验证码发送失败！w(゜Д゜)w ");
                    }
                });
        }
    } else {
        alert("请正确填写手机号哟_(xз」∠)_~");
    }
});

/* 密码 */
function passwordCheck(formText) {
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
}
$("#doc-ipt-pwd-1").blur(function() {
    var formText = $("#doc-ipt-pwd-1").val();
    passwordCheck(formText);
});

/* 密码确认检测 */
function passwordConfirmCheck(formText) {
    if(formText == "") {
        $("#form-password-confirm").removeClass();
        $("#form-password-confirm").addClass("am-form-group");
        $("#form-password-confirm").addClass("am-form-warning");

        $("#form-text-error-password-confirm").html("没有密码肿么确认鸭！");
        $("#form-text-error-password-confirm").css("margin-left", "284px");
        $("#form-text-error-password-confirm").css("display", "block");

        $("#icon-password-confirm-success").css("display", "none");
    }else if (formText!=$("#doc-ipt-pwd-1").val()) {
        $("#form-password-confirm").removeClass();
        $("#form-password-confirm").addClass("am-form-group");
        $("#form-password-confirm").addClass("am-form-warning");

        $("#form-text-error-password-confirm").html("与原密码不同嗷！");
        $("#form-text-error-password-confirm").css("margin-left", "320px");
        $("#form-text-error-password-confirm").css("display", "block");

        $("#icon-password-confirm-success").css("display", "none");
    } else {
        $("#form-password-confirm").removeClass();
        $("#form-password-confirm").addClass("am-form-group");
        $("#form-password-confirm").addClass("am-form-success");

        $("#form-text-error-password-confirm").html("");
        $("#form-text-error-password-confirm").css("display", "none");

        $("#icon-password-confirm-success").css("display", "block");
    }
}
$("#doc-ipt-pwd-confirm-1").blur(function() {
    var formText = $("#doc-ipt-pwd-confirm-1").val();
    passwordConfirmCheck(formText);
});

/* 注册点击检验 */
function toVaild() {
    var formText = $("#doc-ipt-phone-1").val();
    phoneCheck(formText);

    formText = $("#doc-ipt-captcha-1").val();
    captchaCheck(formText);

    formText = $("#doc-ipt-pwd-1").val();
    passwordCheck(formText);

    formText = $("#doc-ipt-pwd-confirm-1").val();
    passwordConfirmCheck(formText);

    var flagP = 0;
    var flagC = 0;

    if($("#form-phone").hasClass("am-form-success")) {
        $.ajax(
            {
                type:"post",
                url:"/phoneExistCheck",
                dataType:"json",
                async: false,
                data:{
                    phone: $("#doc-ipt-phone-1").val()
                },
                success:function(data){
                    if(data['msg']=="success") {
                        flagP = 1;
                    } else {
                        $("#form-phone").removeClass();
                        $("#form-phone").addClass("am-form-group");
                        $("#form-phone").addClass("am-form-warning");

                        $("#form-text-error-phone").html(data['msg']);
                        $("#form-text-error-phone").css("margin-left", "250px");
                        $("#form-text-error-phone").css("display", "block");

                        $("#icon-phone-success").css("display", "none");
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });
    }

    if($("#form-phone").hasClass("am-form-success") && $("#doc-ipt-captcha-1").val()!="") {
        $.ajax(
            {
                type:"post",
                url:"/captchaCheck",
                dataType:"json",
                async: false,
                data:{
                    phone: $("#doc-ipt-phone-1").val(),
                    captcha: $("#doc-ipt-captcha-1").val()
                },
                success:function(data){
                    if(data['msg']=="success") {
                        flagC = 1;
                    } else {
                        $("#form-captcha").removeClass();
                        $("#form-captcha").addClass("am-form-group");
                        $("#form-captcha").addClass("am-form-warning");

                        $("#form-text-error-captcha").html(data['msg']);
                        $("#form-text-error-captcha").css("margin-left", "-70px");
                        $("#form-text-error-captcha").css("margin-top", "10px");
                        $("#form-text-error-captcha").css("display", "block");

                        $("#icon-captcha-success").css("display", "none");
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });

    }

    if($("#form-password").hasClass("am-form-success") &&
        $("#form-password-confirm").hasClass("am-form-success") &&
        $("#form-phone").hasClass("am-form-success") &&
        $("#form-captcha").hasClass("am-form-success") && flagP == 1 && flagC == 1) {
        return true;
    } else {
        return false;
    }
}