/**
 * 通过模板生成Action 
 * template by zxx
 */
package com.dhcc.pms.web.ven.action;

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
import com.dhcc.pms.dto.ven.VenIncPicDto;


@Namespace(value = "/ven")
@Scope("prototype")
@Action(value = "venIncPicCtrl", results = {
		@Result(name = "list", location = "/WEB-INF/jsp/ven/VenIncPic.jsp"),
		@Result(name = "listInfo", location = "/WEB-INF/jsp/ven/VenIncPic.jsp"),
		@Result(name = "listMain", location = "/WEB-INF/jsp/ven/VenIncPic.jsp"),
		@Result(name = "save", location = "/WEB-INF/jsp/ven/VenIncPic.jsp"),
		@Result(name = "delete", location = "/WEB-INF/jsp/ven/VenIncPic.jsp")
})
@InterceptorRefs(value = { @InterceptorRef("fileUploadStack") })
@Blh("venIncPicBlh")
@JsonResults({@JResult(BlhMethod="findById",ognlExpress="dto.venIncPic"),
			  @JResult(BlhMethod="delete",ognlExpress="dto")})
public class VenIncPicAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private VenIncPicDto dto = new VenIncPicDto();
	
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
	
	public VenIncPicDto getDto() {
		return dto ;
	}
	
	public void setDto(VenIncPicDto dto) {
		this.dto = dto;
	}

}
