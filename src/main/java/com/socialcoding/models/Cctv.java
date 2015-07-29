package com.socialcoding.models;

import com.socialcoding.services.CctvPurpose;
import com.socialcoding.services.CctvSource;
import lombok.Data;

import javax.persistence.*;

@Data
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

    @Column(name = "tileName", nullable = false)
    private String tileName;

    @Column(name = "purpose", nullable = false)
    private CctvPurpose purpose; //TODO enum management

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "cctvImage", nullable = false)
    private String cctvImage;

    @Column(name = "noticeImage", nullable = true)
    private String noticeImage;

    @Column(name = "source", nullable = false)
    private CctvSource source; //TODO enum management
}
