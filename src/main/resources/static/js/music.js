var scroll_offset = $("#turn-head").offset(); //得到pos这个div层的offset，包含两个值，top和left
$("body,html").animate({
    scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
},0);

const ap = new APlayer({
    container: document.getElementById('aplayer'),
    autoplay: true,
    theme: '#b7daff',
    mutex: true,
    listMaxHeight: 30,
    audio: [
        {
            name: 'Lemon',
            artist: '米津玄师',
            url: 'http://music.163.com/song/media/outer/url?id=536622304.mp3',
            cover: 'http://seaguller.oss-cn-beijing.aliyuncs.com/static/music/Lemon.png'
        },
        {
            name: 'ヒッチコック',
            artist: 'ヨルシカ',
            url: 'http://music.163.com/song/media/outer/url?id=557581315.mp3',
            cover: 'http://seaguller.oss-cn-beijing.aliyuncs.com/static/music/%E3%83%92%E3%83%83%E3%83%81%E3%82%B3%E3%83%83%E3%82%AF.png'
        },
        {
            name: '曾经我也想过一了百了',
            artist: '中岛美嘉',
            url: 'http://music.163.com/song/media/outer/url?id=26830207.mp3',
            cover: 'http://seaguller.oss-cn-beijing.aliyuncs.com/static/music/%E6%9B%BE%E7%BB%8F%E6%88%91%E4%B9%9F%E6%83%B3%E8%BF%87%E4%B8%80%E4%BA%86%E7%99%BE%E4%BA%86.jpg'
        },
        {
            name: '打上花火',
            artist: '米津玄师',
            url: 'http://music.163.com/song/media/outer/url?id=496869422.mp3',
            cover: 'http://seaguller.oss-cn-beijing.aliyuncs.com/static/music/%E6%89%93%E4%B8%8A%E8%8A%B1%E7%81%AB.png'
        },
        {
            name: 'PLANET',
            artist: 'ラムジ',
            url: 'http://music.163.com/song/media/outer/url?id=812400.mp3',
            cover: 'http://seaguller.oss-cn-beijing.aliyuncs.com/static/music/PLANET.jpg'
        },
        {
            name: '她曾活过啊',
            artist: 'あいみょん',
            url: 'http://music.163.com/song/media/outer/url?id=443875380.mp3',
            cover: 'http://seaguller.oss-cn-beijing.aliyuncs.com/static/music/%E5%A5%B9%E6%9B%BE%E6%B4%BB%E8%BF%87%E5%95%8A.jpg'
        },
    ]
});

function play(num) {
    ap.list.switch(num-1);

    /* 滚动条跳转到指定曲目 */
    var ind;
    if(num==1) {
        ind = 0;
    } else if(num==2) {
        ind = 32;
    } else if(num>=3) {
        ind = 64;
    }
    $(".aplayer-list").animate({
        scrollTop: ind
    },0);
}