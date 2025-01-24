package com.giyeon.awsinfastudying.service;

import com.giyeon.awsinfastudying.domain.Test;
import com.giyeon.awsinfastudying.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestService {

    private final TestRepository testRepository;

    @Transactional
    public void save() {
        Test test = new Test();
        test.writeTestString("Hello world");
        testRepository.save(test);
    }
}
