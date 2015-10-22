package com.socialcoding.api.cctv.service;

import com.google.common.base.Preconditions;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.CctvSource;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.repository.CctvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional(value = "transactionManager", readOnly = true)
public class CctvService {
	private static final String CCTV_IMAGE_PATH = "/cctv/images/cctv-images";
	private static final String NOTICE_IMAGE_PATH = "/cctv/images/notice-images";
	private static final String BASE_URL = "http://147.46.215.150:8099/images";

	@Autowired
	private ImageService imageService;
	@Autowired
	private CctvRepository cctvRepository;

	public Cctv getCctvById(long cctvId)  {
		Cctv cctv = cctvRepository.findOne(cctvId);
		Preconditions.checkArgument(cctv != null, "Cctv is not exist");
		return cctv;
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Cctv registerCctv(Cctv cctv) {
		cctv.setSource(CctvSource.PRIVATE);
		cctv.setModifiedBy(cctv.getCreatedBy());
		cctv.setCctvName(CctvSource.PRIVATE.name());
		return cctvRepository.save(cctv);
	}

	public List<Cctv> listCctvBetween(Position southWest, Position northEast) {
		return cctvRepository.findAllBetweenPosition(southWest, northEast);
	}

	public String saveCctvImage(MultipartFile cctvImage) {
		String filename = imageService.saveImage(cctvImage, CCTV_IMAGE_PATH);
		return new StringBuilder(BASE_URL).append("/cctvs/").append(filename).toString();
	}

	public String saveNoticeImage(MultipartFile noticeImage) {
		String filename = imageService.saveImage(noticeImage, NOTICE_IMAGE_PATH);
		return new StringBuilder(BASE_URL).append("/notices/").append(filename).toString();
	}
}
