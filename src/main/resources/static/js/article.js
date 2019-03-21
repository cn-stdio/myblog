var pageNum = 1;
var page = pageNum;

function timeStampToDate(date){
    var date= new Date(parseInt(date));
    return date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate() + "日";
}

function pageTurn(p) {
    $.ajax(
        {
            type:"post",
            url:"/getAllArticles",
            dataType:"json",
            data:{
                rows:'5',
                pageNum: p
            },
            success:function(data){
                var count = 1;
                var str = "";
                var oUl=$("#art-list");
                var top = 690;

                oUl.html('');

                $.each(data['data'],function(index,obj){
                    if(count >= 2) {
                        top += 355;
                    }
                    str+='<div class="main-middle" id="main-middle-' + (count++) + '" style=top:' + top + 'px;">';
                    str+='<div class="main-middle-article">';
                    str+='<div class="article-badge"><a href="/type/'+ obj['type'] +'" class="badge-a">'+ obj['type'] +'</a></div>';
                    str+='<h1 class="article-title">';
                    str+='<a class="article-title-link" href="/article/'+ obj['articleId'] +'">' + obj['title'] + '</a></h1>';
                    str+='<div class="article-meta">';
                    str+='<span class="am-icon-calendar"></span><span class="meta-text meta-calendar-text">'+ timeStampToDate(obj['createTime']) +'</span>';
                    str+='<span class="am-icon-tags"></span>';
                    str+='<span class="meta-text meta-tags-text">\n';

                    for (var i=0; i<obj['attributeLabelCount']; i++) {
                        if (i != obj['attributeLabelCount']-1) {
                            str+='<span><a class="am-icon-tags-aa" href="/tag/'+ obj['attributeLabel'][i] +'">'+ obj['attributeLabel'][i] +'</a>,</span>\n';
                        } else {
                            str+='<span><a class="am-icon-tags-aa" href="/tag/'+ obj['attributeLabel'][i] +'">'+ obj['attributeLabel'][i] +'</a></span>\n';
                        }
                    }

                    str+='</span>\n';
                    str+='<span class="am-icon-eye"></span><span class="meta-text meta-eye-text">' + obj['read'] +'</span></div>';
                    str+='<div class="article-content">' + obj['summary'] + '</div>';
                    str+='<div class="article-button"><a class="article-btn" href="/article/'+ obj['articleId'] +'">阅读全文 »</a></div></div></div>';
                    oUl.html(str);
                });
                if (data['pages'] >= 2) {
                    if(p==1) {
                        str = "";
                        str += '<nav class="article-pagination" role="navigation"><a class="next" href="#turn-head" onclick="pageTurn(' + (p+1) +')">下一页</a></nav>';
                        oUl.children("div:last-child").append(str);
                    } else if (p == data['pages']-1) {
                        str = "";
                        str += '<nav class="article-pagination" role="navigation"><a class="next" href="#turn-head" onclick="pageTurn(' + (p+1) +')">下一页</a><a class="prev" href="#turn-head" onclick="pageTurn('+ (p-1) +')">上一页</a></nav>';
                        oUl.children("div:last-child").append(str);

                        /* 尾部footer定位 */
                        var cw = $(".prev").offset().top;
                        console.log(cw);
                        $(".footer").css("top", cw+100);

                    } else if (p==data['pages']) {
                        str = "";
                        str += '<nav class="article-pagination" role="navigation"><a class="prev" href="#turn-head" style="margin-right: 570px;" onclick="pageTurn('+ (p-1) +')">上一页</a></nav>';
                        oUl.children("div:last-child").append(str);

                        /* 尾部footer定位 */
                        var cw = $(".prev").offset().top;
                        console.log(cw);
                        $(".footer").css("top", cw+100);

                    } else {
                        str = "";
                        str += '<nav class="article-pagination" role="navigation"><a class="next" href="#turn-head" onclick="pageTurn(' + (p+1) +')">下一页</a><a class="prev" href="#turn-head" onclick="pageTurn('+ (p-1) +')">上一页</a></nav>';
                        oUl.children("div:last-child").append(str);
                    }
                }
            },
            error:function(){
                alert("请求失败");
            }
        });
}
pageTurn(page);
