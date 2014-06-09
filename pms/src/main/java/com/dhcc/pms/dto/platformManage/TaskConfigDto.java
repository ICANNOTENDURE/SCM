package com.dhcc.pms.dto.platformManage;

import com.dhcc.pms.entity.platformManage.TaskConfig;
import com.dhcc.framework.transmission.dto.BaseDto;

/**
 * 
* <p>标题: TaskConfigDto.java</p>
* <p>业务描述: 统一运维及安全管理平台</p>
* <p>公司: 东华软件股份公司</p>
* <p>版权: dhcc2013</p>
* @author 于鸿
* @date 2013年11月15日
* @version V1.0
 */
public class TaskConfigDto extends BaseDto {

	/**  
	* 字段:	字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	
	private TaskConfig taskConfig;
	
	private boolean success = false;
	
	private String message = "";
	
	public TaskConfig getTaskConfig() {
		return taskConfig;
	}

	public void setTaskConfig(TaskConfig taskConfig) {
		this.taskConfig = taskConfig;
	}

	/**  
	 * @return success 
	 */
	public boolean isSuccess() {
		return success;
	}

	/**  
	 * @param success success 
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**  
	 * @return message 
	 */
	public String getMessage() {
		return message;
	}

	/**  
	 * @param message message 
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}