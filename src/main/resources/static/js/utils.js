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


$(".indexTable li").click(function(){
    var indexs=$(this).index();
    $(".indexTable li").removeClass("on").eq(indexs).addClass("on");
    $(".indexTable_con").hide().eq(indexs).show();
});


