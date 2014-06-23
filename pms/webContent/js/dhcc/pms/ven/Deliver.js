// zxx 2014-04-19
$(function (){
//	$.extend($.fn.datagrid.methods, {
//		 editCell: function(jq,param){
//			 return jq.each(function(){
//			 opts = $(this).datagrid('options');
//			 var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
//			 for(var i=0; i<fields.length; i++){
//				 var col = $(this).datagrid('getColumnOption', fields[i]);
//				 col.editor1 = col.editor;
//				 if (fields[i] != param.field){
//					 col.editor = null;
//				 }
//			 }
//			 $(this).datagrid('beginEdit', param.index);
//			 	for(var i=0; i<fields.length; i++){
//			 		var col = $(this).datagrid('getColumnOption', fields[i]);
//			 		col.editor = col.editor1;
//			 	}
//			 });
//		 }
//	 });
	$("#upload").uploadify({
        'swf': $WEB_ROOT_PATH + '/images/uploadify.swf',
        'uploader': $WEB_ROOT_PATH + '/ven/venDeliverCtrl!upload.htm',
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
        'onUploadStart': function(file) {
        	$("#upload").uploadify("settings", 'formData', {'dto.venDeliver.deliverId':$("#deliveryId").val()});
        },
        //上传成功
        'onUploadSuccess':function(file, data, response){
        	var obj=eval('('+data+')');
        	if(obj.opFlg=="1"){
        		$("#importDialog").dialog('close');
        		$CommonUI.getDataGrid('#datagrid').datagrid('load',{
    	    		'dto.venDeliver.deliverId':$("#deliveryId").val()
        		});
        		
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


	$("#search").on('click', function() {
		 $("#searchDeliverTable").datagrid('load', {
			 "dto.stDate":$("#acpStDate").datebox('getValue'),
			 "dto.edDate":$("#acpEdDate").datebox('getValue'),
			 "dto.accpStDate":$("#delStDate").datebox('getValue'),
			 "dto.accpEdDate":$("#delEdDate").datebox('getValue'),
			 "dto.state":$("#delFlag").combobox('getValue'),
			 "dto.hopId":$("#hopSearch").combobox('getValue'),
			 "dto.purLocId":$("#purlocSearch").combobox('getValue'),
			 "dto.emFlag":$("#emflag").attr('checked')
		 });
		
	});
	


	$CommonUI.getComboBox('#purlocSearch').combobox({
			url:getContextPath()+'/hop/hopCtlocCtrl!findHopLocComboxVos.htm?dto.type=1',
			valueField:'id',							
			textField:'name'
	});
	
	$CommonUI.getComboBox('#hopSearch').combobox({
		url:$WEB_ROOT_PATH+'/hop/hospitalCtrl!getHospInfo.htm',
		valueField:'hospitalId',							
		textField:'hospitalName'
	});
	
	
	
	

});


var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true;}
    if ($('#datagrid').datagrid('validateRow', editIndex)){
        $('#datagrid').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickRow(index){
    if (editIndex != index){
        if (endEditing()){
            $('#datagrid').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
        }
    }
}





//导入订单
function importInv(){
	if($("#deliveryId").val()==undefined){
		$CommonUI.alert("请选择发货单");
		return;
	}
	if($("#deliveryId").val()==""){
		$CommonUI.alert("请选择发货单");
		return;
	}
	$('#importDialog').dialog('open');
}

//查询发货单
function searchDeliver(){
	
	$('#searchDeliver').dialog('open');
	

	
	$('#searchDeliverTable').datagrid({  
	    url:$WEB_ROOT_PATH+'/ven/venDeliverCtrl!listDeliver.htm',
	    method:'post',
	    fit:true,
	    toolbar:'#searchDeltoolbar',
	    loadMsg:'加载数据中.....',
	    pagination:true,
	    fitColumns:true,
	    rownumbers:true,
	    queryParams:{
	    	'dto.state':2
	    },
	    onDblClickRow:function(rowIndex, rowData){
	    	$("#searchDeliver").dialog('close');
	    	str="医院:"+rowData.hopname;
	    	str=str+"\r\nHIS单号:"+rowData.hisno;
	    	str=str+"\r\n入库科室:"+rowData.purloc;
	    	str=str+"\r\n收货科室:"+rowData.recloc;
	    	str=str+"\r\n收货地址:"+rowData.destination;
	    	$("#deliverInfo").html(str);
	    	$("#remark").val(rowData.remark);
	    	$("#deliveryState").val(rowData.statedesc);
	    	$("#deliveryId").val(rowData.deliverid);
	    	$("#deliveryDate").datetimebox("setValue",rowData.deliveraccpectdate);
	    	$CommonUI.getDataGrid('#datagrid').datagrid('load',{
	    		'dto.venDeliver.deliverId':rowData.deliverid
    		});
	    },
	    columns:[[  
	  	        {field:'deliverid',hidden:true},
	  	        {field:'hisno',title:'HIS单号',width:100},
	  	        {field:'statedesc',title:'状态',width:100,sortable:true},
	  	        {field:'emflag',title:'加急',width:100,sortable:true},
	  	        {field:'purloc',title:'入库科室',width:150,sortable:true},  
	  	        {field:'recloc',title:'收货科室',width:150,sortable:true},
	  	        {field:'destination',title:'收货地址',width:200,sortable:true},
	  	        {field:'hopname',title:'医院',width:150,sortable:true},
	  	        {field:'deliverydate',title:'发货时间',width:100,sortable:true},
	  	        {field:'deliveraccpectdate',title:'接收时间',width:100,sortable:true}
	  	 ]]
	});
	
	
}

//编辑完就保存
function onAfterEdit(rowIndex, rowData, changes){
	 
	 var changes=$CommonUI.getDataGrid('#datagrid').datagrid('getChanges');
	 
	 if(changes.length==0){
		 return
	 }
	 if(rowData.deliveritmid==undefined){
		 return;
	 }

	 $CommonUI.getDataGrid('#datagrid').datagrid('acceptChanges');
	 $.post(
			 $WEB_ROOT_PATH+'/ven/venDeliverCtrl!saveDeliverItm.htm',
			 {
				 "dto.venDeliveritm.deliveritmId":rowData.deliveritmid,
	          	 "dto.venDeliveritm.deliveritmBatno":rowData.batno,
	          	 "dto.venDeliveritm.deliveritmExpdate":rowData.expdate,
	          	 "dto.venDeliveritm.deliveritmInvnoe":rowData.invno, 
	          	 "dto.venDeliveritm.deliveritmRp":rowData.rp, 
	          	 "dto.venDeliveritm.deliveritmQty":rowData.deliverqty,
	          	 "dto.venDeliveritm.deliveritmHopincid":rowData.hopincid,
	          	 "dto.venDeliveritm.deliveritmParentid":$("#deliveryId").val(),
			 },
			 function(data){
				 
				 if(data.dto.opFlg=="1"){
					 
					 $CommonUI.autoCloseRightBottomMessage("药品修改成功","消息",2500,'slide');
					 $CommonUI.getDataGrid('#datagrid').datagrid('acceptChanges');
				 }	 
	        },
			 'json'
		 );
}


function deleteR(value,row,index){
	str='<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:deleterow('+index+')" data-options="iconCls:"icon-save"><span class="l-btn-left"><span class="l-btn-text icon-cancel l-btn-icon-left">删除</span></span></a>';
	str=str+'<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:addrow('+index+')" data-options="iconCls:"icon-save"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left">增加批次</span></span></a>';
    return str;
}
function deleterow(index){
	deliveritmid=$('#datagrid').datagrid('getRows')[index]['deliveritmid'];
	if(deliveritmid==undefined){
		$('#datagrid').datagrid('deleteRow',index);
		return;
	}
	$CommonUI.loadUIM('messager');
	$.messager.confirm('确认对话框', '确认要删除吗？', function(r){
		if (r){
			
			$.post(
					 $WEB_ROOT_PATH+'/ven/venDeliverCtrl!delDeliverItm.htm',
					 {
						 "dto.venDeliveritm.deliveritmId":deliveritmid,
					 },
					 function(data){
						 if(data.dto.opFlg=="1"){					 
							$CommonUI.getDataGrid('#datagrid').datagrid('reload');
						 }
			        },
					 'json'
			);
		}
	});
}

function saveMain(){
	if($("#deliveryState").val()!="接收"){
		$CommonUI.alert("该发货单已经发货");
		return;
	}
	
	var isValid = $CommonUI.getForm('#saveOrUpdate').form('validate');
	if(!isValid){
		return isValid;
	}
	
	$.post(
			$WEB_ROOT_PATH+'/ven/venDeliverCtrl!saveMain.htm',
            {
           	 	"dto.venDeliver.deliverArrdate":$("#deliveryDate").datebox('getValue'),
           	    "dto.venDeliver.deliverId":$("#deliveryId").val(),
           	    "dto.venDeliver.deliverRemark":$("#remark").val(),
           	    
            },
            function(data){
               if(data.dto.opFlg=="1"){
            	   $CommonUI.alert("订单保存成功");
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

//返回药品名称
function GetName(value,row,index){
	if (row.venincname){
		return row.venincname;
	} else {
		return "(医院)"+row.hopincname;
	}

}
function formatNumber(value,row,index){
	return value.toFixed(2);
}
function GetNameStyle(value,row,index){
	if (row.venincname){
	} else {
		return 'background-color:#ffee00;color:red;';

	}
}

function addrow(index){

	if (endEditing()) {
		hopincid=$('#datagrid').datagrid('getRows')[index]['hopincid'];
		orderqty=$('#datagrid').datagrid('getRows')[index]['orderqty'];
		sendedqty=$('#datagrid').datagrid('getRows')[index]['sendedqty'];
		uom=$('#datagrid').datagrid('getRows')[index]['uom'];
		rp=$('#datagrid').datagrid('getRows')[index]['rp'];
		venincncode=$('#datagrid').datagrid('getRows')[index]['venincncode'];
		venincname=$('#datagrid').datagrid('getRows')[index]['venincname'];
		hopincname=$('#datagrid').datagrid('getRows')[index]['hopincname'];
		
		if(venincname==null){
			venincname="(医院)"+hopincname;
		}

		$.post(
				 $WEB_ROOT_PATH+'/ven/venDeliverCtrl!saveDeliverItm.htm',
				 {
		          	 "dto.venDeliveritm.deliveritmHopincid":$('#datagrid').datagrid('getRows')[index]['hopincid'],
		          	 "dto.venDeliveritm.deliveritmParentid":$("#deliveryId").val(),
				 },
				 function(data){
					 
					 if(data.dto.opFlg=="1"){
						 
						 $('#datagrid').datagrid('appendRow', {
								'hopincid':hopincid,
								'orderqty':orderqty,
								'sendedqty':sendedqty,
								'uom':uom,
								'rp':rp,
								'venincncode':venincncode,
								'venincname':venincname,
								'deliveritmId':data.dto.venDeliveritm.deliveritmId
						 });
						 $CommonUI.getDataGrid('#datagrid').datagrid('acceptChanges');
					     editIndex = $('#datagrid').datagrid('getRows').length - 1;
					     $('#datagrid').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
					 }	 
		        },
				 'json'
			 );

	}
	
}

