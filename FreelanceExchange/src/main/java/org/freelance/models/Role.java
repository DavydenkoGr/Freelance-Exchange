package org.freelance.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
