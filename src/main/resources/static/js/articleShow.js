var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);
function timeStampToDate(date){
    var date= new Date(parseInt(date));
    return date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate() + "日";
}

function stepArticle(articleId) {
    window.location.href = "/article/" + articleId;
}

function pageContent() {
    $.ajax(
        {
            type: "post",
            url: "/getArticleContent",
            dataType: "json",
            async: false,
            data:{
                articleId: $("#articleId").val()
            },

            success: function (data) {
                var rUl = $("#art-list");
                var str = "";

                rUl.html('');

                str += '<div class="main-middle" id="main-middle-article" style="top:690px;&quot;">\n' +
                    '            <div class="main-middle-article">\n' +
                    '                <div class="article-badge">\n' +
                    '                    <a href="/type/'+ data['data']['type'] +'" class="badge-a">'+ data['data']['type'] +'</a>\n' +
                    '                </div>\n' +
                    '                <h1 class="article-title">\n' +
                    '                    <a class="article-title-link">'+ data['data']['title'] +'</a>\n' +
                    '                </h1>\n' +
                    '                <div class="article-meta">\n' +
                    '                    <span class="am-icon-calendar"></span>\n' +
                    '                    <span class="meta-text meta-calendar-text">'+ timeStampToDate(data['data']['createTime']) +'</span>\n' +
                    '                    <span class="am-icon-tags"></span>\n' +
                    '                    <span class="meta-text meta-tags-text">\n';

                for (var i=0; i< data['data']['attributeLabelCount']; i++) {
                    if (i !=  data['data']['attributeLabelCount']-1) {
                        str+='<span><a class="am-icon-tags-aa" href="/tag/'+ data['data']['attributeLabel'][i] +'">'+  data['data']['attributeLabel'][i] +'</a>,</span>\n';
                    } else {
                        str+='<span><a class="am-icon-tags-aa" href="/tag/'+ data['data']['attributeLabel'][i] +'">'+  data['data']['attributeLabel'][i] +'</a></span>\n';
                    }
                }

                str +=    '                    </span>\n' +
                    '                    <span class="am-icon-eye"></span>\n' +
                    '                    <span class="meta-text meta-eye-text">'+ data['data']['read'] +'</span>\n' +
                    '                </div>\n' +
                    '                <div class="article-content">\n' +
                    '                </div>\n' +
                    '                \n' +
                    '                <div id="article-share">\n' +
                    '                    <div class="social-share">\n' +
                    '                        分享到：\n' +
                    '                        <a href="#" class="social-share-icon icon-weibo"></a>\n' +
                    '                        <a href="#" class="social-share-icon icon-qq"></a>\n' +
                    '                        <a href="#" class="social-share-icon icon-wechat"></a>\n' +
                    '                        <a href="#" class="social-share-icon icon-qzone"></a>\n' +
                    '                    </div>\n' +
                    '                    <div class="article-button">\n' +
                    '                        <a class="article-btn" href="/likeArticle">❤ 喜欢 ❤</a>\n' +
                    '                    </div>\n' +
                    '                    <hr/>\n' +
                    '                    <div class="article-page">\n' +
                    '                        <a id="article-page-prev" onclick="stepArticle('+ data['data']['prevArticleId'] +')" rel="prev" >\n' +
                    '                            <i class="fa fa-chevron-left"></i> '+ data['data']['prevArticleTitle'] +'\n' +
                    '                        </a>\n' +
                    '                        <a id="article-page-next" onclick="stepArticle('+ data['data']['nextArticleId'] +')" rel="next" >\n' +
                    '                            '+ data['data']['nextArticleTitle'] +' <i class="fa fa-chevron-right"></i>\n' +
                    '                        </a>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>';

                rUl.html(str);

                var content = data['data']['content'];
                var converter = new Markdown.Converter();
                var htmlContent = converter.makeHtml(content);
                $(".article-content").html(htmlContent);

                if(data['data']['prevArticleId']=="no") {
                    $("#article-page-prev").empty();
                    $("#article-page-prev").html('');
                }
                if(data['data']['nextArticleId']=="no") {
                    $("#article-page-next").empty();
                    $("#article-page-next").html('');
                }

                var $config = {
                    url                 : window.location.href,// 网址，默认使用 window.location.href
                    source              : '', // 来源（QQ空间会用到）, 默认读取head标签：<meta name="site" content="http://overtrue" />
                    title               : data['data']['title'], // 标题，默认读取 document.title 或者 <meta name="title" content="share.js" />
                    description         : data['data']['summary'], // 描述, 默认读取head标签：<meta name="description" content="PHP弱类型的实现原理分析" />
                    image               : '', // 图片, 默认取网页中第一个img标签
                    sites               : ['weibo', 'qq', 'wechat', 'qzone'], // 启用的站点
                    disabled            : ['google', 'facebook', 'twitter'], // 禁用的站点
                    wechatQrcodeTitle   : '微信扫一扫：分享', // 微信二维码提示文字
                    wechatQrcodeHelper  : '<p>微信里点“发现”，扫一下</p><p>二维码便可将本文分享至朋友圈。</p>',
                    target : '_blank', // 打开方式
                    initialized: true // 不自动生成图标
                };
                $('.social-share').share($config);

            },
            error: function () {
                alert("请求失败");
            }
        });
}
pageContent();

/*function pageLog() {
    $.ajax(
        {
            type: "post",
            url: "/getArticleCatalog",
            dataType: "json",

            success: function (data) {
                var rUl = $("#main-right-text");
                var str = "";

                rUl.html('');

                var content = data['data']['content'];
                var converter = new Markdown.Converter();
                var htmlContent = converter.makeHtml(content);
                /!*$(".article-content").html(htmlContent);*!/

                $.each(data['data'], function (index, obj) {
                });
                rUl.html(str);

            },
            error: function () {
                alert("请求失败");
            }
        });
}
pageLog();*/

var catalogFlag;
/* 给所有h标签加id */
$(".article-content :header").each(function () {
    $(this).attr('id', $(this).html());
});

/* 文章目录 */
var countH = 1;
var sstep = 1;
var catalogJudge = 0;

$(".article-content h1").each(function () {
    catalogJudge = 1;

    var str = "";

    str += '<li>\n' +
        '                        <a class="article-catalog">'+ $(this).attr("id") +'</a>\n' +
        '                        <ol id="catalog-h1' + (countH++) +'"></ol>' +
        '                    </li>';
    $("#catalog-h1").append(str);
});
countH = 1;
function catalogPlus(hStep) {
    $(".article-content h" + hStep).each(function () {
        var prevSibling = $(this).prev();

        while(true) {
            if(prevSibling.prop("tagName")==("H"+(hStep-1))) {
                break;
            }

            prevSibling = prevSibling.prev();
        }

       var hId = prevSibling.attr('id');
       var hParent;

       $("#main-right-article-catalog a").each(function () {
          if( $(this).html()==hId ) {
              hParent = $(this).next().attr("id");
              return false;
          }
       });

       console.log(hParent);
       sstep = hParent.substring(hParent.indexOf("h")+1, hParent.length);

        var str = '';
        str += '<li>\n' +
            '                        <a class="article-catalog">'+ $(this).attr("id") +'</a>\n' +
            '                        <ol id="catalog-h'+ sstep + (countH++) +'"></ol>' +
            '                    </li>';

        $("#catalog-h" + sstep).append(str);
    });

    countH = 1;
}
for(var i=2; i<=6; i++) {
    catalogPlus(i);
}
if(catalogJudge==0) {
    $(".right-catalog").css("display", "none");
    $(".right-catalog-article").removeClass("right-catalog-style");
    $(this).addClass("right-catalog-style");

    var oUl=$("#main-right-text");
    var str = "";
    oUl.html("");
    str += '<div class="card-head">\n' +
        '            <div class="card-my-head">\n' +
        '                <div class="head-card-img-div">\n' +
        '                    <div class="card-head-left-img">\n' +
        '                        <img src="../static/img/header.jpg" id="head-card-img"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="card-head-right-word">\n' +
        '                    <h1 id="head-card-right-h">Seaguller</h1>\n' +
        '                    <small class="card-head-right-writing">如果你自己都放弃自己了，还有谁会救你？</small>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="card-middle">\n' +
        '            扣扣~：1656299466<br/>\n' +
        '            微信~：xy554322260\n' +
        '        </div>\n' +
        '        <hr id="hr-one"/>\n' +
        '        <div id="icons">\n' +
        '            <ul class="am-nav am-nav-pills">\n' +
        '                <li><a target="_blank" href="https://github.com/cn-stdio" class="card-icons"><i class="fa fa-github" title="活跃不起来滴小github~"></i></a></li>\n' +
        '                <li><a target="_blank" href="https://gitee.com/Seaguller" class="card-icons"><i class="fa fa-gg" title="码码码码码云~"></i></a></li>\n' +
        '                <li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1656299466&site=qq&menu=yes" class="card-icons"><i class="fa fa-qq" title="点击QQ联系俺~"></i></a></li>\n' +
        '                <li><a href="javascript:void(0);" class="card-icons"><i class="fa fa-weixin">\n' +
        '                </i></a></li>\n' +
        '                <li><a target="_blank" href="https://weibo.com/p/1005053269816883" class="card-icons"><i class="fa fa-weibo" title="基本不碰的小微博~"></i></a></li>\n' +
        '            </ul>\n' +
        '            <div class="card-weixin-divout" id="card-weixin-divout-double"">\n' +
        '                <div class="card-weixin-divin"></div>\n' +
        '                <img src="../static/img/weixin.gif" id="weixin-img"/>\n' +
        '            </div>\n' +
        '\n' +
        '        </div>';

    oUl.html(str);

    /* 微信翻转 */
    $(".card-weixin-divout").css("top", "370px");
    $(".fa-weixin").mouseover(function () {
        $(".card-weixin-divout").css("display", "block");
        $(".card-weixin-divin").css("display", "block");
        $("#weixin-img").css("display", "block");
    });
    $(".fa-weixin").mouseout(function () {
        $(".card-weixin-divout").css("display", "none");
        $(".card-weixin-divin").css("display", "none");
        $("#weixin-img").css("display", "none");
    });
}

/* 滚动监测显示颜色 */
var htmlScrollTop=0;
$(window).scroll(function() {
    if(document.documentElement && document.documentElement.scrollTop) {
        htmlScrollTop = document.documentElement.scrollTop;
    } else if(document.body) {
        htmlScrollTop =document.body.scrollTop;
    }

    $("#main-right-article-catalog a").each(function () {
        var hScrollTop = $($(this).attr('href')).offset().top;
        if(htmlScrollTop >= hScrollTop-1) {
            $(this).parent().prevAll().removeClass("article-catalog-active");
            $(this).parent().prevAll().find("li").removeClass("article-catalog-active");
            $(this).parent().addClass("article-catalog-active");

            console.log($(this).position().top);
        } else {
            $(this).parent().removeClass("article-catalog-active");
        }
    });

});

catalogFlag = $("#main-right-text").html();
/* 目录名片 */
$(".right-catalog-user").click(function () {
    $(".right-catalog-article").removeClass("right-catalog-style");
    $(this).addClass("right-catalog-style");

    var oUl=$("#main-right-text");
    var str = "";
    catalogFlag = oUl.html();
    oUl.html("");
    str += '<div class="card-head">\n' +
        '            <div class="card-my-head">\n' +
        '                <div class="head-card-img-div">\n' +
        '                    <div class="card-head-left-img">\n' +
        '                        <img src="../static/img/header.jpg" id="head-card-img"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="card-head-right-word">\n' +
        '                    <h1 id="head-card-right-h">Seaguller</h1>\n' +
        '                    <small class="card-head-right-writing">如果你自己都放弃自己了，还有谁会救你？</small>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="card-middle">\n' +
        '            扣扣~：1656299466<br/>\n' +
        '            微信~：xy554322260\n' +
        '        </div>\n' +
        '        <hr id="hr-one"/>\n' +
        '        <div id="icons">\n' +
        '            <ul class="am-nav am-nav-pills">\n' +
        '                <li><a target="_blank" href="https://github.com/cn-stdio" class="card-icons"><i class="fa fa-github" title="活跃不起来滴小github~"></i></a></li>\n' +
        '                <li><a target="_blank" href="https://gitee.com/Seaguller" class="card-icons"><i class="fa fa-gg" title="码码码码码云~"></i></a></li>\n' +
        '                <li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1656299466&site=qq&menu=yes" class="card-icons"><i class="fa fa-qq" title="点击QQ联系俺~"></i></a></li>\n' +
        '                <li><a href="javascript:void(0);" class="card-icons"><i class="fa fa-weixin">\n' +
        '                </i></a></li>\n' +
        '                <li><a target="_blank" href="https://weibo.com/p/1005053269816883" class="card-icons"><i class="fa fa-weibo" title="基本不碰的小微博~"></i></a></li>\n' +
        '            </ul>\n' +
        '            <div class="card-weixin-divout">\n' +
        '                <div class="card-weixin-divin"></div>\n' +
        '                <img src="../static/img/weixin.gif" id="weixin-img"/>\n' +
        '            </div>\n' +
        '\n' +
        '        </div>';

    oUl.html(str);

    /* 微信翻转 */
    $(".card-weixin-divout").css("top", "370px");
    $(".fa-weixin").mouseover(function () {
        $(".card-weixin-divout").css("display", "block");
        $(".card-weixin-divin").css("display", "block");
        $("#weixin-img").css("display", "block");
    });
    $(".fa-weixin").mouseout(function () {
        $(".card-weixin-divout").css("display", "none");
        $(".card-weixin-divin").css("display", "none");
        $("#weixin-img").css("display", "none");
    });
});

/* 文章目录点击事件 */
$(".right-catalog-article").click(function () {
    $(".right-catalog-user").removeClass("right-catalog-style");
    $(this).addClass("right-catalog-style");

    var oUl=$("#main-right-text");

    oUl.html(catalogFlag);

    $(".article-catalog").each(function () {
        $(this).attr('href', "#" + $(this).html());
    });
    $("#main-right-article-catalog a").click(function(){
        // 根据a标签的href转换为id选择器，获取id元素所处的位置
        $('html,body').animate({
            scrollTop: ($($(this).attr('href')).offset().top)
        },400);
    });
});

/* 点击目录滚动 */
$(".article-catalog").each(function () {
    $(this).attr('href', "#" + $(this).html());
});
$("#main-right-article-catalog a").click(function(){
    // 根据a标签的href转换为id选择器，获取id元素所处的位置
    $('html,body').animate({
        scrollTop: ($($(this).attr('href')).offset().top)
    },400);
});

/* 获取目标节点位置，定位评论区 */
var article_offset = $("#main-middle-article").offset();
var ah = $("#main-middle-article").css("height").substring(0, $("#main-middle-article").css("height").length-2);
var hh = article_offset.top + parseInt(ah);
$(".article-comment").css("margin-top", hh+15);

/* 回复框框 */
function rep(num, flag) {
    var strRepId = "#comment-reply-squ-" + num;

    if(flag=="open") {
        $(".comment-reply-squ").css("display", "none");
        $(strRepId).css("display", "block");
    } else if(flag=="close") {
        $(strRepId).css("display", "none");
    }
}

var footTall;
var footFlag = 0;
$(".comment-right-btn").click(function () {
    var strId = $(this).attr('id');
    var indx = strId.indexOf("-");
    indx = strId.indexOf("-", indx+1);
    indx = strId.indexOf("-", indx+1) + 1;
    strId = strId.substring(indx);

    rep(strId, "open");

    /* 尾部footer定位 */
    if(footFlag == 0) {
        footTall = $(".footer").css("top");
        var cw = document.body.scrollHeight;
        footFlag = 1;
    } else {
        var cw = footTall;
    }
    console.log(cw);
    $(".footer").css("top", cw+50);
});
$(".comment-reply-btn-left").click(function () {
    var strId = $(this).attr('id');
    var indx = strId.indexOf("-");
    indx = strId.indexOf("-", indx+1);
    indx = strId.indexOf("-", indx+1);
    indx = strId.indexOf("-", indx+1) + 1;
    strId = strId.substring(indx);

    rep(strId, "close");

    /* 尾部footer定位 */
    $(".footer").css("top", footTall);
    footFlag = 0;
});

