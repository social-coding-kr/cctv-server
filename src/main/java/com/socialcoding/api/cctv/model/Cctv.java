package com.socialcoding.api.cctv.model;

import com.socialcoding.api.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "source")
//@ToString(exclude = "comments")
@Table(name = "cctvs")
public abstract class Cctv extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cctvId", nullable = false)
    private Long cctvId;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "purpose")
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", insertable = false, updatable = false)
    private CctvSource source;

//	@OneToMany(mappedBy = "cctv", fetch = FetchType.LAZY)
//	private List<Comment> comments;
}
