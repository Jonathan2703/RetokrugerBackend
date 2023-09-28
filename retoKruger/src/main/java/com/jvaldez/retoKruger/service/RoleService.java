package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.model.entities.Role;

public interface RoleService {
    public Role getRoleById(Long id);

    public Role getRoleByName(String name);
}
