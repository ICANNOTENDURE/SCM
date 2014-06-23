<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发货</title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/uploadify.css">
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/ven/Deliver.js"></script>		
</head>
<body>
	<div id="toolbar" class="toolbar">
	 	<div  style="margin-bottom:5px;margin-top:5px">
			
			<a  class="linkbutton" onclick="javascript:importInv()"
				data-options="iconCls:'icon-add',plain:true">导入发票(按订单)</a>
			<a  class="linkbutton" onclick="javascript:saveMain()"
				data-options="iconCls:'icon-save',plain:true">保存</a>	
			<a  class="linkbutton" onclick="javascript:searchDeliver()"
				data-options="iconCls:'icon-search',plain:true">查询发货单</a>
			<a  class="linkbutton" onclick="javascript:searchOrder()"
				data-options="iconCls:'icon-search',plain:true">查询订单</a>		
			<a  class="linkbutton" onclick="javascript:complete()"
				data-options="iconCls:'icon-save',plain:true">发货</a>
			<a  class="linkbutton" onclick="javascript:deleteOrder()"
				data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a  class="linkbutton" onclick="javascript:canclecomplete()"
				data-options="iconCls:'icon-remove',plain:true">取消完成状态</a>							
		 </div>
		 <div  style="margin-bottom:5px;margin-top:5px">
		 	<form id="saveOrUpdate">
		 	<table style="width: 100%;" >
		 		<tr style="display: none">
		 			<input type="hidden" id="deliveryId"/>
		 		</tr>
				<tr>
					<td class="textLabel">计划送达时间:</td>
					<td class="textParent"><input style="width: 250px;"
						class="datetimebox"  
						id="deliveryDate"/></td>
					<td class="textLabel">发货单状态:</td>
					<td class="textParent"><input style="width: 250px;"
						id="deliveryState" readonly="readonly"/></td>					
				
				</tr>
				<tr>
					<td class="textLabel">订单信息:</td>
					<td class="textParent"><textarea   style="width: 250px;height: 100px" id="deliverInfo" readonly="readonly"></textarea></td>	
					<td class="textLabel">备注:</td>
					<td class="textParent"><textarea  name="dto.ord.remark" style="width: 250px;height: 100px" id="remark"></textarea></td>					
				</tr>
			</table>
			</form>
		 </div>
	</div>


	<table id="datagrid"  title="发货单" class="datagrid"
		data-options="toolbar:'#toolbar',
		 			 fit:true,
					 fitColumns:true,
					 singleSelect:true,
					 pagination:true,
	    			 method:'post',
	    			 rownumbers:true,
	    			 onClickRow: onClickRow,
	    			 onAfterEdit:onAfterEdit,
	    			 striped:true,
	    			 url:'<%=request.getContextPath()%>/ven/venDeliverCtrl!listDeliverItm.htm'
					 ">
					 
		<thead>
			<tr>
				<th data-options="field:'deliveritmid',hidden:true"></th>
				<th data-options="field:'venincncode',width:90">代码</th>
				<th data-options="field:'venincname',width:200,formatter:GetName,styler:GetNameStyle">药品</th>
				<th data-options="field:'deliverqty',width:50,align:'center',editor : 'numberbox'">数量</th>
				<th data-options="field:'batno',width:100,align:'center',editor:'text'">批号</th>
				<th data-options="field:'expdate',width:100,align:'center',editor:'datebox'">效期</th>
				<th data-options="field:'invno',width:100,align:'center',editor:'text'">发票</th>	
				<th data-options="field:'orderqty',width:50,align:'center'">订单数</th>
				<th data-options="field:'sendedqty',width:50,align:'center'">历史已发货数</th>	
				<th data-options="field:'uom',width:80,align:'center'">单位</th>
				<th data-options="field:'rp',width:50,align:'center',editor : {
							type : 'numberbox',
                            options : {
                                precision:2
                            }
                        }
				">价格</th>
				
				<th data-options="field:'manf',width:150">产地</th>
				<th data-options="field:'delte',width:140,formatter:deleteR">编辑</th>
			</tr>
		</thead>
	</table>

	<div id="importDialog" class="dialog" title="导入发票"
		style="width:400px;height: 200px; background-color: #F5FAFD;"
		data-options="
				modal:true,
		        closed:true,
				collapsible:false,
				minimizable:false,
				maximizable:false">
			<table style="width: 100%;">
				<tr>
					<td class="textLabel" style="text-align: right; width: 40%">导入Excel文件:</td>
					<td class="textParent" style="text-align: left; width: 60%"><input
						style="width: 250px;" class="validatebox" type="file"
						name="upload" id="upload"></input></td>
				</tr>
			</table>
	</div>
	

	
	<!-- 弹出查询订单 -->
	<div id="searchDeliver" class="dialog" title="查询发货单（双击选择）"
		data-options="modal:true,width:1000,height:450,closed:true,maximizable:true"
		style="vertical-align: middle">
		<table id="searchDeliverTable" />
	</div>
	<!-- 弹出查询订单toolbar-->
	<div id="searchDeltoolbar" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			订单接收开始时间: <input class="datebox" style="width:100px" id="acpStDate">
			订单接收结束时间: <input class="datebox" style="width:100px" id="acpEdDate">
			医&nbsp;&nbsp;&nbsp;&nbsp;院:
			<input class="combobox" panelHeight="auto" style="width:250px" id="hopSearch"/>
			
		</div>
		<div style="margin-bottom:5px">
			订单发货开始日期: <input class="datebox" style="width:100px" id="delStDate">
			订单发货结束日期: <input class="datebox" style="width:100px" id="delEdDate">
			入库科室:
			<input class="combobox" panelHeight="auto" style="width:250px" id="purlocSearch"/>
			
		 </div>
		 <div style="margin-bottom:5px">
		 	加急:<input type="checkbox" id="emflag"/>
			订单发货状态
			<select class="combobox" panelHeight="auto" style="width:150px" id="delFlag">
				<option value="2">已接收</option>
				<option value="3">已发货</option>
			</select>
		 	<a href="#" class="linkbutton" iconCls="icon-search" id="search">查询</a>
		 </div>
		</div>			
</body>
</html>
