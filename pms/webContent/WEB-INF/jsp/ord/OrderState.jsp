<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dic</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/easyui/themes/metro-blue/easyui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.8.3.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.json-2.4.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dhcc/pms/ord/datagrid-detailview.js"></script>	

<script type="text/javascript" src="<%=request.getContextPath()%>/js/dhcc/pms/ord/OrderState.js"></script>
<!--  -->	
<link href="<%=request.getContextPath()%>/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />	


</head>
<body>
	
	<table id="datagrid" />
   

	<div id="selectWin" class="dialog" title="条件查询"
		data-options="modal:true,width:350,height:150,closed:true,buttons:'#searchBtnDiv'"
		style="vertical-align: middle;">
		<table id="saveOrUpdateTable" style="width: 100%;">
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">状态代码:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						style="width: 250px;" class="validatebox" type="text"
						name="dto.state.stateCode" ></input>
					</td>
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">状态名称:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						style="width: 250px;" class="validatebox" type="text"
						name="dto.state.stateName" ></input>
					</td>
				</tr>
			</table>	
			<div id="searchBtnDiv" align="center">
				<table cellpadding="0" cellspacing="0" style="width: 100%">
					<tr>
						<td style="text-align: center;"><a id="selectBt" class="linkbutton"
							data-options="iconCls:'icon-save'">提交</a> <a id="cancelBtn"
							class="linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="javascript:cancelClick()">取消</a>
						</td>
					</tr>
				</table>
			</div>
	</div>
	
	
	<div id="detail" class="dialog" title="订单明细"
		data-options="modal:true,width:700,height:350,closed:true,maximizable:true"
		style="vertical-align: middle">
		<table id="detailgrid" />
	</div>
	
	
	<div id="toolbar" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			订单开始日期: <input class="datebox" style="width:100px" id="stdate">
			结束日期: <input class="datebox" style="width:100px" id="eddate">
			状态:
			<input class="combobox" panelHeight="auto" style="width:150px" id="state"/>
			供应商:
			<input class="combobox" panelHeight="auto" style="width:150px" id="vendor"/>
			采购科室:
			<input class="combobox" panelHeight="auto" style="width:150px" id="purloc"/>
			
		 </div>
		 <div style="margin-bottom:5px">
			要求到达日期: <input class="datebox" style="width:100px" id="reqStDate">
			结束日期: <input class="datebox" style="width:100px" id="reqEdDate">
			加急:
			<select class="combobox" panelHeight="auto" style="width:100px" id="emflag">
				<option value="check"></option>
				<option value="checked">加急</option>
			</select>

			<a href="#" class="linkbutton" iconCls="icon-search" id="search">查询</a>
			</div>
		 </div>			
</body>
</html>
