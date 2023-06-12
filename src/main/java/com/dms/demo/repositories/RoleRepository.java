package com.dms.demo.repositories;

import com.dms.demo.models.entities.Role;
import com.dms.demo.util.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleType(RoleType roleType);

}