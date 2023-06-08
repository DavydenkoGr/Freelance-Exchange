package org.freelance.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * General model for all type of users
 * Contains all users information
 */
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @Getter @Setter private Role role;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false)
    @Getter @Setter private String surname;

    @Column
    @Getter @Setter private int age;

    @Column(length = 50)
    @Getter @Setter private String address;

    @Column(length = 10000)
    @Getter @Setter private String resume;

    @Column
    @Getter @Setter private String organization;

    @Column(length = 10000)
    @Getter @Setter private String information;

    @OneToMany(mappedBy = "employee",
            fetch = FetchType.EAGER)
    @Getter @Setter private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "employer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @Getter @Setter private List<Task> offers = new ArrayList<>();

    @Transient
    @Getter @Setter long roleId;
}
