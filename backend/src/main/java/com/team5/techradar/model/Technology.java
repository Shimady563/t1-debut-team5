package com.team5.techradar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "technologies")
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Technology that)) return false;
        return Objects.equals(name, that.name) && moved == that.moved
                && level == that.level && type == that.type
                && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, moved, level, type, isActive);
    }
}

