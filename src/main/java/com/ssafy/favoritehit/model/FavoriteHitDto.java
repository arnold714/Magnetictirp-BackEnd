package com.ssafy.favoritehit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FavoriteHitDto {
	private int contentId;
	private int hit;
}
