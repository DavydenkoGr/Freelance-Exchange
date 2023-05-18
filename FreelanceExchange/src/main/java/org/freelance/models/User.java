package org.freelance.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(unique = true, nullable = false)
    @Getter @Setter private String login;

    @Column(nullable = false)
    @Getter @Setter private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @Getter @Setter private Role role;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false)
    @Getter @Setter private String surname;

    @Getter @Setter private int age;

    @Column(length = 10000)
    @Getter @Setter private String resume;

    @Column(columnDefinition = "varchar(255) default 'sole trader'")
    @Getter @Setter private String organization;

    @Column(length = 10000)
    @Getter @Setter private String information;

    @OneToMany(mappedBy = "employee")
    private List<Task> tasks =  new ArrayList<>();

    @OneToMany(mappedBy = "employer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Task> offers = new ArrayList<>();
}
