var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);
