$(function(){
    //定义鼠标按下事件
    $("div").mousedown(function(){
        qqqq();
    });
});

function qqqq() {
    realTimeClData = setInterval(realTimeCl, 400);
    function realTimeCl() {
        console.log("等待400毫秒");
        clearInterval(realTimeClData);
    }

}


