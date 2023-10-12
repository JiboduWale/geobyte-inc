package com.geobyte.geobyteinc.service;

import com.geobyte.geobyteinc.data.dtos.*;
import com.geobyte.geobyteinc.data.dtos.request.LoginRequest;
import com.geobyte.geobyteinc.data.dtos.request.RegistrationRequest;


public interface UserService {
    Response register(RegistrationRequest registrationRequest);
    AuthenticationResponse authenticate(LoginRequest loginRequest);

}
