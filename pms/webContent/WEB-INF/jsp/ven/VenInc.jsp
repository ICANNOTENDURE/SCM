<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dic</title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/ven/VenInc.js"></script>
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


	<table id="datagrid" title="供应商药品信息维护"
		data-options="toolbar:'#toolbar',fitColumns:true,singleSelect:true,pagination:true">
	</table>
	
	<div id="drugInfoWin" class="dialog" title="新增药品信息"
		data-options="modal:true,width:750,height:500,closed:true,buttons:'#btnDiv0'"
		style="vertical-align: middle;">
		<form id="incdetail" method="post">
			<table style="width: 100%;" >
				<tr style="display: none">
					<input type="hidden" name="dto.venInc.venIncId" />
				</tr>
				<tr>
					<td class="textLabel">药品代码:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncCode"
						data-options="required:true" id="venIncCode" /></td>
					<td class="textLabel">药品名称:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncName"
						data-options="required:true" id="venIncName"/></td>
				</tr>								
				<tr>
					<td class="textLabel">单位代码:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncUomcode"
						data-options="required:true" id="venIncUomcode" /></td>
					<td class="textLabel">单位名称:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncUomname"
						data-options="required:true" id="venIncUomname"/></td>
				</tr>
				<tr>
					<td class="textLabel">小单位代码:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncBuomcode"
						data-options="required:true" id="venIncBuomcode" /></td>
					<td class="textLabel">小单位名称:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncBuomname"
						data-options="required:true" id="venIncBuomname"/></td>
				</tr>
				<tr>
					<td class="textLabel">厂商ID:</td>
					<td class="textParent"><input style="width: 250px;"
						class="combobox" type="text" name="dto.venInc.venIncManfid"
						data-options="required:true" id="venIncManfid" /></td>
					<td class="textLabel">供应商ID:</td>
					<td class="textParent"><input style="width: 250px;"
						class="combobox" type="text" name="dto.venInc.venIncVenid"
						data-options="required:true" id="venIncVenid"/></td>
				</tr>
				<tr>
					<td class="textLabel">转换系数:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncFac"
						data-options="required:true" id="venIncFac" /></td>
					<td class="textLabel">价格:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncPrice"
						data-options="required:true" id="venIncPrice" /></td>
				</tr>
				<tr>
					<td class="textLabel">供应商系统药品ID:</td>
					<td class="textParent"><input style="width: 250px;"
						class="validatebox" type="text" name="dto.venInc.venIncVensysid"
						data-options="required:true" id="venIncVensysid" /></td>
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
					type="text" name="dto.venInc.venIncCode"
					id="venIncCodes" /></td>
			</tr>
			<tr>
				<td class="textLabel">药品描述:</td>
				<td class="textParent"><input style="width: 250px;"
					type="text" name="dto.venInc.venIncName"
					id="venIncNames" /></td>
			</tr>
			<tr>
				<td class="textLabel">厂商:</td>
				<td class="textParent"><input style="width: 250px;"
					class="combobox" type="text" name="dto.venInc.venIncManfid"
					id="venIncManfids" /></td>
			</tr>
			<tr>
				<td class="textLabel">供应商:</td>
				<td class="textParent"><input style="width: 250px;"
					class="combobox" type="text" name="dto.venInc.venIncVenid"
					id="venIncVenids" /></td>
			</tr>
			<tr>
				<td class="textLabel">系统药品ID:</td>
				<td class="textParent"><input style="width: 250px;"
					type="text" name="dto.venInc.venIncVensysid"
					id="venIncVensysids" /></td>
			</tr>
		</table>
		<div id="searchBtnDiv0" align="center">
			<table cellpadding="0" cellspacing="0" style="width: 100%">
				<tr>
					<td style="text-align: center;"><a id="searchIncInfoBtn" class="linkbutton"
						data-options="iconCls:'icon-save'">提交</a> <a id="searchIncCanBt"
						class="linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:selectCanBtClick()">取消</a></td>
				</tr>
			</table>
		</div>
	</div>
	
	
</body>
</html>
