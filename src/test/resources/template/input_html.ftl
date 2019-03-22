<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理后台</title>
  <link rel="stylesheet" href="../../webjarslocator/layui/css/layui.css">
</head>
<body>
<div style="width:80%;margin-top:10px">
<form class="layui-form" action="">
	<input type="hidden" name="seqid" value="0" />
<#list inputs as inp>	
  <div class="layui-form-item">
    <label class="layui-form-label">${inp.label}</label>
    <div class="layui-input-block">
      <input type="text" name="${inp.name}" <#if inp.must>required lay-verify="required"</#if> 
       placeholder="${inp.tips}" autocomplete="off" class="layui-input">
    </div>
  </div>
 </#list> 
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="${model}_submit">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</div>
<script src="../../webjarslocator/jquery/jquery.js"></script>
<script src="../../webjarslocator/layui/layui.js"></script>
<script>
layui.use('form', function(){
	  var form = layui.form;
	  
	  //监听提交
	  form.on('submit(${model}_submit)', function(data){
		  $.post('../../api/${model}/save', data.field, function(info){
				if(info.code == 200){
					layer.msg("保存成功");
	    			close();
				}else{
	  				layer.alert(info.message);
	  			} 
	  	  });
	    
	    return false;
	  });
});

var id = getQueryString('id');
if(id){
	$.post('../../api/${model}/find', "id="+id, function(info){
		if(info.code == 200){
			var model = info.data;
			$('input[name="seqid"]').val(model.seqid);
			<#list inputs as inp>
			$('input[name="${inp.name}"]').val(model.${inp.name});
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