package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.model.entities.UserEntity;

public interface UserService {
    public UserEntity getUserById(Long id);

    public UserEntity saveUser(UserEntity userEntity);
}
