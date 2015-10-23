package com.socialcoding.api.cctv.service;

import com.google.common.base.Preconditions;
import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.CctvSource;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.repository.CctvRepository;
import com.socialcoding.api.common.assembler.ObjectAssembler;
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
	private ObjectAssembler assembler;
	@Autowired
	private ImageService imageService;
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

	private String saveImage(MultipartFile image, String imagePath, String imageUrlMiddle) {
		if (image == null) {
			return null;
		}

		String filename = imageService.saveImage(image, imagePath);
		return new StringBuilder(baseUrl).append(imageUrlMiddle).append(filename).toString();
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Cctv registerPrivateCctv(CctvRegistrationForm form, MultipartFile cctvImage, MultipartFile noticeImage) {
		String cctvImageUrl = saveImage(cctvImage, cctvImagePath, "cctvs/");
		String noticeImageUrl = saveImage(noticeImage, noticeImagePath, "notices/");

		Cctv cctv = assembler.assemble(form, Cctv.class);
		cctv.setCctvImage(cctvImageUrl);
		cctv.setNoticeImage(noticeImageUrl);
		cctv.setSource(CctvSource.PRIVATE);
		cctv.setModifiedBy(cctv.getCreatedBy());
		cctv.setCctvName(CctvSource.PRIVATE.name());

		return cctvRepository.save(cctv);
	}
}
