package com.sks.study.cmmn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sks.study.cmmn.dao.CommonDAO;
import com.sks.study.cmmn.service.CommonService;

@Service( "commonService" )
public class CommonServiceImpl implements CommonService {
	private static final Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Resource( name="commonDAO" )
	private CommonDAO commonDAO;
	
	@Override
	public List<Map<String, Object>> selectMainTestData() throws Exception {
		logger.debug("function : selectMainTestData()");
		return commonDAO.selectMainTestData();
	}
	
}
