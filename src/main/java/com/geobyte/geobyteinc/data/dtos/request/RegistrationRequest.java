package com.geobyte.geobyteinc.data.dtos.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
}
