$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

function formatTime(date,format) {

    var formatArr  = ['Y','M','D','h','m','s'];
    var returnArr   = [];

    returnArr.push(date.getFullYear());
    returnArr.push(formatNumber(date.getMonth() + 1));
    returnArr.push(formatNumber(date.getDate()));

    returnArr.push(formatNumber(date.getHours()));
    returnArr.push(formatNumber(date.getMinutes()));
    returnArr.push(formatNumber(date.getSeconds()));

    for (var i in returnArr)
    {
        format = format.replace(formatArr[i], returnArr[i]);
    }
    return format;
}

Window.prototype.getNowFormatDate = function(time) {
    var date = new Date(time); // 增加8小时
    return formatTime(date, 'Y-M-D h:m:s')
}

Window.prototype.parseMd = function (md) {
    var parse_md = document.createElement("div")
    parse_md.id = "md"+new Date().getTime();
    parse_md.style = "display: none;";
    document.body.appendChild(parse_md);
    var testEditor = editormd.markdownToHTML( parse_md.id, {
        markdown:md,
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : false,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析
    });
    var html = testEditor.html();
    document.body.removeChild(parse_md)
    return html;
}

var has_more = "加载更多...";
var has_no_more = "没有了!!!!";