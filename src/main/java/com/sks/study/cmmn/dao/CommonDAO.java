package com.sks.study.cmmn.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository( "commonDAO" )
public class CommonDAO extends AbstractDAO {
	private static final Logger logger = Logger.getLogger(CommonDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMainTestData() throws Exception {
		logger.debug("function : selectMainTestData()");
        return selectList( "com.sks.study.cmmn.commonMapper.selectMainTestData" );
    }
}
