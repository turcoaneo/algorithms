package org.algorithms.test.copilot.thales;


import jakarta.persistence.*;

@Entity
@Table
public class DetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String details;

    public DetailsEntity() {}

    public DetailsEntity(String details) {
        this.details = details;
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }
}