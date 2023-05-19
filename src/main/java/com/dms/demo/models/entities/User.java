package com.dms.demo.models.entities;

import com.dms.demo.util.enums.UserRole;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", length = 26)
    private String id;

    @Column(name = "first_name")
    private String userFirstName;

    @Column(name = "second_name")
    private String userSecondName;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "dob")
    private LocalDate userDateOfBirth;

    @Column(name = "interests")
    private String userInterests;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name ="user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserRole> roles;

    @Column(name = "account_creation_date")
    private LocalDate userAccountCreationDate;

    public User(String id) {
        this.id = new ULID().nextULID();
        this.roles.add(UserRole.ROLE_USER);
    }
}