package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int emailCheck(String email) throws Exception;
	void joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(MemberDto memberDto) throws Exception;
	
	/* Admin */
	List<MemberDto> listMember(Map<String, Object> map) throws Exception;
	MemberDto getMember(String userId) throws Exception;
	void updateMember(MemberDto memberDto) throws Exception;
	void deleteMember(String userId) throws Exception;
	MemberDto searchpassword(String userId)throws Exception;
}
