<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dic</title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/uploadify.css">

<script type="text/javascript">
$(function (){
	$('#stdate').datebox('setValue',new Date().format("yyyy-MM-dd"));
	$('#eddate').datebox('setValue',new Date().format("yyyy-MM-dd"));
	
	
	$("#search").on('click', function() {
		$CommonUI.getDataGrid('#datagrid').datagrid({  
		    url:$WEB_ROOT_PATH+'/ord/ordCtrl!list.htm',
		    queryParams: {
		    	"dto.stdate":$("#stdate").datebox('getValue'),
		   	    "dto.eddate":$("#eddate").datebox('getValue'),
			},
			onDblClickRow:function(rowIndex, rowData){
				$CommonUI.getDataGrid('#datagrid2').datagrid({  
				    url:$WEB_ROOT_PATH+'/ord/ordCtrl!listItm.htm',
				    queryParams: {
				    	"dto.ordSerial":rowData.ordId,
					}
				});
			}
		});
	});
	
	
	
	$("#orderUpload").uploadify({
        'swf': $WEB_ROOT_PATH + '/images/uploadify.swf',
        'uploader': $WEB_ROOT_PATH + '/ord/orderCtrl!upload.htm',
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'buttonText':'Upload',
        'fileTypeDesc': '支持的格式：',
        'fileTypeExts': '*.xls;*.xlsx',
        'fileSizeLimit': '30MB',
        'width': '60',
        'height': '20',
        'debug' : false,
        'fileObjName':'dto.upload',
        'auto': true,
        'removeCompleted':true,
        'onUploadStart': function(file) {
        	$("#orderUpload").uploadify("settings", 'formData', {'dto.uploadFileName': file.name});
        },
        'onSelect': function(){  
	        	$("#gg").dialog("open");
	        	$("#err").html("");	
        }, 
        //上传成功
        'onUploadSuccess':function(file, data, response){
        	$("#gg").dialog("close");
        	var obj=eval('('+data+')');
        	if(obj.opFlg=="1"){
        		$("#importDialog").dialog('close');
        	}else{
        		$CommonUI.alert("失败");
        		$("#err").html(obj.msg);
        	};
        },
        //检测FLASH失败调用
        'onFallback': function() {
        	$CommonUI.alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError': function(file, errorCode, errorMsg) {
            switch (errorCode) {
            case - 100 : $CommonUI.alert("上传的文件数量已经超出系统限制的" + $('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");
                break;
            case - 110 : $CommonUI.alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");
                break;
            case - 120 : $CommonUI.alert("文件 [" + file.name + "] 大小异常！");
                break;
            case - 130 : $CommonUI.alert("文件 [" + file.name + "] 类型不正确！");
                break;
            }
        }
    });	
	
	
	//导入订单
	$("#import").on('click', function() {
		$('#importDialog').dialog('open');
		$('#impModel').html("");
		$('#impModel').append("<td class='time'>模版 </td>");
		$.post(
			$WEB_ROOT_PATH+"/sys/sysImpModelCtrl!listImpModel.htm",
			{
				'dto.impModel.type':'ORDER'
			},
			function(data){
				$.each(data,function(i,dd){
						$('#impModel').append("<td class='drop'><div class='item'>"+dd.name+"</div></td>");
				});
			},
			"json"
		);
	});
	
	
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
	
	 $.extend($.fn.datagrid.defaults.editors, {
			combogrid: {
				init: function(container, options){
					var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container); 
					input.combogrid(options);
					return input;
				},
				destroy: function(target){
					$(target).combogrid('destroy');
				},
				getValue: function(target){
					return $(target).combogrid('getText');
				},
				setValue: function(target, value){
					$(target).combogrid('setValue', value);
					
				},
				resize: function(target, width){
					$(target).combogrid('resize',width);
				}
			}
	});
})
function deleteR(value,row,index){
		if(parseInt(row.state)>1){
			return "";
		}else{
			return '<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:deleterow('+index+')" data-options="iconCls:"icon-save"><span class="l-btn-left"><span class="l-btn-text icon-cancel l-btn-icon-left">删除</span></span></a>';
		}
}
function deleterow(index){
	orderitmid=$('#datagrid2').datagrid('getRows')[index]['orderitmid'];
	if(orderitmid==undefined){
		$('#datagrid2').datagrid('deleteRow',index);
		return;
	}
	$CommonUI.loadUIM('messager');
	$.messager.confirm('确认对话框', '确认要删除吗？', function(r){
		if (r){
			
			$.post(
					 $WEB_ROOT_PATH+'/ord/ordCtrl!deleteItm.htm',
					 {
						 "dto.orderItmId":orderitmid,
					 },
					 function(data){
						 $CommonUI.getDataGrid('#datagrid2').datagrid('load');
			        },
					 'json'
			);
		}
	});
	
	
	
	
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

function onAfterEdit(rowIndex, rowData, changes){
	 var changes=$CommonUI.getDataGrid('#datagrid2').datagrid('getChanges');
	 if(changes.length==0){
		 return
	 }
	 $.post(
			 $WEB_ROOT_PATH+'/ord/ordCtrl!saveOrditm.htm',
			 {
				 "dto.orderItmId":rowData.orderitmid,
	          	 "dto.qty":rowData.qty,
	          	 "dto.rp":rowData.rp,
			 },
			 function(data){
				 if(data.opFlg=="1"){					 
					 $CommonUI.autoCloseRightBottomMessage("("+rowData.incname+")"+"药品修改成功","消息",2500,'slide');
					 $CommonUI.getDataGrid('#datagrid2').datagrid('acceptChanges');
				 }	 
	        },
			 'json'
		 );
}

function fillValue(rowIndex, rowData){
	
	 
	orderitmid=$('#datagrid2').datagrid('getRows')[editIndex]['orderitmid'];
	qty=$('#datagrid2').datagrid('getRows')[editIndex]['qty'];
	hopincid=rowData.hopincid;
	incuomname=rowData.uom;
	incrp=rowData.rp;
	incname=rowData.hopincname;
	 $.post(
		 $WEB_ROOT_PATH+'/ord/ordCtrl!saveOrditm.htm',
		 {
			 "dto.orderItmId":orderitmid,
          	 "dto.incId":hopincid,
          	 "dto.rp":incrp,
          	 "dto.uom":incuomname,
          	 "dto.qty":qty,
		 },
		 function(data){
			 if(data.opFlg=="1"){
				 $('#datagrid2').datagrid('updateRow', { 
					 	index: editIndex, 
						row: {
							inccode: rowData.hopinccode, 
					 	    incname: rowData.hopincname,
					 		uom:rowData.uom,
					 		rp:rowData.rp,
					 		manf:rowData.manf,
					 		orderitmid:data.orderItmId,
						}
				 });
				 
				 $CommonUI.autoCloseRightBottomMessage("("+rowData.hopincname+")"+"药品修改成功","消息",2500,'slide');
				 $CommonUI.getDataGrid('#datagrid2').datagrid('acceptChanges');
			 }
			 
			 
        },
		 'json'
	 );
}
</script>
</head>
<body>
	<div id="toolbar" class="toolbar">
			订单开始日期: <input class="datebox" style="width:100px" id="stdate">
			结束日期: <input class="datebox" style="width:100px" id="eddate">
			<a
				class="linkbutton" id="search"
				data-options="iconCls:'icon-search'">查询</a>
			<a class="linkbutton" id="import"
				data-options="iconCls:'icon-add',plain:true">导入订单</a>

	</div>


	<div class="layout" data-options="fit:'true',border:true">
		<div data-options="region:'north',title:'订单(双击查看明细)',iconCls:'icon-ok'"
			style="height: 200px">
			<table id="datagrid"  class="datagrid"
				data-options="toolbar:'#toolbar',
					 			 fit:true,
								 fitColumns:true,
								 singleSelect:true,
								 pagination:true,
				    			 method:'post',
				    			 rownumbers:true,
				    			 striped:true,
				    			 singleselect:true,
				    			 pageSize:3,
				    			 pageList:[3,6,9],
								 ">

				<thead>
					<tr>
						<th data-options="field:'ordId',width:100">流水号</th>
						<th data-options="field:'ordDate',width:100">时间</th>
					</tr>
				</thead>
			</table>

		</div>
		<div
			data-options="region:'center',title:'订单明细',iconCls:'icon-ok'">
			<table id="datagrid2"  class="datagrid"
				data-options="
					 			 fit:true,
								 fitColumns:true,
								 singleSelect:true,
				    			 method:'post',
				    			 rownumbers:true,
				    			 striped:true,
				    			 singleselect:true,
				    			 remoteSort:true,
				    			  onClickCell: onClickCell,
				    			  onAfterEdit:onAfterEdit,
								 ">

				<thead>
					<tr>
						<th data-options="field:'orderid',hidden:true">IncId ID</th>
						<th data-options="field:'orderitmid',hidden:true">IncId ID</th>
						<th data-options="field:'inccode',width:30,sortable:true">代码</th>
						<th data-options="field:'incname',width:70,editor:{
								type:'combogrid',
								options:{
									required : true,
								    id:'INC',
								    fitColumns:true,
								    fit: true,//自动大小  
									pagination : true,
									panelWidth:500,
									textField:'incname',
									mode:'remote',
									url:'<%=request.getContextPath()%>/ven/venIncCtrl!listContrantInc.htm',
									columns:[[
										{field:'hopincid',title:'代码',hidden:true},
										{field:'hopinccode',title:'代码',width:60},
										{field:'hopincname',title:'名称',width:140},
										{field:'uom',title:'单位',width:90},
										{field:'rp',title:'价格',width:90},
										{field:'manf',title:'产地',width:113}
									]],
									onSelect:function(rowIndex, rowData) {
                       					 fillValue(rowIndex, rowData);
                    				}	   
								}
							},sortable:true">名称</th>
						<th data-options="field:'qty',width:20,editor : {
							type : 'numberbox',
                            options : {
                                required : true
                            }
                        },sortable:true
					   ">数量</th>
						<th data-options="field:'uom',width:20,sortable:true">单位</th>
						<th data-options="field:'rp',width:20,sortable:true,editor : {
							type : 'numberbox',
                            options : {
                                required : true
                            }
                        },sortable:true
					   ">进价</th>
						<th data-options="field:'manf',width:70,sortable:true">产地</th>
						<th data-options="field:'spec',width:30,sortable:true">规格</th>
						<th data-options="field:'vendor',width:70,sortable:true">供应商</th>
						<th data-options="field:'state',width:40,formatter:deleteR">编辑</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>


	
	
	
	<div id="importDialog" class="dialog" title="导入订单"
		style="width: 1000px; height: 400px; background-color: #F5FAFD;"
		data-options="
				modal:true,
		        closed:true,
				collapsible:false,
				minimizable:false,
				maximizable:true">
		<table id="addFuncsTable" style="width: 100%;">
			<tr>
				<td class="textLabel" style="text-align: right; width: 40%">导入Excel文件:</td>
				<td class="textParent" style="text-align: left; width: 60%"><input
					style="width: 250px;" class="validatebox" type="file" name="upload"
					id="orderUpload"></input></td>
			</tr>
		</table>
		<table>
			<tr id="impModel">
				<td class="time">模版</td>
			</tr>
		</table>
		<div id="err"></div>
	</div>
	<style type="text/css">
.item {
	text-align: center;
	border: 1px solid #499B33;
	background: #fafafa;
	color: #444;
	width: 90px;
}
</style>


	<div id="gg" class="dialog" title="请等待"
		style="width: 1000px; height: 400px; padding: 10px;"
		data-options="
				modal:true,
				draggable:false,
				closable:false,
				closed:true,
				collapsible:false,
				minimizable:false,
				maximizable:false">

		<p1>正在处理上传数据，请等待</p1>
	</div>
</body>
</html>
