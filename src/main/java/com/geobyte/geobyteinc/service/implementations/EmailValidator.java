package com.geobyte.geobyteinc.service.implementations;

import com.geobyte.geobyteinc.security.InvalidEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class EmailValidator {

    private static final String EMAIL_REGEX_STRING="^[A-Za-z0-9+_.-]+@(.+)$";
    public boolean verifyEmail(String email) {
        if (email.matches(EMAIL_REGEX_STRING)) {
            return true;
        } else throw new InvalidEmail("Email is not valid");
    }
}
