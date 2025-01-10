package com.spring.api.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.api.jwt.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

    Optional<RoleEntity> findByName(String name);

}
