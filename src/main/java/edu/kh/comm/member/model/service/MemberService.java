package edu.kh.comm.member.model.service;

import edu.kh.comm.member.model.vo.Member;

/*Service Interface를 사용하는 이유
 * 1. 프로젝트에 규칙성을 부여하기 위해서  
 * 
 * 2. Spring AOP()를 위해서 필요 (스프링개요pdf)
 * 흩어진 관심사 : 소스코드상에서 계속 반복해서 사용되는 부분들
 * -> 유지보수를 어렵게 만든다 
 * 
 * 3. 클래스간의 결합도를 악화 시키기 위해서 -> 유지보수성 향상 
 * 
 * 
 * 
 * */
public interface MemberService {

	//모든 메서드가 추상메서드 (묵시적으로 public abstract)
	//모든 필드는 상수 이다 (묵시적으로 public static final)
	
	/** 알트 쉬프트 J 
	 * @param inputMember
	 * @return 
	 */
	public abstract Member login(Member inputMember);

	
	


	public abstract int emailDupCheck(String memberEmail);





	public abstract int nicknameDupCheck(String memberNickname);





	public abstract int signUp(Member inputMember);





	public abstract Member selectOne(Member inputMember);
	
	
	
	
	
	
	
	
	
	
	
}
