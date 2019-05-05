String.prototype.render = function (context) {
    var tokenReg = /(\\)?\{([^\{\}\\]+)(\\)?\}/g;

    return this.replace(tokenReg, function (word, slash1, token, slash2) {
        if (slash1 || slash2) {
            return word.replace('\\', '');
        }

        var variables = token.replace(/\s/g, '').split('.');
        var currentObject = context;
        var i, length, variable;

        for (i = 0, length = variables.length; i < length; ++i) {
            variable = variables[i];
            currentObject = currentObject[variable];
            if (currentObject === undefined || currentObject === null) return '';
        }
        return currentObject;
    });
};

var re = /x/;
console.log(re);
re.toString = function() {
    showMessage('哈哈，你打开了控制台，是想要看看我的秘密吗？', 5000, true);
    $(".pio-dialog").addClass("active");
    return '';
};

$(document).on('copy', function (){
    showMessage('你都复制了些什么呀，转载要记得加上出处哦', 5000, true);
    $(".pio-dialog").addClass("active");
});

function initTips() {
    $.ajax({
        cache: true,
        url: "../static/live2d/message.json",
        dataType: "json",
        success: function (result) {
            $.each(result.mouseover, function (index, tips) {
                $(document).on("mouseover", tips.selector, function () {
                    var text = tips.text;
                    if (Array.isArray(tips.text)) text = tips.text[Math.floor(Math.random() * tips.text.length + 1) - 1];
                    text = text.render({text: $(this).text()});
                    showMessage(text, 3000);
                    $(".pio-dialog").addClass("active");

                });
            });
            $.each(result.seasons, function (index, tips) {
                var now = new Date();
                var after = tips.date.split('-')[0];
                var before = tips.date.split('-')[1] || after;

                if ((after.split('/')[0] <= now.getMonth() + 1 && now.getMonth() + 1 <= before.split('/')[0]) &&
                    (after.split('/')[1] <= now.getDate() && now.getDate() <= before.split('/')[1])) {
                    var text = tips.text;
                    if (Array.isArray(tips.text)) text = tips.text[Math.floor(Math.random() * tips.text.length + 1) - 1];
                    text = text.render({year: now.getFullYear()});
                    showMessage(text, 6000, true);
                    $(".pio-dialog").addClass("active");
                }
            });
        }
    });
}
initTips();

(function (){
    var text;
    var referrer = document.createElement('a');
    if(document.referrer !== ''){
        referrer.href = document.referrer;
    }

    if(referrer.href !== '' && referrer.hostname != 'seaguller.cn'){
        var referrer = document.createElement('a');
        referrer.href = document.referrer;
        text = 'Hello! 来自 <span style="color:#0099cc;">' + referrer.hostname + '</span> 的朋友';
        var domain = referrer.hostname.split('.')[1];
        if (domain == 'baidu') {
            text = 'Hello! 来自 百度搜索 的朋友<br>你是搜索 <span style="color:#0099cc;">' + referrer.search.split('&wd=')[1].split('&')[0] + '</span> 找到的我吗？';
        }else if (domain == 'so') {
            text = 'Hello! 来自 360搜索 的朋友<br>你是搜索 <span style="color:#0099cc;">' + referrer.search.split('&q=')[1].split('&')[0] + '</span> 找到的我吗？';
        }else if (domain == 'google') {
            text = 'Hello! 来自 谷歌搜索 的朋友<br>欢迎阅读<span style="color:#0099cc;">『' + document.title.split(' - ')[0] + '』</span>';
        }
    }else {
        if (window.location.href == 'https://www.seaguller.cn/') { //如果是主页
            var now = (new Date()).getHours();
            if (now > 23 || now <= 5) {
                text = '你是夜猫子呀？这么晚还不睡觉，明天起的来嘛';
            } else if (now > 5 && now <= 7) {
                text = '早上好！一日之计在于晨，美好的一天就要开始了';
            } else if (now > 7 && now <= 11) {
                text = '上午好！工作顺利嘛，不要久坐，多起来走动走动哦！';
            } else if (now > 11 && now <= 14) {
                text = '中午了，工作了一个上午，现在是午餐时间！';
            } else if (now > 14 && now <= 17) {
                text = '午后很容易犯困呢，今天的运动目标完成了吗？';
            } else if (now > 17 && now <= 19) {
                text = '傍晚了！窗外夕阳的景色很美丽呢，最美不过夕阳红~';
            } else if (now > 19 && now <= 21) {
                text = '晚上好，今天过得怎么样？';
            } else if (now > 21 && now <= 23) {
                text = '已经这么晚了呀，早点休息吧，晚安~';
            } else {
                text = '嗨~ 快来逗我玩吧！';
            }
        }else {
            text = '欢迎来到<span style="color:#0099cc;">「 ' + 'Seaguller的小窝做客！' + ' 」</span>';
        }
    }
    showMessage(text, 6000);
    $(".pio-dialog").addClass("active");
})();

window.hitokotoTimer = window.setInterval(showHitokoto,12000);

function showHitokoto() {
    $.getJSON("https://v1.hitokoto.cn/", function (result) {
        showMessage(result.hitokoto, 5000);
        $(".pio-dialog").addClass("active");
    });
}
function showMessage(text, timeout, flag){
    if(flag || sessionStorage.getItem('pio-text') === '' || sessionStorage.getItem('pio-text') === null){
        if(Array.isArray(text)) text = text[Math.floor(Math.random() * text.length + 1)-1];
        //console.log(text);
        if(flag) sessionStorage.setItem('pio-text', text);
        $('.pio-dialog').stop();
        $('.pio-dialog').html(text).fadeTo(200, 1);
        if (timeout === null) timeout = 5000;
        hideMessage(timeout);
    }
}
function hideMessage(timeout){
    $('.pio-dialog').stop().css('opacity',1);
    if (timeout === null) timeout = 5000;
    window.setTimeout(function() {sessionStorage.removeItem('pio-text')}, timeout);
    $('.pio-dialog').delay(timeout).fadeTo(200, 0);
}
function initLive2d() {
    $('.hide-button').fadeOut(0).on('click', () => {
        $('#landlord').css('display', 'none')
    })
    $('#landlord').hover(() => {
        $('.hide-button').fadeIn(600)
    }, () => {
        $('.hide-button').fadeOut(600)
    })
}

initLive2d();