package com.giyeon.awsinfastudying.controller;

import com.giyeon.awsinfastudying.dto.ImageDto;
import com.giyeon.awsinfastudying.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hello from /api/test");
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("Success health check!");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save() {
        testService.save();
        return ResponseEntity.ok("Save success!");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            testService.upload(file);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Upload success!");
    }

    @GetMapping("/image")
    public ImageDto getImage(){
        return testService.getUserImage();
    }
}