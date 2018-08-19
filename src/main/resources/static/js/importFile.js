
//全局变量URL
var baseURL = "http://localhost:8080/admin/"

//导出文件
function inFile(){
    //文件验证
    var f = $("#file").val();
    var fName = $("#text").val();
    var suffix = fName.substring(fName.lastIndexOf(".") + 1)
    if(!f) return alert("上传文件不能为空")
    if(suffix != 'txt') return alert("请上传txt文件")


    var form = new FormData($("#formData")[0]);
    $.ajax({
        url: baseURL + "orderSysReport/import",
        type: "post",
        data: form,
        processData: false,
        contentType: false,
        success: function(data){
            if(data.code == 200){
                alert("上传成功")
            }else{
                alert(data.msg)
            }
        }
    })
}

//下载文件
function downFile(){
    var startTime = '2017-11-25';
    var endTime = '2018-02-17';
    location.href = baseURL + "orderSysReport/download?startTime=" + startTime + "&endTime=" + endTime;
}

//显示文件名
$('#file').change(function(){
    $("#text").val($("#file").val());
})
