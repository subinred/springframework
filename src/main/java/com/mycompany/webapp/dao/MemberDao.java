package com.mycompany.webapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.service.MemberService;
@Repository
public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	public MemberDao() {
		logger.info("실행");
	}
	

}
