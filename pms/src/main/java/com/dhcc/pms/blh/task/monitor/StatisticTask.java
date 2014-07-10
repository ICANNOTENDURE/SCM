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
			SendMailUtil.sendEmail("哦哦哦", "xxxx", "908067886@qq.com", 60 * 1000);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
