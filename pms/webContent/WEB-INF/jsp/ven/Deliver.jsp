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
			
			<a id="addBt" class="linkbutton" onclick="javascript:importDel()"
				data-options="iconCls:'icon-add',plain:true">导入发票</a>
			<a id="addBt" class="linkbutton" onclick="javascript:saveMain()"
				data-options="iconCls:'icon-save',plain:true">保存</a>	
			<a id="addBt" class="linkbutton" onclick="javascript:searchOrder()"
				data-options="iconCls:'icon-search',plain:true">查询</a>	
			<a id="addBt" class="linkbutton" onclick="javascript:complete()"
				data-options="iconCls:'icon-save',plain:true">发货</a>
			<a id="addBt" class="linkbutton" onclick="javascript:deleteOrder()"
				data-options="iconCls:'icon-remove',plain:true">删除</a>
				<a id="addBt" class="linkbutton" onclick="javascript:canclecomplete()"
				data-options="iconCls:'icon-remove',plain:true">取消完成状态</a>							
		 </div>
		 <div  style="margin-bottom:5px;margin-top:5px">
		 	<form id="saveOrUpdate">
		 	<table style="width: 100%;" >
		 		<tr style="display: none">
		 			<input type="hidden" id="orderId"/>
		 		</tr>
				<tr>
					<td class="textLabel">计划送达时间:</td>
					<td class="textParent"><input style="width: 250px;"
						class="datebox"  name="dto.order.deliveryDate"
						id="deliveryDate"/></td>				
				
				</tr>
				<tr>
					<td class="textLabel">订单信息:</td>
					<td class="textParent"><textarea  name="dto.ord.remark" style="width: 250px;height: 100px" id="remark" readonly="readonly"></textarea></td>	
					<td class="textLabel">备注:</td>
					<td class="textParent"><textarea  name="dto.ord.remark" style="width: 250px;height: 100px" id="remark"></textarea></td>					
				</tr>
			</table>
			</form>
		 </div>
	</div>


	<table id="datagrid"  title="手工建立订单" class="datagrid"
		data-options="toolbar:'#toolbar',
		 			 fit:true,
					 fitColumns:true,
					 singleSelect:true,
					 pagination:true,
	    			 method:'post',
	    			 rownumbers:true,
	    			 onClickCell: onClickCell,
	    			 onAfterEdit:onAfterEdit,
	    			 striped:true,
	    			 url:'<%=request.getContextPath()%>/ord/orderStateCtrl!listOrdItm.htm'
					 ">
					 
		<thead>
			<tr>
				<th data-options="field:'orderitmid',hidden:true">IncId ID</th>
				<th data-options="field:'orderid',hidden:true">IncId ID</th>
				<th data-options="field:'hopincid',hidden:true">IncId ID</th>
				<th data-options="field:'inccode',width:90">代码</th>
				<th data-options="field:'incname',width:100">药品</th>
				<th data-options="field:'qty',width:80,align:'center',editor : {
							type : 'numberbox',
                            options : {
                                required : true
                            }
                        }
					,sortable:true">数量</th>
				<th data-options="field:'uom',width:80,align:'center',editor:'text',sortable:true">单位</th>
				<th data-options="field:'rp',width:80,align:'center',editor:'numberbox'">价格</th>
				<th data-options="field:'batno',width:80,align:'center',editor:'numberbox'">批号</th>
				<th data-options="field:'expdate',width:80,align:'center',editor:'numberbox'">效期</th>
				<th data-options="field:'invno',width:80,align:'center',editor:'numberbox'">发票</th>
				<th data-options="field:'manf',width:90">产地</th>
				<th data-options="field:'delte',width:90,formatter:deleteR">编辑</th>
			</tr>
		</thead>
	</table>

	<div id="importDialog" class="dialog" title="导入订单"
		style="width:400px;height: 200px; background-color: #F5FAFD;"
		data-options="
				modal:true,
		        closed:true,
				collapsible:false,
				minimizable:false,
				maximizable:false">
			<table id="addFuncsTable" style="width: 100%;">
				<tr>
					<td class="textLabel" style="text-align: right; width: 40%">导入Excel文件:</td>
					<td class="textParent" style="text-align: left; width: 60%"><input
						style="width: 250px;" class="validatebox" type="file"
						name="upload" id="orderUpload"></input></td>
				</tr>
			</table>
	</div>
	

	
	<!-- 弹出查询订单 -->
	<div id="searchOrder" class="dialog" title="查询订单（双击选择）"
		data-options="modal:true,width:850,height:450,closed:true,maximizable:true"
		style="vertical-align: middle">
		<table id="searchOrderTable" />
	</div>
	<!-- 弹出查询订单toolbar-->
	<div id="searchtoolbar" style="height: auto">
		  <div  style="margin-bottom:5px;margin-top:5px">
			订单开始日期: <input class="datebox" style="width:100px" id="stdate">
			结束日期: <input class="datebox" style="width:100px" id="eddate">
			医院:
			<input class="combobox" panelHeight="auto" style="width:250px" id="vendorSearch"/>
			加急:<input type="checkbox" id="emflag"/>
			订单发货状态
			<select class="combobox" panelHeight="auto" style="width:150px" id="emflag">
				<option value="0">未发货</option>
				<option value="1">部分发货</option>
				<option value="1">部分发货</option>
			</select>
		</div>
		 <div style="margin-bottom:5px">
			要求到达日期: <input class="datebox" style="width:100px" id="reqStDate">
			结束日期: <input class="datebox" style="width:100px" id="reqEdDate">
			
			入库科室:
			<input class="combobox" panelHeight="auto" style="width:250px" id="purlocSearch"/>
			<a href="#" class="linkbutton" iconCls="icon-search" id="search">查询</a>
		 </div>
		</div>			
</body>
</html>
