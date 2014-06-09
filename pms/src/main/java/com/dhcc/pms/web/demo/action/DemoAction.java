package com.dhcc.pms.web.demo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.annotation.Blh;
import com.dhcc.framework.annotation.Blh2WS;
import com.dhcc.framework.annotation.JsonResult;
import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.pms.dto.demo.DemoDto;

/**
 * <p>标题: DemoAction.java</p>
 * <p>业务描述:DemoAction</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2013</p>
 * @author daic
 * @date 2013年12月3日
 * @version V1.0 
 */
@Namespace(value="/demo")
@Scope("prototype")
@Action(value="demoCtrl",results={
		@Result(name="list" ,location="/WEB-INF/jsp/demo/demo.jsp"),
//		@Result(name="delete" ,location="/WEB-INF/jsp/demo/demo.jsp"),
		@Result(name="index" ,location="/index.jsp")
})
@Blh("demoBlh")
@JsonResult("findById:dto.demo")
public class DemoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	//DemoDto用于数据的传递
	private DemoDto dto=new DemoDto();
	@Override
	public BaseDto getBaseDto() {
		return dto;
	}

	@Override
	public String directlyJump() {
		//直接返回jsp
		if("index".equals(super.getBusinessFlow())){
			return "index";
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

	public DemoDto getDto() {
		return dto;
	}

	public void setDto(DemoDto dto) {
		this.dto = dto;
	}

}
