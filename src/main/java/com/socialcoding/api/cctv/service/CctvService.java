package com.socialcoding.api.cctv.service;

import com.google.common.base.Preconditions;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.CctvSource;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.repository.CctvRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class CctvService {
	@Value("${image.path.cctv}")
	private String cctvImagePath;
	@Value("${image.path.notice}")
	private String noticeImagePath;
	@Value("${image.url.base}")
	private String baseUrl;

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
		String filename = imageService.saveImage(cctvImage, cctvImagePath);
		log.debug("cctv image filename: {}", filename);
		return new StringBuilder(baseUrl).append("cctvs/").append(filename).toString();
	}

	public String saveNoticeImage(MultipartFile noticeImage) {
		if (noticeImage == null) {
			return null;
		}
		String filename = imageService.saveImage(noticeImage, noticeImagePath);
		log.debug("notice image filename: {}", filename);
		return new StringBuilder(baseUrl).append("notices/").append(filename).toString();
	}
}
