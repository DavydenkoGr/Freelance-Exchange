package org.freelance.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private int age;

    private String resume;

    @OneToMany(mappedBy = "tasks")
    private List<Task> tasks;
}
