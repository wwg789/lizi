// 定义一个函数，用来读取特定的cookie值。
function getCookie(name) {
    var cookies = document.cookie;
    var list = cookies.split("; ");          // 解析出名/值对列表

    for(var i = 0; i < list.length; i++) {
        var arr = list[i].split("=");          // 解析出名和值
        if(arr[0] == name)
            return decodeURIComponent(arr[1]);   // 对cookie值解码
    }
    return "";
}

$(window).load(function(){
    var spotlight = {
        opacity : 0.2,
        imgWidth : $('.spotlightWrapper ul li').find('img').width(),
        imgHeight : $('.spotlightWrapper ul li').find('img').height()
    };
    $('.spotlightWrapper ul li').css({ 'width' : spotlight.imgWidth, 'height' : spotlight.imgHeight });
    $('.spotlightWrapper ul li').hover(function(){
        $(this).find('img').addClass('active').css({ 'opacity' : 1});
        $(this).siblings('li').find('img').css({'opacity' : spotlight.opacity}) ;
    }, function(){
        $(this).find('img').removeClass('active');
    });
    $('.spotlightWrapper ul').bind('mouseleave',function(){
        $(this).find('img').css('opacity', 1);
    });
});

//设置分页
function setPageInfo(data) {
    $('#box').paging({
        initPageNo: 1, // 初始页码
        totalPages: data.pages, //总页数
        totalCount: '合计' + data.count + '条数据', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        jump: true, //是否支持跳转
        callback: function(page) { // 回调函数
            thisPage = page;
        }
    })
}


