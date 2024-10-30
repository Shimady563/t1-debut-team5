package com.team5.techradar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vote")
@NamedEntityGraph(
        name = "vote_user_technology_load_graph",
        includeAllAttributes = true
)
public class Vote {

    @Id
    @SequenceGenerator(
            name = "vote_id_seq",
            sequenceName = "vote_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(generator = "vote_id_seq")
    private Long id;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id")
    private Technology technology;
}
