package com.dhcc.pms.blh.task.monitor;

import org.apache.commons.mail.EmailException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dhcc.framework.util.SendMailUtil;

public class StatisticTask implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			SendMailUtil.sendEmail("还能做朋友吗，能不能正经点", "还能做朋友吗，能不能正经点", "1053373777@qq.com", 60 * 1000);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
