package com.dhcc.pms.blh.task.monitor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dhcc.framework.common.SpringContextHolder;
import com.dhcc.pms.blh.platformManage.MonStatisticBlh;

public class SendMailStatisticTask implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MonStatisticBlh monStatisticBlh = SpringContextHolder.getBean("monStatisticBlh");
		monStatisticBlh.monSysSta();
	}

}
