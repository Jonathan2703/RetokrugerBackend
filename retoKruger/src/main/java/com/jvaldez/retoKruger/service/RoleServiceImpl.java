package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.Repository.RoleRepository;
import com.jvaldez.retoKruger.model.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

}
