var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

function timeStampToDate(date){
    var date= new Date(parseInt(date));
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}
function articleTurn(id) {
    window.location.href = "/article/" + id;
}
var pageNum = 1;

var page = pageNum;
function pageTurn(p, dd) {
    $.ajax(
        {
            type: "post",
            url: "/getTypePage",
            dataType: "json",
            data: {
                rows: '5',
                pageNum: p,
                typeName: dd
            },
            success: function (data) {
                var str = "";
                var oUl = $(".VivaTimeline");
                var iUl = $(".container-before-icon");

                oUl.html('');

                str += '<span class="am-icon-star am-icon-lg"></span>\n' +
                    '该标签总计 '+ data['articleSize'] +' 篇日志。 (゜-゜)つロ 干杯';
                iUl.html(str);

                str = "";
                $.each(data['data'], function (index, obj) {
                    var date= new Date(parseInt(obj['createTime']));

                    str += '<dl>\n';
                    if(index%2 == 0) {
                        str += '<dd class="pos-right clearfix">\n';
                    } else {
                        str += '<dd class="pos-left clearfix">\n';
                    }
                    str +=
                        '                                <div class="circ"></div>\n' +
                        '                                <div class="time">'+ (date.getMonth()+1) +'月'+ date.getDate() +'日</div>\n' +
                        '                                <div class="events">\n' +
                        '                                    <div class="events-header" onclick="articleTurn('+ obj['articleId'] +')">'+ obj['title'] +'</div>\n' +
                        '                                    <div class="events-body">\n' +
                        '                                        <span class="am-icon-calendar">\n' +
                        '                                            <a id="events-body-create-time">'+ timeStampToDate(obj['createTime']) +'</a>\n' +
                        '                                        </span>\n' +
                        '                                        <span class="am-icon-folder">\n' +
                        '                                            <a href="/type/'+ obj['type'] +'">'+ obj['type'] +'</a>\n' +
                        '                                        </span><br/>\n' +
                        '                                        <span class="am-icon-tags">\n';

                    var lAttributeLabel = obj['attributeLabel'].length;
                    $.each(obj['attributeLabel'], function (index, obj) {
                        if(index == lAttributeLabel-1) {
                            str += '<a href="/tag/'+ obj +'">'+ obj +'</a>\n';
                        } else {
                            str += '<a href="/tag/'+ obj +'">'+ obj +'</a>，\n';
                        }
                    });

                    str += '                                        </span>\n' +
                        '                                    </div>\n' +
                        '                                </div>\n' +
                        '                            </dd>\n' +
                        '                        </dl>';
                });
                oUl.html(str);
                /*oUl.append("<dl></dl>");*/
                var rUl = $(".VivaTimeline").children(":first");
                str = "";
                str += '<dt>'+ dd +'</dt>';
                rUl.prepend(str);

                var lUl = $(".article-pagination");
                lUl.html("");
                if (data['pages'] >= 2) {
                    if (p == 1) {
                        str = "";
                        str += '<a class="next" href="#turn-head" onclick="pageTurn(' + (p + 1) + ',\'' + dd + '\')">下一页</a>';
                        lUl.html(str);

                        if(data['pages']==2) {
                            /* 尾部footer定位 */
                            $(".footer").css("top", $(".next").offset().top+100);
                        }
                    } else if (p == data['pages']-1) {
                        str = "";
                        str += '<a class="next" href="#turn-head" onclick="pageTurn(' + (p + 1) + ',\'' + dd + '\')">下一页</a><a class="prev" href="#turn-head" onclick="pageTurn(' + (p - 1) + ',\'' + dd + '\')">上一页</a>';
                        lUl.html(str);

                        /* 尾部footer定位 */
                        var cw = $(".prev").offset().top;
                        console.log(cw);
                        $(".footer").css("top", cw+100);

                    } else if (p == data['pages']) {
                        str = "";
                        str += '<a class="prev" href="#turn-head" style="margin-right: 570px;" onclick="pageTurn(' + (p - 1) + ',\'' + dd + '\')">上一页</a>';
                        lUl.html(str);

                        /* 尾部footer定位 */
                        var cw = $(".prev").offset().top;
                        console.log(cw);

                        if(cw>=1380) {
                            $(".footer").css("top", cw+100);
                        } else {
                            $(".footer").css("top", 1380);
                        }

                    } else {
                        str = "";
                        str += '<a class="next" href="#turn-head" onclick="pageTurn(' + (p + 1) + ',\'' + dd + '\')">下一页</a><a class="prev" href="#turn-head" onclick="pageTurn(' + (p - 1) + ',\'' + dd + '\')">上一页</a>';
                        lUl.html(str);
                    }
                } else {
                    /* 尾部footer定位 */
                    $(".footer").css("top", 0);
                    var cw = $(".article-pagination").offset().top;
                    console.log(cw);
                    if(cw>=1380) {
                        $(".footer").css("top", cw+100);
                    } else {
                        $(".footer").css("top", 1380);
                    }
                }
            },
            error: function () {
                alert("请求失败");
            }
        });

}
pageTurn(page, $("#typeName").val());

/* 获取文章类别 */
function pageCategory() {
    $.ajax(
        {
            type: "post",
            url: "/getPageCategory",
            dataType: "json",

            success: function (data) {
                var rUl = $("#main-right-text-ul");
                var str = "";

                rUl.html('');

                $.each(data['data'], function (index, obj) {
                    str += '<li><a href="/type/'+ obj +'" class="main-right-date" >>> '+ obj +'</a></li>';
                });
                rUl.html(str);

            },
            error: function () {
                alert("请求失败");
            }
        });
}
pageCategory();

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
        '                        <img src="https://seaguller.oss-cn-beijing.aliyuncs.com/headImg/cc5d76e15a0144f4ba911ca27889e88c.jpeg" id="head-card-img"/>\n' +
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
        '                <li><a target="_blank" href="https://wpa.qq.com/msgrd?v=3&uin=1656299466&site=qq&menu=yes" class="card-icons"><i class="fa fa-qq" title="点击QQ联系俺~"></i></a></li>\n' +
        '                <li><a href="javascript:void(0);" class="card-icons"><i class="fa fa-weixin">\n' +
        '                </i></a>\n' +
        '            <div class="card-weixin-divout">\n' +
        '                <div class="card-weixin-divin"></div>\n' +
        '                <img src="https://seaguller.oss-cn-beijing.aliyuncs.com/static/weixin.gif" id="weixin-img"/>\n' +
        '            </div>\n' +
        '          </li>\n' +
        '                <li><a target="_blank" href="https://weibo.com/p/1005053269816883" class="card-icons"><i class="fa fa-weibo" title="基本不碰的小微博~"></i></a></li>\n' +
        '            </ul>\n' +
        '\n' +
        '        </div>';

    oUl.html(str);

    /* 微信翻转 */
    $(".card-weixin-divout").css("top", "51px");
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
    var str = '';
    str += '<ul class="am-list am-list-border" id="main-right-text-ul"></ul>';
    oUl.html(str);

    pageCategory();
});



