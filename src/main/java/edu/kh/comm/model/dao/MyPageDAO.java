package edu.kh.comm.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MyPageDAO {
private SqlSessionTemplate sqlSession; 
	
	private Logger logger= LoggerFactory.getLogger(MyPageDAO.class);
	public int changePw(String currentPw, String newPw, int memberNo) {
		// TODO Auto-generated method stub
		return sqlSession.update("myPageMapper.changePw");
	}

}
