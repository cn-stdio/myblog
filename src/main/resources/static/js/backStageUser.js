/* 鼠标点击出现颜文字 */
var a_idx = 0;
$(document).ready(function ($) {
    $("body").click(function (e) {
        var a = new Array("(๑•̀ㅂ•́)و✧", "ヽ(✿ﾟ▽ﾟ)ノ", "w(ﾟДﾟ)w", "Σ(°△°|||)︴", "(u‿ฺu✿ฺ)", "φ(≧ω≦*)♪","o(*≧▽≦)ツ┏━┓","o(￣ヘ￣o＃)","~(﹁ ﹁)~~","┑(￣Д ￣)┍","(。﹏。*)");
        var $i = $("<span></span>").text(a[a_idx]);
        a_idx = (a_idx + 1) % a.length;
        var x = e.pageX,
            y = e.pageY;
        $i.css({
            "z-index": 99,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "color": "rgb(" + ~~(255 * Math.random()) + "," + ~~(
                255 * Math.random()) + "," + ~~(255 * Math.random()) +
                ")"
        });
        $("body").append($i);
        $i.animate({
                "top": y - 180,
                "opacity": 0
            },
            1500,
            function () {
                $i.remove();
            });
    });
});

var jj = {
    "code" : "200",
    "acgurl" : "https://acg.toubiec.cn/random",
    "width" : "100",
    "height" : "100"
};

/* 进度条 */
(function(){
    document.onreadystatechange = function(){
        $.AMUI.progress.start();
        console.log(document.readyState);
        if(document.readyState == "Uninitialized"){
            $.AMUI.progress.set(1);
        }
        if(document.readyState == "Interactive"){
            $.AMUI.progress.set(0.5);
        }
        if(document.readyState == "complete"){
            $.AMUI.progress.done();
        }
    }
})();

/* 登录成功后加入名称 */
var username = "";
$.ajax(
    {
        type:"get",
        url:"/getUserName",
        dataType:"json",
        async:false,
        success:function(data){
            if(data['msg']=="success") {
                console.log("已登录");
                username = data['username'];
            }
        },
        error:function(){
            console.log("未登录");
        }
    });
if(username != "") {
    $("#login-user-name").html(username);
    $("#login-user-name-phone").html(username);
}

/* 反馈点击 */
$("#feedback-submit").click(function () {
    var feedback = $("#feedback-text").val();
    var contact = $("#feedback-contact").val();
    if(contact==""){
        contact = "无";
    }
    $.ajax(
        {
            type:"post",
            url:"/insertFeedback",
            dataType:"json",
            data:{
                msg: feedback,
                contact: contact
            },
            success:function(data){
                if(data['msg']=="success") {
                    alert("我已经听到啦，嘻嘻嘻~");
                } else {
                    alert("呜呜呜，悄悄话在中途丢失嘞...");
                }
            },
            error:function(){
                alert("呜呜呜，悄悄话在中途丢失嘞...");
            }
        });
});

/* 写博客点击 */
$("#login-button-circle").click(function () {
    window.location.href = "/publish";
});

/* 获得评论回复量 */
$.ajax({
    type:"post",
    url:"/getUserReplyNum",
    dataType:"json",
    async:false,
    success:function(data){
        if(data['msg']=="noLogin") {
            window.location.href = "/login";
        } else {
            if(data['replyNum']==0) {
                $("#user-menu-reply-count").css("display", "none");
            } else {
                $("#user-menu-reply-count").html(data['replyNum']);
                $("#user-menu-reply-count").css("display", "block");
            }
        }
    },
    error:function(){
        console.log("获取回复信息失败！");
    }
});

/* 获得当前用户个人信息 */
var userPhone;
var userName;
function getUserInformation() {
    $.ajax({
        type:"post",
        url:"/getUserInformation",
        dataType:"json",
        async:false,
        success:function(data){
            if(data['msg']=="noLogin") {
                window.location.href = "/login";
            } else {
                $("#user-head-img").attr("src", data['headImg']);
                userName = data['username'];
                $(".user-name-input").val(data['username']);
                var phoneStr = data['phone'];
                userPhone = phoneStr;
                phoneStr = phoneStr.replace(phoneStr.substring(3, 7), '****');
                $(".user-phone-number").html(phoneStr);
                $(".user-real-name-input").val(data['realName']);
                if(data['sex']=="1") {
                    $("input[type='radio']").eq(2).attr('checked','checked');
                } else {
                    $("input[type='radio']").eq(1).attr('checked','checked');
                }
                $(".user-birthday-input").val(data['birthday']);
                $(".user-email-input").val(data['email']);
                $(".user-introduce-input").val(data['introduce']);
            }
        },
        error:function(){
            console.log("获取个人信息失败！");
        }
    });
}
getUserInformation();

/* 更换头像点击 */
function imgChange(e) {
    console.info(e.target.files[0]);//图片文件
    var dom = $("input[id^='img-upload']")[0];
    console.info(dom.value);    //这个是文件的路径 C:\fakepath\icon (5).png
    console.log(e.target.value);//这个也是文件的路径和上面的dom.value是一样的
    var reader = new FileReader();
    reader.onload = (function (file) {
        return function (e) {
            console.info(this.result); //这个就是base64的数据了
            var sss = $("#user-head-img");
            $("#user-head-img")[0].src = this.result;
            $.ajax({
                type:"post",
                url:"/updateUserImg",
                dataType:"json",
                async: false,
                data: {
                    headImg: this.result
                },
                success:function(data){
                    if(data['msg']=="noLogin") {
                        window.location.href = "/login";
                    } else {
                        $("#notice-box-text").html("更新头像成功！");
                        $(".notice-box").css("color", "limegreen");
                        $(".notice-box").css("display", "block");
                        $(".notice-box").delay(3000).hide(0);
                    }
                },
                error:function(){
                    console.log("更新个人头像失败！");
                }
            });
        };
    })(e.target.files[0]);
    reader.readAsDataURL(e.target.files[0]);
}
$(".tpl-right").on("click", ".user-head-btn", function () {
    $("#img-upload").click();
});

/* 昵称合法检查 */
function nameCheck(formText) {
    if (formText=="") {
        $(".user-name-input").addClass("am-form-warning");

        $("#user-name-error").html("请告诉我你的名字叭");
        $("#user-name-error").css("margin-left", "300px");
        $("#user-name-error").css("display", "block");

    } else if (formText.length > 10) {
        $(".user-name-input").addClass("am-form-warning");

        $("#user-name-error").html("您的名字好长好长鸭，请控制在10个字符内！");
        $("#user-name-error").css("margin-left", "110px");
        $("#user-name-error").css("display", "block");

    } else if (formText.match(/\s/g,"")) {
        $(".user-name-input").addClass("am-form-warning");

        $("#user-name-error").html("您的名字中不能含有空格的哟~");
        $("#user-name-error").css("margin-left", "240px");
        $("#user-name-error").css("display", "block");
    } else {
        $(".user-name-input").removeClass("am-form-warning");

        $("#user-name-error").html("");
        $("#user-name-error").css("display", "none");
    }
}
function emailCheck(formText) {
    if(formText != "") {
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        if(!reg.test(formText)) {
            $(".user-email-input").addClass("am-form-warning");

            $("#user-email-error").html("要填写正确的邮箱鸭~");
            $("#user-email-error").css("margin-left", "300px");
            $("#user-email-error").css("display", "block");
        } else {
            $(".user-email-input").removeClass("am-form-warning");

            $("#user-email-error").html("");
            $("#user-email-error").css("display", "none");
        }
    } else {
        $(".user-email-input").removeClass("am-form-warning");

        $("#user-email-error").html("");
        $("#user-email-error").css("display", "none");
    }
}

$(".tpl-right").on("click", "#user-personal-profile", function () {
    nameCheck($(".user-name-input").val());
    emailCheck($(".user-email-input").val());

    if($(".user-name-input").hasClass("am-form-warning") ||
        $(".user-email-input").hasClass("am-form-warning")) {
        $("#notice-box-text").html("有信息麻油按规则填写鸭~");
        $(".notice-box").css("color", "lightcoral");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(2000).hide(0);
    } else {
        var name = $(".user-name-input").val();
        var nameFlag = 0;
        $.ajax(
            {
                type:"post",
                url:"/user/nameCheck",
                dataType:"json",
                async: false,
                data:{
                    name: name
                },
                success:function(data){
                    if(data['msg']=="noLogin") {
                        window.location.href = "/login";
                    } else if(data['msg']=="none") {
                        nameFlag = 1;

                        $(".user-name-input").removeClass("am-form-warning");

                        $("#user-name-error").html("");
                        $("#user-name-error").css("display", "none");
                    } else {
                        $(".user-name-input").addClass("am-form-warning");

                        $("#user-name-error").html(data['msg']);
                        $("#user-name-error").css("margin-left", "290px");
                        $("#user-name-error").css("display", "block");

                        $("#notice-box-text").html(data['msg']);
                        $(".notice-box").css("color", "lightcoral");
                        $(".notice-box").css("display", "block");
                        $(".notice-box").delay(2000).hide(0);
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });

        if(nameFlag == 1) {
            var realName = $(".user-real-name-input").val();
            var sex = $('input[name="gender"]:checked').val();
            var birthday = $(".user-birthday-input").val();
            var email = $(".user-email-input").val();
            var privateMsg = $(".user-introduce-input").val();

            if(realName == "") {
                realName = "-1";
            }
            if(birthday == "") {
                birthday = "-1";
            }
            if(email == "") {
                email = "-1";
            }
            if(privateMsg == "") {
                privateMsg = "-1";
            }

            $.ajax({
                type:"post",
                url:"/updateUserInformation",
                dataType:"json",
                async:false,
                data: {
                    name: name,
                    realName: realName,
                    sex: sex,
                    birthday: birthday,
                    email: email,
                    privateMsg: privateMsg,
                    headImg: $("#user-head-img").attr("src")
                },
                success:function(data){
                    if(data['msg']=="noLogin") {
                        window.location.href = "/login";
                    } else {
                        if(data['headImgFlag']==1) {
                            $("#user-head-img").attr("src", data['headImg']);
                        }

                        $("#notice-box-text").html("更新个人信息成功！");
                        $(".notice-box").css("color", "limegreen");
                        $(".notice-box").css("display", "block");
                        $(".notice-box").delay(3000).hide(0);

                        userName = name;
                    }
                },
                error:function(){
                    console.log("更新个人信息失败！");
                }
            });
        }
    }
});

/* 个人资料点击 */
$("#user-menu-introduce").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");

    var introduceHtml = '<div class="user-head">\n' +
        '                <div class="user-head-text">头像更换</div>\n' +
        '                <hr style="width: 90%;"/>\n' +
        '                <img id="user-head-img" src="http://seaguller.oss-cn-beijing.aliyuncs.com/headImg/default/boy.jpg">\n' +
        '                <div class="user-head-button">\n' +
        '                    <a class="user-head-btn" >更改头像</a>\n' +
        '                    <input type="file" id="img-upload" onchange="imgChange(event)" accept=".gif,.jpg,.jpeg,.png" style="display: none;"/>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="user-name">\n' +
        '                <div class="user-name-text" style="margin-left: 34px;">昵  称</div>\n' +
        '                <input class="user-name-input" placeholder="要改一个更好听的名字呢~" type="text" value=""/>\n' +
        '                <div class="user-private-error" id="user-name-error"></div>\n' +
        '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
        '            </div>\n' +
        '            <div class="user-phone">\n' +
        '                <div class="user-phone-text" style="margin-left: 34px;">手  机</div>\n' +
        '                <div class="user-phone-number"></div>\n' +
        '                <hr style="width: 90%;"/>\n' +
        '            </div>\n' +
        '            <div class="user-real-name">\n' +
        '                <div class="user-real-name-text" style="margin-left: 34px;">姓  名</div>\n' +
        '                <input class="user-real-name-input" placeholder="要不要向我透露真名捏" type="text"/>\n' +
        '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
        '            </div>\n' +
        '            <div class="user-sex">\n' +
        '                <div class="user-sex-text" style="margin-left: 34px;">性  别</div>\n' +
        '                <div class="radio_input am-form-group am-form-group-sex user-sex-label" style="margin: 0 0 10px auto">\n' +
        '                    <label class="am-radio-inline">\n' +
        '                        <input data-am-ucheck="" name="gender" type="radio" value="male" class="am-ucheck-radio" checked><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span>\n' +
        '                        <span class="fa fa-mars fa-lg"></span>\n' +
        '                    </label>\n' +
        '                    <label class="am-radio-inline" style="margin-left: 40px">\n' +
        '                        <input data-am-ucheck="" name="gender" type="radio" value="female" class="am-ucheck-radio"><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span>\n' +
        '                        <span class="fa fa-venus fa-lg"></span>\n' +
        '                    </label>\n' +
        '                </div>\n' +
        '                <hr style="width: 90%;"/>\n' +
        '            </div>\n' +
        '            <div class="user-birthday">\n' +
        '                <div class="user-birthday-text" style="margin-left: 34px;">生  日</div>\n' +
        '                <input class="user-birthday-input" placeholder="叭填生日就要叫我小哥哥！" type="text" data-am-datepicker="{viewMode: \'years\'}" readonly required/>\n' +
        '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
        '            </div>\n' +
        '            <div class="user-email">\n' +
        '                <div class="user-email-text" style="margin-left: 34px;">邮  箱</div>\n' +
        '                <input class="user-email-input" placeholder="让我们回归写信滴生活叭！" type="text"/>\n' +
        '                <div class="user-private-error" id="user-email-error"></div>\n' +
        '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
        '            </div>\n' +
        '            <div class="user-introduce">\n' +
        '                <div class="user-introduce-text">个人简介</div>\n' +
        '                <textarea class="user-introduce-input" placeholder="你有什么特别之处捏，嘻嘻嘻~"></textarea>\n' +
        '                <hr style="width: 90%;"/>\n' +
        '            </div>\n' +
        '            <div>\n' +
        '                <a id="user-personal-profile">更新</a>\n' +
        '            </div>';
    $(".tpl-right").css("height", "1000px");
    $("#user-paper-white").css("margin-top", 1015);
    $(".tpl-right").html(introduceHtml);
    getUserInformation();
});

/* 密码修改点击 */
$("#user-menu-password").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");

    var passwordHtml =
       '            <div class="user-phone">\n' +
       '                <div class="user-phone-text" style="margin-left: 34px;">手  机</div>\n' +
       '                <div class="user-phone-number">'+ userPhone.replace(userPhone.substring(3, 7), '****') +'</div>\n' +
       '                <hr style="width: 90%;"/>\n' +
       '            </div>\n' +
       '            <div class="user-captcha">\n' +
       '                <div class="user-captcha-text" style="margin-left: 34px;">验证码</div>\n' +
       '                <input class="user-captcha-input" placeholder="请填入验证码" type="text" value=""/>\n' +
       '                <div class="user-captcha-button">\n' +
       '                    <button type="button" class="user-captcha-btn" >获取验证码</button>\n' +
       '                </div>\n' +
       '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
       '            </div>\n' +
       '            <div>\n' +
       '                <a id="user-next-step">下一步</a>\n' +
       '            </div>';

    $(".tpl-right").css("height", "280px");
    $("#user-paper-white").css("margin-top", 0);
    $(".tpl-right").html(passwordHtml);
});

/* 手机号更换点击 */
$(".tpl-right").on('click', '.user-phone-btn', function () {
    $("#notice-box-text").html("大懒猪这个功能还没做，嘻嘻嘻(o´・ω・`)σ)Д`)");
    $(".notice-box").css("color", "mediumslateblue");
    $(".notice-box").css("display", "block");
    $(".notice-box").delay(3000).hide(0);
});

/* 验证码 */
var countdown=60;
function settime(val) {
    if (countdown == 0) {
        val.removeAttribute("disabled");
        $(".user-captcha-btn").html("获取验证码");
        countdown = 60;
        return false;
    } else {
        val.setAttribute("disabled", true);
        $(".user-captcha-btn").html("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function() {
        settime(val);
    },1000);
}
$(".tpl-right").on('click', '.user-captcha-btn', function () {
    var phone = userPhone;

    settime(this);
    $.ajax(
        {
            type:"post",
            url:"/sendIdentifyCode",
            dataType:"json",
            data:{
                phone: phone,
                type: "safety"
            },
            success:function(data){
                if(data['msg']=="success") {
                    $("#notice-box-text").html("手机验证码成功发送！(๑•ㅂ•́)و✧");
                    $(".notice-box").css("color", "limegreen");
                    $(".notice-box").css("display", "block");
                    $(".notice-box").delay(3000).hide(0);
                } else {
                    $("#notice-box-text").html("手机验证码发送失败！w(゜Д゜)w ");
                    $(".notice-box").css("color", "lightcoral");
                    $(".notice-box").css("display", "block");
                    $(".notice-box").delay(3000).hide(0);
                }
            },
            error:function(){
                $("#notice-box-text").html("手机验证码发送失败！w(゜Д゜)w ");
                $(".notice-box").css("color", "lightcoral");
                $(".notice-box").css("display", "block");
                $(".notice-box").delay(3000).hide(0);
            }
        });
});

$(".tpl-right").on('click', "#user-next-step", function () {
    var captcha = $(".user-captcha-input").val();

    if(captcha == "") {
        $("#notice-box-text").html("验证码麻油填写哟~");
        $(".notice-box").css("color", "lightcoral");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(2000).hide(0);
    } else {
        $.ajax(
            {
                type:"post",
                url:"/captchaCheck",
                dataType:"json",
                async: false,
                data:{
                    phone: userPhone,
                    captcha: captcha
                },
                success:function(data){
                    if(data['msg']=="success") {
                        var keyHtml =
                            '            <div class="user-phone">\n' +
                            '                <div class="user-phone-text" style="margin-left: 34px;">手  机</div>\n' +
                            '                <div class="user-phone-number">'+ userPhone.replace(userPhone.substring(3, 7), '****') +'</div>\n' +
                            '                <div class="user-phone-button">\n' +
                            '                    <a class="user-phone-btn" >更换</a>\n' +
                            '                </div>\n' +
                            '                <hr style="width: 90%;"/>\n' +
                            '            </div>\n' +
                            '            <div class="user-new-password">\n' +
                            '                <div class="user-new-password-text" style="margin-left: 34px;">新密码</div>\n' +
                            '                <input type="password" class="user-new-password-input" placeholder="这次要设一个更复杂滴！" type="text" value=""/>\n' +
                            '                <div class="user-new-password-error" id="user-new-password-error"></div>\n' +
                            '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
                            '            </div>\n' +
                            '            <div class="user-new-password-confirm">\n' +
                            '                <div class="user-new-password-confirm-text" style="margin-left: 34px;">确认密码</div>\n' +
                            '                <input type="password" class="user-new-password-confirm-input" placeholder="再次确认哦~" type="text" value=""/>\n' +
                            '                <div class="user-new-password-confirm-error" id="user-new-password-confirm-error"></div>\n' +
                            '                <hr style="width: 90%; margin-top: -1px;"/>\n' +
                            '            </div>\n' +
                            '            <div>\n' +
                            '                <a id="user-confirm">确认修改</a>\n' +
                            '            </div>';

                        $(".tpl-right").html(keyHtml);
                        $(".tpl-right").css("height", "370px");
                        $("#user-paper-white").css("margin-top", 0);
                    } else {
                        $("#notice-box-text").html(data['msg']);
                        $(".notice-box").css("color", "lightcoral");
                        $(".notice-box").css("display", "block");
                        $(".notice-box").delay(2000).hide(0);
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });
    }
});

/* 新密码检测 */
function passwordCheck(formText) {
    if (formText=="") {
        $(".user-new-password-input").addClass("am-form-warning");

        $("#user-new-password-error").html("不能不能没有密码鸭！");
        $("#user-new-password-error").css("margin-left", "284px");
        $("#user-new-password-error").css("display", "block");

    } else if (formText.length < 6) {
        $(".user-new-password-input").addClass("am-form-warning");

        $("#user-new-password-error").html("您的密码好短好短鸭，请大于6个字符！");
        $("#user-new-password-error").css("margin-left", "140px");
        $("#user-new-password-error").css("display", "block");

    } else if (formText.length > 16) {
        $(".user-new-password-input").addClass("am-form-warning");

        $("#user-new-password-error").html("您的密码好长好长鸭，请控制在16个字符内！");
        $("#user-new-password-error").css("margin-left", "110px");
        $("#user-new-password-error").css("display", "block");

    } else if (formText.match(/\s/g,"")) {
        $(".user-new-password-input").addClass("am-form-warning");

        $("#user-new-password-error").html("密码中有空格哪行鸭！");
        $("#user-new-password-error").css("margin-left", "284px");
        $("#user-new-password-error").css("display", "block");

    } else {
        $(".user-new-password-input").removeClass("am-form-warning");

        $("#user-new-password-error").html("");
        $("#user-new-password-error").css("display", "none");
    }
}
/* 密码确认检测 */
function passwordConfirmCheck(formText) {
    if (formText!=$(".user-new-password-input").val()) {
        $(".user-new-password-confirm-input").addClass("am-form-warning");

        $("#user-new-password-confirm-error").html("与原密码不同嗷！");
        $("#user-new-password-confirm-error").css("margin-left", "320px");
        $("#user-new-password-confirm-error").css("display", "block");

    } else {
        $(".user-new-password-confirm-input").removeClass("am-form-warning");

        $("#user-new-password-confirm-error").html("");
        $("#user-new-password-confirm-error").css("display", "none");
    }
}

/* 确认修改点击 */
$(".tpl-right").on('click', '#user-confirm', function () {
    var pas = $(".user-new-password-input").val();
    var pasConfirm = $(".user-new-password-confirm-input").val();

    passwordCheck(pas);
    passwordConfirmCheck(pasConfirm);

    if(!$(".user-new-password-input").hasClass("am-form-warning") &&
       !$(".user-new-password-confirm-input").hasClass("am-form-warning")) {

        $.ajax(
            {
                type:"post",
                url:"/updatePassword",
                dataType:"json",
                async: false,
                data:{
                    password: pas
                },
                success:function(data){
                    if(data['msg']=="noLogin") {
                        window.location.href = "/login";
                    } else {
                        $("#notice-box-text").html("修改成功啦，耶！");
                        $(".notice-box").css("color", "limegreen");
                        $(".notice-box").css("display", "block");
                        $(".notice-box").delay(2000).hide(0);
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });
    } else {
        $("#notice-box-text").html("信息有误哦，快去检查~");
        $(".notice-box").css("color", "lightcoral");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(2000).hide(0);
    }
});

/* 评论回复获取 */
function getUserReplied(pageNum) {
    $.ajax(
        {
            type:"post",
            url:"/getUserReplied",
            dataType:"json",
            async: false,
            data:{
                rows:'6',
                pageNum: pageNum
            },
            success:function(data){
                if(data['msg']=="noLogin") {
                    window.location.href = "/login";
                } else if(data['msg']=="noReply") {
                    $(".user-reply-list").html("");
                    $("#user-reply-none").css("display", "block");
                    $(".tpl-right").css("height", 250);
                    $("#user-paper-white").css("margin-top", 0);
                } else {
                    $("#user-reply-none").css("display", "none");
                    $(".tpl-right").css("height", 820);
                    $("#user-paper-white").css("margin-top", 804);

                    var replyStr = '';
                    $.each(data['data'], function (index, obj) {
                        replyStr += '<div>\n' +
                            '                    <div>\n';
                        if(obj['state']==1) {
                            replyStr += '<span style="font-weight: bold; color: #999;">已读&nbsp;&nbsp;</span>';
                        } else {
                            replyStr += '<span>⭐&nbsp;&nbsp;</span>';
                        }

                        if(obj['articleId']==0) {
                            replyStr += '<span><a class="user-reply-title" href="/messageBoard">'+ obj['title'] +'</a></span>';
                        } else {
                            replyStr += '<span><a class="user-reply-title" href="/article/'+ obj['articleId'] +'">'+ obj['title'] +'</a></span>';
                        }
                        replyStr +=
                            '                        <span class="user-reply-time">'+ obj['time'] +'</span>\n' +
                            '                    </div>\n' +
                            '                    <div class="user-reply-content">\n' +
                            '                        <span style="margin-right: 5px;">'+ obj['respondentName'] +':</span>\n' +
                            '                        <span style="color:black;margin-right: 5px;">@'+ obj['answerName'] +'</span>\n' +
                            '                        <span>'+ obj['content'] +'</span>\n' +
                            '                    </div>\n' +
                            '                </div>' +
                            '<hr style="margin-left: -20px;width: 93%;"/>';
                    });

                    $(".user-reply-list").html(replyStr);

                    var replyPage = Math.ceil(data['replyNum'] / 6);

                    if(replyPage == 1) {
                        $(".user-prev").css("display", "none");
                        $(".user-next").css("display", "none");

                        if(data['replyNum'] == 1) {
                            $(".tpl-right").css("height", 300);
                            $("#user-paper-white").css("margin-top", 0);
                        } else if(data['replyNum'] == 5) {
                            $(".tpl-right").css("height", 300 + (data['replyNum']-1)*104);
                            $("#user-paper-white").css("margin-top", 700);
                        } else if(data['replyNum'] == 6) {
                            $(".tpl-right").css("height", 300 + (data['replyNum']-1)*104);
                            $("#user-paper-white").css("margin-top", 804);
                        } else {
                            $(".tpl-right").css("height", 300 + (data['replyNum']-1)*104);
                            $("#user-paper-white").css("margin-top", 0);
                        }
                    } else {
                        var pageTurn = "";

                        if(pageNum == 1) {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: none;">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: block;" onclick="getUserReplied('+ (pageNum+1) +')">下一页</a>\n';
                        } else if(pageNum == replyPage) {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: block;" onclick="getUserReplied('+ (pageNum-1) +')">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: none;">下一页</a>\n';

                            var replyLastNum = data['replyNum'] % 6;
                            if(replyLastNum == 1) {
                                $(".tpl-right").css("height", 300);
                                $("#user-paper-white").css("margin-top", 0);
                            } else if(replyLastNum == 5) {
                                $(".tpl-right").css("height", 300 + (replyLastNum-1)*104);
                                $("#user-paper-white").css("margin-top", 700);
                            } else if(replyLastNum == 6) {
                                $(".tpl-right").css("height", 300 + (replyLastNum-1)*104);
                                $("#user-paper-white").css("margin-top", 804);
                            } else {
                                $(".tpl-right").css("height", 300 + (replyLastNum-1)*104);
                                $("#user-paper-white").css("margin-top", 0);
                            }
                        } else {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: block;" onclick="getUserReplied('+ (pageNum-1) +')">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: block;" onclick="getUserReplied('+ (pageNum+1) +')">下一页</a>\n';
                        }

                        $("#user-reply-turn").html(pageTurn);
                    }
                }
            },
            error:function(){
                console.log("请求失败");
            }
        });
}

/* 评论点击 */
$("#user-menu-reply").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");
    $("#user-menu-reply-count").css("display", "none");

    var replyInitHtml = '<div>\n' +
        '                <div class="user-reply">回复鸭</div>\n' +
        '                <img id="user-reply-img" src="../static/img/replyImg.gif"/>\n' +
        '                <hr/>\n' +
        '            </div>\n' +
        '            <div class="user-reply-list">\n' +
        '                \n' +
        '            </div>\n' +
        '            <div id="user-reply-none">目前麻油任何人回复您的评论鸭~</div>\n' +
        '<div id="user-reply-turn"></div>';
    $(".tpl-right").html(replyInitHtml);

    getUserReplied(1);
});

/* 悄悄话点击 */
$("#user-menu-talk").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");

    var talkHtml = '<div>\n' +
        '                <span><img src="../static/img/talk/t-1.jpg" class="user-talk-img"/></span>\n' +
        '                <span><img src="../static/img/talk/t-2.jpg" class="user-talk-img"/></span>\n' +
        '                <span><img src="../static/img/talk/t-3.jpg" class="user-talk-img"/></span>\n' +
        '            </div>\n' +
        '            <div id="user-talk-title">❤  我知道你有很多话想偷偷对我说啦，嘻嘻嘻，叭要着急，慢慢来鸭~</div>\n' +
        '            <div id="user-talk-div">\n' +
        '                <textarea maxlength="500" id="user-talk-text" class="am-modal-prompt-input" placeholder="畅所欲言叭，骚年d(`･∀･)b！"></textarea>\n' +
        '                <div><a id="user-talk-btn">冲鸭~</a></div>\n' +
        '            </div>';

    $(".tpl-right").html(talkHtml);
    $(".tpl-right").css("height", 600);
    $("#user-paper-white").css("margin-top", 0);
});
/* 悄悄话发布 */
$(".tpl-right").on('click', '#user-talk-btn', function () {
    if($("#user-talk-text").val()=="") {
        $("#notice-box-text").html("你神马都麻油写鸭！");
        $(".notice-box").css("color", "lightcoral");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(3000).hide(0);
    } else {
        $.ajax(
            {
                type:"post",
                url:"/user/insertFeedback",
                dataType:"json",
                async: false,
                data:{
                    msg: $("#user-talk-text").val()
                },
                success:function(data){
                    if(data['msg']=="noLogin") {
                        window.location.href = "/login";
                    } else {
                        $("#user-talk-text").val("");

                        $("#notice-box-text").html("我已经收到你的悄悄话啦！");
                        $(".notice-box").css("color", "limegreen");
                        $(".notice-box").css("display", "block");
                        $(".notice-box").delay(3000).hide(0);
                    }
                },
                error:function(){
                    console.log("请求失败");
                }
            });
    }
});
