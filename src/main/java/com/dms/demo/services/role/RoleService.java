package com.dms.demo.services.role;

import com.dms.demo.models.entities.Role;
import com.dms.demo.util.enums.UserRoles;

import java.util.Optional;

public interface RoleService {

    public Role createRole(UserRoles userRoles);

    public Optional<Role> getRoleByName(UserRoles userRoles);
}