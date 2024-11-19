package com.ssafy.notice.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "NoticesDto : 공지사항정보", description = "공지사항의 상세 정보를 나타낸다.")
public class NoticesDto {

	@Schema(description = "공지사항 번호")
	private int noticesNo;
	@Schema(description = "작성자 아이디")
	private String adminId;
	@Schema(description = "작성자 이름")
	private String userName;
	@Schema(description = "공지사항 제목")
	private String subject;
	@Schema(description = "공지사항 내용")
	private String content;
	@Schema(description = "공지사항 내용 종류 : 안내, 공지, 이벤트, 발표")
	private String contentType;
	@Schema(description = "작성일")	
	private String registerTime;
	@Schema(description = "업로드 파일정보")
	private List<FileInfoDto> fileInfos;

}
