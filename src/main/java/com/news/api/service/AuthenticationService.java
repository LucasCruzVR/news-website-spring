package com.news.api.service;

import com.news.api.domain.User;
import com.news.api.model.UserAuthReqDTO;
import com.news.api.model.UserRespDTO;
import com.news.api.repository.UserRepository;
import com.news.api.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService{

    private final JwtUtil jwtConfig;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return modelMapper.map(user, UserAuthReqDTO.class);
    }

    public Map<String, String> authenticationToken(UserAuthReqDTO userReq) {
        Map<String, String> map = new HashMap<>();
        User user = userRepository.findByEmail(userReq.getEmail());
        String accessToken = jwtConfig.generateToken(user.getEmail());
        map.put("token", accessToken);
        return map;
    }
}
