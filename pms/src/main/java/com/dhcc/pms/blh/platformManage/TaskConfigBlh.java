package com.dhcc.pms.blh.platformManage;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.AbstractBaseBlh;
import com.dhcc.framework.transmission.event.BusinessRequest;
import com.dhcc.pms.dto.platformManage.TaskConfigDto;
import com.dhcc.pms.service.platformManage.TaskConfigService;

/**
 * 
* <p>标题: TaskConfigBlh.java</p>
* <p>业务描述: 统一运维及安全管理平台</p>
* <p>公司: 东华软件股份公司</p>
* <p>版权: dhcc2013</p>
* @author 于鸿
* @date 2013年11月15日
* @version V1.0
 */
@Component
public class TaskConfigBlh extends AbstractBaseBlh {

	private static Log logger = LogFactory.getLog(TaskConfigBlh.class);

	@Resource
	private TaskConfigService taskConfigService;

	public TaskConfigBlh() {
		logger.info("====new TaskConfigBlh====");
	}
	
	/**
	 * 进入某个列表的入口方法
	 * 列表方法，也就是查询方法，调用的时候不需要xxxCtrl!list
	 * 框架 在不调Ctrl时，不指定方法，就默认为它list，在action中通过
	 * json注解，所dto中的pageModel to json
	 * @param: res
	 *  
	 */
	public void list(BusinessRequest res) {
	
		TaskConfigDto dto = super.getDto(TaskConfigDto.class, res);
		
		//调用对应的service方法
		taskConfigService.list(dto);
	}
	
	//保存
	public void save(BusinessRequest res) {
	
		TaskConfigDto dto = super.getDto(TaskConfigDto.class, res);
		
		//调用对应的service方法
		taskConfigService.save(dto);
	}
	
	//删除
	public void delete(BusinessRequest res) {
	
		TaskConfigDto dto = super.getDto(TaskConfigDto.class, res);
		
		//调用对应的service方法
		taskConfigService.delete(dto);
	}
	
	//更新
	public void update(BusinessRequest res) {
	
		TaskConfigDto dto = super.getDto(TaskConfigDto.class, res);
		
		//调用对应的service方法
		taskConfigService.update(dto);
	}
	
	/**
	 * 修改初始化方法
	 * 也是根据iD查询实体的方法
	 * 在action加能过注解把这个实体to json
	 * @param: res
	 *  
	 */
	public void findById(BusinessRequest res) {
	
		TaskConfigDto dto = super.getDto(TaskConfigDto.class, res);
		
		//调用对应的service方法
		taskConfigService.findById(dto);
		
	}
	
}
