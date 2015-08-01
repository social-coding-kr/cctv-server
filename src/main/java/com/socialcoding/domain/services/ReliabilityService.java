package com.socialcoding.domain.services;

import com.socialcoding.domain.models.Reliability;
import com.socialcoding.domain.repositories.ReliabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReliabilityService {
	@Autowired
	private ReliabilityRepository reliabilityRepository;

	public ReliablePoint getReliablePointByCctvId(Long cctvId) {
		List<Reliability> reliabilities = reliabilityRepository.findByCctvId(cctvId);

		long correctPoint = reliabilities.stream().filter(point -> point.getReliable()).count();
		long incorrectPoint = reliabilities.stream().filter(point -> !point.getReliable()).count();

		return ReliablePoint.of(correctPoint, incorrectPoint);
	}
}
