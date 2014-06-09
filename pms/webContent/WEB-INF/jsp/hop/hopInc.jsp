<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dic</title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/appLog/appLog.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/hop/hopInc.js"></script>
</head>
<body>
	<div id="toolbar" class="toolbar">
		<a id="addBt" class="linkbutton" onclick="javascript:addClick()"
			data-options="iconCls:'icon-add',plain:true">增加</a> <a
			class="linkbutton" data-options="iconCls:'icon-edit',plain:true"
			onclick="javascript:editRow()">修改</a> <a class="linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="javascript:delRow()">删除</a> <a class="linkbutton"
			data-options="iconCls:'icon-search',plain:true"
			onclick="javascript:selectClick()">查询</a>
	</div>


	<table id="datagrid" title="药品信息维护"
		data-options="toolbar:'#toolbar',fitColumns:true,singleSelect:true,pagination:true">
	</table>
	
	<div id="drugInfoWin" class="dialog" title="新增药品信息"
		data-options="modal:true,width:750,height:500,closed:true,buttons:'#btnDiv0'"
		style="vertical-align: middle;">
		<form id="incdetail" method="post">
			<table style="width: 100%;" cellspacing="10">
				<tr style="display: none">
					<input type="hidden" name="dto.hopInc.incId" />
				</tr>
				<tr>
					<td class="textLabel">药品代码:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incCode"
						data-options="required:true" id="inciCode" /></td>
					<td class="textLabel">药品名称:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incName"
						data-options="required:true" id="inciName"/></td>
				</tr>								
				<tr>
					<td class="textLabel">单位代码:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incUomcode"
						data-options="required:true" id="incUomcode" /></td>
					<td class="textLabel">单位名称:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incUomname"
						data-options="required:true" id="incUomname"/></td>
				</tr>
				<tr>
					<td class="textLabel">小单位代码:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incBuomcode"
						data-options="required:true" id="incBuomcode" /></td>
					<td class="textLabel">小单位名称:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incBuomname"
						data-options="required:true" id="incBuomname"/></td>
				</tr>
				<tr>
					<td class="textLabel">厂商ID:</td>
					<td class="textParent"><input style="width: 250px;"
						class="combobox" type="text" name="dto.hopInc.incManfid"
						data-options="required:true" id="incManfid" /></td>
					<td class="textLabel">医院ID:</td>
					<td class="textParent"><input style="width: 250px;"
						class="combobox" type="text" name="dto.hopInc.incHospid"
						data-options="required:true" id="incHospid"/></td>
				</tr>
				<tr>
					<td class="textLabel">转换系数:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incFac"
						data-options="required:true" id="incFac" /></td>
					<td class="textLabel">进价:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incRp"
						data-options="required:true" id="incRp" /></td>
				</tr>
				<tr>
					<td class="textLabel">His药品ID:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.hopInc.incHissysid"
						data-options="required:true" id="incHissysid" /></td>
				</tr>
				<tr>
					<td class="textLabel">图片顺序:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="xxxx"
						data-options="required:true" id="xxxx" /></td>
					<td class="textLabel">(以^作为分隔)</td>
				</tr>
				<tr>					
					<td class="textLabel">图片路径:</td>
					<td class="textParent"><input style="width: 250px;"
					   	id="file_upload" type="file" name="upload" multiple="true"
						data-options="required:true" id="xxx" /><input type="button" 
						name="uploadPics" value="上传图片" id="uploadPic"/>
					<div id="queues" name="queue"></div></td>
					
				</tr>											
			</table>			
					
			<div id="btnDiv0" align="center">
				<table cellpadding="0" cellspacing="0" style="width: 100%">
					<tr>
						<td style="text-align: center;"><a id="saveOrUpdateIncBtn" class="linkbutton"
							data-options="iconCls:'icon-save'">提交</a> <a id="cancelBtn"
							class="linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="javascript:cancelClick()">取消</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>	
			
	<div id="searchIncWin" class="dialog" title="查询药品信息"
		data-options="modal:true,width:400,height:250,closed:true,buttons:'#searchBtnDiv0'"
		style="vertical-align: middle;">
		<table style="width: 100%;" cellspacing="10">				
			<tr>
				<td class="textLabel">药品代码:</td>
				<td class="textParent"><input style="width: 250px;"
					type="text" name="dto.hopInc.incCode"
					id="incCodes" /></td>
			</tr>
			<tr>
				<td class="textLabel">药品描述:</td>
				<td class="textParent"><input style="width: 250px;"
					type="text" name="dto.hopInc.incName"
					id="incNames" /></td>
			</tr>
			<tr>
				<td class="textLabel">厂商:</td>
				<td class="textParent"><input style="width: 250px;"
					class="combobox" type="text" name="dto.hopInc.incManfid"
					id="incManfs" /></td>
			</tr>
			<tr>
				<td class="textLabel">医院:</td>
				<td class="textParent"><input style="width: 250px;"
					class="combobox" type="text" name="dto.hopInc.incHospid"
					id="incHosps" /></td>
			</tr>
			<tr>
				<td class="textLabel">His药品:</td>
				<td class="textParent"><input style="width: 250px;"
					type="text" name="dto.hopInc.incHissysid"
					id="incHissysids" /></td>
			</tr>
		</table>
		<div id="searchBtnDiv0" align="center">
			<table cellpadding="0" cellspacing="0" style="width: 100%">
				<tr>
					<td style="text-align: center;"><a id="searchIncInfoBtn" class="linkbutton"
						data-options="iconCls:'icon-save'">提交</a> <a id="searchHospCanBt"
						class="linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:selectCanBtClick()">取消</a></td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>