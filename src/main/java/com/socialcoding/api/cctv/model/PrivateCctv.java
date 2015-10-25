package com.socialcoding.api.cctv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "private_cctvs")
@DiscriminatorValue("PRIVATE")
public class PrivateCctv extends Cctv {
	@Column(name = "cctvImage")
	private String cctvImage;

	@Column(name = "noticeImage")
	private String noticeImage;
}
