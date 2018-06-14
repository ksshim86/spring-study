package com.sks.study.util;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob extends QuartzJobBean{
	
	private static final Logger logger = Logger.getLogger(QuartzJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException
	{
		// TODO Auto-generated method stub
		logger.debug( ctx.getTrigger().getName() );
	}

}
