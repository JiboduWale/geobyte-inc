package com.geobyte.geobyteinc.data.models;

import com.geobyte.geobyteinc.data.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime localDateTime;
}
