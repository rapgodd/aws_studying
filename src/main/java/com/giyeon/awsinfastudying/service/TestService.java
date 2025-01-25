package com.giyeon.awsinfastudying.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.giyeon.awsinfastudying.domain.Test;
import com.giyeon.awsinfastudying.dto.ImageDto;
import com.giyeon.awsinfastudying.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestService {

    private final TestRepository testRepository;
    private final AmazonS3 amazonS3Client;
    @Value("${spring.cloud.aws.s3.bucket}")
    private String BUCKET;

    @Transactional
    public void save() {
        Test test = new Test();
        test.writeTestString("Hello world");
        testRepository.save(test);
    }

    @Transactional
    public void upload(MultipartFile file) throws IOException {
        try{
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(BUCKET + "/post", file.getOriginalFilename(), file.getInputStream(), metadata);


            String s3Url = amazonS3Client.getUrl(BUCKET + "/post", file.getOriginalFilename()).toString();
            Test test = new Test();
            test.writeTestString(s3Url);
            testRepository.save(test);

        }catch (Exception e){
            throw new FileUploadException();
        }
    }

    public ImageDto getUserImage() {
        Test image = testRepository.getImage();
        return new ImageDto(image.getTestString());
    }
}
