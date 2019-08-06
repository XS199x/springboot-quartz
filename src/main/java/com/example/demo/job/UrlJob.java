package com.example.demo.job;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.demo.util.http.HttpApiService;
import com.example.demo.util.http.HttpResult;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 发送url请求
 * @author
 */
public class UrlJob implements BaseJob{
	
	private static Logger _log = LoggerFactory.getLogger(UrlJob.class);
	
	public UrlJob() {
	
	}
	@Autowired
	private HttpApiService httpApiService;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String url = jobDataMap.getString("url");
		try {
			_log.info(JSONUtils.toJSONString(jobDataMap));
			HttpResult httpResult = httpApiService.doPost(url);
			_log.info(httpResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.toString());
		}
	}
}
