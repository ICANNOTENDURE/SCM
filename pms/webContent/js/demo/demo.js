$(function (){
	//gird数据加载
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/demo/demoCtrl!list.htm',
	    method:'post',
	    fit:true,
	    columns:[[ 
            {field:'ck1',checkbox:true},  
	        {field:'demoName',title:'姓名',width:100},  
	        {field:'demoGender',title:'性别',width:100},  
	        {field:'demoAge',title:'年龄',width:100},  
	    ]] 
	});
	
	//新增或更新成功的回调函数
	function succ(data){
		var demoId=$('#detail input[name="dto.demo.demoId"]').val();	
		if(demoId){
			$CommonUI.alert("更新demo信息成功");
		}else{
			$CommonUI.alert("新增demo信息成功");
		}
		 $("#datagrid").datagrid('reload');
		 $("#detailWin").dialog('close');
	}
	
	//新增或更新失败的回调函数
	function err(xhr,textStatus,errorThrown){
		$CommonUI.alert("更新demo信息失败");	
	}
	
	// 新增和修改
	$("#submitBtn").on('click', function() {
		postReq($WEB_ROOT_PATH+'/demo/demoCtrl!save.htm',"#detail",succ,err,{skipHidden:false});
	});
	
	
	// 点击查询的提交按钮
	$("#selectBt").on('click', function() {
		 var json=$CommonUI.loopBlock('#selectWin');
		 $("#datagrid").datagrid('load', json);
		 $CommonUI.getDialog('#selectWin').dialog('close');
	});
	
	
	
	
	$('#title').css({width:500,height:60});
	
	$('#title1').css({width:500,height:60});
});

// 增加
function addClick() {
	$CommonUI.getDialog("#detailWin").dialog("setTitle","增加demo");
	$CommonUI.getDialog("#detailWin").dialog("center");
	$CommonUI.getDialog("#detailWin").dialog("open");
	$CommonUI.getForm('#detail').form('clear');
}

//刪除記錄
function delRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个删除');
		return;
	}
	$CommonUI.confirm('确定删除吗？', 'question', 0, function(){
		var row = $CommonUI.getDataGrid("#datagrid").datagrid('getSelected');
		$.post($WEB_ROOT_PATH+"/demo/demoCtrl!delete.htm", {'dto.demo.demoId':row.demoId},function(){
			$CommonUI.getDataGrid("#datagrid").datagrid('reload');
		} );
	});
}

// 編輯一行记录
function editRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个修改');
		return;
	}
	$CommonUI.getForm('#detail').form('clear');
	
	var row =$("#datagrid").datagrid('getSelected');
	var demoId=row.demoId;
	var url = $WEB_ROOT_PATH+'/demo/demoCtrl.htm?BLHMI=findById&dto.demo.demoId='+demoId;
	$("#detailWin").dialog("open");
	$CommonUI.getDialog("#detailWin").dialog("setTitle","修改demo");
	$.getJSON(url, function(data){
		$CommonUI.fillBlock('#detailWin',data);
	});
}

// 取消按钮
function cancelClick() {
	$CommonUI.getWindow("#detailWin").dialog("close");
}

// 条件查询提交事件
function selectClick() {
	$CommonUI.getDialog("#selectWin").dialog("center");
	$CommonUI.getDialog("#selectWin").dialog("open");
	$("#title1").val('');
}

// 条件查询取消事件
function selectCanBtClick() {
	$CommonUI.getWindow("#selectWin").dialog("close");
}