package com.test.testjenkins;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final Environment env;


    @GetMapping("/")
    public String checkPort() {
        List<String> portCheck = Arrays.asList(env.getActiveProfiles());
        List<String> realPortChecks = Arrays.asList("real1", "real2");
        String defaultPortCheck = portCheck.isEmpty() ? "default" : portCheck.get(0);

        return portCheck.stream()
                .filter(realPortChecks::contains)
                .findAny()
                .orElse(defaultPortCheck);
    }


    @GetMapping("/hello")
    public String sayHello() {
        return "자동 배포 진짜 성공...!!!!";
    }

}
