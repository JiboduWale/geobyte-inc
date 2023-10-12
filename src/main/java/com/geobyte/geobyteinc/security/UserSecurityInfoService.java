package com.geobyte.geobyteinc.security;


import com.geobyte.geobyteinc.data.models.User;
import com.geobyte.geobyteinc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSecurityInfoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> userInfo =  userRepository.findUserByEmail(username);
       return userInfo.map(AppUserInfoDetails::new)
               .orElseThrow(() -> new UserNotFoundException(
                       String.format("%S%S%S", "user with email",
                               username, "not found")));
    }
}
