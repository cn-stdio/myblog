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

/* 编辑点击跳转 */
function updateArticle(aId) {
    window.location.href = "/updateArticle/" + aId;
}

/* 获得反馈消息量 */
$.ajax({
    type:"post",
    url:"/getUserFeedbackNum",
    dataType:"json",
    async:false,
    success:function(data){
        if(data['msg']=="noLogin") {
            window.location.href = "/login";
        } else {
            if(data['feedbackNum']==0) {
                $("#admin-menu-feedback-count").css("display", "none");
            } else {
                $("#admin-menu-feedback-count").html(data['feedbackNum']);
                $("#admin-menu-feedback-count").css("display", "block");
            }
        }
    },
    error:function(){
        console.log("获取回复信息失败！");
    }
});


/* 获得文章列表 */
function getArticle(pageNum) {
    $.ajax(
        {
            type:"post",
            url:"/admin/getAllArticle",
            dataType:"json",
            async:false,
            data: {
              pageNum: pageNum,
              rows: '6'
            },
            success:function(data){
                if(data['msg']=="noLogin") {
                    window.location.href = "/login";
                } else if(data['msg']=="noArticle") {
                    var adminTitle = '<div class="admin-title">文章鸭</div>\n' +
                        '            <img id="user-reply-img" src="../static/img/replyImg.gif"/>\n' +
                        '            <hr/>';
                    $(".admin-title-div").html(adminTitle);

                    $(".admin-list").html("");
                    $("#user-reply-none").css("display", "block");
                    $(".tpl-right").css("height", 250);
                    $("#user-paper-white").css("margin-top", 0);
                } else {
                    $("#user-reply-none").css("display", "none");
                    $(".tpl-right").css("height", 945);
                    $("#user-paper-white").css("margin-top", 998);

                    var adminTitle = '<div class="admin-title">文章鸭</div>\n' +
                        '            <img id="user-reply-img" src="../static/img/replyImg.gif"/>\n' +
                        '            <hr/>';
                    $(".admin-title-div").html(adminTitle);

                    var articleHtml = "";
                    $.each(data['data'], function (index, obj) {
                        articleHtml +=
                            '            <div>\n' +
                            '                <span>⭐&nbsp;&nbsp;</span>\n' +
                            '                <span><a class="admin-article-title" href="/article/'+ obj['articleId'] +'">'+ obj['title'] +'</a></span>\n' +
                            '                <div class="admin-article-content">'+ obj['summary'] +'</div>\n' +
                            '                <div>\n' +
                            '                    <span class="admin-article-bottom">'+ obj['time'] +'</span>\n' +
                            '                    <span class="admin-article-split">|</span>\n' +
                            '                    <span class="admin-article-bottom">阅读数 </span>\n' +
                            '                    <span class="admin-article-bottom admin-article-count">'+ obj['read'] +'</span>\n' +
                            '                    <span class="admin-article-split">|</span>\n' +
                            '                    <span class="admin-article-bottom">评论数 </span>\n' +
                            '                    <span class="admin-article-bottom admin-article-count">'+ obj['comment'] +'</span>\n' +
                            '                    <span><button type="button" class="admin-article-button" onclick="updateArticle('+ obj['articleId'] +')">编辑</button></span>\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <hr style="margin-left: -20px;width: 95%;"/>';
                    });

                    $(".admin-list").html(articleHtml);

                    var articlePage = Math.ceil(data['articleNum'] / 6);
                    if(articlePage == 1) {
                        $(".user-prev").css("display", "none");
                        $(".user-next").css("display", "none");

                        if(data['articleNum'] == 1) {
                            $("#user-paper-white").css("margin-top", 0);
                        } else if(data['articleNum'] == 4) {
                            $("#user-paper-white").css("margin-top", 700);
                        } else if(data['articleNum'] == 5) {
                            $("#user-paper-white").css("margin-top", 804);
                        } else if(data['articleNum'] == 6) {
                            $("#user-paper-white").css("margin-top", 908);
                        } else {
                            $("#user-paper-white").css("margin-top", 0);
                        }

                        $(".tpl-right").css("height", "auto");

                    } else {
                        var pageTurn = "";

                        $(".tpl-right").css("height", 1090);

                        if(pageNum == 1) {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: none;">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: block;" onclick="getArticle('+ (pageNum+1) +')">下一页</a>\n';
                        } else if(pageNum == articlePage) {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: block;" onclick="getArticle('+ (pageNum-1) +')">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: none;">下一页</a>\n';

                            var articleLastNum = data['articleNum'] % 6;
                            if(articleLastNum == 1) {
                                $("#user-paper-white").css("margin-top", 0);
                            } else if(articleLastNum == 4) {
                                $("#user-paper-white").css("margin-top", 700);
                            } else if(articleLastNum == 5) {
                                $("#user-paper-white").css("margin-top", 804);
                            } else if(articleLastNum == 6) {
                                $("#user-paper-white").css("margin-top", 908);
                            } else {
                                $("#user-paper-white").css("margin-top", 0);
                            }

                            $(".tpl-right").css("height", "auto");
                            var tplRightHeight = $(".tpl-right").css("height");
                            $(".tpl-right").css("height", parseInt(tplRightHeight.split("p")[0])+40);

                        } else {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: block;" onclick="getArticle('+ (pageNum-1) +')">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: block;" onclick="getArticle('+ (pageNum+1) +')">下一页</a>\n';
                        }

                        $("#admin-turn").html(pageTurn);
                    }
                }
            },
            error:function(){
                console.log("获取文章列表失败");
            }
        });
}
getArticle(1);

/* 文章管理点击 */
$("#admin-menu-article").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");

    var menuArticleHtml = '<div class="admin-title-div"></div>\n' +
        '        <div class="admin-list"></div>\n' +
        '        <div id="user-reply-none">目前您没有写任何文章鸭~</div>\n' +
        '        <div id="admin-turn"></div>';

    $(".tpl-right").html(menuArticleHtml);

    getArticle(1);
});

/* 获得反馈列表 */
function getFeedback(pageNum) {
    $.ajax(
        {
            type:"post",
            url:"/admin/getFeedback",
            dataType:"json",
            async:false,
            data: {
                pageNum: pageNum,
                rows: '6'
            },
            success:function(data){
                if(data['msg']=="noLogin") {
                    window.location.href = "/login";
                } else if(data['msg']=="noFeedback") {
                    var adminTitle = '<div class="admin-title">反馈鸭</div>\n' +
                        '            <img id="user-reply-img" src="../static/img/replyImg.gif"/>\n' +
                        '            <hr/>';
                    $(".admin-title-div").html(adminTitle);

                    $(".admin-list").html("");
                    $("#user-reply-none").css("display", "block");
                    $(".tpl-right").css("height", 250);
                    $("#user-paper-white").css("margin-top", 0);
                } else {
                    $("#user-reply-none").css("display", "none");
                    $(".tpl-right").css("height", "auto");

                    var adminTitle = '<div class="admin-title">反馈鸭</div>\n' +
                        '            <img id="user-reply-img" src="../static/img/replyImg.gif"/>\n' +
                        '            <hr/>';
                    $(".admin-title-div").html(adminTitle);

                    var feedbackHtml = "";
                    $.each(data['data'], function (index, obj) {
                        feedbackHtml += '<div>\n';

                        if(obj['state']==1) {
                            feedbackHtml += '<span style="font-weight: bold; color: #999;">已读&nbsp;&nbsp;</span>';
                        } else {
                            feedbackHtml += '<span>⭐&nbsp;&nbsp;</span>';
                        }

                        feedbackHtml +=
                            '                <span><a class="admin-article-title admin-contact-a" style="cursor: auto;">'+ obj['contact'] +'</a></span>\n' +
                            '                <div class="admin-article-content">'+ obj['content'] +'</div>\n' +
                            '            </div>\n' +
                            '            <hr style="margin-left: -20px;width: 95%;"/>';
                    });

                    $(".admin-list").html(feedbackHtml);

                    var feedbackPage = Math.ceil(data['feedbackNum'] / 6);
                    if(feedbackPage == 1) {
                        $(".user-prev").css("display", "none");
                        $(".user-next").css("display", "none");
                    } else {
                        var pageTurn = "";

                        if(pageNum == 1) {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: none;">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: block;" onclick="getFeedback('+ (pageNum+1) +')">下一页</a>\n';
                        } else if(pageNum == feedbackPage) {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: block;" onclick="getFeedback('+ (pageNum-1) +')">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: none;">下一页</a>\n';
                        } else {
                            pageTurn +=
                                '                <a href="#" class="user-prev" style="display: block;" onclick="getFeedback('+ (pageNum-1) +')">上一页</a>\n' +
                                '                <a href="#" class="user-next" style="display: block;" onclick="getFeedback('+ (pageNum+1) +')">下一页</a>\n';
                        }

                        $("#admin-turn").html(pageTurn);
                    }

                    var tplHeight = $(".tpl-right").css("height");
                    tplHeight = parseInt(tplHeight.split("p")[0]) + 50;
                    $(".tpl-right").css("height", tplHeight);
                    $("#user-paper-white").css("margin-top", tplHeight + 50);
                }
            },
            error:function(){
                console.log("获取反馈列表失败");
            }
        });
}

/* 反馈回复点击 */
$("#admin-menu-reply").click(function () {
    $(".nav-link").removeClass("active");
    $(this).addClass("active");
    $("#admin-menu-feedback-count").css("display", "none");

    var menuFeedbackHtml = '<div class="admin-title-div"></div>\n' +
        '        <div class="admin-list"></div>\n' +
        '        <div id="user-reply-none">目前您没有收到任何反馈鸭~</div>\n' +
        '        <div id="admin-turn"></div>';

    $(".tpl-right").html(menuFeedbackHtml);

    getFeedback(1);
});


