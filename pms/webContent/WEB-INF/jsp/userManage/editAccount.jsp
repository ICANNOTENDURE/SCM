<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
 <script>

    $(document).ready(function () {
    	$CommonUI.getDialog("#Dialog1").dialog("center");
    	$CommonUI.getDialog("#Dialog1").dialog("open");	
    	
    });
</script>
</head>
<body >
	
	<div id="Dialog1" class="dialog" title="修改个人资料"
		style="width: 900px; height: 320px"
		data-options="
				buttons:'#Button',
				modal:true,
		        closed:true,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				shadow:true">
		<form id="saveOrUpdateForm" method="post">

			<table id="saveOrUpdateTable" style="width: 100%;">
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">账户名:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.accountAlias"
						class="validatebox" type="text" style="width: 200px;"
						data-options="
			      required:true,
			      validType:['accountAlias']
			      " />
					</td>
				
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">真实姓名:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.realName"
						class="validatebox" type="text" style="width: 200px;" /></td>
					<td class="textLabel" style="text-align: right; width: 20%">性别:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						id="normalUserSex" class="combobox" style="width: 200px;"
						name="normalAccountDto.normalAccount.normalUser.sex"
						data-options="
	              dict:'gender',
	              valueField:'value',
	              textField:'desc',
	              multiple:false,
	              required:false,
	              width:200,
	              panelHeight:'auto'" />
					</td>
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">民族:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.nation"
						class="validatebox" type="text" style="width: 200px;" /></td>
					<td class="textLabel" style="text-align: right; width: 20%">籍贯:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.province"
						class="validatebox" type="text" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">出生日期:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						id="normalUserBirthday" class="datebox" style="width: 200px;"
						type="text"
						name="normalAccountDto.normalAccount.normalUser.birthday"></input>
					</td>
					<td class="textLabel" style="text-align: right; width: 20%">身份证号:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.idCard"
						class="validatebox" type="text" style="width: 200px;"
						data-options="
					validType:['idCard']
			      " /></td>
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">手机号:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.telephone"
						class="validatebox" type="text" style="width: 200px;"
						data-options="
					validType:['telphone']
			      " /></td>
					<td class="textLabel" style="text-align: right; width: 20%">住址:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.address"
						class="validatebox" type="text" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">电子邮件:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.email"
						class="validatebox" type="text" style="width: 200px;"
						data-options="
					validType:['email']
			      " /></td>
					<td class="textLabel" style="text-align: right; width: 20%">邮编:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.zipCode"
						class="validatebox" type="text" style="width: 200px;" /></td>
				</tr>

				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">描述:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.description"
						class="validatebox" type="text" style="width: 200px;" /></td>
					<td class="textLabel" style="text-align: right; width: 20%">人员类型:</td>
					<td class="textParent" style="text-align: left; width: 30%">
					<select class="combobox" panelHeight="auto" style="width:200px"  
					     name="normalAccountDto.normalAccount.normalUser.type" id="type"
					     data-options="required:true">
					<option value="0">工作人员</option>
					<option value="1">医院用户</option>
					<option value="2">供应商用户</option>
					</select></td>	
				</tr>
				<tr>
					<td class="textLabel" style="text-align: right; width: 20%">医院:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input style="width: 200px;" 
					 class="combobox" id="hop"/></td>
					<td class="textLabel" style="text-align: right; width: 20%">科室:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input style="width: 200px;" 
					name="normalAccountDto.normalAccount.normalUser.locId"  class="combobox" id="loc"/></td>	
				</tr>

				<tr style="display: none;">
					<td class="textLabel" style="text-align: right; width: 20%">会话密钥:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.sessionKey"
						class="validatebox" type="text" style="width: 200px;" /></td>
					<td class="textLabel" style="text-align: right; width: 20%">账户密码:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.password" class="validatebox"
						type="text" style="width: 200px;" /></td>
				</tr>
				<tr style="display: none;">
					<td class="textLabel" style="text-align: right; width: 20%">普通账户信息标识符:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.accountId"
						class="validatebox" type="text" style="width: 200px;" /></td>
					<td class="textLabel" style="text-align: right; width: 20%">普通用户信息标识符:</td>
					<td class="textParent" style="text-align: left; width: 30%"><input
						name="normalAccountDto.normalAccount.normalUser.userId"
						class="validatebox" type="text" style="width: 200px;" /></td>
				</tr>



			</table>

		</form>
	</div>
	<div id="Button" align="center">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="text-align: center;"><a id="submitFuncBtn"
					href="javascript:void(0)" class="linkbutton"
					data-options="iconCls:'icon-save',plain:true">保存</a></td>
			</tr>
		</table>
	</div>

	
</body>
</html>