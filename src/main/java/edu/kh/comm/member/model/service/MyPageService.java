package edu.kh.comm.member.model.service;

import org.springframework.stereotype.Service;


public interface MyPageService {

	int changePw(String currentPw, String newPw, int memberNo);

}
