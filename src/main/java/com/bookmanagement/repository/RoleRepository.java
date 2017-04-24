package com.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
