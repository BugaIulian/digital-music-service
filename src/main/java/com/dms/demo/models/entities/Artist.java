package com.dms.demo.models.entities;

import com.dms.demo.util.enums.UserRole;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "id", length = 26)
    private String id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "city")
    private String city;

    @Column(name = "password")
    private String password;

    @Column(name = "stage_name")
    private String stageName;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "id"))
    private Set<UserRole> roles = new HashSet<>();

    public Artist() {
        this.id = new ULID().nextULID();
        this.roles.add(UserRole.ROLE_ARTIST);
    }
}