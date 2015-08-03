package com.socialcoding.domain.services.reliability;

import com.socialcoding.domain.models.Reliability;
import com.socialcoding.domain.repositories.reliability.ReliabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReliabilityService {
	@Autowired
	private ReliabilityRepository reliabilityRepository;

	public ReliablePoint getReliablePointByCctvId(Long cctvId) {
		List<Reliability> reliabilities = reliabilityRepository.findByCctvId(cctvId);
		return ReliablePoint.of(getCorrectPoint(reliabilities), getIncorrectPoint(reliabilities));
	}

	public long getCorrectPoint(List<Reliability> reliabilities) {
		return reliabilities.stream().filter(point -> point.getReliable()).count();
	}

	public long getIncorrectPoint(List<Reliability> reliabilities) {
		return reliabilities.stream().filter(point -> !point.getReliable()).count();
	}
}
