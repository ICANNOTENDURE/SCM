<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
 <script>
    
    function ConT(value,row,index){
		if(row.venid==null){
			return '<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:ConTra('+row.hopvenid+')" data-options="iconCls:"icon-save"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left">对照</span></span></a>';
		}else{
			return '<a id="addBt" class="dhc-linkbutton l-btn l-btn-plain" onclick="javascript:deleteTra('+row.hopvenid+')" data-options="iconCls:"icon-remove"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left">删除</span></span></a>';
			
		}
	};
	function ConTra(hopvenid){
		if ($CommonUI.getDataGrid("#datagrid").datagrid('getSelections').length != 1) {
			$CommonUI.alert('请选一个供应商对照');
			return;
		}
		var row =$("#datagrid").datagrid('getSelected');
		var venid=row.vendorId;
		$.post(
			$WEB_ROOT_PATH+'/hop/hopVendorCtrl!contranstVendor.htm',
			{
				'dto.hopVendor.hopVendorId': hopvenid,
				'dto.hopVendor.hopVenId': venid,
			},
			function(data){
				if(data.dto.opFlg=="1"){
					$CommonUI.alert(row.name+",对照成功!");
					$CommonUI.getDataGrid('#datagrid2').datagrid('reload');
					//$CommonUI.autoCloseRightBottomMessage("("+row.name+")"+row.name+"对照成功","消息",3000,'slide');
				}
			},
			"json"
		);
	}
	function deleteTra(hopvenid){
		$.post(
				$WEB_ROOT_PATH+'/hop/hopVendorCtrl!contranstVendor.htm',
				{
					'dto.hopVendor.hopVendorId': hopvenid,
					'dto.hopVendor.hopVenId': null,
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
    $(function(){
    	$CommonUI.getDataGrid('#datagrid').datagrid({
    		onDblClickRow: function(rowIndex, rowData){
    			$CommonUI.getDataGrid('#datagrid2').datagrid({
    				url:$WEB_ROOT_PATH+'/hop/hopVendorCtrl!listHopCon.htm',
        		    queryParams: {
        		    	'dto.hopVendor.hopVenId': rowData.vendorId,
        			}
    			});
    		}

    	});
    	
    	$CommonUI.getComboBox("hop").combobox({
			url:$WEB_ROOT_PATH+'/hop/hospitalCtrl!getHospInfo.htm',
			valueField:'hospitalId',							
			textField:'hospitalName'
		});
    	$("#searchVen").on('click', function() {
    		$CommonUI.getDataGrid('#datagrid').datagrid({  
    		    url:$WEB_ROOT_PATH+'/ven/vendorCtrl!list.htm',
    		    queryParams: {
    		    	'dto.vendor.name': $("#venName").val(),
    		    	'dto.vendor.code': $("#venCode").val(),
    		    	'dto.vendor.alias': $("#venAlias").val(),
    			}

   		 	});
   		});
    	
    	$("#searcHop").on('click', function() {
    		$CommonUI.getDataGrid('#datagrid2').datagrid({  
    		    url:$WEB_ROOT_PATH+'/hop/hopVendorCtrl!listHopCon.htm',
    		    queryParams: {
    		    	'dto.hopVendor.hopCode': $("#HopCode").val(),
    		    	'dto.hopVendor.hopName': $("#HopName").val(),
    		    	'dto.hopVendor.hopAlias': $("#HopAlias").val(),
    		    	'dto.flag':$("#flag").combobox('getValue'),
    		    	'dto.hopVendor.hopHopId': $("#hop").combobox('getValue'),
    			}

   		 	});
   		});
    });
    </script>

</head>
<body >
	<div id="toolbar" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			名称: <input id="venName" style="width: 100px;"
			type="text" />
			代码: <input id="venCode" style="width: 100px;"
			type="text" />
			别名: <input id="venAlias" style="width: 100px;"
			type="text" />
			<a href="#" class="linkbutton" iconCls="icon-search" id="searchVen">查询</a>
		 </div>
	</div>	
    <div id="toolbar2" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			名称: <input id="HopName" style="width: 100px;"
			type="text" />
			代码: <input id="HopCode" style="width: 100px;"
			type="text" />
			别名: <input id="HopAlias" style="width: 100px;"
			type="text" />
		  </div>
		  <div  style="margin-bottom:5px;margin-top:5px">	
			状态:
			<select class="combobox" panelHeight="auto" style="width:100px" id="flag">
				<option value="0">空</option>
				<option value="1">以对照</option>
				<option value="2">未对照</option>
			</select>
			医院:
			<input style="width: 100px;"
						class="combobox" type="text" 
						 id="hop" />
			<a href="#" class="linkbutton" iconCls="icon-search" id="searcHop">查询</a>
		 </div>
	</div>
  <div class="layout" data-options="fit:'true',border:true">
        <div data-options="region:'east',title:'医院供应商',iconCls:'icon-ok',split:true" style="width:550px">
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
								 ">
								 
					<thead>
						<tr>
							<th data-options="field:'hopvenid',hidden:true">IncId ID</th>
							<th data-options="field:'hopvenname',width:100,sortable:true">供应商</th>
							<th data-options="field:'hopvencode',width:100,sortable:true">医院供应商代码</th>
							<th data-options="field:'hopname',width:50,sortable:true">医院</th>
							<th data-options="field:'venname',width:50,sortable:true">对照供应商</th>
							<th data-options="field:'venid',hidden:true">xx</th>
							<th data-options="field:'operate',width:50,sortable:true,formatter:ConT">对照</th>
						</tr>
					</thead>
				</table>
        
        </div>
        <div data-options="region:'center',title:'供应商(双击查看已经对照医院供应商)',iconCls:'icon-ok'" >
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
							<th data-options="field:'vendorId',hidden:true">IncId ID</th>
							<th data-options="field:'code',width:100,sortable:true">代码</th>
							<th data-options="field:'name',width:100,sortable:true">供应商</th>
						</tr>
					</thead>
				</table>
        </div>
    </div>
</body>
</html>
