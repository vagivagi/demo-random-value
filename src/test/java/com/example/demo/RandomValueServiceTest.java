package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RandomValueServiceTest {
    private RandomValueService randomValueService = new RandomValueService();

    @Test
    public void is_password() {
        for (int i = 0; i < 100; i++) {
            String password = randomValueService.randomValueForPassword();
            this.assertPassword(password);
        }
    }

    private void assertPassword(String password) {
        assertEquals(8, password.length());
        assertTrue(StringUtils.containsAny(password, "0123456789"));
        assertTrue(StringUtils.containsAny(password, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(StringUtils.containsAny(password, "abcdefghijklmnopqrstuvwxyz".toUpperCase()));
    }

    @Test
    public void is_coupon_code() {
        for (int i = 0; i < 100; i++) {
            String code = randomValueService.randomValueForCouponCode();
            this.assertCouponCode(code);
        }
    }

    private void assertCouponCode(String code) {
        assertEquals(8, code.length());
        assertTrue(StringUtils.containsAny(code, "23456789"));
        assertTrue(StringUtils.containsAny(code, "abcdefghijkmnpqrstuvwxyz"));
        assertTrue(StringUtils.containsAny(code, "abcdefghijkmnpqrstuvwxyz".toUpperCase()));
        assertTrue(!StringUtils.containsAny(code, "0oO1lI"));
    }
}
