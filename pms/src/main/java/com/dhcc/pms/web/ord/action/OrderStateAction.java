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
import com.dhcc.pms.dto.ord.OrderStateDto;


@Namespace(value = "/ord")
@Scope("prototype")
@Action(value = "orderStateCtrl", results = {
		@Result(name = "listMain", location = "/WEB-INF/jsp/ord/OrderState.jsp"),
		@Result(name = "listVenOrder", location = "/WEB-INF/jsp/ord/VenOrderState.jsp")})
@Blh("orderStateBlh")
@JsonResults({@JResult(BlhMethod="findById",ognlExpress="dto.exeState")})
public class OrderStateAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private OrderStateDto dto = new OrderStateDto();
	
	@Override
	public String directlyJump() {
		//直接返回jsp
		if("listMain".equals(super.getBusinessFlow())){
			return "listMain";
		}
		//直接返回jsp
		if("listVenOrder".equals(super.getBusinessFlow())){
			return "listVenOrder";
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
	
	public OrderStateDto getDto() {
		return dto ;
	}
	
	public void setDto(OrderStateDto dto) {
		this.dto = dto;
	}

}
