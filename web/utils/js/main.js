(function () {
    var app = new Vue({
        el: '#app',
        data: {
            input1: 'Hello Vue!',
            output1: 'Hello Vue!',
            pageReady: false,
            separatorOptions: {
                row: '按行分隔',
                space: '按空格分隔',
                custom: '自定义',
            },
            strForm: {
                customSeparator: ',',
                separator: null,
                includes: '","',
                out: {
                    separator: null,
                    customSeparator: '',
                    includes: '","',
                }
            }
        },
        mounted: function () {
            var that = this;
            setTimeout(() => {
                that.pageReady = true;
            }, 1000);
        },
        methods: {
            formatInput1: function () {
                var s = this.input1;
                var sArr = [];
                var splitSeparator = '';
                var joinSeparator = '';
                var strForm = this.strForm;
                var separator = strForm.separator;
                var outSeparator = strForm.out.separator;
                var inIncludeArr = strForm.includes.split(',');
                var outIncludeArr = strForm.out.includes.split(',');

                if (outSeparator) {
                    switch (outSeparator) {
                        case 'row': joinSeparator = '\n'; break;
                        case 'space': joinSeparator = ' '; break;
                        case 'custom': joinSeparator = strForm.out.customSeparator; break;
                    }
                }

                if (separator) {
                    switch (strForm.separator) {
                        case 'row': splitSeparator = '\n'; break;
                        case 'space': splitSeparator = ' '; break;
                        case 'custom': splitSeparator = strForm.customSeparator; break;
                    }

                    this.input1.split(splitSeparator).forEach(function (item) {
                        if(inIncludeArr.length > 1){
                            sArr.push(item.replace(inIncludeArr[0], outIncludeArr[0]).replace(inIncludeArr[1], outIncludeArr[1]));
                        }else{
                            sArr.push(outIncludeArr[0] + item + outIncludeArr[1]);
                        }
                    });

                    if (sArr.length > 1 && sArr[sArr.length - 1].replace(new RegExp('^' + outIncludeArr[0] + '(.*)' + outIncludeArr[1] + '$', 'ig'), '$1').length < 1) {
                        console.log(sArr[sArr.length - 1].replace(new RegExp('^' + outIncludeArr[0] + '(.*)' + outIncludeArr[1] + '$', 'ig'), '$1'));
                        sArr.pop();
                    }
                    s = sArr.join(joinSeparator);
                }
                return s;
            }
        }
    })
})();