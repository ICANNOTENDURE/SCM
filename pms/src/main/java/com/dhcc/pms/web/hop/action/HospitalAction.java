/**
 * 通过模板生成Action 
 * template by zxx
 */
package com.dhcc.pms.web.hop.action;

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
import com.dhcc.pms.dto.hop.HospitalDto;


@Namespace(value = "/hop")
@Scope("prototype")
@Action(value = "hospitalCtrl", results = {
		@Result(name="listMain" ,location="/WEB-INF/jsp/hop/hospital.jsp"),
		@Result(name = "list", location = "/WEB-INF/jsp/hop/hospital.jsp"),
		@Result(name = "delete", location = "/WEB-INF/jsp/hop/hospital.jsp"),
		@Result(name = "getHospInfo", location = "/WEB-INF/jsp/hop/hospital.jsp")
		})
@Blh("hospitalBlh")
@JsonResults({@JResult(BlhMethod="findById",ognlExpress="dto.hospital")})
public class HospitalAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private HospitalDto dto = new HospitalDto();
	
	@Override
	public String directlyJump() {
		//直接返回jsp
		if("listMain".equals(super.getBusinessFlow())){
			return "listMain";
		}
		return	null;
	}
	
	@Override
	public BaseDto getBaseDto() {

		return dto;
	}
	
	@Override
	protected void prepareRequest(BusinessRequest reqEvent) throws BaseException {

		reqEvent.setDto(dto);
	}
	
	public HospitalDto getDto() {
		return dto ;
	}
	
	public void setDto(HospitalDto dto) {
		this.dto = dto;
	}

}
