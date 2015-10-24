package com.socialcoding.api.cctv.service;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.CctvSource;
import com.socialcoding.api.cctv.repository.CctvRepository;
import com.socialcoding.api.common.assembler.ObjectAssembler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(value = "transactionManager", readOnly = false)
public class CctvCommandService {
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

	private String saveImage(MultipartFile image, String imagePath, String imageUrlMiddle) {
		if (image == null || StringUtils.isEmpty(image.getOriginalFilename())) {
			return null;
		}

		String filename = imageService.saveImage(image, imagePath);
		return new StringBuilder(baseUrl).append(imageUrlMiddle).append(filename).toString();
	}

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
