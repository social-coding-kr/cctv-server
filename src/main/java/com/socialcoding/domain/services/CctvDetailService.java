package com.socialcoding.domain.services;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.repositories.CctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CctvDetailService {
	@Autowired
	private CctvRepository cctvRepository;

	public Cctv getCctvById(long cctvId) {
		return cctvRepository.findOne(cctvId);
	}
}
