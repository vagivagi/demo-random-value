package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomValueService {
    private static final Logger log = LoggerFactory.getLogger(RandomValueService.class);
    private final int VALUE_LENGTH = 8;
    private final char[] NUMBER = IntStream.rangeClosed('0', '9')
            .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
    private final char[] LOWERCASE_ALPHABETS = IntStream.rangeClosed('a', 'z')
            .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
    private final char[] UPPERCASE_ALPHABETS = IntStream.rangeClosed('A', 'Z')
            .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
    private final Set<Character> FORBIDDEN_CODE_CODE = new HashSet<>(Arrays.asList('1', 'l', 'I', 'o', 'O', '0'));
    private final char[] COUPON_CODE_CHARACTERS =
            IntStream.rangeClosed(0x30, 0x7a)
                    .filter(c -> Character.isLetterOrDigit(c) && !FORBIDDEN_CODE_CODE.contains((char) c))
                    .mapToObj(c -> (char) c + "").collect(Collectors.joining()).toCharArray();

    public String randomValueForPassword() {
        String randomValue = RandomStringUtils.randomAlphanumeric(VALUE_LENGTH);
        while (isInValidRandomFormat(randomValue)) {
            randomValue = this.randomValueForPassword();
        }
        return randomValue;
    }

    public String randomValueForCouponCode() {
        String randomValue = RandomStringUtils.random(VALUE_LENGTH, COUPON_CODE_CHARACTERS);
        while (isInValidRandomFormat(randomValue)) {
            randomValue = this.randomValueForCouponCode();
        }
        return randomValue;
    }

    private boolean isInValidRandomFormat(String value) {
        log.info("check value={}", value);
        return !(StringUtils.containsAny(value, NUMBER)
                && StringUtils.containsAny(value, LOWERCASE_ALPHABETS)
                && StringUtils.containsAny(value, UPPERCASE_ALPHABETS));
    }
}
