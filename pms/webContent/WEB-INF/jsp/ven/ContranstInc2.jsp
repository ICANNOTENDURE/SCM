<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
 <script>
    $(function(){
    	 $.extend($.fn.datagrid.methods, {
    		 editCell: function(jq,param){
    			 return jq.each(function(){
    			 opts = $(this).datagrid('options');
    			 var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
    			 for(var i=0; i<fields.length; i++){
    				 var col = $(this).datagrid('getColumnOption', fields[i]);
    				 col.editor1 = col.editor;
    				 if (fields[i] != param.field){
    					 col.editor = null;
    				 }
    			 }
    			 $(this).datagrid('beginEdit', param.index);
    			 	for(var i=0; i<fields.length; i++){
    			 		var col = $(this).datagrid('getColumnOption', fields[i]);
    			 		col.editor = col.editor1;
    			 	}
    			 });
    		 }
    	 });
    	 
    	 
    	$('#ven').combobox({
	    	url:$WEB_ROOT_PATH+"/ven/vendorCtrl!getVenCombox.htm",
	    	panelHeight:"auto",
	        valueField:'vendorId',  
	        textField:'name',
	        mode: 'remote',
	    }); 
    	$("#searchHopInc").on('click', function() {
    		$CommonUI.getDataGrid('#datagrid').datagrid({  
    		    url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listContrantInc.htm',
    		    queryParams: {
    		    	'dto.venIncContranstDto.incName': $("#incHopName").val(),
    		    	'dto.venIncContranstDto.incCode': $("#incHopCode").val(),
    		    	'dto.venIncContranstDto.flag': $("#hopFlag").combobox('getValue'),
    		    	'dto.venInc.venIncAlias': $("#incHopAlias").val(),
    			}

   		 	});
   		});
    	
    	$("#searchVenInc").on('click', function() {
    		$CommonUI.getDataGrid('#datagrid2').datagrid({  
    		    url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listVenContranst.htm',
    		    queryParams: {
    		    	'dto.venIncContranstDto.incName': $("#incVenName").val(),
    		    	'dto.venIncContranstDto.incCode': $("#incVenCode").val(),
    		    	'dto.venInc.venIncAlias': $("#incVenAlias").val(),
    		    	'dto.venInc.venIncVenid': $("#ven").combobox('getValue'),
    		    	'dto.venIncContranstDto.flag': $("#venFlag").combobox('getValue'),
    			}

   		 	});
   		});
    	
    	$CommonUI.getDataGrid('#datagrid').datagrid({
    		onDblClickRow: function(rowIndex, rowData){
    			$CommonUI.getDataGrid('#datagrid2').datagrid({
    				url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listVenContranst.htm',
        		    queryParams: {
        		    	'dto.venHopInc.hopIncId': rowData.hopincid,
        			}
    			});
    		}

    	});
    });
    function ConT(value,row,index){
		if(row.facid==null){
			str='<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:ConTra('+row.venincid+','+row.fac+')" data-options="iconCls:"icon-save"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left">对照</span></span></a>'
			return str;
		}else{
			var str='<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:deleteTra('+index+')" data-options="iconCls:"icon-remove"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">删除</span></span></a>';
			str=str+'<a class="dhc-linkbutton l-btn l-btn-plain" data-options="iconCls:icon-edit" onclick="javascript:updateConTra('+index+')" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a>';
			return str;
			
		}
	};
	
	//保存对照
	function ConTra(venincid,fac){
		
		if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
			$CommonUI.alert('请选一个医院药品对照');
			return;
		}
		var row =$("#datagrid").datagrid('getSelected');
		var hopincid=row.hopincid;
		$.post(
			$WEB_ROOT_PATH+'/ven/venIncCtrl!saveContranstInc.htm',
			{
				'dto.venHopInc.hopIncId': hopincid,
				'dto.venHopInc.venIncId': venincid,
				'dto.venHopInc.venIncFac': fac,
			},
			function(data){
				if(data.dto.opFlg=="1"){
					$CommonUI.alert(row.hopincname+",对照成功!");
					$CommonUI.getDataGrid('#datagrid2').datagrid('reload');
				}
			},
			"json"
		);
	}
	//保存对照
	function updateConTra(row){
		$('#datagrid2').datagrid('endEdit', row);
		facid=$('#datagrid2').datagrid('getRows')[row]['facid'];
		fac=$('#datagrid2').datagrid('getRows')[row]['fac'];
		$.post(
			$WEB_ROOT_PATH+'/ven/venIncCtrl!updateContranstInc.htm',
			{
				'dto.venHopInc.venHopIncId': facid,
				'dto.venHopInc.venIncFac': fac,
			},
			function(data){
				if(data.dto.opFlg=="1"){
					$CommonUI.alert(",修改对照成功!");
					$CommonUI.getDataGrid('#datagrid2').datagrid('reload');
				}
			},
			"json"
		);
	}
	//删除对照
	function deleteTra(row){
		venHopIncId=$('#datagrid2').datagrid('getRows')[row]['facid'];
		$.post(
				$WEB_ROOT_PATH+'/ven/venIncCtrl!deleteContranstInc.htm',
				{
					'dto.venHopInc.venHopIncId': venHopIncId,
				},
				function(data){
					if(data.dto.opFlg=="1"){
						//$CommonUI.autoCloseRightBottomMessage("("+row.name+")"+row.name+"对照成功","消息",3000,'slide');
						$CommonUI.alert("删除对照成功!");
						$CommonUI.getDataGrid('#datagrid2').datagrid('reload');
					}
				},
				"json"
		);
	}
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true;};
		if ($('#datagrid2').datagrid('validateRow', editIndex)){
			$('#datagrid2').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if (endEditing()){
			$('#datagrid2').datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
			editIndex = index;
		}
	}
    </script>

</head>
<body >
	<div id="toolbar" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			药品名称: <input id="incHopName" style="width: 200px;"
			type="text" />
			药品代码: <input id="incHopCode" style="width: 200px;"
			type="text" />
			别名: <input id="incHopAlias" style="width: 200px;"
			type="text" />
			对应状态:
			<select class="combobox" panelHeight="auto" style="width:100px" id="hopFlag">
				<option value="0">空</option>
				<option value="1">以对照</option>
				<option value="2">未对照</option>
			</select>
			<a href="#" class="linkbutton" iconCls="icon-search" id="searchHopInc">查询</a>
		 </div>
	</div>	
    <div id="toolbar2" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			药品名称: <input id="incVenName" style="width: 200px;"
			type="text" />
			药品代码: <input id="incVenCode" style="width: 200px;"
			type="text" />
			别名: <input id="incVenAlias" style="width: 200px;"
			type="text" />
			</div>
			<div style="margin-bottom:5px;margin-top:5px">
			对应状态:
			<select class="combobox" panelHeight="auto" style="width:100px" id="venFlag">
				<option value="0">空</option>
				<option value="1">以对照</option>
				<option value="2">未对照</option>
			</select>
			供应商:<input style="width: 200px;"
						class="combobox" type="text" 
						 id="ven" />
			<a href="#" class="linkbutton" iconCls="icon-search" id="searchVenInc">查询</a>
		 </div>
	</div>
  <div class="layout" data-options="fit:'true',border:true">
        <div data-options="region:'north',title:'供应商药品',iconCls:'icon-ok'" style="height:250px">
        	<table id="datagrid2" style="height: 250px"  class="datagrid"
					data-options="toolbar:'#toolbar2',
					 			 fit:true,
								 fitColumns:true,
								 singleSelect:true,
								 pagination:true,
				    			 method:'post',
				    			 rownumbers:true,
				    			 striped:true,
				    			 singleselect:true,
				    			 onClickCell:onClickCell,
								 ">
								 
					<thead>
						<tr>
							<th data-options="field:'venincid',hidden:true">IncId ID</th>
							<th data-options="field:'venname',width:100,sortable:true">供应商</th>
							<th data-options="field:'veninccode',width:120,sortable:true">药品代码</th>
							<th data-options="field:'venincname',width:100,sortable:true">药品名称</th>
							<th data-options="field:'manf',width:100,sortable:true">产地</th>
							<th data-options="field:'spec',width:100,sortable:true">规格</th>
							<th data-options="field:'uom',width:50,sortable:true">单位</th>
							<th data-options="field:'fac',width:40,sortable:true,editor : {
								type : 'numberbox',
                            	options : {
                                	required : true
                            	}
                        	}">系数</th>
							<th data-options="field:'hopincuom',width:50,sortable:true">医院单位</th>
							<th data-options="field:'hopincid',hidden:true">IncId ID</th>
							<th data-options="field:'hopinccode',width:60,sortable:true,hidden:true">医院药品代码</th>
							<th data-options="field:'hopincname',width:100,sortable:true">医院药品名称</th>
							
							<th data-options="field:'facid',width:40,hidden:true">对照表rowID</th>
							<th data-options="field:'contranst',width:90,formatter:ConT">对照</th>
						</tr>
					</thead>
				</table>
        
        </div>
        <div data-options="region:'center',title:'医院药品(双击查看已经对照药品)',iconCls:'icon-ok'" >
            <table id="datagrid" style="height: 250px"  class="datagrid"
					data-options="toolbar:'#toolbar',
					 			 fit:true,
								 fitColumns:true,
								 singleSelect:true,
								 pagination:true,
				    			 method:'post',
				    			 rownumbers:true,
				    			 striped:true,
				    			 singleselect:true,
								 ">
								 
					<thead>
						<tr>
							<th data-options="field:'hopincid',hidden:true">IncId ID</th>
							<th data-options="field:'hopinccode',width:100,sortable:true">药品代码(医院)</th>
							<th data-options="field:'hopincname',width:100,sortable:true">药品名称(医院)</th>
							<th data-options="field:'manf',width:100,sortable:true">产地(医院)</th>
							<th data-options="field:'spec',width:100,sortable:true">规格(医院)</th>
							<th data-options="field:'uom',width:100,sortable:true">入库单位(医院)</th>
						</tr>
					</thead>
				</table>
        </div>
    </div>
</body>
</html>
