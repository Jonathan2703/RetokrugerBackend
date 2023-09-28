package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.Repository.UserRepository;
import com.jvaldez.retoKruger.model.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuario = userRepository.findByUsername(username);
        String rol = usuario.getRole().getName().toUpperCase();
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(usuario.getPassword()).roles(rol).build();
    }
}
