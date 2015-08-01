package com.socialcoding.domain.services.cctv;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.repositories.cctv.CctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CctvService {
	@Autowired
	private CctvRepository cctvRepository;

	public Cctv getCctvDetailById(long cctvId) {
		return cctvRepository.findOneWithComments(cctvId);
	}
}
