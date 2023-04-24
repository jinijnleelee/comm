package edu.kh.comm.member.controller;
//POJO 기반 프레임워크 : 외부 라이브러리 상속 X
// class : 객체를 만들기위한 설계도 
// -> 객체로 생성 되어야지 기능 수행 가능하다. 
// --> IOC (제어의 역전, 객체 생명주기를 스프링이 관리)를 이용하여 객체 생성 
// ** 이 때, 스프링이 생성한 객체를 bean 이라고 한다. ** 

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.comm.member.model.service.MemberService;
import edu.kh.comm.member.model.service.MemberServiceImpl;
import edu.kh.comm.member.model.vo.Member;

// bean 등록 == 스플링이 객체로 만들어서 가지고 있어라 

//@Component // 해당클래스를 bean 으로 등록하라고 프로그램에게 알려주는 주석 (Anotation)
@Controller // 생성된 bean이 controller임을 명시 + bean 등록
@RequestMapping("/member") //  localhost :: 8080/comm/member 이하의 요청을 처리하는 컨트롤러 


public class MemberController {
	

	private Logger logger= LoggerFactory.getLogger(MemberController.class);
	

//	private MemberService service = new MemberServiceImpl();//다형성, 
	//IOC(제어의 역전 ) : new 연산자를 통해서 개발자가 직접 객체 생성하지 않는다 
	
	@Autowired //bean 으로 등록된 객체 중 타입이 같거나 상속관계인 bean을 주입하는 역할 
	private MemberService service;// DI(의존성 주입 )
	
	
	
	
	// Controller : 요청/응답을 제어하는 역할을 하는 클래스 
	
	/*@RequestMapping : 클라이언트 요청(url) 에 맞는 클래스 or 메서드를 연결 시켜주는 어노테이션 
	 *  
	 * 
	 * [ 위치에 따른 해석 ] 
	 * - 클래스 레벨 : 공통 주소 
	 * - 메서드 레벨 : 공통 주소 외 나머지 주소 
	 * 
	 * 단, 클래스레벨에 RequestMapping이 존재하지 않는다면
	 * - 메서드 레벨 : 단독 요청 처리 주소
	 * 
	 * [작성법에 따른 해석]
	 * 
	 * 1) RequestMapping{"url"}
	 * --> 요청방식(post/get)관계 없이 url 이 일치하는 요청 처리
	 * RequestMapping value = "url", method = RequestMothed.GET : POST)
	 * --> 요청방식에 따라 요청 처리함 
	 * 
	 * ** 메서드 레벨에서 get/post 방식을 구분하여  매핑할 경우
	 * @GetMapping("url") / @PostMapping("url") 사용하는것이 일반적
	 (메서드 레벨에서만 작성 가능 )
	 
	 
	 
	 * 
	 * 
	 * 
	 * 
	 * */ 
	//로그인
	//요청시 파라미터를 얻어오는 방법 :
	//HTTPServletRequest 이용 
	
//	@RequestMapping("/login")
//	public String login(HttpServletRequest req) {
//		logger.info("로그인 요청");
//		
//		String inputEmail = req.getParameter("inputEmail");
//		String inputPw = req.getParameter("inputPw");
//		
//		logger.debug(inputPw + "inputPw");
//		logger.debug(inputEmail + "inputEmail ");	
//		
//		return "redirect:/";
//	}
//	
	
	
	//요청시 파라미터를 얻어오는 방법 2 
	// -> @RequestParam ("name설정값") 자료형 변수명
	// - 클라이언트 요청시 같이 전달될 파라미터를 변수에 저장
	//어떤 파라미터를 변수에 저장할지는 "name설정값" 를 이용해 지정 
	
	// 매개변수 지정 시 데이터 파싱을 자유롭게 진행 할 수 있음 ex ) String -> int로 변환
	//[속성]
	//value : input태그의 name 속성값
	
	//required : 입력된 name 속성값이 필수적으로 파라미터에 포함되어야 하는지 지정
	//required = true/false (기본값)
	//defaultValue : required 가  false인 상태에서 파라미터가 존재하지 않을 경우의 값을 지정 
/*	
	@RequestMapping("/login")
	public String login(@RequestParam ("inputEmail") String inputEmail,
			@RequestParam ("inputPw") String inputPw,
			@RequestParam (value = "inputName",required = false, defaultValue = "홍길동")String inputName) {
	
		
		logger.debug(inputPw + "inputPw");
		logger.debug(inputEmail + "inputEmail ");	
		logger.debug(inputName + "inputName ");	
		
		// email 숫자만 입력받는다고 자어
		//logger.debug(inputEmail + 100);
		
		return "redirect:/";
}*/
	
	// 요청시 파라미터를 얻어오는 방법 3 
	// -> @modelAttribute 어노테이션 사용 
	
	// @modelAttribute vo타입 변수명 
	//-파라미터 중 name 속성값이 VO 의 필드와 일치하면 
	//해당 VO 객체의 필드값을 세팅
	// *** @modelAttribute 를 이용해서 객체에 값을 직접 담는 경우에 대한 주의 사항 
	// - 반드시 필요한 내용
	//-- VO기본 생성자 
	//-- VO 필드에 대한 Setter
	
	
	@PostMapping("/login")
public String login(@ModelAttribute Member inputMember) {
	
	logger.info("로그인 기능 수행됨"); 
	
	
	//아이디 , 비밀번호가 일치하는 회원 정보를 조회하는 service 호출 후 결과 반환 받기 
	Member loginmember = service.login(inputMember);
	
	return "redirect:/"; 
}
	
	//회원 가입 화면 전환
	
	@GetMapping("/signUp")
	public String  signUp() { //GET방식 : 
		
		return "member/signUp";
	}
}

