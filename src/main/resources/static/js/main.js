/* 导航下滚消失上滚出现 */
(function() {
    new Headroom(document.querySelector("header.am-topbar.am-topbar-inverse.am-topbar-fixed-top"), {
        "tolerance": 5,
        "offset": 600,
        "classes": {
            "initial": "animated",
            "pinned": "slideDown",
            "unpinned": "slideUp"
        }
    }).init();
}());

/* 头像当鼠标经过时转动 */
var shine = new Shine(document.getElementById('headline'));

function handleMouseMove(event) {
    shine.light.position.x = event.clientX;
    shine.light.position.y = event.clientY;
    shine.draw();
}

window.addEventListener('mousemove', handleMouseMove, false);

/* 名片随页面滚动 */
window.onload= function() {
    var oDiv = document.getElementById("main-right-div"),
        H = 0,
        Y = oDiv;
    while (Y) {
        H += Y.offsetTop;
        Y = Y.offsetParent;
    }
    window.onscroll = function()
    {
        var s = document.body.scrollTop || document.documentElement.scrollTop;
        if(s>H-50) {
            oDiv.style = "position:fixed;top:50px;";
        } else {
            oDiv.style = "";
        }
    }

    /* 尾部footer定位 */
    var cw = document.body.scrollHeight;
    console.log(cw);
    $(".footer").css("top", cw+50);

};

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
}

/* 悄悄话点击 */
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
