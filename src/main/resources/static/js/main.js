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