package com.socialcoding.api.cctv.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrivateCctvDto extends CctvDto {
	private String cctvImage;
	private String noticeImage;
}
