package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PasswordEncoderTest {

    @Test
    public void testEncoderPassWord() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "Admin123";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        boolean match = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println(match);
        assertThat(match).isTrue();
    }
}
