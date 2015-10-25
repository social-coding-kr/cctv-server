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
@Table(name = "public_cctvs")
@DiscriminatorValue("PUBLIC")
public class PublicCctv extends Cctv {
	@Column(name = "cctvName", nullable = false)
	private String cctvName;

	@Column(name = "address")
	private String address;

	@Column(name = "borough")
	private String borough;

	@Column(name = "dong")
	private String dong;

	@Column(name = "\"range\"")
	private String range;

	@Column(name = "department")
	private String department;

	@Column(name = "pixel")
	private String pixel;

	@Column(name = "form")
	private String form;

	@Column(name = "installedAt")
	private String installedAt;

}
