package com.ssafy.map.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "SidoCodeDto : 시도정보", description = "시도의 이름을 나타낸다.")
public class SidoCodeDto {

	@Schema(description = "시도코드")
	private String sidoCode;
	@Schema(description = "시도이름")
	private String sidoName;
	
}
