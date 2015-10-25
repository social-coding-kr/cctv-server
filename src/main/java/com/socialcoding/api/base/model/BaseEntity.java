package com.socialcoding.api.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	@Column(name = "createdBy")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	@Column(name = "createdAt", nullable = false)
	private Date createdAt;

	@Column(name = "modifiedBy")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.ALWAYS)
	@Column(name = "modifiedAt", nullable = false)
	private Date modifiedAt;
}
