package com.socialcoding.api.cctv.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImageService {

	private static final int MAX_SIZE = 400;

	private AtomicInteger atomicInteger = new AtomicInteger();

    public String saveImage(MultipartFile image, String path) {
		try {
			if (image.getSize() == 0) {
				throw new RuntimeException("image size is 0");
			}

			BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
			BufferedImage resized = resizeImage(bufferedImage);
			String filename = genFilename(image);
			String filePath = FilenameUtils.normalize(path + filename);
			ImageIO.write(resized, FilenameUtils.getExtension(filePath), new File(filePath));
			log.debug("image saved: {}", filePath);
			return filename;
		} catch (Exception e) {
			//TODO exception handling
			log.error("File save failed", e);
			throw new RuntimeException("File save failed", e);
		}
    }

    private String genFilename(MultipartFile file) throws NoSuchAlgorithmException {
        int seed = atomicInteger.getAndIncrement();
        //TODO contentType에서 가져오도록
        StringBuilder filenameBase = new StringBuilder()
                .append(seed).append(new Date())
                .append(file.getOriginalFilename());
        String filename = DigestUtils.sha1Hex(filenameBase.toString());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return filename + "." + extension;
    }

	private BufferedImage resizeImage(BufferedImage image) {
		Scalr.Mode mode = image.getWidth() > image.getHeight() ? Scalr.Mode.FIT_TO_WIDTH : Scalr.Mode.FIT_TO_HEIGHT;
		return Scalr.resize(image, Scalr.Method.QUALITY, mode, MAX_SIZE);
	}
}
