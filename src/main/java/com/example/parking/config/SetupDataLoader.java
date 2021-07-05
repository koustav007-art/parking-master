package com.example.parking.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.parking.model.Role;
import com.example.parking.model.RoleRepository;
import com.example.parking.model.UserEntity;
import com.example.parking.model.UserRepository;
 
 
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private boolean alreadySetup = false;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
 
        // Create user roles
        Role userRole = createRoleIfNotFound(Role.ROLE_USER);
        Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
 
        // Create users
        createUserIfNotFound("Car:WB24354138", userRole);
        createUserIfNotFound("admin", adminRole);
 
        alreadySetup = true;
    }
 
    @Transactional
    private final Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }
 
    @Transactional
    private final UserEntity createUserIfNotFound(final String name, final Role role) {
        UserEntity user = userRepository.findByUsername(name);
        if (user == null) {
            user = new UserEntity(name, "{noop}password");
            Set<Role> set = new HashSet<Role>();
            set.add(role);
            user.setRoles(set);
            user = userRepository.save(user);
        }
        return user;
    }
    

}
