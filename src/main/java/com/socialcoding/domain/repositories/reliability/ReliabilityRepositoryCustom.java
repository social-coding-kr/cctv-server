package com.socialcoding.domain.repositories.reliability;

import com.socialcoding.domain.models.Reliability;

import java.util.List;

public interface ReliabilityRepositoryCustom {
	List<Reliability> findByCctvId(Long cctvId);
}
