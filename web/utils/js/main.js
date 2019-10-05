(function(){
    var app = new Vue({
        el: '#app',
        data: {
            input1: 'Hello Vue!',
            output1: 'Hello Vue!',
            pageReady: false
        },
        mounted: function () {
            var that = this;
            setTimeout(() => {
                that.pageReady = true;
            }, 1000);
        },
        methods: {
            formatInput1: function(){
                var s = '';
                console.log(this.input1.split('\n'));
                var sArr = [];
                this.input1.split('\n').forEach(function(item){
                    if(item.length > 0){
                        sArr.push('"' + item + '"');
                    }
                });
                return sArr.join(',');
            }
        }
    })
})();