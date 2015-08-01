package com.socialcoding.domain.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(exclude = "cctv")
@Entity
@Table(name = "reliabilities")
public class Reliability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reliabilityId", nullable = false)
    private Long reliabilityId;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "reliable", nullable = false)
    private Boolean isReliable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cctvId")
	private Cctv cctv;
}
