$(function (){	
	//获取下拉科室的所有下拉框
	var ctlocCombox=[$CommonUI.getComboBox('#comboCtloc'),$CommonUI.getComboBox('#ctlocDesDr')];
	for(var i=0;i<ctlocCombox.length;i++){
		ctlocCombox[i].combobox({
			url:$WEB_ROOT_PATH+'/hop/hopCtlocCtrl!getCtlocList.htm',
			valueField:'hopCtlocId',							
			textField:'name'
		});
	}
	
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/hop/hopCtlocDestinationCtrl!listInfo.htm',	   
	    method:'post',
	    fit:true,
	    columns:[[ 
            {field:'ck1',checkbox:true},  
	        {field:'hopCtlocDestinationId',title:'表ID',width:100}, 
	        {field:'desCtlocDr',title:'科室表ID', hidden:true},
	        {field:'desCtlocName',title:'科室描述',width:100},
	        {field:'destination',title:'收货地点',width:100},  
	        {field:'desContact',title:'联系人',width:100}, 
	        {field:'desTel',title:'联系电话',width:100},  
	    ]]	 
	});
	//新增或更新成功的回调函数
	function succ(data){
		var demoId=null;	
		demoId=$('#ctlocDesDestail input[name="dto.hopCtlocDestination.hopCtlocDestinationId"]').val();	
		if(demoId){
			$CommonUI.alert("更新信息成功");
		}else{
			$CommonUI.alert("新增信息成功");
		}
		$("#datagrid").datagrid('reload');		
		$("#detailWin").dialog('close');
	}
	
	//新增或更新失败的回调函数
	function err(xhr,textStatus,errorThrown){
		$CommonUI.alert("执行失败");	
	}	
	
	// hopCtlocDestination表新增和修改
	$("#saveOrUpdateCtlocDesBtn").on('click', function() {
		$("#saveOrUpdateCtlocDesBtn").hide();
		postReq($WEB_ROOT_PATH+'/hop/hopCtlocDestinationCtrl!save.htm',"#ctlocDesDestail",succ,err,{skipHidden:false});
	});
		
	// 点击查询的提交按钮,查询hopCtlocDestination数据信息
	$("#searchCtlocDesInfoBt").on('click', function() {
		 var json=$CommonUI.loopBlock('#searchCtlocDesWin');
		 $("#datagrid").datagrid('load', json);
		 $CommonUI.getDialog('#searchCtlocDesWin').dialog('close');
	});
		
	
	$('#title').css({width:500,height:60});
	
	$('#title1').css({width:500,height:60});
});


//增加一条记录
function addClick() {

	$CommonUI.getDialog("#detailWin").dialog("setTitle","新增收货信息");
	$CommonUI.getDialog("#detailWin").dialog("center");
	$CommonUI.getDialog("#detailWin").dialog("open");
	$CommonUI.getForm('#ctlocDesDestail').form('clear');
	$("#saveOrUpdateCtlocDesBtn").show();	
}

//編輯一行记录
function editRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个修改');
		return;
	}
	$("#saveOrUpdateCtlocDesBtn").show();	
	$CommonUI.getForm('#ctlocdetail').form('clear');
	var row =$("#datagrid").datagrid('getSelected');
	var Id=row.hopCtlocDestinationId;
	$("#detailWin").dialog("open");
	$CommonUI.getDialog("#detailWin").dialog("setTitle","修改收货信息");
	var url = $WEB_ROOT_PATH+'/hop/hopCtlocDestinationCtrl.htm?BLHMI=findById&dto.hopCtlocDestination.hopCtlocDestinationId='+Id;
	$.getJSON(url, function(data){
		$CommonUI.fillBlock('#detailWin',data);
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
		$.post($WEB_ROOT_PATH+"/hop/hopCtlocDestinationCtrl!delete.htm", {'dto.hopCtlocDestination.hopCtlocDestinationId':row.hopCtlocDestinationId},function(){
			$CommonUI.getDataGrid("#datagrid").datagrid('reload');
		});
		
	});
}

//条件查询提交事件
function selectClick() {
	$CommonUI.getDialog("#searchCtlocDesWin").dialog("center");
	$CommonUI.getDialog("#searchCtlocDesWin").dialog("open");
	$("#title1").val('');
	
}

//取消按钮
function cancelClick() {
	$CommonUI.getWindow("#detailWin").dialog("close");
}