package es.dylanhurtado.springbootapirestjava.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    private UUID id=UUID.randomUUID();
    private String name;
    private Double shinyRate;
    private Integer level;
    private String image;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Trainer trainer;

    public Pokemon( String name, Double shinyRate, Integer level, String image, Trainer trainer) {
        this.name = name;
        this.shinyRate = shinyRate;
        this.level = level;
        this.image = image;
        this.trainer = trainer;
    }

    public Pokemon() {
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getShinyRate() {
        return shinyRate;
    }

    public void setShinyRate(Double shinyRate) {
        this.shinyRate = shinyRate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @CreatedDate
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shinyRate=" + shinyRate +
                ", level=" + level +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", trainer=" + trainer +
                '}';
    }
}

