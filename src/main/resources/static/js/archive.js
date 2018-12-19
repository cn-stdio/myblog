var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

/* 目录名片 */
$(".right-catalog-user").click(function () {
    $(".right-catalog-article").removeClass("right-catalog-style");
    $(this).addClass("right-catalog-style");

    var oUl=$("#main-right-text");
    var str = "";
    oUl.html("");
    str += '<div class="card-head">\n' +
        '            <div class="card-my-head">\n' +
        '                <div class="head-card-img-div">\n' +
        '                    <div class="card-head-left-img">\n' +
        '                        <img src="../static/img/123.png" id="head-card-img"/>\n' +
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

$(".right-catalog-article").click(function () {
    $(".right-catalog-user").removeClass("right-catalog-style");
    $(this).addClass("right-catalog-style");
    var oUl=$("#main-right-text");
    oUl.html("");
});



