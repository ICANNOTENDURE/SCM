$(function (){	
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/hop/hopIncCtrl!listInfo.htm',	   
	    method:'post',
	    fit:true,
	    
	    columns:[[ 
            {field:'ck1',checkbox:true},
            {field:'incid',title:'表ID',hidden:true},
	        {field:'inccode',title:'药品代码',width:100},  
	        {field:'incname',title:'药品描述',width:100},
	        {field:'incuomcode',title:'单位代码',width:80}, 
	        {field:'incuomname',title:'单位描述',width:80}, 
	        {field:'incbuomcode',title:'小单位代码',width:100}, 
	        {field:'incbuomname',title:'小单位描述',width:100},
	        {field:'incfac',title:'系数',width:100},
	        {field:'incrp',title:'进价',width:100},
	        {field:'incmanfid',title:'厂商ID',hidden:true},
	        {field:'manfname',title:'厂商',width:100},
	        {field:'inchospid',title:'医院ID',hidden:true},
	        {field:'hospitalname',title:'医院',width:100},
	        {field:'inchissysid',title:'his药品ID',width:100},
	        {field:'hopincpicid',title:'药品图片表id',hidden:true},
	        {field:'incpicpath',title:'图片路径',width:140},
	        {field:'incpicseq',title:'图片顺序',width:100},
	    ]]	 
	});
	//新增或更新成功的回调函数
	function succ(data){
		var demoId=null;		
		demoId=$('#incdetail input[name="dto.hopInc.incId"]').val();		
		if(demoId){
			$CommonUI.alert("更新信息成功");
		}else{
			$CommonUI.alert("新增信息成功");
		}
		$("#datagrid").datagrid('reload');
		$("#drugInfoWin").dialog('close');		
	}
	
	//新增或更新失败的回调函数
	function err(xhr,textStatus,errorThrown){
		$CommonUI.alert("执行失败");	
	}
	
	// hopInc表新增和修改
	$("#saveOrUpdateIncBtn").on('click', function() {
		$("#saveOrUpdateIncBtn").hide();
		postReq($WEB_ROOT_PATH+'/hop/hopIncCtrl!save.htm',"#incdetail",succ,err,{skipHidden:false});
	});
	
	
	// 点击查询的提交按钮,查询hopInc数据信息
	$("#searchIncInfoBtn").on('click', function() {
		 var json=$CommonUI.loopBlock('#searchIncWin');
		 $("#datagrid").datagrid('load', json);
		 $CommonUI.getDialog('#searchIncWin').dialog('close');
	});
			
	
	//获取下拉医院的所有下拉框
	var hospCombox=[$CommonUI.getComboBox('#incHospid'),$CommonUI.getComboBox('#incHosps')];
	for(var i=0;i<hospCombox.length;i++){
		hospCombox[i].combobox({
			url:$WEB_ROOT_PATH+'/hop/hospitalCtrl!getHospInfo.htm',
			valueField:'hospitalId',							
			textField:'hospitalName'
		});
	}
	
	//获取下拉厂商的所有下拉框
	var manfCombox=[$CommonUI.getComboBox('#incManfid'),$CommonUI.getComboBox('#incManfs')];
	for(var i=0;i<manfCombox.length;i++){
		manfCombox[i].combobox({
			url:$WEB_ROOT_PATH+'/manf/hopManfCtrl!getManfInfo.htm',
			valueField:'hopManfId',							
			textField:'manfName'
		});
	}
	
		
	$('#title').css({width:500,height:60});
	
	$('#title1').css({width:500,height:60});
});


//增加一条记录
function addClick() {	
	$CommonUI.getDialog("#drugInfoWin").dialog("setTitle","新增药品信息");
	$CommonUI.getDialog("#drugInfoWin").dialog("center");
	$CommonUI.getDialog("#drugInfoWin").dialog("open");
	$CommonUI.getForm('#incdetail').form('clear');
	$("#saveOrUpdateIncBtn").show();
	
}

//編輯一行记录
function editRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个修改');
		return;
	}
	$("#saveOrUpdateIncBtn").show();
	$CommonUI.getForm('#incdetail').form('clear');
	$CommonUI.getForm('#incpicdetail').form('clear');
	var row =$("#datagrid").datagrid('getSelected');
	
	var Id=row.incid;
	$("#drugInfoWin").dialog("open");
	$CommonUI.getDialog("#drugInfoWin").dialog("setTitle","修改药品信息数据");
	var url = $WEB_ROOT_PATH+'/hop/hopIncCtrl.htm?BLHMI=findById&dto.hopInc.incId='+Id;
	$.getJSON(url, function(data){
		$CommonUI.fillBlock('#drugInfoWin',data);
	});
	
}

//刪除記錄
function delRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个删除');
		return;
	}
	$CommonUI.confirm('确定删除吗？', 'question', 0, function(){		
		var row = $CommonUI.getDataGrid("#datagrid").datagrid('getSelected');		
		$.post($WEB_ROOT_PATH+"/hop/hopIncCtrl!delete.htm", {'dto.hopInc.incId':row.incid},function(){
			$CommonUI.getDataGrid("#datagrid").datagrid('reload');
		});
				
	});
}

//条件查询提交事件
function selectClick() {	
	$CommonUI.getDialog("#searchIncWin").dialog("center");
	$CommonUI.getDialog("#searchIncWin").dialog("open");
	$("#title1").val('');
	
}

//取消按钮
function cancelClick() {
	$CommonUI.getWindow("#drugInfoWin").dialog("close");	
}