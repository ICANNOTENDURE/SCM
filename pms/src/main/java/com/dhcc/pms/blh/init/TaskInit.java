package com.dhcc.pms.blh.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.dhcc.pms.blh.task.TaskOperate;
import com.dhcc.pms.dto.platformManage.TaskConfigDto;
import com.dhcc.pms.entity.platformManage.TaskConfig;

/**
 * 
* <p>标题: AutoInitTask.java</p>
* <p>业务描述: 统一运维及安全管理平台</p>
* <p>公司: 东华软件股份公司</p>
* <p>版权: dhcc2013</p>
* @author 于鸿
* @date 2013年11月4日
* @version V1.0
 */
@Component
@DependsOn("propertiesBean")
public class TaskInit {
	
	private static Log logger = LogFactory.getLog(TaskInit.class);
	
	public TaskInit() {
		logger.info("====TaskInit====");
	};
	
	/**
	 * 
	* 方法名:		TaskLoad
	* 方法功能描述:	系统启动后执行任务
	* @param:		无
	* @return:		无
	* @Author:		于鸿
	* @Create Date:   2013年11月18日 下午3:49:46
	 */
	@PostConstruct
	public void TaskLoad() {
		logger.info("\n-------------------TaskInit--------------------\n");
		// 获取任务，存入内存 
		List<TaskConfig> tcList = getTestData();
		if(tcList.size()!=0){
//			TaskConfigDto tcdto=new TaskConfigDto();
//			for(int i=0;i<tcList.size();i++){
//				tcdto.setTaskConfig(tcList.get(i));
//				TaskOperate.instance().addJob(tcdto);
//				logger.info("\n-------------------add Job--------------------\n"+tcList.get(i).getJobName());
//			}
		}
	}
	
	/**
	 * 
	* 方法名:		getMemCacheForTest
	* 方法功能描述:	测试使用，获取任务设置测试数据
	* @param:		业务请求对象
	* @return:		无
	* @Author:		于鸿
	* @Create Date:   2013年11月4日 下午4:42:04
	 */
	private List<TaskConfig> getTestData() {
		TaskConfig taskConfig=new TaskConfig();
		taskConfig.setJobName("MonSta");
		taskConfig.setJobGroup("PMS");
		taskConfig.setTriggerName("MonSta");
		taskConfig.setTriggerGroup("PMS");
		taskConfig.setTaskClass("com.dhcc.pms.blh.task.monitor.MonStatisticTask");
		taskConfig.setTimePeriodType("3");
		taskConfig.setTimePeriod("6");
		
		List<TaskConfig> list=new ArrayList<TaskConfig>();
		list.add(taskConfig);
		return list;
	}
}
