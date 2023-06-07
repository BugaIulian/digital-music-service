package com.dms.demo.models.entities;

import com.dms.demo.util.enums.UserRoles;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    public Role() {
        this.id = new ULID().nextULID();
    }
}