package com.socialcoding.domain.services;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.repositories.CctvRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CctvService {
	@Autowired
	private CctvRepository cctvRepository;

	public Cctv getCctvDetailById(long cctvId) {
		Cctv cctv = cctvRepository.findOneWithComments(cctvId);
		log.debug("test: {}", cctv);
		return cctv;
	}
}
