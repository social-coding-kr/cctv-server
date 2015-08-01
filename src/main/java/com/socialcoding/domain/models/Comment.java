package com.socialcoding.domain.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(exclude = "cctv")
@Entity
@Table(name = "reliabilities")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentId", nullable = false)
    private Long commentId;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cctvId")
	private Cctv cctv;
}
