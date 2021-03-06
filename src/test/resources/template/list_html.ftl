<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理后台</title>
  <link rel="stylesheet" href="../../webjarslocator/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">ADMIN</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">所有商品</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
<div class="layadmin-tabsbody-item layui-show">
<div class="layui-card layadmin-header">
 </div>
 <div class="layui-fluid">
 	<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="height:auto;display:none" id="${model}_search" lay-filter="${model}_search">
      <div class="layui-form-item">
      
       <#list searchs as sc>
        <div class="layui-inline">
          <label class="layui-form-label">${sc.label}</label>
          <div class="layui-input-block">
            <input type="text" name="${sc.name}" placeholder="${sc.tips}" autocomplete="off" class="layui-input">
          </div>
        </div>
        </#list>
        
        <div class="layui-inline">
          <button class="layui-btn layuiadmin-btn-useradmin" lay-submit="" lay-filter="${model}_search_submit">
            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
          </button>
        </div>
      </div>
    </div>
    
    <div class="layui-card-body">
      <div class="layui-btn-container">
		  <button id="${model}_add" class="layui-btn">添加</button> 
		  <button id="${model}_update" class="layui-btn  layui-btn-normal">更新</button> 
		  <button id="${model}_delete" class="layui-btn  layui-btn-danger">删除</button> 
		  <button id="${model}_detail" class="layui-btn ">详情</button> 
		  <button id="${model}_show" class="layui-btn ">展开查询</button>
	</div>
      
      <table id="${model}_table" lay-filter="${model}_table"></table>
      
    </div>
</div>
    
  </div>
  
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="../../webjarslocator/jquery/jquery.js"></script>
<script src="../../webjarslocator/layui/layui.js"></script>
<script>
layui.use(['element', 'form', 'table', 'layer','laydate'], function(){
  var element = layui.element
  ,table = layui.table
  ,form = layui.form
  ,layer = layui.layer
  ,laydate = layui.laydate;
  
  table.render({
    elem: '#${model}_table'
    ,height: 430
    ,url: '../../api/${model}/list' //数据接口
    ,method: 'post'
    ,request: {
    	pageName: 'start'
    	,limitName: 'size'
    }
    ,page: true //开启分页
    ,toolbar: false
    ,defaultToolbar: ['filter', 'print', 'exports']
    ,limit: 20
    ,limits: [20,50,100,500,1000]
    //,title: '${model}'
    ,cols: [[ //表头
      {field: 'seqid', title: 'ID',type:'checkbox', width:80, sort: true, fixed: 'left'}
     <#list lists as clo>
      <#if clo.type = "date">
      ,{field: '${clo.name}', title: '${clo.label}', templet: function(d){
         return new Date(d.${clo.name}).toLocaleString()
       }
       }
      <#else>
      ,{field: '${clo.name}', title: '${clo.label}'}
      </#if>
     </#list>
    ]]
    ,parseData: function(res){
    	return {
    		"code": res.code == 200 ? 0 : res.code,
    		"msg": res.message,
    		"count": res.data.totalElements,
    		"data": res.data.content
    	}
    }
  	,done: function(res, curr, count){
  	    //如果是异步请求数据方式，res即为你接口返回的信息。
  	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
  	    //console.log(res);
  	    
  	    //得到当前页码
  	    //console.log(curr); 
  	    
  	    //得到数据总量
  	    //console.log(count);
  	  }
  });
  
  form.render(null, '${model}_search');
  
   //监听搜索
  form.on('submit(${model}_search_submit)', function(data){
    var field = data.field;
    //console.log(field)
    //执行重载
    table.reload('${model}_table', {
      where: field
    });
  });

  //事件
  $("#${model}_show").click(function(){
	  $("#${model}_search").toggle();
  });
  
  $("#${model}_add").click(function(){
	  layer.open({
		  title: '添加操作'
		  ,type: 2
		  ,content: '${model}_input.html'
		  ,area: ['600px', '400px']
	  	  ,end: function(){
	  		table.reload('${model}_table');
	  	  }
	  });
  });
  
  $("#${model}_update").click(function(){
	  var checkStatus = table.checkStatus('${model}_table')
      ,checkData = checkStatus.data; //得到选中的数据
	  if(checkData.length === 0){
        return layer.msg('请选择数据');
      }
      var id = checkData[0].seqid;
      
	  layer.open({
		  title: '更新操作'
		  ,type: 2
		  ,content: '${model}_input.html?id='+id
		  ,area: ['600px', '400px']
	  	  ,end: function(){
	  		table.reload('${model}_table');
	  	  }
	  });
  });
  
  $("#${model}_delete").click(function(){
	  var checkStatus = table.checkStatus('${model}_table')
      ,checkData = checkStatus.data; //得到选中的数据
	  if(checkData.length === 0){
        return layer.msg('请选择数据');
      }
      var ids = '';
      checkData.forEach(function(currentValue, index, arr){
    	  ids += currentValue.seqid + ","
      });
      ids = ids.substring(0,ids.length-1)
	  layer.confirm('确定删除吗？', function(index) {
         
		  $.post('../../api/${model}/deletes', "ids="+ids, function(info){
			 if(info.code == 200){
		         table.reload('${model}_table');
		         layer.msg('已删除');
			 }else{
				 layer.msg(info.message);
			 }
		  });
       });  
  });
  
  $("#${model}_detail").click(function(){
	  var checkStatus = table.checkStatus('${model}_table')
      ,checkData = checkStatus.data; //得到选中的数据
	  if(checkData.length === 0){
        return layer.msg('请选择数据');
      }
      var id = checkData[0].seqid;
      
	  layer.open({
		  title: '查看详情'
		  ,type: 2
		  ,content: '${model}_detail.html?id='+id
		  ,area: ['600px', '400px']
	  });
  });

});
</script>
</body>
</html>