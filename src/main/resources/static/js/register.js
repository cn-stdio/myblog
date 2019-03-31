var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

/* 昵称 */
function nameCheck(formText) {
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
}
$("#doc-ipt-name-1").blur(function() {
    var formText = $("#doc-ipt-name-1").val();
    nameCheck(formText);
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
function testCheck(formText) {
    if (formText=="") {
        $("#form-test").removeClass();
        $("#form-test").addClass("am-form-group");
        $("#form-test").addClass("am-form-warning");

        $("#form-text-error-test").html("验证码都不写！空手套白狼吗！");
        $("#form-text-error-test").css("margin-left", "-70px");
        $("#form-text-error-test").css("margin-top", "10px");
        $("#form-text-error-test").css("display", "block");

        $("#icon-test-success").css("display", "none");
    } else {
        $("#form-test").removeClass();
        $("#form-test").addClass("am-form-group");
        $("#form-test").addClass("am-form-success");

        $("#form-text-error-test").html("");
        $("#form-text-error-test").css("display", "none");

        $("#icon-test-success").css("display", "block");
    }
}
$("#doc-ipt-test-1").blur(function () {
   var formText = $("#doc-ipt-test-1").val();
   testCheck(formText);
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

$("#msg_btn").click(function () {
    if($("#form-phone").hasClass("am-form-success")) {
        var flagPhone = 0;

        var phone = $("input[name=phone]").val();
        console.log(phone);
        $.ajax(
            {
                type:"post",
                url:"/phoneCheck",
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
                        $("#form-text-error-phone").css("margin-left", "170px");
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
                        phone: phone
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

/* 性别 */
var $sex = $('[name="gender"]');
$sex.on('change', function() {
    $("#form-sex-judge").css("display", "none");
});

/* 注册点击检验 */
function toVaild() {
    var formText = $("#doc-ipt-name-1").val();
    nameCheck(formText);

    formText = $("#doc-ipt-pwd-1").val();
    passwordCheck(formText);

    formText = $("#doc-ipt-phone-1").val();
    phoneCheck(formText);

    formText = $("#doc-ipt-test-1").val();
    testCheck(formText);

    var flag = 0;
    var flagP = 0;
    var flagC = 0;
    $.ajax(
        {
            type:"post",
            url:"/nameCheck",
            dataType:"json",
            async: false,
            data:{
                name: $("#doc-ipt-name-1").val()
            },
            success:function(data){
                if(data['msg']=="success") {
                    flag = 1;
                } else {
                    $("#form-name").removeClass();
                    $("#form-name").addClass("am-form-group");
                    $("#form-name").addClass("am-form-warning");

                    $("#form-text-error-name").html(data['msg']);
                    $("#form-text-error-name").css("margin-left", "260px");
                    $("#form-text-error-name").css("display", "block");

                    $("#icon-name-success").css("display", "none");
                }
            },
            error:function(){
                console.log("请求失败");
            }
        });
    $.ajax(
        {
            type:"post",
            url:"/phoneCheck",
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
                    $("#form-text-error-phone").css("margin-left", "170px");
                    $("#form-text-error-phone").css("display", "block");

                    $("#icon-phone-success").css("display", "none");
                }
            },
            error:function(){
                console.log("请求失败");
            }
        });

    if($("#form-phone").hasClass("am-form-success") && $("#doc-ipt-test-1").val()!="") {
        $.ajax(
            {
                type:"post",
                url:"/captchaCheck",
                dataType:"json",
                async: false,
                data:{
                    phone: $("#doc-ipt-phone-1").val(),
                    captcha: $("#doc-ipt-test-1").val()
                },
                success:function(data){
                    if(data['msg']=="success") {
                        flagC = 1;
                    } else {
                        $("#form-test").removeClass();
                        $("#form-test").addClass("am-form-group");
                        $("#form-test").addClass("am-form-warning");

                        $("#form-text-error-test").html(data['msg']);
                        $("#form-text-error-test").css("margin-left", "-70px");
                        $("#form-text-error-test").css("margin-top", "10px");
                        $("#form-text-error-test").css("display", "block");

                        $("#icon-test-success").css("display", "none");
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });

    }

    if($("#form-name").hasClass("am-form-success") &&
       $("#form-password").hasClass("am-form-success") &&
       $("#form-phone").hasClass("am-form-success") &&
       $("#form-test").hasClass("am-form-success") && flag == 1 && flagP == 1 && flagC == 1) {
        return true;
    } else {
        return false;
    }
}

$("#registerUpdateFormBtn").click(function() {
   window.location.href = "/login";
});

