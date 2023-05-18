package org.freelance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(nullable = false)
    @Getter @Setter String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    @Getter @Setter private User employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @Getter @Setter private User employee;

    @Column(nullable = false)
    @Getter @Setter private int price;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Getter @Setter private boolean isFree;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Getter @Setter private boolean isCompleted;

    @Column(length = 10000)
    @Getter @Setter private String description;
}
