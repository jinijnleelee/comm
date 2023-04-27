package edu.kh.comm.main.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionController {

	// 이 컨트롤러 하나로 전역의 에러들을 잡을수있다 
	
	@ExceptionHandler(Exception.class)//여기만 수정해주면 된다 
	
	public String exceptionHandler(Exception e, Model model) {

	e.printStackTrace();
	model.addAttribute("errorMessage","서비스 이용 중 문제가 발생했습니다.");
	model.addAttribute("e",e);

	return "common/error";

	}
}
