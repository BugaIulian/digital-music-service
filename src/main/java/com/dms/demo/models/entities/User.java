package com.dms.demo.models.entities;

import com.dms.demo.models.datamapping.gender.GenderConverter;
import com.dms.demo.util.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> userRoles = new HashSet<>();

    @Column(name = "account_creation_date")
    private LocalDate accountCreationDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "sms_token")
    private Integer smsToken;

    @Column(name = "token_expiry_time")
    private LocalDateTime tokenExpiryTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    @JsonBackReference
    private Subscription subscription;

    public User() {
        this.id = new ULID().nextULID();
    }
}