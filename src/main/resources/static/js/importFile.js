

/*
function getName(){
    $("input[type=file]").onclick(function(){
        $("#text").val($("#file").val());
    })
}
*/


function inFile(){
    var form = new FormData(document.getElementById("formData"));
    $.ajax({
        url: "http://localhost:8080/admin/orderSysReport/importFile",
        type: "post",
        data: form,
        processData: false,
        contentType: false,
        success: function(data){
            if(data.code = 200){
                alert("上传成功")
            }else{
                alert(data.msg)
            }
        }

    })
}