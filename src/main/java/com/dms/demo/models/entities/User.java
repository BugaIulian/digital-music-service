package com.dms.demo.models.entities;

import com.dms.demo.util.enums.UserRole;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", length = 26)
    private String id;

    @Column(name = "first_name")
    private String userFirstName;

    @Column(name = "second_name")
    private String userSecondName;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "dob")
    private LocalDate userBirthDate;

    @Column(name = "age")
    private int userAge;

    @Column(name = "interests")
    private String userInterests;

    @CollectionTable(name = "user_passwords", joinColumns = @JoinColumn(name = "user_id"))
    private String password;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "id"))
    private Set<UserRole> roles = new HashSet<>();

    @Column(name = "account_creation_date")
    private LocalDate userAccountCreationDate;

    public User() {
        this.id = new ULID().nextULID();
        this.roles.add(UserRole.ROLE_USER);
    }
}