package com.giyeon.awsinfastudying.controller;

import com.giyeon.awsinfastudying.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private TestService testService;

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
}