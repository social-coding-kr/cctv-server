package com.socialcoding.api.cctv.service;

import com.google.common.base.Preconditions;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.repository.CctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", readOnly = true)
public class CctvQueryService {
	@Autowired
	private CctvRepository cctvRepository;

	public Cctv getCctvById(long cctvId)  {
		Cctv cctv = cctvRepository.findOne(cctvId);
		Preconditions.checkArgument(cctv != null, "Cctv is not exist");
		return cctv;
	}

	public List<Cctv> listCctvBetween(Position southWest, Position northEast) {
		return cctvRepository.findAllBetweenPosition(southWest, northEast);
	}
}
