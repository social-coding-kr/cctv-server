package com.socialcoding.domain.models;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import com.socialcoding.domain.services.cctv.CctvSource;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    private CctvPurpose purpose;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "borough")
    private String borough;

    @Column(name = "dong")
    private String dong;

    @Column(name = "cctvImage", nullable = true)
    private String cctvImage;

    @Column(name = "noticeImage", nullable = true)
    private String noticeImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private CctvSource source;

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
