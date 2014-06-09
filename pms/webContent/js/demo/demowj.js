$(function (){
	//gird数据加载
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/demowj/demowjCtrl!list.htm',
	    method:'post',
	    fit:true,
	    columns:[[ 
            {field:'ck1',checkbox:true},  
	        {field:'demowjName',title:'姓名',width:10, sortable:true},  
//	        {field:'demowjId',title:'ID',width:20, sortable:true},  
	    ]] 
	});
	

	//新增或更新成功的回调函数
	function succ(data){
		/*var demoId=$('#detail input[name="dto.demo.demoId"]').val();*/	
		var demowjId=$('#detail input[name="dto.demowj.demowjId"]').val();	
		if(demowjId){
			$CommonUI.alert("更新demowj信息成功");
		}else{
			$CommonUI.alert("新增demowj信息成功");
		}
		 $("#datagrid").datagrid('reload');
		 $("#detailWin").dialog('close');
	}

	//新增或更新失败的回调函数
	function err(xhr,textStatus,errorThrown){
		$CommonUI.alert("更新demowj信息失败");	
	}

	// 新增或修改
	$("#submitBtn").on('click', function() {
		/*postReq($WEB_ROOT_PATH+'/demo/demoCtrl!save.htm',"#detail",succ,err,{skipHidden:false});*/
		postReq($WEB_ROOT_PATH+'/demowj/demowjCtrl!save.htm',"#detail",succ,err,{skipHidden:false});
	});

	// 点击查询的提交按钮
	$("#selectBt").on('click', function() {
		 var json = $CommonUI.loopBlock('#selectWin');
		 $("#datagrid").datagrid('load', json);
		 $CommonUI.getDialog('#selectWin').dialog('close');
	});
	
	$('#title').css({width:500,height:60});
	
});


//增加
function addClick() {
	$CommonUI.getDialog("#detailWin").dialog("center");
	$CommonUI.getDialog("#detailWin").dialog("open");
	$("#demowjName").val('');
}

//編輯一行记录
function editRow() {
	if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
		$CommonUI.alert('请选一个修改');
		return;
	}
	var row = $("#datagrid").datagrid('getSelected');
	var demowjId = row.demowjId;// alert(demowjId);
//	var url = $WEB_ROOT_PATH+'/demowj/demowjCtrl!findById?dto.demowj.demowjId='+demowjId;
	var url = $WEB_ROOT_PATH+'/demowj/demowjCtrl!findById.htm?dto.demowj.demowjId='+demowjId;
	$("#detailWin").dialog("open");
	$CommonUI.getDialog("#detailWin").dialog("setTitle","标题：修改demowj");
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
//		alert(row.demowjId);
		$.post($WEB_ROOT_PATH+"/demowj/demowjCtrl!delete.htm"
				, {'dto.demowj.demowjId':row.demowjId, 'dto.demowj.demowjName':row.demowjName}, function(){
					$CommonUI.getDataGrid("#datagrid").datagrid('reload');
				} );
	});
}

//取消按钮
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

