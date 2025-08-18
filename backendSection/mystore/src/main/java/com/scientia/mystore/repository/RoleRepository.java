package com.scientia.mystore.repository;

import com.scientia.mystore.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable("roles")
    Optional<Role> findByName(String name);

}
