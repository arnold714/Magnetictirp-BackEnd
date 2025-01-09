package com.ssafy.member.model;

import org.apache.ibatis.type.Alias;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(title = "MemberDto (회원정보)", description = "회원의 아이디, 비번, 이름을 가진 Domain Class")
public class MemberDto {

	@Schema(description = "회원 코드", requiredMode = Schema.RequiredMode.REQUIRED)
	private String sub;
	@Schema(description = "회원이름")
	private String name;
	@Schema(description = "프로필")
	private String picture;
	@Schema(description = "이메일아이디")
	private String email;	
	@Schema(description = "사용자", defaultValue = "USER")
	private String role;
	@Schema(description = "가입일", defaultValue = "현재시간")
	private String joinDate;

	

	
}
