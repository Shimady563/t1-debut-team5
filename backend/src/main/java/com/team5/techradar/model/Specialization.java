package com.team5.techradar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "specialization")
public class Specialization {

    @Id
    @SequenceGenerator(
            name = "specialization_id_seq",
            sequenceName = "specialization_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(generator = "specialization_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialization")
    private List<User> users = new ArrayList<>();
}
