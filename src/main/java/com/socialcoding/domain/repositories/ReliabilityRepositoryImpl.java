package com.socialcoding.domain.repositories;

import com.socialcoding.domain.models.QReliability;
import com.socialcoding.domain.models.Reliability;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class ReliabilityRepositoryImpl extends QueryDslRepositorySupport implements ReliabilityRepositoryCustom {
	private QReliability reliability = QReliability.reliability;

	public ReliabilityRepositoryImpl() {
		super(Reliability.class);
	}

	@Override
	public List<Reliability> findByCctvId(Long cctvId) {
		return from(reliability).where(reliability.cctv.cctvId.eq(cctvId)).list(reliability);
	}
}
