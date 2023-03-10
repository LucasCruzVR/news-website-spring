package com.news.api.config;

import com.news.api.domain.Privilege;
import com.news.api.domain.Role;
import com.news.api.domain.User;
import com.news.api.repository.PrivilegeRepository;
import com.news.api.repository.RoleRepository;
import com.news.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;

    @Value("${admin.username}")
    private String email;

    @Value("${admin.password}")
    private String password;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        var emailExists = userRepository.findByEmail(email);
        if (emailExists != null) {
            return;
        }

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Set<Privilege> adminPrivileges = new HashSet<>();
        Collections.addAll(adminPrivileges, readPrivilege, writePrivilege);
        Set<Privilege> userPrivileges = new HashSet<>();
        Collections.addAll(userPrivileges, readPrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_STAFF", userPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        Role role = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> adminRole = new HashSet<>();
        Collections.addAll(adminRole, role);

        User user = new User();
        user.setName("Admin");
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(adminRole);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(null, name, null);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Set<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(null, name, null, privileges);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
