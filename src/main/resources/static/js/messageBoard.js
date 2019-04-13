var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

var loginCheck = 0;
$.ajax(
    {
        type:"get",
        url:"/checkLogin",
        dataType:"json",
        async: false,
        success:function(data){
            if(data['msg']=="noLogin") {
                loginCheck = 1;
            }
        },
        error:function(){
            console.log("请求失败");
        }
    });

/* 获得评论列表 */
var selfIdEnd;
function getComment() {
    $.ajax(
        {
            type:"post",
            url:"/getArticleComment",
            dataType:"json",
            async: false,
            data:{
                articleId: 0
            },
            success:function(data){
                var commentStr = "";

                if(data['msg']=="empty") {
                    $(".article-comment-nonpeople").css("display", "block");
                    $(".article-comment-list-ol").html(commentStr);
                    selfIdEnd = 0;
                } else {
                    $(".article-comment-nonpeople").css("display", "none");
                    selfIdEnd = data['data']['comment'][0]['selfId'];

                    $.each(data['data']['comment'],function(index,obj){
                        var commentStrUnit = "";
                        commentStrUnit += '<li id="article-comment-list-li">\n' +
                            '                    <div>\n' +
                            '                        <div class="article-comment-list-left">\n' +
                            '                            <img class="article-comment-headimg" src="'+ obj['commentImg'] +'" />\n' +
                            '                        </div>\n' +
                            '                        <div class="article-comment-list-right">\n' +
                            '                            <span class="comment-right-name" id="comment-right-name-'+ obj['selfId'] +'">'+ obj['respondentName'] +'</span>\n' +
                            '                            <span class="comment-right-self">#'+ obj['selfId'] +'楼</span>\n' +
                            '                            <span class="comment-right-time">'+ obj['date'] +'</span>\n' +
                            '                            <div class="comment-right-text">\n' +
                            obj['content'] +
                            '                            </div>\n' +
                            '                            <div>\n' +
                            '                                <span><a class="comment-right-btn" id="comment-right-btn-'+ obj['selfId'] +'">回复</a></span>\n' +
                            '                                <span class="comment-reply-like-heart" id="comment-reply-like-heart-'+ obj['selfId'] +'"></span>\n' +
                            '                                <span class="comment-reply-like-text" id="comment-reply-like-text-'+ obj['selfId'] +'">'+ obj['like'] +'</span>\n' +
                            '                            </div>\n' +
                            '\n' +
                            '                            <div class="comment-reply-squ" id="comment-reply-squ-'+ obj['selfId'] +'">\n' +
                            '                                <textarea maxlength="500" class="am-modal-prompt-input private-conversation comment-reply comment-reply-text" id="comment-reply-text-'+ obj['selfId'] +'" placeholder="畅所欲言叭，骚年d(`･∀･)b！"></textarea>\n' +
                            '                                <div class="comment-reply-btn-div">\n' +
                            '                                    <a class="comment-reply-btn-left" id="comment-reply-btn-left-'+ obj['selfId'] +'">取消回复</a>\n' +
                            '                                    <a class="comment-reply-btn-right">发表评论</a>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <div class="article-comment-list-reply">\n' +
                            '                        <ol class="article-comment-list-ol" id="article-comment-list-ol-'+ obj['selfId'] +'">\n';
                        $.each(obj['reply'],function(index, obj2){
                            commentStrUnit +=
                                '                            <li>\n' +
                                '                                <div>\n' +
                                '                                    <div class="article-comment-list-left">\n' +
                                '                                        <img class="article-comment-headimg" src="'+ obj2['commentImg'] +'" />\n' +
                                '                                    </div>\n' +
                                '                                    <div class="article-comment-list-right">\n' +
                                '                                        <span class="comment-right-name" id="comment-right-name-'+ obj['selfId'] + '-' + obj2['selfId'] +'">'+ obj2['respondentName'] +'</span>\n' +
                                '                                        <span class="comment-right-time">'+ obj2['date'] +'</span>\n' +
                                '                                        <div class="comment-right-text">\n' +
                                '                                            <span class="comment-right-text-replyname">@'+ obj2['answerName'] +'</span>\n' +
                                '                                            <span>'+ obj2['content'] +'</span>\n' +
                                '                                        </div>\n' +
                                '                                        <div>\n' +
                                '                                            <span><a class="comment-right-btn" id="comment-right-btn-'+ obj['selfId'] + '-' + obj2['selfId'] +'">回复</a></span>\n' +
                                '                                            <span class="comment-reply-like-heart" id="comment-reply-like-heart-'+ obj['selfId'] + '-' + obj2['selfId'] +'" style="margin-right: 410px !important;"></span>\n' +
                                '                                            <span class="comment-reply-like-text" id="comment-reply-like-text-'+ obj['selfId'] + '-' + obj2['selfId'] +'">'+ obj2['like'] +'</span>\n' +
                                '                                        </div>\n' +
                                '                                        <div class="comment-reply-squ" id="comment-reply-squ-'+ obj['selfId'] + '-' + obj2['selfId'] +'">\n' +
                                '                                            <textarea maxlength="500" class="am-modal-prompt-input private-conversation comment-reply comment-reply-text" id="comment-reply-text-'+ obj['selfId'] + '-' + obj2['selfId'] +'" placeholder="畅所欲言叭，骚年d(`･∀･)b！"></textarea>\n' +
                                '                                            <div class="comment-reply-btn-div">\n' +
                                '                                                <a class="comment-reply-btn-left"  id="comment-reply-btn-left-'+ obj['selfId'] + '-' + obj2['selfId'] +'">取消回复</a>\n' +
                                '                                                <a class="comment-reply-btn-right" style="float: right; margin-left: 0 !important;">发表评论</a>\n' +
                                '                                            </div>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                </div>\n' +
                                '                                <div></div>\n' +
                                '                         <div class="clear"></div> ' +
                                '                            </li>\n';
                        });

                        commentStrUnit +=                             '                        </ol>\n' +
                            '                    </div>\n' +
                            '                    <div class="clear"></div> ' +
                            '                </li>';

                        commentStr += commentStrUnit;

                    });
                    $(".article-comment-list-ol").html(commentStr);

                    if(data['data']['likeList'] != "noLogin") {
                        $.each(data['data']['likeList'],function(index, obj) {
                            if(obj.split(",").length != 1) {
                                var likeHeart = "#comment-reply-like-heart-" + obj.split(",")[0] + "-" + obj.split(",")[1];
                            } else {
                                var likeHeart = "#comment-reply-like-heart-" + obj;

                            }
                            $(likeHeart).addClass("comment-reply-like-heart2");
                        });
                    }
                }

            },
            error:function(){
                console.log("评论显示失败！");
            }
        });
}
getComment();

/* 评论插入 */
if(loginCheck == 0) {
    $("#comment-btn").css("margin-top", "20.5px");
}
$("#comment-btn").click(function () {
    if(loginCheck==1) {
        window.location.href = "/login";
    } else {
        if($("#comment-text").val()=="") {
            alert("嘿，你还麻油输入评论内容捏(=m=)！")
        } else {
            var selfIdFinal = parseInt(selfIdEnd) + 1;
            $.ajax(
                {
                    type:"post",
                    url:"/insertComment",
                    dataType:"json",
                    async: false,
                    data:{
                        articleId: 0,
                        selfId: selfIdFinal,
                        answerName: "Seaguller",
                        content: $("#comment-text").val()
                    },
                    success:function(data){
                        if(data['msg']=="success") {
                            $(".article-comment-nonpeople").css("display", "none");
                            getComment();

                            $("#comment-text").val("");
                            $(".footer").css("top", document.body.scrollHeight);
                        }
                    },
                    error:function(){
                        console.log("评论插入失败！");
                    }
                });


        }
    }
});

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
$(".article-comment-list-ol").on("click", ".comment-right-btn",function () {
    if(loginCheck==1) {
        window.location.href = "/login";
    } else {
        var strId = $(this).attr('id');
        if(strId.split("-").length==4) {
            strId = strId.split("-")[3];
            rep(strId, "open");
        } else {
            rep(strId.split("-")[3] + '-' + strId.split("-")[4], "open");
        }

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
    }
});

$(".article-comment-list-ol").on("click", ".comment-reply-btn-left", function () {
    var strId = $(this).attr('id');
    if(strId.split("-").length==5) {
        strId = strId.split("-")[4];
        rep(strId, "close");
    } else {
        rep(strId.split("-")[4] + "-" + strId.split("-")[5], "close");
    }

    /* 尾部footer定位 */
    $(".footer").css("top", footTall);
    footFlag = 0;
});

/* 评论点赞 */
$(".article-comment-list-ol").on("click", ".comment-reply-like-heart", function() {
    if(!$(this).hasClass("comment-reply-like-heart2")) {
        if(loginCheck==1) {
            window.location.href = "/login";
        } else{
            var selfIdUnit = $(this).attr("id");
            if(selfIdUnit.split("-").length==5) {
                var selfId = selfIdUnit.split("-")[4];
            } else {
                var selfId = selfIdUnit.split("-")[4] + "," + selfIdUnit.split("-")[5];
            }

            var thisComment = $(this);
            $.ajax(
                {
                    type:"post",
                    url:"/likeComment",
                    dataType:"json",
                    async: false,
                    data:{
                        articleId: 0,
                        selfId: selfId
                    },
                    success:function(data){
                        if(data['msg']=="success") {
                            thisComment.addClass("comment-reply-like-heart2");
                            var commentLikeCount = parseInt(thisComment.next().html()) + 1;
                            thisComment.next().html(commentLikeCount);
                        }
                    },
                    error:function(){
                        console.log("评论点赞失败！");
                    }
                });
        }
    }
});

/* 发表回复 */
$(".article-comment-list-ol").on("click", ".comment-reply-btn-right", function () {
    var selfIdSplit = $(this).prev().attr("id").split("-");
    if (selfIdSplit.length == 5) {
        var selfIdOl = $("#article-comment-list-ol-" + selfIdSplit[selfIdSplit.length-1]);
        var selfIdLiCount = selfIdOl.children("li").length + 1;
        var selfIdFinal = selfIdSplit[selfIdSplit.length-1] + "," + selfIdLiCount;

        var answerNameReplyFloor =  selfIdSplit[selfIdSplit.length-1];
    } else {
        var selfIdOl = $("#article-comment-list-ol-" + selfIdSplit[selfIdSplit.length-2]);
        var selfIdLiCount = selfIdOl.children("li").length + 1;
        var selfIdFinal = selfIdSplit[selfIdSplit.length-2] + "," + selfIdLiCount;

        var answerNameReplyFloor =  selfIdSplit[selfIdSplit.length-2] + "-" + selfIdSplit[selfIdSplit.length-1];
    }

    if($("#comment-reply-text-" + answerNameReplyFloor).val()=="") {
        alert("嘿，你还麻油输入回复内容捏(=m=)！");
        console.log("reply:  " + answerNameReplyFloor);
    } else {
        var answerNameReply = $("#comment-right-name-" + answerNameReplyFloor).html();
        $.ajax(
            {
                type:"post",
                url:"/insertComment",
                dataType:"json",
                async: false,
                data:{
                    articleId: 0,
                    selfId: selfIdFinal,
                    answerName: answerNameReply,
                    content: $("#comment-reply-text-" + answerNameReplyFloor).val()
                },
                success:function(data){
                    if(data['msg']=="success") {
                        $("#comment-reply-text-" + answerNameReplyFloor).val("");
                        getComment();

                        /* 尾部footer定位 */
                        $(".footer").css("top", parseInt(footTall.split("p")[0])+100+"px");
                        footFlag = 0;
                        console.log(footTall);
                    }
                },
                error:function(){
                    alert("评论插入失败！");
                }
            });


    }
});