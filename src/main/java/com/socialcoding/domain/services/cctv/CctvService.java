package com.socialcoding.domain.services.cctv;

import com.google.common.base.Preconditions;
import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.repositories.cctv.CctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "transactionManager", readOnly = true)
public class CctvService {
	@Autowired
	private CctvRepository cctvRepository;

	public Cctv getCctvById(long cctvId)  {
		Cctv cctv = cctvRepository.findOne(cctvId);
		Preconditions.checkArgument(cctv != null, "Cctv is not exist");
		return cctv;
	}

	public Cctv getCctvDetailById(long cctvId) {
		Cctv cctv = cctvRepository.findOne(cctvId);
		Preconditions.checkArgument(cctv != null, "Cctv is not exist");
		return cctv;
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Cctv register(Cctv cctv) {
		cctv.setSource(CctvSource.USER);
		return cctvRepository.save(cctv);
	}
}
