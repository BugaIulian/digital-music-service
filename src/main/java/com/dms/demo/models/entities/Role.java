package com.dms.demo.models.entities;

import com.dms.demo.util.enums.RoleType;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role() {
        this.id = new ULID().nextULID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleType);
    }
}