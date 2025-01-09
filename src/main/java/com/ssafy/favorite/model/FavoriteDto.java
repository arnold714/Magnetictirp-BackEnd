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
	private int contentId;
	private String email;
	private int favoNo;
}
