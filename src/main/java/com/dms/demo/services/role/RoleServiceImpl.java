package com.dms.demo.services.role;

import com.dms.demo.models.entities.Role;
import com.dms.demo.repositories.RoleRepository;
import com.dms.demo.util.enums.RoleType;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(RoleType roleType) {
        Role role = new Role();
        role.setRoleType(roleType);
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleByName(RoleType roleType) {
        //TODO add some useful code here
        return Optional.empty();
    }

    @Override
    public Role getOrCreateRole(RoleType roleType) {
        Optional<Role> optionalRole = Optional.ofNullable(roleRepository.findByRoleType(roleType));
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        } else {
            Role newRole = new Role();
            newRole.setRoleType(roleType);
            return roleRepository.save(newRole);
        }
    }
}