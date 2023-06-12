package com.dms.demo.services.role;

import com.dms.demo.models.entities.Role;
import com.dms.demo.util.enums.RoleType;

import java.util.Optional;

public interface RoleService {

    public Role createRole(RoleType roleType);

    public Optional<Role> getRoleByName(RoleType roleType);

    Role getOrCreateRole(RoleType roleType);
}