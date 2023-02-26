package com.news.api.service;

import com.news.api.domain.User;
import com.news.api.dto.user.UserRespDTO;
import com.news.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public UserRespDTO saveUser(User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return modelMapper.map(userRepository.save(newUser), UserRespDTO.class);
    }
}
