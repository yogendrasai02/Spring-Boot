package com.springboot.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@AllArgsConstructor
@Data
class TestClass {
    private String message;
}

//@Component
//@Controller
@RestController
public class TestController {

    @Value("${sample.message}")
    private String message;

    // @RequestMapping(value = "/test-endpoint", method = RequestMethod.GET)
    @GetMapping("/test-endpoint")
    public TestClass testMethod() {
        return new TestClass(message);
    }

}
