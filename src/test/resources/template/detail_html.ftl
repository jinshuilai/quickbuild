<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理后台</title>
  <link rel="stylesheet" href="../webjarslocator/layui/css/layui.css">
</head>
<body>
<div style="width:80%;margin-top:10px">
<form class="layui-form" action="">
	<input type="hidden" name="seqid" />
<#list details as dt>
  <div class="layui-form-item">
    <label class="layui-form-label">${dt.label}</label>
    <div class="layui-input-block">
      <input type="text" name="${dt.name}" readonly autocomplete="off" class="layui-input">
    </div>
  </div>
</#list>  
</form>
</div>
<script src="../webjarslocator/jquery/jquery.js"></script>
<script src="../webjarslocator/layui/layui.js"></script>
<script>
var id = getQueryString('id');
if(id){
	$.post('/${model}/find', "id="+id, function(info){
		if(info.code == 200){
			var model = info.data;
			//console.log(model)
			<#list details as dt>
			$('input[name="${dt.name}"]').val(model.${dt.name});
			</#list>
	
		}else{
			layer.alert(info.message);
		} 
	  });
}

function getQueryString(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
   var r = window.location.search.substr(1).match(reg);
   if (r!=null) return (r[2]); return null;
}
function close(){
	//当你在iframe页面关闭自身时
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}
</script>
</body>
</html>