package edu.kh.comm.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import edu.kh.comm.member.controller.MemberController;
import edu.kh.comm.member.model.vo.Member;
import lombok.Getter;

@Repository//데이터 저장소.영속성(지속 가능한 성질을 가지고 있는 것 )을 가지는 db파일과 연결되는 클래스 임을 명시 + bean 등록 
public class MemberDAO {

	// DAO는 DB랑 연결하기 위한 connection이 공통적으로 필요하다!
	// -> 필드에 선언
	//Mybartis 영속성 프레임워크를 이용하려면  connection 을 이용해 만들어진 객체 
	// SqlSessionTemplate을 사용 
	
	
	@Autowired // root-context.xml에서 생성된 SqlSessionTemplate bean을 의조선 주입(DI)

	private SqlSessionTemplate sqlSession; 
	
	private Logger logger= LoggerFactory.getLogger(MemberDAO.class);
	
	public Member login(Member inputMember) { 

		//1행 조회(파라미터 X) 방법
	//	int count = sqlSession.selectOne("namespace값.id값");
		//int count = sqlSession.selectOne("memberMapper.test1");	
	//	logger.debug(count + "");
		
		
		//1행 조회(파라미터 O) 방법 
		//String memberNickname = sqlSession.selectOne("memberMapper.test2", inputMember.getMemberEmail());
		//logger.debug(memberNickname);
		
		//1행 조회(파라미터가 VO인 경우)
	//	String memberTel = sqlSession.selectOne("memberMapper.test3", inputMember);
																//memberEmail,memberPW
	//	logger.debug(memberTel);
		
		// 1행 조회 (파라미터가 vo, 반환되는 결과도 vo )
		Member loginMember = sqlSession.selectOne("memberMapper.login" , inputMember);
		
		
		
		return loginMember;
		
		
	}
	
	public int emailDupCheck(String memberEmail) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.emailDupCheck" , memberEmail);
	}
	
	public int nicknameDupCheck(String memberNickname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.nicknameDupCheck" , memberNickname);
	}

	public int signUp(Member inputMember) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.signUp" , inputMember);
	}

	public Member selectOne(Member inputMember) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.selectOne" , inputMember);
	}

}
