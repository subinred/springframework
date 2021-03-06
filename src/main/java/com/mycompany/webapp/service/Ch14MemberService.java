package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service
public class Ch14MemberService {
	public enum JoinResult{
		SUCCESS, FAIL, DUPLICATED
	}
	public enum LoginResult{
		SUCCESS, FAIL_MID, FAIL_MPASSWORD, FAIL
	}
	@Resource
	private Ch14MemberDao memberDao;
	public JoinResult join(Ch14Member member) {
		//try {
		Ch14Member dbMember=memberDao.selectByMid(member.getMid());
		if(dbMember==null) {
			//password 암호화
			PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
			member.setMpassword(passwordEncoder.encode(member.getMpassword()));
			int result=memberDao.insert(member);
			return JoinResult.SUCCESS;
			//throw new RuntimeException("저장 예외"); 예외 강제로 발생시키기
		}
		else {
			return JoinResult.DUPLICATED;
		}
		/*}catch(Exception e) {
			return JoinResult.FAIL;
		}*/

	}
	public LoginResult login(Ch14Member member) {
		Ch14Member dbMember=memberDao.selectByMid(member.getMid());
		if(dbMember==null) {
			return LoginResult.FAIL_MID;
		}else {
			PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
			if(passwordEncoder.matches(member.getMpassword(), dbMember.getMpassword())) {
				return LoginResult.SUCCESS;
			}else {
				return LoginResult.FAIL_MPASSWORD;
			}
		}
	}
}
