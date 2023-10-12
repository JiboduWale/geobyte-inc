package com.geobyte.geobyteinc.service.implementations;

import com.geobyte.geobyteinc.data.dtos.*;
import com.geobyte.geobyteinc.data.dtos.request.LoginRequest;
import com.geobyte.geobyteinc.data.dtos.request.RegistrationRequest;
import com.geobyte.geobyteinc.data.enums.Role;
import com.geobyte.geobyteinc.data.models.User;
import com.geobyte.geobyteinc.repository.UserRepository;
import com.geobyte.geobyteinc.security.EmailAlreadyExist;
import com.geobyte.geobyteinc.security.JwtService;
import com.geobyte.geobyteinc.security.UserNotFoundException;
import com.geobyte.geobyteinc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation of UserService that provides user registration and authentication functionality.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           EmailValidator emailValidator, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Register a new user.
     *
     * @param registrationRequest The registration request containing user details.
     * @return Response indicating the result of the registration.
     * @throws EmailAlreadyExist If the email is already registered.
     */
    @Override
    public Response register(RegistrationRequest registrationRequest) {
        validateRegistrationRequest(registrationRequest);

        User newUser = createUserFromRegistrationRequest(registrationRequest);
        userRepository.save(newUser);

        return Response.builder()
                .message("Registration successful")
                .status(HttpStatus.CREATED)
                .build();
    }

    /**
     * Authenticate a user with the provided login credentials.
     *
     * @param loginRequest The login request containing user credentials.
     * @return AuthenticationResponse with a JWT token upon successful authentication.
     * @throws UserNotFoundException If the user is not found or the credentials are invalid.
     */
    @Override
    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticateUser(loginRequest);

        String token = jwtService.generateToken(loginRequest.getEmail());

        return buildAuthenticationResponse(token);
    }

    // Private helper methods

    private void validateRegistrationRequest(RegistrationRequest registrationRequest) {
        boolean isValidEmail = emailValidator.verifyEmail(registrationRequest.getEmail());
        Optional<User> existingUser = userRepository.findUserByEmail(registrationRequest.getEmail());

        if (!isValidEmail || existingUser.isPresent()) {
            throw new EmailAlreadyExist("User with email already registered");
        }
    }

    private User createUserFromRegistrationRequest(RegistrationRequest registrationRequest) {
        User newUser = new User();
        newUser.setName(registrationRequest.getName());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setLocalDateTime(LocalDateTime.now());
        return newUser;
    }

    private Authentication authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new UserNotFoundException("Wrong email or password");
        }

        return authentication;
    }

    private AuthenticationResponse buildAuthenticationResponse(String token) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        response.setMessage("Authentication Successful");
        response.setStatus(HttpStatus.OK);
        return response;
    }
}
