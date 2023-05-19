package com.dms.demo.models.entities;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(length = 26)
    private String id;

    public User(String id) {
        this.id = new ULID().nextULID();
    }
}