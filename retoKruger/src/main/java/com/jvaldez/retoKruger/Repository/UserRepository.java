package com.jvaldez.retoKruger.Repository;

import com.jvaldez.retoKruger.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
