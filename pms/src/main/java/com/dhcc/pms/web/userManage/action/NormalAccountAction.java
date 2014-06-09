package com.dhcc.pms.web.userManage.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.annotation.JsonResult4Pojo;
import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.pms.dto.userManage.NormalAccountDto;

/**
 * 标题: NormalAccountAction.java
 * 业务描述：安全运维管理平台
 * 公司:东华软件股份公司
 * 版权:dhcc2013
 * @author 聂文来
 * @date 2013年10月16日
 * @version V1.0 
 */
@Namespace(value="/normalAccount")
@Scope("prototype")
@Action(value="normalAccountCtrl",results={
		@Result(name="normalAccountMain",location="/WEB-INF/jsp/userManage/normalAccount.jsp"),
		@Result(name="normalAccountList",location="/WEB-INF/jsp/userManage/normalAccount.jsp"),
		@Result(name="deleteNormalAccount",location="/WEB-INF/jsp/userManage/normalAccount.jsp")
})
@JsonResult4Pojo("getNormalAccountRole:normalAccountDto.roles;saveOrUpdateNormalAccount,saveNormalAccountRole:normalAccountDto;getNormalAccountFunc:normalAccountDto.funcs;updatePassword:normalAccountDto")
public class NormalAccountAction extends BaseAction{

	/**  
	* 字段:      序列化id
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	
	private NormalAccountDto normalAccountDto = new NormalAccountDto();
	
	/**  
	 * @return normalAccountDto 
	 */
	public NormalAccountDto getNormalAccountDto() {
		return normalAccountDto;
	}

	/**  
	 * @param normalAccountDto normalAccountDto 
	 */
	public void setNormalAccountDto(NormalAccountDto normalAccountDto) {
		this.normalAccountDto = normalAccountDto;
	}

	@Override
	public BaseDto getBaseDto() {
		return normalAccountDto;
	}

	@Override
	protected void prepareRequest(BusinessRequest arg0) throws BaseException {
		arg0.setDto(normalAccountDto);
	}
	
	/**
	 * 有时候不走后台，只要页面跳成时，复写这个父类方法， super.getBusinessFlow() 能得调用的方法，也就是URL中!后的方法名
	 */
	@Override
	public String directlyJump() {
		if ("normalAccountMain".equals(super.getBusinessFlow())) {
			return "normalAccountMain";
		}
		return null;
	}
	
	

}
