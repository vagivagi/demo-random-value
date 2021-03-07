package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomValueController {
    private final RandomValueService randomValueService;

    public RandomValueController(RandomValueService randomValueService) {
        this.randomValueService = randomValueService;
    }

    @GetMapping("password")
    String getPasswordValue() {
        return randomValueService.randomValueForPassword();
    }

    @GetMapping("coupon")
    String getCouponCodeValue() {
        return randomValueService.randomValueForCouponCode();
    }
}
