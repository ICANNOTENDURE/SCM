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
			url:$WEB_ROOT_PATH+"/ven/vendorCtrl!getVenCombox.htm",
	    	panelHeight:"auto",
	        valueField:'vendorId',  
	        textField:'name',
	        mode: 'remote',
		});
	}
	
	$CommonUI.getDataGrid('#datagrid').datagrid({  
	    url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listInfo.htm',
	    iconCls:'icon-edit',//图标
	    method:'post',
	    fit:true,
	    fitColumns:true,
	    columns:[[ 
	        {field:'venincid',title:'表ID',hidden:true},  
	        {field:'veninccode',title:'药品代码',width:200},
	        {field:'venincname',title:'药品名称',width:200},
	        {field:'venincuomcode',title:'单位代码',width:50,hidden:true},
	        {field:'venincuomname',title:'单位',width:50},
	        {field:'venincbuomcode',title:'小单位代码',width:50,hidden:true},
	        {field:'venincbuomname',title:'小单位',width:50},
	        {field:'venincfac',title:'系数',width:50},
	        {field:'venincprice',title:'价格',width:50},
	        {field:'venincmanfid',title:'厂商Id',hidden:true},
	        {field:'manfname',title:'厂商',width:150},
	        {field:'venincvenid',title:'供应商Id',hidden:true},
	        {field:'name',title:'供应商',width:150},
	        {field:'venincvensysid',title:'系统ID',hidden:true},
	        {field:'venincpicid',title:'药品图片表id',hidden:true},
	        {field:'venincpicpath',title:'图片路径',hidden:true},
	        {field:'venincpicseq',title:'图片顺序',hidden:true},
	    ]],
	    pageSize:20,
	    pageList:[20,30,40,50]
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
	
	
	$("#upload").uploadify({
        'swf': $WEB_ROOT_PATH + '/images/uploadify.swf',
        'uploader': $WEB_ROOT_PATH + '/ven/venIncPicCtrl!upload.htm',
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'buttonText':'Upload',
        'fileTypeDesc': '支持的格式：',
        'fileTypeExts': '*.jpg;*.jpeg;*.png',
        'fileSizeLimit': '30MB',
        'width': '60',
        'height': '20',
        'debug' : false,
        'fileObjName':'dto.upload',
        'auto': true,
        'removeCompleted':true,
        'onUploadStart': function(file) {
        	if($("#incdetail input[name='dto.venInc.venIncId']").val()==""){
        		$CommonUI.alert("请先保存药品信息,再上传图片");
        		$('#upload').uploadify('cancel');
        	}else{
        		$("#upload").uploadify("settings", 'formData', {'dto.vendorIncId':$("#incdetail input[name='dto.venInc.venIncId']").val(),'dto.vendorIncPicSeq':$("#venIncSeq").val()});
        	};
        },
        //上传成功
        'onUploadSuccess':function(file, data, response){
        	var obj=eval('('+data+')');
        	if(obj.opFlg=="1"){
        		//$("#importDialog").dialog('close');
        		dd=obj.venIncPic;
        		imgUrl=$WEB_ROOT_PATH +"/uploadPic/"+dd.venIncPicPath;
			 	imgId="item"+dd.venIncPicId;
			 	html="<tr id='tr"+dd.venIncPicId+"' name='trPic'><td class='textLabel'>图片:</td><td ><img src='"+imgUrl+"' width=105px></img>";
			 	html=html+"<div><a class='dhc-linkbutton l-btn l-btn-plain'  onclick='javascript:viewPic("+imgId+")' ><span class='l-btn-left'><span class='l-btn-text icon-search l-btn-icon-left'>预览</span></span></a>";
			 	html=html+"<a class='dhc-linkbutton l-btn l-btn-plain'  onclick='javascript:delPic("+dd.venIncPicId+")' ><span class='l-btn-left'><span class='l-btn-text icon-cancel l-btn-icon-left'>删除</span></span></a></div>";
			 	html=html+"<div id='"+imgId+"' src='"+imgUrl+"' style='float:left'></div></td>";
			 	html=html+"<td class='textLabel'>顺序:</td><td class='textParent'><input style='width: 250px;' type='text'  onnlur='saveSeq("+dd.venIncPicId+")' name='pic"+dd.venIncPicId+"' value="+dd.venIncPicSeq+" /></td></tr>";
			 	//alert(html);
			 	$('#tableDetail').append(html);
        		
        		
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
});

//增加
function addClick() {
	$CommonUI.getDialog("#drugInfoWin").dialog("setTitle","增加药品信息");
	$CommonUI.getDialog("#drugInfoWin").dialog("center");
	$CommonUI.getDialog("#drugInfoWin").dialog("open");
	$CommonUI.getForm('#incdetail').form('clear');
	$("#saveOrUpdateIncBtn").show();
	$("tr[name='trPic']").remove();
	
}

function saveSeq(venIncPicId){
	$.post(
			 $WEB_ROOT_PATH+'/ven/venIncPicCtrl!saveSeq.htm',
			 {
				 "dto.vendorIncPicId":venIncPicId,
				 "dto.vendorIncPicSeq":$("input[name='pic"+venIncPicId+"']").val(),
			 }
	 );
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
	$("tr[name='trPic']").remove();
	$.post(
			 $WEB_ROOT_PATH+'/ven/venIncPicCtrl!listIncPic.htm',
			 {
				 "dto.vendorIncId":Id,
			 },
			 function(data){
				 $.each(data,function(i,dd){
					 	imgUrl=$WEB_ROOT_PATH +"/uploadPic/"+dd.venIncPicPath;
					 	imgId="item"+dd.venIncPicId;
					 	html="<tr id='tr"+dd.venIncPicId+"' name='trPic'><td class='textLabel'>图片:</td><td ><img src='"+imgUrl+"' width=105px></img>";
					 	html=html+"<div><a class='dhc-linkbutton l-btn l-btn-plain'  onclick='javascript:viewPic("+imgId+")' ><span class='l-btn-left'><span class='l-btn-text icon-search l-btn-icon-left'>预览</span></span></a>";
					 	html=html+"<a class='dhc-linkbutton l-btn l-btn-plain'  onclick='javascript:delPic("+dd.venIncPicId+")' ><span class='l-btn-left'><span class='l-btn-text icon-cancel l-btn-icon-left'>删除</span></span></a></div>";
					 	html=html+"<div id='"+imgId+"' src='"+imgUrl+"' style='float:left'></div></td>";
					 	html=html+"<td class='textLabel'>顺序:</td><td class='textParent'><input style='width: 250px;' type='text'  onblur='saveSeq("+dd.venIncPicId+")' name='pic"+dd.venIncPicId+"' value='"+dd.venIncPicSeq+"'/></td></tr>";
					 	
					 	//alert(html);
					 	$('#tableDetail').append(html);
						
				 });
	         },
			 'json'
	 );
	
	
}

function viewPic(imgId){
	$CommonUI.imageTransfer(imgId,$WEB_ROOT_PATH+"/js",450,300,{
		'Close':true,
		'Reset':true
	});
}
function delPic(picId){
	$CommonUI.confirm('确定删除吗？', 'question', 0, function(){
		$.post(
				 $WEB_ROOT_PATH+'/ven/venIncPicCtrl!delete.htm',
				 {
					 "dto.venIncPic.venIncPicId":picId,
				 },
				 function(data){
					 if(data.dto.opFlg=="1"){
						 $("#tr"+picId).remove();
					 }
		         },
				 'json'
		 );
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
		$.post($WEB_ROOT_PATH+"/ven/venIncCtrl!delete.htm", {'dto.venInc.venIncId':row.venincid},function(){
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
//导入订单
function importClick(){
	$('#importDialog').dialog('open');
	$('#impModel').html("");
	$('#impModel').append("<td class='time'>模版 </td>");
	$.post(
		$WEB_ROOT_PATH+"/sys/sysImpModelCtrl!listImpModel.htm",
		{
			'dto.impModel.type':'VENINC'
		},
		function(data){
			$.each(data,function(i,dd){
					$('#impModel').append("<td class='drop'><div class='item'>"+dd.name+"</div></td>");
			});
		},
		"json"
	);
	$("#import").uploadify({
        'swf': $WEB_ROOT_PATH + '/images/uploadify.swf',
        'uploader': $WEB_ROOT_PATH + '/ven/venIncCtrl!upload.htm',
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'buttonText':'Upload',
        'fileTypeDesc': '支持的格式：',
        'fileTypeExts': '*.xlsx;*.xls',
        'fileSizeLimit': '300MB',
        'width': '60',
        'height': '20',
        'debug' : false,
        'fileObjName':'dto.upload',
        'auto': true,
        'removeCompleted':true,
        //上传成功
        'onSelect': function(){  
        	$("#gg").dialog("open");
        	$("#err").html("");
        }, 
        'onUploadSuccess':function(file, data, response){
        	$("#gg").dialog("close");
        	var obj=eval('('+data+')');
        	if(obj.opFlg=="1"){
        		$CommonUI.alert("导入成功");
        		$("#err").html(obj.msg);
        		//$("#importDialog").dialog('close');
        		$CommonUI.getDataGrid("#datagrid").datagrid('load');
        	}else{
        		$CommonUI.alert("导入失败");
        		$("#err").html(obj.msg);
        	};
        },
        //检测FLASH失败调用
        'onFallback': function() {
        	$("#gg").dialog("close");
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError': function(file, errorCode, errorMsg) {
        	$("#gg").dialog("close");
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
}
