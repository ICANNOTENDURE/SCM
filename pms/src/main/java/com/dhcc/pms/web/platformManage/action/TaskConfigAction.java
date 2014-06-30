package com.dhcc.pms.web.platformManage.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.dhcc.framework.annotation.Blh;
import com.dhcc.framework.exception.BaseException;
import com.dhcc.framework.transmission.dto.BaseDto;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.framework.web.BaseAction;
import com.dhcc.pms.dto.platformManage.TaskConfigDto;

/**
 * 
* <p>标题: TaskConfigAction.java</p>
* <p>业务描述: 统一运维及安全管理平台</p>
* <p>公司: 东华软件股份公司</p>
* <p>版权: dhcc2013</p>
* @author 于鸿
* @date 2013年11月15日
* @version V1.0
 */
@Namespace(value = "/task")
@Scope("prototype")
@Action(value = "TaskConfigCtrl", results = {@Result(name = "listMain", location = "/WEB-INF/jsp/platformManage/TaskConfig.jsp")})
@Blh("TaskConfigBlh")
//@JsonResult("findById:dto.taskConfig")
//@JsonResult4Pojo("list:dto.pageModel")
public class TaskConfigAction extends BaseAction {

	/**  
	* 字段:	字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	
	private TaskConfigDto dto = new TaskConfigDto();
	
	@Override
	public String directlyJump() {
		// TODO Auto-generated method stub
		if ("listMain".equals(super.getBusinessFlow())) {
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
	
	public TaskConfigDto getDto() {
		return dto ;
	}
	
	public void setDto(TaskConfigDto dto) {
		this.dto = dto;
	}

}
