<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/appLog/appLog.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/demo/demo.js"></script>
</head>
<body>
	<div id="toolbar" class="toolbar">
		<a id="addBt" class="linkbutton" onclick="javascript:addClick()"
			data-options="iconCls:'icon-add',plain:true">增加</a> <a
			class="linkbutton" data-options="iconCls:'icon-edit',plain:true"
			onclick="javascript:editRow()">修改</a> <a class="linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="javascript:delRow()">删除</a> <a class="linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="javascript:selectClick()">查询</a>
	</div>


	<table id="datagrid" style="height: 250px"
		data-options="toolbar:'#toolbar',fitColumns:true,singleSelect:true,pagination:true">
	</table>

	<div id="detailWin" class="dialog" title="新增demo"
		data-options="modal:true,width:300,height:200,closed:true,buttons:'#btnDiv'"
		style="vertical-align: middle;">
		<form id="detail" method="post">
			<div align="center">
				<input type="hidden" name="dto.demo.demoId" />
			</div>
			<br>
			<div align="center">
				<label>姓名:</label> <input type="text" id="demoName"
					name="dto.demo.demoName" />
			</div>
			<br />
			<div align="center">
				<label>年龄:</label> <input type="text" id="demoAge"
					name="dto.demo.demoAge" />
			</div>
			<br>
			<div align="center">
				<label>性别:</label> <input id="personName" class="combobox"
					name="dto.demo.demoGender"
					data-options="
                dict:'gender',
                dictType:'custom',
                valueField:'value',
                textField:'desc',
                multiple:false,
                required:true,
                panelHeight:'auto'" />

			</div>
			<div id="btnDiv">
				<a id="submitBtn" class="linkbutton"
					data-options="iconCls:'icon-save'">提交</a> <a id="cancelBtn"
					class="linkbutton" data-options="iconCls:'icon-cancel'"
					onclick="javascript:cancelClick()">取消</a>
			</div>
		</form>
	</div>

	<div id="selectWin" class="dialog" title="条件查询"
		data-options="modal:true,width:300,height:200,closed:true,buttons:'#searchBtnDiv'"
		style="vertical-align: middle;">
		<br>
		<div align="center">
			<label>姓名：</label> <input type="text" name="dto.demo.demoName" />
		</div>
		<div align="center">
			<label>年龄：</label> <input type="text" name="dto.demo.demoAge" />
		</div>
		<br>
		<div id="searchBtnDiv">
			<a id="selectBt" class="linkbutton"
				data-options="iconCls:'icon-save'">提交</a> <a id="selectCanBt"
				class="linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:selectCanBtClick()">取消</a>
		</div>
	</div>
</body>
</html>