package com.example.demo.service;


import com.example.demo.entity.JobAndTrigger;
import com.github.pagehelper.PageInfo;

public interface IJobAndTriggerService {
	/**
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
