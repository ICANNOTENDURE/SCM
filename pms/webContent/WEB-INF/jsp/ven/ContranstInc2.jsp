<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
 <script>
    $(function(){
    	$("#searchHopInc").on('click', function() {
    		$CommonUI.getDataGrid('#datagrid').datagrid({  
    		    url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listContrantInc.htm',
    		    queryParams: {
    		    	'dto.venIncContranstDto.incName': $("#incHopName").val(),
    		    	'dto.venIncContranstDto.incCode': $("#incHopCode").val(),
    		    	'dto.venIncContranstDto.flag': $("#hopFlag").combobox('getValue'),
    			}

   		 	});
   		});
    	
    	$("#searchVenInc").on('click', function() {
    		$CommonUI.getDataGrid('#datagrid2').datagrid({  
    		    url:$WEB_ROOT_PATH+'/ven/venIncCtrl!listContrantInc.htm',
    		    queryParams: {
    		    	'dto.venIncContranstDto.incName': $("#incHopName").val(),
    		    	'dto.venIncContranstDto.incCode': $("#incHopCode").val(),
    		    	'dto.venIncContranstDto.flag': $("#hopFlag").combobox('getValue'),
    			}

   		 	});
   		});
    });
    </script>

</head>
<body >
	<div id="toolbar" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			药品名称: <input id="incHopName" style="width: 100px;"
			type="text" />
			药品代码: <input id="incHopCode" style="width: 100px;"
			type="text" />
			别名: <input id="incHopAlias" style="width: 100px;"
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
			药品名称: <input id="incHopName" style="width: 100px;"
			type="text" />
			药品代码: <input id="incHopCode" style="width: 100px;"
			type="text" />
			别名: <input id="incHopAlias" style="width: 100px;"
			type="text" />
			对应状态:
			<select class="combobox" panelHeight="auto" style="width:100px" id="hopFlag">
				<option value="0">空</option>
				<option value="1">以对照</option>
				<option value="2">未对照</option>
			</select>
			供应商:<input style="width: 100px;"
						class="combobox" type="text" 
						 id="hop" />
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
								 ">
								 
					<thead>
						<tr>
							<th data-options="field:'hopincid',hidden:true">IncId ID</th>
							<th data-options="field:'vendorname',width:100,sortable:true">供应商</th>
							<th data-options="field:'hopinccode',width:50,sortable:true">药品代码</th>
							<th data-options="field:'hopincname',width:100,sortable:true">药品名称</th>
							<th data-options="field:'manf',width:100,sortable:true">产地</th>
							<th data-options="field:'spec',width:100,sortable:true">规格</th>
							<th data-options="field:'uom',width:50,sortable:true">单位</th>
							<th data-options="field:'hopcode',width:60,sortable:true">医院药品代码</th>
							<th data-options="field:'hopname',width:100,sortable:true">医院药品名称</th>
							<th data-options="field:'fac',width:70,sortable:true">与医院单位转换系数</th>
							<th data-options="field:'contranst',width:70,sortable:true">对照</th>
						</tr>
					</thead>
				</table>
        
        </div>
        <div data-options="region:'center',title:'医院药品',iconCls:'icon-ok'" >
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
