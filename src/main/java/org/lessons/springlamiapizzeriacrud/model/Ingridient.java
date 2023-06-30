package org.lessons.springlamiapizzeriacrud.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ingridients")
public class Ingridient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
