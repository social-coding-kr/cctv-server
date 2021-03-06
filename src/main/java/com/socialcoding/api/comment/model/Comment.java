package com.socialcoding.api.comment.model;


import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Data
//@ToString(exclude = "cctv")
@Entity
@Table(name = "comments")
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
    @Generated(GenerationTime.INSERT)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "modifiedAt", nullable = false)
    private Date modifiedAt;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "cctvId")
//	private Cctv cctv;
}
