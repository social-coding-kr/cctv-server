package com.socialcoding.domain.models;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
    private Boolean reliable;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cctvId")
    private Cctv cctv;
}
