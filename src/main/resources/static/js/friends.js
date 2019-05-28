var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

$.ajax(
    {
        type:"post",
        url:"/getFriends",
        dataType:"json",
        async:false,
        success:function(data) {

            var friendHtml = '';

            $.each(data['data'], function (index, obj) {
               friendHtml += '<li>\n' +
                   '                    <a class="friend-a" href="'+ obj['url'] +'" title="'+ obj['name'] +'" target="_blank">\n' +
                   '                        <img class="link-img" src="'+ obj['img'] +'">\n' +
                   '                        <div class="link-text">'+ obj['name'] +'</div>\n' +
                   '                        <div class="link-text-introduce">'+ obj['introduce'] +'</div>\n' +
                   '                    </a>\n' +
                   '                </li>';
            });

            $("#friend-ul").html(friendHtml);
        },
        error:function () {
            console.log("获取友链失败");
        }
});
