package com.geobyte.geobyteinc.data.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse extends Response{
    private String token;
}
