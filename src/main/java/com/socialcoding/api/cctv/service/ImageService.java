package com.socialcoding.api.cctv.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImageService {

    private AtomicInteger atomicInteger = new AtomicInteger();

    public String saveImage(MultipartFile file, String path) {
        if (file == null) {
            return null;
        }

        try {
            String filename = genFilename(file);
			file.transferTo(new File(path + filename));
            return filename;
        } catch (IOException | NoSuchAlgorithmException e) {
            //TODO exception handling
            log.error("File save failed", e);
            throw new RuntimeException("File save failed", e);
        }
    }

    private String genFilename(MultipartFile file) throws NoSuchAlgorithmException {
        int seed = atomicInteger.getAndIncrement();
        //TODO contentType에서 가져오도록
        StringBuilder filenameWillDigest = new StringBuilder()
                .append(seed).append(new Date())
                .append(file.getOriginalFilename());
        String filename = DigestUtils.sha1Hex(filenameWillDigest.toString());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return filename + "." + extension;
    }
}
