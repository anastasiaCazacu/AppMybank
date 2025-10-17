package com.mybank.repository;

import com.mybank.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name); //cautare dupa nume
}
