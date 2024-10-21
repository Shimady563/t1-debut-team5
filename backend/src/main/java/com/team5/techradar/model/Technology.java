package com.team5.techradar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "technology")
public class Technology {

    @Id
    @GeneratedValue(generator = "technology_id_seq")
    @SequenceGenerator(
            name = "technology_id_seq",
            sequenceName = "technology_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "moved")
    @Enumerated(EnumType.STRING)
    private Moved moved = Moved.NOT_MOVED;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_technology",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }
}

