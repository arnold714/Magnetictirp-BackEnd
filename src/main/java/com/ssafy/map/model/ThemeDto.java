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
@Schema(title = "ThemeDto : 테마정보", description = "테마의 종류를 나타낸다.")
public class ThemeDto {
    @Schema(description = "테마아이디")
    private String themeId;
    @Schema(description = "테마이름")
    private String themeName;
}
