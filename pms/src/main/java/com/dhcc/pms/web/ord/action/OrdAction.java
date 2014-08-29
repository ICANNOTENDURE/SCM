/**
 * 通过模板生成Action 
 * template by zxx
 */
package com.dhcc.pms.web.ord.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.annotation.Blh;
import com.dhcc.framework.annotation.JResult;
import com.dhcc.framework.annotation.JsonResults;
import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.pms.dto.ord.OrdDto;


@Namespace(value = "/ord")
@Scope("prototype")
@Action(value = "ordCtrl", results = {
		@Result(name = "listMain", location = "/WEB-INF/jsp/ord/Ord.jsp")})
@Blh("ordBlh")
@JsonResults({@JResult(BlhMethod="findById",ognlExpress="dto.ord")})
public class OrdAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private OrdDto dto = new OrdDto();
	
	@Override
	public String directlyJump() {
		//直接返回jsp
		if("listMain".equals(super.getBusinessFlow())){
			return "listMain";
		}
		return null;
	}
	
	@Override
	public BaseDto getBaseDto() {

		return dto;
	}
	
	@Override
	protected void prepareRequest(BusinessRequest reqEvent) throws BaseException {

		reqEvent.setDto(dto);
	}

	/**
	 * @return the dto
	 */
	public OrdDto getDto() {
		return dto;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(OrdDto dto) {
		this.dto = dto;
	}
	
	
}
