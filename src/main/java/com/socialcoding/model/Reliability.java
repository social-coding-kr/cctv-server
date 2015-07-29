package com.socialcoding.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reliabilities")
public class Reliability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reliabilityId", nullable = false)
    private Long reliabilityId;

    @Column(name = "cctvId", nullable = false)
    private Long cctvId; //TODO foreign key setting

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "reliable", nullable = false)
    private Boolean isReliable;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt; //TODO temporal setting
}
