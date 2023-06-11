package com.dms.demo.models.entities;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
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

    @Column(name = "music_genre")
    private String musicGenre;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "artist_roles", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> artistRoles = new HashSet<>();

    @Column(name = "account_creation_date")
    private LocalDate accountCreationDate;

    public Artist() {
        this.id = new ULID().nextULID();
    }
}