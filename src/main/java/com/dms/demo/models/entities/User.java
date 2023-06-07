package com.dms.demo.models.entities;

import com.dms.demo.models.datamapping.gender.GenderConverter;
import com.dms.demo.util.enums.Gender;
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
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "age")
    private int age;

    @Column(name = "interests")
    private String interests;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @ElementCollection(fetch = FetchType.EAGER)
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