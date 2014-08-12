/**
 * 通过模板生成Action 
 * template by zxx
 */
package com.dhcc.pms.web.hop.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.framework.annotation.Blh;
import com.dhcc.framework.annotation.JResult;
import com.dhcc.framework.annotation.JsonResults;
import com.dhcc.pms.dto.hop.HopVendorDto;


@Namespace(value = "/hop")
@Scope("prototype")
@Action(value = "hopVendorCtrl", results = {
		@Result(name = "list", location = "/WEB-INF/jsp/hop/HopVendor.jsp"),
		@Result(name = "listMain", location = "/WEB-INF/jsp/hop/HopVendor.jsp"),
		@Result(name = "listDetail", location = "/WEB-INF/jsp/hop/HopVendorDetail.jsp"),
		@Result(name = "ContranstVendor", location = "/WEB-INF/jsp/hop/ContranstVendor.jsp")})
@Blh("hopVendorBlh")
@InterceptorRefs(value = { @InterceptorRef("fileUploadStack") })
@JsonResults({@JResult(BlhMethod="findById",ognlExpress="dto.hopVendor"),
			  @JResult(BlhMethod="contranstVendor",ognlExpress="dto")})
public class HopVendorAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private HopVendorDto dto = new HopVendorDto();
	
	@Override
	public String directlyJump() {
		//直接返回jsp
		if("listMain".equals(super.getBusinessFlow())){
			return "listMain";
		}
		//直接返回jsp
		if("listDetail".equals(super.getBusinessFlow())){
			return "listDetail";
		}
		//直接返回jsp
		if("ContranstVendor".equals(super.getBusinessFlow())){
			return "ContranstVendor";
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
	
	public HopVendorDto getDto() {
		return dto ;
	}
	
	public void setDto(HopVendorDto dto) {
		this.dto = dto;
	}

}
