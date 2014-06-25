<!-- zxx 2014-04-19 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dhcc/pms/ven/saveOrUpdate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/imageTrans/CJL.0.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/imageTrans/ImageTrans.js"></script>		
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/uploadify.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome/css/font-awesome-ie7.min.css">
<style>
.idContainer{
		border:1px solid #000;
		width:300px; 
		height:250px; 
		background:#FFF center no-repeat;
		position: absolute;
		align:"center";
		}
</style>		
</head>
<body>
	<!-- 增加修改界面 -->
		<form id="detail" method="post" >
			<table style="width: 100%;">
				<tr style="display: none">
					<input type="hidden" name="dto.vendor.vendorId" value="<s:property value="dto.vendor.vendorId"/>"/>
				</tr>
				<tr>
					<td class="textLabel">代码:</td>
					<td class="textParent"><input type="text"
						style="width: 250px;" name="dto.vendor.code" value="<s:property value="dto.vendor.code"/>"/></td>
					<td class="textLabel">名称:</td>
					<td class="textParent"><input style="width: 250px;"
						type="text" name="dto.vendor.name" value="<s:property value="dto.vendor.name"/>" /></td>
				</tr> 
				<tr>
					<td class="textLabel">地址:</td>
					<td class="textParent"><input type="text"
						style="width: 250px;" name="dto.vendor.address" value="<s:property value="dto.vendor.address"/>"/></td>
					<td class="textLabel">传真:</td>
					<td class="textParent"><input style="width: 250px;"
						type="text" name="dto.vendor.fax" value="<s:property value="dto.vendor.fax"/>"/></td>
				</tr>
				<tr>
					<td class="textLabel">电话:</td>
					<td class="textParent"><input style="width: 250px;"
						type="text" name="dto.vendor.tel" value="<s:property value="dto.vendor.tel"/>"></input></td>
					<td class="textLabel">账号:</td>
					<td class="textParent"><input style="width: 250px;"
						type="text" name="dto.vendor.account"  value="<s:property value="dto.vendor.account"/>"/></td>
				</tr>

				<tr>
					<td class="textLabel">联系人:</td>
					<td class="textParent"><input style="width: 250px;"
						type="text" name="dto.vendor.contact" value="<s:property value="dto.vendor.contact"/>"></input></td>
					<td class="textLabel">上级供应商:</td>
					<td class="textParent"><input style="width: 250px;"
						class="combobox" name="dto.vendor.parentId" id="parentId"  value="<s:property value="dto.vendor.parentId"/>" /></td>
				</tr>
				<tr>
					<td class="textLabel">HIS标识:</td>
					<td class="textParent"><input style="width: 250px;" type="text"
						name="dto.vendor.hisId" value="<s:property value="dto.vendor.hisId"/>"></input></td>
					<td class="textLabel">别名:</td>
					<td class="textParent"><input style="width: 250px;" type="text"
					name="dto.vendor.alias" value="<s:property value="dto.vendor.alias"/>"></input></td>	
				</tr>
				<tr>
					<td class="textLabel">邮箱:</td>
					<td class="textParent"><input style="width: 250px;" type="text"
						name="dto.vendor.email" value="<s:property value="dto.vendor.email"/>"></input></td>
				</tr>
				<tr>
					<table id="subDetail">
						<s:iterator value="dto.venQualifTypeVOList" status="all"
							id="venQualifys">

							<tr>
								<input type="hidden" value="${venQualifys.qualif}"
									name="dto.venQualificationList.venQualification.venQualificationId" id="${venQualifys.qualif}" />
								<input type="hidden" id="${venQualifys.type}"  value="${venQualifys.type}"
									name="dto.venQualificationList.venQualification.venQualifyTypeId" />
									
								<td class="textLabel" >${venQualifys.name}：</td>
								
								<td class="textParent"><input id="expdate" class="datebox"
									style="width: 200px;" type="text"
									name='dto.venQualificationList.venQualification.expdate'  value="${venQualifys.expdate}"/></td>
									
								<td class="textLabel" style="text-align: left; width: 100%">
									<input id="file_upload${venQualifys.type}" name="upload" type="file" multiple="true" style="width: 100%">
									 <div id="queue${venQualifys.type}" name="queue"></div>
									 <s:iterator value="venQualifPics" status="all" id="venQualifPicss">
										<a><s:property value="#venQualifPicss.name" /></a>
										<!--  <a href="<%=request.getContextPath()%><s:property value="#venQualifPics.path"/>">下载</a>-->
										<a href="javascript:deletePic(<s:property value="#venQualifPicss.id"/>)" class="linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
										<a href="javascript:viewPic(<s:property value="#venQualifPicss.id"/>)" class="linkbutton" data-options="iconCls:'icon-search',plain:true">预览</a>
										<div id="idContainer<s:property value="#venQualifPicss.id" />"   class="idContainer" style="display: none;z-index:100"><div style="float: left;width: 20px;height: 20px;z-index: 9999;cursor: pointer;" onclick="closePic(<s:property value="#venQualifPicss.id" />);"><i class="icon-remove-sign icon-2x"></i></div></div>
										<script>
											(function(){
											
											    var container = $$("idContainer<s:property value="#venQualifPicss.id" />"), src ="<%=request.getContextPath()%>/uploads/<s:property value="#venQualifPicss.path"/>",
											    
												options = {
													onPreLoad: function(){ container.style.backgroundImage = "url('http://images.cnblogs.com/cnblogs_com/cloudgamer/169629/o_loading.gif')"; },
													onLoad: function(){ container.style.backgroundImage = ""; },
													onError: function(err){ container.style.backgroundImage = ""; alert(err); }
												},
												it = new ImageTrans( container, options );
											    it.load(src);
											})()
											</script>
											<br>
									 </s:iterator>
								</td>
							</tr>

						</s:iterator>
					</table>
				</tr>
			</table>
		</form>		
	<div style="border-top: 1px solid #DEDEDE; background: #F5F5F5">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="text-align: center;"><a id="submitBtn"
					href="javascript:void(0)" class="linkbutton" >保存</a> </td>
			</tr>
		</table>
	</div>
</body>
</html>
