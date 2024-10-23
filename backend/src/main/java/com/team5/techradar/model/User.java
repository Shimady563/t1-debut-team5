package com.team5.techradar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tech_radar_user")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "tech_radar_user_id_seq",
            sequenceName = "tech_radar_user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(generator = "tech_radar_user_id_seq")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_technology",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    public void addTechnology(Technology technology) {
        technologies.add(technology);
    }

    public void removeTechnology(Technology technology) {
        technologies.remove(technology);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
