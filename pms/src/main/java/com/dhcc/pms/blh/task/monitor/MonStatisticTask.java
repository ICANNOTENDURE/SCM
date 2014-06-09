package com.dhcc.pms.blh.task.monitor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dhcc.framework.common.SpringContextHolder;
import com.dhcc.pms.blh.platformManage.MonStatisticBlh;

/**
 * <p>标题: SysMonTask.java</p>
 * <p>业务描述: 统一运维及安全管理平台</p>
 * <p>公司: 东华软件股份公司</p>
 * <p>版权: dhcc2013</p>
 * @author 于鸿
 * @date 2013年11月1日
 * @version V1.0 
 */
public class MonStatisticTask implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MonStatisticBlh monStatisticBlh = SpringContextHolder.getBean("monStatisticBlh");
		monStatisticBlh.monSysSta();
	}

}
