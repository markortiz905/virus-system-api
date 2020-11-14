package com.system.virus.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "virus_family")
public class VirusFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please provide a virus family name")
    private String name;
    @NotNull(message = "Please provide a min size")
    private Double sizeMin;
    @NotNull(message = "Please provide a max size")
    private Double sizeMax;
    @NotNull(message = "Please provide a type of strand [Single Strand, Double Strand or some others]")
    private String strand;
    @NotNull(message = "Please provide a enveloped [true or false]")
    private Boolean enveloped;

    @OneToMany(mappedBy = "family")
    private List<Virus> virusList = new ArrayList<>();

    public VirusFamily() {
    }

    public VirusFamily(@NotNull(message = "Please provide a virus family name") String name, @NotNull(message = "Please provide a min size") Double sizeMin, @NotNull(message = "Please provide a max size") Double sizeMax, @NotNull(message = "Please provide a type of strand [Single Strand, Double Strand or some others]") String strand, @NotNull(message = "Please provide a enveloped [true or false]") Boolean enveloped) {
        this.name = name;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
        this.strand = strand;
        this.enveloped = enveloped;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(Double sizeMin) {
        this.sizeMin = sizeMin;
    }

    public Double getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(Double sizeMax) {
        this.sizeMax = sizeMax;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public Boolean getEnveloped() {
        return enveloped;
    }

    public void setEnveloped(Boolean enveloped) {
        this.enveloped = enveloped;
    }

    public List<Virus> getVirusList() {
        return virusList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirusFamily that = (VirusFamily) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "VirusFamily{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sizeMin=" + sizeMin +
                ", sizeMax=" + sizeMax +
                ", strand='" + strand + '\'' +
                ", enveloped=" + enveloped +
                '}';
    }
}
