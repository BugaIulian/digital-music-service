package com.dms.demo.services.role;

import com.dms.demo.models.entities.Role;
import com.dms.demo.repositories.RoleRepository;
import com.dms.demo.util.enums.UserRoles;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(UserRoles userRoles) {
        Role role = new Role();
        role.setUserRoles(userRoles);
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleByName(UserRoles userRoles) {
    //TODO add some useful code here
        return Optional.empty();
    }
}