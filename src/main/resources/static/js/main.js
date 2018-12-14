 /* 导航下滚消失上滚出现 */
(function() {
    new Headroom(document.querySelector("header.am-topbar.am-topbar-inverse.am-topbar-fixed-top"), {
        "tolerance": 5,
        "offset": 600,
        "classes": {
            "initial": "animated",
            "pinned": "slideDown",
            "unpinned": "slideUp"
        }
    }).init();
}());

/* 头像当鼠标经过时转动 */
var shine = new Shine(document.getElementById('headline'));

function handleMouseMove(event) {
    shine.light.position.x = event.clientX;
    shine.light.position.y = event.clientY;
    shine.draw();
}

window.addEventListener('mousemove', handleMouseMove, false);