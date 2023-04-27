package edu.kh.comm.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.model.dao.MemberDAO;
import edu.kh.comm.model.dao.MyPageDAO;

@Service
public class MyPageServiceImpl implements MyPageService{
	
	
	
	@Autowired //bean 등록된 객체 중 타입이 같거나 상속관계인 bean을 자동으로 주입 
	private MyPageDAO dao;
	@Autowired
	private BCryptPasswordEncoder Bcrypt;

	@Override
	public int changePw(String currentPw, String newPw, int memberNo) {
		
		return dao.changePw(currentPw,newPw,memberNo);
	}
	
}
