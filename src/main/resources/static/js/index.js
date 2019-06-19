var vm = new Vue({
    el:"#app",
    data:{
        loadString:has_more,
        page_index:0,
        articles:[]
    },
    created:function(){
        this.loadArticle();
    },
    methods:{
        loadArticle:function () {
            let _this = this;
            if(_this.page_index >= 0){
                let tag_key = $("#tag-span").html()
                let url = "/article/"+_this.page_index;
                if(tag_key){
                    url += "/"+tag_key.substring(1);
                }
                $.get(url, function (response) {
                    console.log(response)
                    if(response.data.next){
                        _this.page_index += 1;
                    }else{
                        _this.page_index = -1;
                        _this.loadString = has_no_more;
                    }
                    _this.articles = _this.articles.concat(response.data.data)

                })
            }
        }
    }
});

$(window).scroll(
    function() {
        if(vm.loadString === has_no_more){
            return;
        }
        var scrollTop = $(this).scrollTop();
        var scrollHeight = $(document).height();
        var windowHeight = $(this).height();
        if (scrollTop + windowHeight === scrollHeight) {
            // 此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
            vm.loadArticle();
        }
    }
);