package com.socialcoding.api.cctv.model;

import com.socialcoding.api.comment.model.Comment;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "comments")
@Entity
@Table(name = "cctvs")
public class Cctv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cctvId", nullable = false)
    private Long cctvId;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "cctvName", nullable = false)
    private String cctvName;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "address")
    private String address;

    @Column(name = "borough")
    private String borough;

    @Column(name = "dong")
    private String dong;

    @Column(name = "cctvImage")
    private String cctvImage;

    @Column(name = "noticeImage")
    private String noticeImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private CctvSource source;

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

    @Column(name = "createdBy")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "modifiedAt", nullable = false)
    private Date modifiedAt;

    @Column(name = "modifiedBy")
    private String modifiedBy;

	@OneToMany(mappedBy = "cctv", fetch = FetchType.LAZY)
	private List<Comment> comments;
}
