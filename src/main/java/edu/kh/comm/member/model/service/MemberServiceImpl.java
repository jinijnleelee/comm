package edu.kh.comm.member.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.vo.Member;
import edu.kh.comm.model.dao.MemberDAO;

@Service // 비즈니스로직(데이터 가공, DB 연결 )을 처리하는 클래스임을 명시 + bean 등록 
public class MemberServiceImpl implements MemberService{

	@Autowired //bean 등록된 객체 중 타입이 같거나 상속관계인 bean을 자동으로 주입 
	private MemberDAO dao;

	/*connection을 service 에서 얻어왔던 이유
	 * 
	 * - service 의 메서드 하나는 요청을 처리하는 업무 단위
	 * -> 해당업무가 끝난 후 트랜잭션을 한번에 처리하기 위해서
	 * 어쩔수없이 connection 을 service에서 얻어올수밖에없었다 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	//암호화를 위한 Bcrypt 갹채 의존성 주입 (DI)
	@Autowired
	private BCryptPasswordEncoder Bcrypt;
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	
	
	//로그인 서비스 구현 
	/**
	 *
	 */
	@Override
	public Member login(Member inputMember) {
		
		// 전달 받은 비밀번호를 암호화하여
		//DB에서 조회한 비밀번호와 비교 (DB에서 비교X)
		
		// sha 방식 암호화 
		// A  회원 / 비밀번호 1234 -> 암호화 : abcd 
		// B  회원 / 비밀번호 1234 -> 암호화 : abcd (암호화시 변경된 내용이 같음)
		
		// Bcrypt 암호화 : 암호화 하기전에 salt를 추가하여 변형된 상태로 암호화를 진행 
		// A  회원 / 비밀번호 1234 -> 암호화 : abcd 
		// B  회원 / 비밀번호 1234 -> 암호화 : @ddsd
		
		// * 매번 암호화되는 비밀번호가 달라져서 DB에서 직접 비교 불가능 
		// 대신 Bcrypt 암호화를 지원하는 객체가
		// 이를 비교하는 기능(메서드) 가지고있어서 이를 활용하면 된다
		
		// ** Bcrypt 암호화를 사용하기 위해 이를 지원하는 Spring- security 모듈 추가 **
		//	메이븐레파지토리에서 Spring- security  검색해서 각각 5.7.1 버전  소스 pom에 추가 
		//		 Spring Security Core
		//		 Spring Security Web
		//		 Spring Security Config
		
		// 보안설ㅈ정용 xml 생성 
		//spring 폴더에 컨트롤 n 
		
		//Spring Security를 읽으라는 코드를 추가 
		
		//web.xml에 <param-value>에 
		//classpath:spring/spring-Security.xml 추가 
		
		
		//	//암호화를 위한 Bcrypt 객채 의존성 주입 (DI)
		//맴버서비스 인플에 추가 
		//디버거 만들고 
		//확인해보기
		//logger.debug(inputMember.getMemberPw() + "/" + Bcrypt.encode(inputMember.getMemberPw()));
		//안되면 프로젝트 클린, 서버 클린, 메이븐 클린, 메이븐 업데이트 해보기 .

		logger.debug(inputMember.getMemberPw() + "/" + Bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + "/" + Bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + "/" + Bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + "/" + Bcrypt.encode(inputMember.getMemberPw()));
		
		// MAPPER 가서  <!--  	 MEMBER_PW 추가, 	AND MEMBER_PW = #{memberPw} 삭제 혹은 주석 -->
		
		
		
				
	
		Member loginMember = dao.login(inputMember);
		
		// loginMember == NULL 일치하는 이메일이 없다 
		
		
		if(loginMember !=null) { //일치하는 이메일을 가진 회원 정보가 있을 경우 
			// 평문과 암호화된 비밀번호 비교 
			
			// 					입력된 비밀번호 (평문) , 조회된 비밀번호 (암호화) 비교 => 같으면 true
			if(Bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw()  )) { //비밀번호가 일치할경우 
				
				loginMember.setMemberPw(null); // 비교 끝났으면 비밀번호 지우기. 세션에 못올라가게. 
				
			}else {//비밀번호가 일치하지 않을 경우 
				loginMember = null;
				
			}
			
		}
		return loginMember;
		
		
		
		//$2a$10$eCZxoSo1GbrZZxfK0/eNlu4y7Ijq8R5DSksbWjT4eurjnJn8GHfIm
		//
		
		//connection을 얻어오거나/ 반환하거나
		//트랜잭션 처리를 하는 구문을 작성하지 앟아도
		//Spring에서 제어를 하기 때문에 Service구문이 간단해진다. 
		
	}



	@Override
	public int  emailDupCheck(String memberEmail) {
	int result = dao.emailDupCheck(memberEmail);
		return result;
	}



	@Override
	public int nicknameDupCheck(String memberNickname) {
		int result = dao.nicknameDupCheck(memberNickname);		
		
		return result;
	}



	@Override
	public int signUp(Member inputMember) {
		int result = dao.signUp(inputMember);		
		return result;
	}



	@Override
	public Member selectOne(Member inputMember) {
		Member result = dao.selectOne(inputMember);		
		return result;
	}


}
