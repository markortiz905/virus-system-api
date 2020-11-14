package com.system.virus.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Virus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please provide a virus name")
    private String name;
    @NotNull(message = "Please provide a human_affected")
    private Boolean humanAffected;
    @NotNull(message = "Please provide a animal_affected")
    private Boolean animalAffected;

    @ManyToOne
    private VirusFamily family;

    public Virus() {
    }

    public Virus(@NotNull(message = "Please provide a virus name") String name, @NotNull(message = "Please provide a human_affected") Boolean humanAffected, @NotNull(message = "Please provide a animal_affected") Boolean animalAffected, VirusFamily family) {
        this.name = name;
        this.humanAffected = humanAffected;
        this.animalAffected = animalAffected;
        this.family = family;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getHumanAffected() {
        return humanAffected;
    }

    public Boolean getAnimalAffected() {
        return animalAffected;
    }

    public VirusFamily getFamily() {
        return family;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHumanAffected(Boolean humanAffected) {
        this.humanAffected = humanAffected;
    }

    public void setAnimalAffected(Boolean animalAffected) {
        this.animalAffected = animalAffected;
    }

    public void setFamily(VirusFamily family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Virus virus = (Virus) o;
        return getId().equals(virus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Virus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", humanAffected=" + humanAffected +
                ", animalAffected=" + animalAffected +
                ", family=" + family.getName() +
                '}';
    }
}
