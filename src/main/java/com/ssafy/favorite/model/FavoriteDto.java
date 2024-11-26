package com.ssafy.favorite.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FavoriteDto {
	private String contentId;
	private String email;
	private String favoNo;
}
