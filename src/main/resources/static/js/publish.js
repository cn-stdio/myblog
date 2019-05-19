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
    $("#login-user-name-phone").html(username);
}

/* 添加标签 */
var tagCount = 0;
$("#publish-tag-button").click(function () {
    if(tagCount!=0) {
        var tagFlag = $("#publish-modal-tags div").last().children("p").html();
    } else {
        var tagFlag = -1;
    }

    console.log(tagFlag)
    
    if(tagCount != 3 && tagFlag!="") {
        tagCount ++;

        var tagUnit = '<div style="display: inline-block;">\n' +
            '                        <p class="tag-name" contenteditable="true"></p>\n' +
            '                        <i class="am-icon-times remove-tag" style="color: #CCCCCC; cursor: pointer;"></i>\n' +
            '                    </div>';

        $("#publish-modal-tags").append(tagUnit);
    }

    if(tagCount != 0) {
        $("#publish-tag-button").css("margin-top", 0);
        $("#publish-tag-button").css("margin-right", "230px");
    } else {
        $("#publish-tag-button").css("margin-top", "-25px");
        $("#publish-tag-button").css("margin-right", "345px");
    }
});

/* 删除标签点击 */
$("#publish-modal-tags").on('click', '.remove-tag', function () {
    $(this).parent().remove();
    tagCount --;

    if(tagCount == 0) {
        $("#publish-tag-button").css("margin-top", "-25px");
        $("#publish-tag-button").css("margin-right", "345px");
    }
});

/* 警告框设置 */
$("#write-title-button").click(function () {
    var articleTitle = $("#write-title-text").val();
    if(articleTitle == "") {
        $("#notice-box-text").html("文章标题不能为空");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(1500).hide(0);
    } else if(testEditor.getMarkdown()=="") {
        $("#notice-box-text").html("文章内容不能为空");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(1500).hide(0);
    } else {
        $('#publish-article-modal').modal({
            closeOnConfirm: false
        });
    }
});

/* 发布文章 */
$("#publish-modal-confirm").click(function () {
    var childrenTag = $("#publish-modal-tags").find(".tag-name");
    var childrenTagFlag = 0;
    childrenTag.each(function(index, e){
        if($(e).html()=="") {
            childrenTagFlag = 1;
            return false;
        }
    });

    if(tagCount==0 || childrenTagFlag==1) {
        $("#notice-box-text").html("文章标签不能为空");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(1500).hide(0);
    } else if($('#publish-modal-classify option:selected').val()=="choose") {
        $("#notice-box-text").html("文章类型不能为空");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(1500).hide(0);
    } else if($('#publish-modal-type option:selected').val()=="choose") {
        $("#notice-box-text").html("文章分类不能为空");
        $(".notice-box").css("display", "block");
        $(".notice-box").delay(1500).hide(0);
    } else {
        var attributeLabel = "";
        childrenTag.each(function(index, e){
            attributeLabel += $(e).html() + ",";
        });

        var content = testEditor.getMarkdown();
        var converter = new Markdown.Converter();
        var htmlContent = converter.makeHtml(content);

        $.ajax(
            {
                type:"post",
                url:"/insertArticle",
                dataType:"json",
                data:{
                    title: $("#write-title-text").val(),
                    content: testEditor.getMarkdown(),
                    attributeLabel: attributeLabel.substring(0, attributeLabel.length-1),
                    type: $('#publish-modal-type option:selected').val(),
                    classify: $('#publish-modal-classify option:selected').val(),
                    htmlContent: htmlContent
                },
                success:function(data){
                    if(data['msg']=="noLogin") {
                        window.location.href = "/login";
                    } else {
                        window.location.href = "/publishSuccess";
                    }
                },
                error:function(){
                    console.log("文章插入失败！");
                }
            });
    }
});