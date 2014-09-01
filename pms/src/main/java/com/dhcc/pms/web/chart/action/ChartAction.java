package com.dhcc.pms.web.chart.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.annotation.Blh;
import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.pms.dto.chart.OrdVenDistributionDto;

@Namespace(value="/chart")
@Scope("prototype")
@Action(value="chartCtrl",results={
		@Result(name="list" ,location="/WEB-INF/jsp/demo/demo.jsp"),
//		@Result(name="delete" ,location="/WEB-INF/jsp/demo/demo.jsp"),
		@Result(name="hop" ,location="/WEB-INF/jsp/chart/hop.jsp")
})
@Blh("chartBlh")
public class ChartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	//DemoDto用于数据的传递
	private OrdVenDistributionDto dto=new OrdVenDistributionDto();
	@Override
	public BaseDto getBaseDto() {
		return dto;
	}

	@Override
	public String directlyJump() {
		//直接返回jsp
		if("index".equals(super.getBusinessFlow())){
			Long type=WebContextHolder.getContext().getVisit().getUserInfo().getUserType();
			if(type.toString().equals("1")){
				return "hop";
			}
			if(type.toString().equals("0")){
				return "hop";
			}
			return "list";
		}
		return null;
	}
	
	/**
	 * 将页面请求过来的数据放到用户数据类中进行数据转换
	 */
	@Override
	protected void prepareRequest(BusinessRequest reqEvent) throws BaseException {
		reqEvent.setDto(dto);
	}

	public OrdVenDistributionDto getDto() {
		return dto;
	}

	public void setDto(OrdVenDistributionDto dto) {
		this.dto = dto;
	}

}
