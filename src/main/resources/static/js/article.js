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
                    str+='<div class="article-badge"><a href="#" class="badge-a">臭不要脸</a></div>';
                    str+='<h1 class="article-title">';
                    str+='<a class="article-title-link" href="#">' + obj['title'] + '</a></h1>';
                    str+='<div class="article-meta">';
                    str+='<span class="am-icon-calendar"></span><span class="meta-text meta-calendar-text">'+ timeStampToDate(obj['createTime']) +'</span>';
                    str+='<a class="am-icon-tags-aa" href="#"><span class="am-icon-tags"></span><span class="meta-text meta-tags-text">' + obj['mainLabel'] +'</span></a>';
                    str+='<span class="am-icon-eye"></span><span class="meta-text meta-eye-text">' + obj['read'] +'</span></div>';
                    str+='<div class="article-content">' + obj['summary'] + '</div>';
                    str+='<div class="article-button"><a class="article-btn" href="#">阅读全文 »</a></div></div></div>';
                    oUl.html(str);
                });
                if(p==1) {
                    str = "";
                    str += '<nav class="article-pagination" role="navigation"><a class="next" href="#turn-head" onclick="pageTurn(' + (p+1) +')">下一页</a></nav>';
                    oUl.children("div:last-child").append(str);
                } else if (p==data['pages']) {
                    str = "";
                    str += '<nav class="article-pagination" role="navigation"><a class="prev" href="#turn-head" style="margin-right: 570px;" onclick="pageTurn('+ (p-1) +')">上一页</a></nav>';
                    oUl.children("div:last-child").append(str);
                } else {
                    str = "";
                    str += '<nav class="article-pagination" role="navigation"><a class="next" href="#turn-head" onclick="pageTurn(' + (p+1) +')">下一页</a><a class="prev" href="#turn-head" onclick="pageTurn('+ (p-1) +')">上一页</a></nav>';
                    oUl.children("div:last-child").append(str);
                }
            },
            error:function(){
                alert("请求失败");
            }
        });
}
pageTurn(page);
/*$("#art-list").on('mouseenter', function () {
    $(".next").click(function() {
        alert(123);
        pageTurn(++page);
    });
});
$(".prev").click(function() {
    pageTurn(--page);
});*/
/*$.ajax(
{
    type:"post",
    url:"/getAllArticles",
    dataType:"json",
    data:{
        rows:'5',
        pageNum: page
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
            str+='<div class="article-badge"><a href="#" class="badge-a">臭不要脸</a></div>';
            str+='<h1 class="article-title">';
            str+='<a class="article-title-link" href="#">' + obj['title'] + '</a></h1>';
            str+='<div class="article-meta">';
            str+='<span class="am-icon-calendar"></span><span class="meta-text meta-calendar-text">'+ obj['createTime'] +'</span>';
            str+='<a class="am-icon-tags-aa" href="#"><span class="am-icon-tags"></span><span class="meta-text meta-tags-text">' + obj['mainLabel'] +'</span></a>';
            str+='<span class="am-icon-eye"></span><span class="meta-text meta-eye-text">' + obj['read'] +'</span></div>';
            str+='<div class="article-content">' + obj['summary'] + '</div>';
            str+='<div class="article-button"><a class="article-btn" href="#">阅读全文 »</a></div></div></div>';
            oUl.html(str);
        });

        str = "";
        str += '<nav class="article-pagination" role="navigation"><a class="next" href="/page/">下一页</a></nav>';
        oUl.children("div:last-child").append(str);
    },
    error:function(){
        alert("请求失败");
    }
});*/
