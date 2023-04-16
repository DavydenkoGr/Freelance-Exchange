package org.freelance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(unique = true, nullable = false)
    @Getter @Setter private String login;

    @Column(nullable = false)
    @Getter @Setter private String password;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false)
    @Getter @Setter private String surname;

    @Column(columnDefinition = "varchar(255) default 'sole trader'")
    @Getter @Setter private String organization;

    @Column(length = 10000)
    @Getter @Setter private String information;

    @OneToMany(mappedBy = "tasks")
    private List<Task> tasks;
}
