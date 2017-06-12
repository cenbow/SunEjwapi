package com.huaao.sunejwapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huaao.sunejwapi.common.Page;
import com.huaao.sunejwapi.dao.SysLogsRequest;
import com.huaao.sunejwapi.dao.SysLogsRequestExample;
import com.huaao.sunejwapi.dao.SysLogsRequestExample.Criteria;
import com.huaao.sunejwapi.dao.SysLogsRequestWithBLOBs;
import com.huaao.sunejwapi.mapper.SysLogsRequestMapper;
 

@Service
public class RequestLogService {

	@Autowired
	private SysLogsRequestMapper sysLogsRequestMapper;
	
	public List<SysLogsRequest> query(Page p ,SysLogsRequest log, Date startTime, Date endTime){
		SysLogsRequestExample example = new SysLogsRequestExample();
		Criteria c = example.createCriteria();
		if(!StringUtils.isEmpty(log.getReqId())){
			if(log.getReqId().length() < 30){
				c.andReqIdLike("%"+log.getReqId()+"%");
			}else{
				c.andReqIdEqualTo(log.getReqId());
			}
		}
		if(!StringUtils.isEmpty(log.getReturnCode())  ){
			if(log.getReturnCode() == -1){
				c.andReturnCodeNotEqualTo(0);
			}else{
				c.andReturnCodeEqualTo(log.getReturnCode());
			}
		}
		if(!StringUtils.isEmpty(startTime)){
			c.andCreateTimeGreaterThanOrEqualTo(startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			c.andCreateTimeLessThan(endTime);
		}
		if(!StringUtils.isEmpty(log.getApi())){
			if(log.getApi().startsWith("api") || log.getApi().startsWith("/api")){
				c.andApiEqualTo(log.getApi());
			}else{
				c.andApiLike("%"+log.getApi()+"%");
			}
		}
		if(log.getRunTime() != null){
			c.andRunTimeGreaterThan(log.getRunTime());
		}
		
		int count = sysLogsRequestMapper.countByExample(example);
		p.setTotalCount(count);
		example.setOffset(p.getOffset());
		example.setLimit(p.getPageSize());
		example.setOrderByClause(" create_time desc");
		return sysLogsRequestMapper.selectByExample(example);
	}
	
	
	public SysLogsRequestWithBLOBs queryFullInfo(String reqId){
		return sysLogsRequestMapper.selectByPrimaryKey(reqId);
	}
}
