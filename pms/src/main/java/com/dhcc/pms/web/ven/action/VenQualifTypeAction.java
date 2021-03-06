/**
 * 通过模板生成Action 
 * template by zxx
 */
package com.dhcc.pms.web.ven.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.annotation.Blh;
import com.dhcc.framework.annotation.JsonResult;
import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.pms.dto.ven.VenQualifTypeDto;


@Namespace(value = "/ven")
@Scope("prototype")
@Action(value = "venQualifTypeCtrl", results = {
		@Result(name = "list", location = "/WEB-INF/jsp/ven/VenQualifTypeList.jsp"),
		@Result(name = "listMain", location = "/WEB-INF/jsp/ven/VenQualifTypeList.jsp")})
@Blh("venQualifTypeBlh")
@JsonResult("findById:dto.venQualifType")
public class VenQualifTypeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VenQualifTypeDto dto = new VenQualifTypeDto();
	
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
	
	public VenQualifTypeDto getDto() {
		return dto ;
	}
	
	public void setDto(VenQualifTypeDto dto) {
		this.dto = dto;
	}

}
