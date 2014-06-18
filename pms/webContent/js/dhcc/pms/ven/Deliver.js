// zxx 2014-04-19
$(function (){	
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
	
	
	$("#orderUpload").uploadify({
        'swf': $WEB_ROOT_PATH + '/images/uploadify.swf',
        'uploader': $WEB_ROOT_PATH + '/ord/orderCtrl!upload.htm',
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'buttonText':'Upload',
        'fileTypeDesc': '支持的格式：',
        'fileTypeExts': '*.xls',
        'fileSizeLimit': '30MB',
        'width': '60',
        'height': '20',
        'debug' : false,
        'fileObjName':'dto.upload',
        'auto': true,
        'removeCompleted':false,
        'checkExisting':false,

        //上传成功
        'onUploadSuccess':function(file, data, response){
        	var obj=eval('('+data+')');
        	if(obj.opFlg=="1"){
        		$("#importDialog").dialog('close');
        		$CommonUI.getDataGrid('#datagrid').datagrid('load',{
    	    		'dto.exeState.ordId':obj.order.orderId,
        		});
        		clearData();
        		$("#emFlag").attr("checked",obj.order.emFlag);
    	    	$("#hisNO").val(obj.order.orderNo);
    	    	$("#deliveryDate").datebox('setValue',obj.order.deliveryDate);
    	    	$("#purId").combobox('setValue',obj.order.purLoc);
    	    	
    	    	$("#vendorId").combobox('setValue',obj.order.vendorId);
    	    	$("#orderId").val(obj.order.orderId);
    	    	
    	    	if(obj.order.recLoc!=null){ 
    	    		$("#locId").combobox('setValue',obj.order.recLoc);	
	    	    	$("#recDestination").combobox({
	    				url:$WEB_ROOT_PATH+'/ord/orderCtrl!findLocDesctionComboList.htm?dto.loc='+obj.order.recLoc,
	    				valueField:'hopCtlocDestinationId',							
	    				textField:'destination',
	    				mode:'remote',
	    			});
    	    	}
    	    	if(obj.order.recDestination!=null){
    	    		$("#recDestination").combobox('setValue',obj.order.recDestination);
    	    	}
    	    	$("#remark").val(obj.order.remark);
        	}else{
        		$CommonUI.alert(obj.msg);
        	};
        },
        //检测FLASH失败调用
        'onFallback': function() {
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError': function(file, errorCode, errorMsg) {
            switch (errorCode) {
            case - 100 : alert("上传的文件数量已经超出系统限制的" + $('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");
                break;
            case - 110 : alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");
                break;
            case - 120 : alert("文件 [" + file.name + "] 大小异常！");
                break;
            case - 130 : alert("文件 [" + file.name + "] 类型不正确！");
                break;
            }
        }
    });
	
//	$CommonUI.getDataGrid('#datagrid').datagrid({ 
//		url:'orderStateCtrl!listOrdItm.htm?dto.exeState.ordId=XX',
//	});

	$("#search").on('click', function() {
		 $("#searchOrderTable").datagrid('load', {
			 "dto.stdate":$("#stdate").datebox('getValue'),
			 "dto.eddate":$("#eddate").datebox('getValue'),
			 "dto.reqStDate":$("#reqStDate").datebox('getValue'),
			 "dto.reqEdDate":$("#reqEdDate").datebox('getValue'),
			 "dto.state":($("#state").attr('checked')=='checked'?1:0),
			 "dto.vendor":$("#vendorSearch").combobox('getValue'),
			 "dto.purloc":$("#purlocSearch").combobox('getValue'),
			 "dto.emflag":$("#emflag").attr('checked')
		 });
		
	});
	


	$CommonUI.getComboBox('#purId').combobox({
			url:getContextPath()+'/hop/hopCtlocCtrl!findHopLocComboxVos.htm?dto.type=1',
			valueField:'id',							
			textField:'name'
	});
	
	$CommonUI.getComboBox('#locId').combobox({
		url:getContextPath()+'/hop/hopCtlocCtrl!findHopLocComboxVos.htm?dto.type=2',
		valueField:'id',							
		textField:'name'
	});
	
	
	
	
	

});



var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true;};
	if ($('#datagrid').datagrid('validateRow', editIndex)){
		$('#datagrid').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}


function onClickCell(index, field){
	if (endEditing()){
		$('#datagrid').datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}





//导入订单
function importOrder(){
	$('#importDialog').dialog('open');
}

//查询导入订单
function searchOrder(){
	
	   $('#searchOrder').dialog('open');
	

	
	$('#searchOrderTable').datagrid({  
	    url:'orderStateCtrl!list.htm',
	    method:'post',
	    fit:true,
	    toolbar:'#searchtoolbar',
	    loadMsg:'加载数据中.....',
	    pagination:true,
	    fitColumns:true,
	    rownumbers:true,
	    queryParams:{
	    	'dto.state':0
	    },
	    onDblClickRow:function(rowIndex, rowData){
	    	$("#searchOrder").dialog('close');
	    	$CommonUI.getDataGrid('#datagrid').datagrid('load',{
	    		'dto.exeState.ordId':rowData.orderid
    		});
	    	clearData();
	    	$("#emFlag").attr("checked",rowData.emflag);
	    	$("#hisNO").val(rowData.hisno);
	    	$("#deliveryDate").datebox('setValue',rowData.deliverydate);
	    	$("#purId").combobox('setValue',rowData.purlocid);
	    	$("#locId").combobox('setValue',rowData.reclocid);
	    	$("#vendorId").combobox('setValue',rowData.vendor);
	    	$("#vendor").val(rowData.vendorid);
	    	$("#orderId").val(rowData.orderid);
	    	$("#orderState").val(rowData.statedesc);
	    	$("#recDestination").combobox({
				url:$WEB_ROOT_PATH+'/ord/orderCtrl!findLocDesctionComboList.htm?dto.loc='+rowData.reclocid,
				valueField:'hopCtlocDestinationId',							
				textField:'destination',
				mode:'remote',
			});
	    	$("#recDestination").combobox('setValue',rowData.destinationid);
	    	$("#remark").val(rowData.remark);
	    },
	    columns:[[  
	  	        {field:'orderid',title:'ID',width:100},
	  	        {field:'hisno',title:'HIS单号',width:100},
	  	        {field:'statedesc',title:'状态',width:100,sortable:true},
	  	        {field:'emflag',title:'加急',width:100,sortable:true},
	  	        {field:'purloc',title:'入库科室',width:150,sortable:true},  
	  	        {field:'recloc',title:'收货科室',width:150,sortable:true},
	  	        {field:'destination',title:'收货地址',width:200,sortable:true},
	  	        {field:'vendor',title:'供应商',width:150,sortable:true},
	  	        {field:'deliverydate',title:'要求送达日期',width:100,sortable:true}
	  	 ]]
	});
	
	
}




function fillValue(rowIndex, rowData){
	
	 
	orderitmid=$('#datagrid').datagrid('getRows')[editIndex]['orderitmid'];
	ordid=$('#datagrid').datagrid('getRows')[editIndex]['orderid'];
	qty=$('#datagrid').datagrid('getRows')[editIndex]['qty'];
	hopincid=rowData.incid;
	incuomname=rowData.incuomname;
	incrp=rowData.incrp;
	 $.post(
		 $WEB_ROOT_PATH+'/ord/orderCtrl!saveOrditm.htm',
		 {
			 "dto.orderItm.orderitmId":orderitmid,
          	 "dto.orderItm.incId":hopincid,
          	 "dto.orderItm.ordId":ordid, 
          	 "dto.orderItm.rp":incrp,
          	 "dto.orderItm.uom":incuomname,
          	 "dto.orderItm.reqqty":qty,
		 },
		 function(data){
			 
			 if(data.dto.opFlg=="1"){
				 $('#datagrid').datagrid('updateRow', { 
					 	index: editIndex, 
						row: {
							inccode: rowData.inccode, 
					 	    incname: rowData.incname,
					 		uom:rowData.incuomname,
					 		rp:rowData.incrp,
					 		manf:rowData.manfname,
					 		hopincid:rowData.incid,
						}
				 });
				 
				 $CommonUI.autoCloseRightBottomMessage("("+rowData.incname+")"+"药品修改成功","消息",2500,'slide');
				 $CommonUI.getDataGrid('#datagrid').datagrid('acceptChanges');
			 }
			 
			 
        },
		 'json'
	 );
}

function onAfterEdit(rowIndex, rowData, changes){
	 var changes=$CommonUI.getDataGrid('#datagrid').datagrid('getChanges');
	 if(changes.length==0){
		 return
	 }
	 $.post(
			 $WEB_ROOT_PATH+'/ord/orderCtrl!saveOrditm.htm',
			 {
				 "dto.orderItm.orderitmId":rowData.orderitmid,
	          	 "dto.orderItm.incId":rowData.hopincid,
	          	 "dto.orderItm.ordId":rowData.orderid, 
	          	 "dto.orderItm.rp":rowData.rp,
	          	 "dto.orderItm.uom":rowData.uom,
	          	 "dto.orderItm.reqqty":rowData.qty,
			 },
			 function(data){
				 
				 if(data.dto.opFlg=="1"){					 
					 $CommonUI.autoCloseRightBottomMessage("("+rowData.incname+")"+"药品修改成功","消息",2500,'slide');
					 $CommonUI.getDataGrid('#datagrid').datagrid('acceptChanges');
				 }	 
	        },
			 'json'
		 );
}


function deleteR(value,row,index){
	return '<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:deleterow('+index+')" data-options="iconCls:"icon-save"><span class="l-btn-left"><span class="l-btn-text icon-cancel l-btn-icon-left">删除</span></span></a>';
	//return '<a href="#" onclick="deleterow('+index+')">删除</a>';
}
function deleterow(index){
	orderitmid=$('#datagrid').datagrid('getRows')[index]['orderitmid'];
	if(orderitmid==undefined){
		$('#datagrid').datagrid('deleteRow',index);
		return;
	}
	$CommonUI.loadUIM('messager');
	$.messager.confirm('确认对话框', '确认要删除吗？', function(r){
		if (r){
			
			$.post(
					 $WEB_ROOT_PATH+'/ord/orderCtrl!deleteOrditm.htm',
					 {
						 "dto.orderItm.orderitmId":orderitmid,
					 },
					 function(data){
						 if(data.dto.opFlg=="1"){					 
							$CommonUI.getDataGrid('#datagrid').datagrid('load');
						 }
			        },
					 'json'
			);
		}
	});
}

function saveMain(){
	if($("#orderState").val()!=""){
		$CommonUI.alert("该订单已经完成");
		return;
	}
	
	var isValid = $CommonUI.getForm('#saveOrUpdate').form('validate');
	if(!isValid){
		return isValid;
	}
	
	$.post(
			$WEB_ROOT_PATH+'/ord/orderCtrl!saveMain.htm',
            {
           	 	"dto.order.deliveryDate":$("#deliveryDate").datebox('getValue'),
           	    "dto.order.emFlag":$("#emFlag").attr("checked"),
           	    "dto.order.remark":$("#remark").val(),
           	    "dto.order.vendorId":$("#vendorId").combobox('getValue'),
           	    "dto.order.recLoc":$("#locId").combobox('getValue'),
           	    "dto.order.recDestination":$("#recDestination").combobox('getValue'),
           	    "dto.order.purLoc":$("#purId").combobox('getValue'),
           	    "dto.order.orderNo":$("#hisNO").val(),
           	    "dto.order.orderId":$("#orderId").val(),
           	    "dto.order.remark":$("#remark").val()
           	    
            },
            function(data){
               if(data.dto.opFlg=="1"){
            	   $CommonUI.alert("订单保存成功");
            	   $("#orderId").val(data.dto.order.orderId);
               }else{
            	   $CommonUI.alert("订单保存失败");
               }
            },
            "json"
    );
}

function deleteOrder(){
	if($("#orderId").val()==undefined){
		$CommonUI.alert("请查找订单");
		return;
	}
	$CommonUI.loadUIM('messager');
	$.messager.confirm('确认对话框', '确认要删除吗？', function(r){
		if (r){
			
			$.post(
					 $WEB_ROOT_PATH+'/ord/orderCtrl!delete.htm',
					 {
						 "dto.order.orderId":$("#orderId").val(),
					 },
					 function(data){
						 if(data.dto.opFlg=="1"){
							 $CommonUI.getDataGrid('#datagrid').datagrid('load',{
						    		'dto.exeState.ordId':0
					    	 });
							 clearData();
							 $CommonUI.alert("删除成功");
						 }else{
							 $CommonUI.alert("删除失败");
						 }
			        },
					 'json'
			);
		}
	});
}

function clearData(){
	$("#emFlag").attr("checked","");
    $("#hisNO").val("");
    $("#deliveryDate").datebox('setValue',"");
    $("#purId").combobox('setValue',"");
    //$("#locId").combobox('setValue',"");
    //$("#recDestination").combobox('setValue',"");
    $("#vendorId").combobox('setValue',"");
    $("#orderId").val(undefined);
    $("#remark").val("");
    $("#orderState").val("");
    
}

function complete(){
	
	if($("#orderId").val()==undefined){
		$CommonUI.alert("请选择订单");
		return;
	}
	if($("#orderId").val()==""){
		$CommonUI.alert("请选择订单");
		return;
	}
	if($("#orderState").val()!=""){
		$CommonUI.alert("该订单已经完成");
		return;
	}
	$.post(
			 $WEB_ROOT_PATH+'/ord/orderCtrl!complete.htm',
			 {
				 "dto.order.orderId":$("#orderId").val(),
				 "dto.order.remark":$("#remark").val(),
			 },
			 function(data){
				 if(data.dto.opFlg=="1"){
	
					 $CommonUI.alert("确认成功");
					 $("#orderState").val("新建");
				 }else{
					 $CommonUI.alert("确认失败");
				 }
	        },
			 'json'
	);
}



function canclecomplete(){
	
	if($("#orderId").val()==undefined){
		$CommonUI.alert("请选择订单");
		return;
	}
	if($("#orderId").val()==""){
		$CommonUI.alert("请选择订单");
		return;
	}
	$.post(
			 $WEB_ROOT_PATH+'/ord/orderCtrl!cancleComplete.htm',
			 {
				 "dto.order.orderId":$("#orderId").val(),
			 },
			 function(data){
				 if(data.dto.opFlg=="1"){
	
					 $CommonUI.alert("操作成功");
					 $("#orderState").val("");
				 }else{
					 $CommonUI.alert("操作失败");
				 }
	        },
			 'json'
	);
}
