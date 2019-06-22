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


