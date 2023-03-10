package com.news.api.service;

import com.news.api.domain.Privilege;
import com.news.api.domain.Role;
import com.news.api.domain.User;
import com.news.api.dto.UserAuthReqDTO;
import com.news.api.repository.UserRepository;
import com.news.api.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.*;

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

    private List<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    public Map<String, String> authenticationToken(UserAuthReqDTO userReq) {
        Map<String, String> map = new HashMap<>();
        User user = userRepository.findByEmail(userReq.getEmail());
        String accessToken = jwtConfig.generateToken(user.getEmail());
        map.put("token", accessToken);
        return map;
    }
}
