package com.socialcoding.domain.models;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import com.socialcoding.domain.services.cctv.CctvSource;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
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
    private Long latitude;

    @Column(name = "longitude", nullable = false)
    private Long longitude;

    @Column(name = "cctvName", nullable = false)
    private String cctvName;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    private CctvPurpose purpose;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "cctvImage", nullable = false)
    private String cctvImage;

    @Column(name = "noticeImage", nullable = true)
    private String noticeImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private CctvSource source;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "modifiedAt", nullable = false)
    private Date modifiedAt;

	@OneToMany(mappedBy = "cctv", fetch = FetchType.LAZY)
	private List<Comment> comments;
}
