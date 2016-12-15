package com.socialcoding.api.cctv.service;

import com.google.common.base.Preconditions;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.CctvSource;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.repository.CctvRepository;
import com.socialcoding.api.cctv.repository.PrivateCctvRepository;
import com.socialcoding.api.cctv.repository.PublicCctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", readOnly = true)
public class CctvQueryService {
	@Autowired
	private CctvRepository cctvRepository;
	@Autowired
	private PrivateCctvRepository privateCctvRepository;
	@Autowired
	private PublicCctvRepository publicCctvRepository;

	public Cctv getCctvById(long cctvId)  {
		Cctv cctv = cctvRepository.findOne(cctvId);
		Preconditions.checkArgument(cctv != null, "Cctv is not exist");
		if (CctvSource.PRIVATE == cctv.getSource()) {
			return privateCctvRepository.findOne(cctvId);
		} else if (CctvSource.PUBLIC == cctv.getSource()) {
			return publicCctvRepository.findOne(cctvId);
		}
		return cctv;
	}

	public List<Cctv> listCctvBetween(Position southWest, Position northEast) {
		return cctvRepository.findAllBetweenPosition(southWest, northEast);
	}

	public long countCctvBetween(Position southWest, Position northEast) {
		return cctvRepository.countAllBetweenPosition(southWest, northEast);
	}

}
