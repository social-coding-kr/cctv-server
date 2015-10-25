package com.socialcoding.api.cctv.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicCctvDto extends CctvDto {
	private String address;
	private String borough;
	private String dong;
	private String range;
	private String department;
	private String pixel;
	private String form;
	private String installedAt;
}
