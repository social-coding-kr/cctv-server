package com.socialcoding.domain.services.reliability;

import com.socialcoding.domain.models.Reliability;
import com.socialcoding.domain.repositories.reliability.ReliabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", readOnly = true)
public class ReliabilityService {
	@Autowired
	private ReliabilityRepository reliabilityRepository;

	public TotalReliability getTotalReliabilityByCctvId(Long cctvId) {
		List<Reliability> reliabilities = reliabilityRepository.findByCctvId(cctvId);
		return TotalReliability.of(getCorrectReliability(reliabilities), getIncorrectReliability(reliabilities));
	}

	public long getCorrectReliability(List<Reliability> reliabilities) {
		return reliabilities.stream().filter(reliability -> reliability.getReliable()).count();
	}

	public long getIncorrectReliability(List<Reliability> reliabilities) {
		return reliabilities.stream().filter(reliability -> !reliability.getReliable()).count();
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Reliability selectReliability(Reliability reliability) {
		return reliabilityRepository.save(reliability);
	}
}
