// zxx 2014-04-19
$(function (){
	
	//获取下拉厂商的所有下拉框
	var manfCombox=[$CommonUI.getComboBox('#venIncManfid'),$CommonUI.getComboBox('#venIncManfids')];
	for(var i=0;i<manfCombox.length;i++){
		manfCombox[i].combobox({
			url:$WEB_ROOT_PATH+'/manf/hopManfCtrl!getManfInfo.htm',
			valueField:'hopManfId',							
			textField:'manfName'
		});
	}
	
	//获取下拉医院的所有下拉框
	var venCombox=[$CommonUI.getComboBox('#venIncVenid'),$CommonUI.getComboBox('#venIncVenids')];
	for(var i=0;i<venCombox.length;i++){
		venCombox[i].combobox({
			url:$WEB_ROOT_PATH+'/ven/vendorCtrl!getVenCombox.htm?dto.vendor.name=',
			valueField:'vendorId',							
			textField:'name'
		});
	}
	
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listInfo.htm',
	    iconCls:'icon-edit',//图标
	    method:'post',
	    fit:true,
	    columns:[[ 
            {field:'ck1',checkbox:true},  
	        {field:'venincid',title:'表ID',hidden:true},  
	        {field:'veninccode',title:'药品代码',width:100},
	        {field:'venincname',title:'药品名称',width:100},
	        {field:'venincuomcode',title:'单位代码',width:80},
	        {field:'venincuomname',title:'单位代码',width:80},
	        {field:'venincbuomcode',title:'小单位代码',width:100},
	        {field:'venincbuomname',title:'小单位代码',width:100},
	        {field:'venincfac',title:'系数',width:100},
	        {field:'venincprice',title:'价格',width:100},
	        {field:'venincmanfid',title:'厂商Id',hidden:true},
	        {field:'manfname',title:'厂商',width:100},
	        {field:'venincvenid',title:'供应商Id',hidden:true},
	        {field:'name',title:'供应商',width:100},
	        {field:'venincvensysid',title:'系统ID',width:100},
	        {field:'venincpicid',title:'药品图片表id',hidden:true},
	        {field:'venincpicpath',title:'图片路径',width:140},
	        {field:'venincpicseq',title:'图片顺序',width:100},
	    ]]	 
	});
	
	//新增或更新成功的回调函数
	function succ(data){
		var Id=$('#incdetail input[name="dto.venInc.venIncId"]').val();	
		if(Id){
			$CommonUI.alert("更新成功");
		}else{
			$CommonUI.alert("增加成功");
		}
		 $("#datagrid").datagrid('reload');
		 $("#drugInfoWin").dialog('close');
	}
	
	//新增或更新失败的回调函数
	function err(xhr,textStatus,errorThrown){
		$CommonUI.alert("faile");	
	}
	
	// 新增和修改
	$("#saveOrUpdateIncBtn").on('click', function() {
		$("#saveOrUpdateIncBtn").hide();
		postReq($WEB_ROOT_PATH+'/ven/venIncCtrl!save.htm',"#incdetail",succ,err,{skipHidden:false});
	});
	
	
	// 点击查询的提交按钮
	$("#searchIncInfoBtn").on('click', function() {
		 var json=$CommonUI.loopBlock('#searchIncWin');
		 $("#datagrid").datagrid('load', json);
		 $CommonUI.getDialog('#searchIncWin').dialog('close');
	});
	
	
	$('#title').css({width:500,height:60});
	
	$('#title1').css({width:500,height:60});
});

//增加
function addClick() {
	$CommonUI.getDialog("#drugInfoWin").dialog("setTitle","增加药品信息");
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
	var row =$("#datagrid").datagrid('getSelected');
	var Id=row.venincid;
	var url = $WEB_ROOT_PATH+'/ven/venIncCtrl.htm?BLHMI=findById&dto.venInc.venIncId='+Id;
	$("#drugInfoWin").dialog("open");
	$CommonUI.getDialog("#drugInfoWin").dialog("setTitle","修改药品信息");
	$.getJSON(url, function(data){
		$CommonUI.fillBlock('#drugInfoWin',data);
	});
}

// 取消按钮
function cancelClick() {
	$CommonUI.getWindow("#drugInfoWin").dialog("close");
}

//刪除記錄
function delRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个删除');
		return;
	}
	$CommonUI.confirm('确定删除吗？', 'question', 0, function(){
		var row = $CommonUI.getDataGrid("#datagrid").datagrid('getSelected');
		$.post($WEB_ROOT_PATH+"/ven/venIncCtrl!delete.htm", {'dto.venInc.venincid':row.venIncId},function(){
			$CommonUI.getDataGrid("#datagrid").datagrid('reload');
		} );
	});
}

//条件查询提交事件
function selectClick() {
	$CommonUI.getDialog("#searchIncWin").dialog("center");
	$CommonUI.getDialog("#searchIncWin").dialog("open");
	$("#title1").val('');
}

// 条件查询取消事件
function selectCanBtClick() {
	$CommonUI.getWindow("#searchIncWin").dialog("close");
}
