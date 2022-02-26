package es.dylanhurtado.springbootapirestjava.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "trainer")
public class Trainer {
    private UUID id=UUID.randomUUID();
    private String username;
    private String password;
    private String avatar;
    private String fullName;
    private String email;
    private Set<TrainerRole> roles;
    private List<Pokemon> teamPokemon;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    public Trainer() {
    }

    public Trainer(String username, String password, String avatar, String fullName, String email, Set<TrainerRole> roles, List<Pokemon> teamPokemon) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.fullName = fullName;
        this.email = email;
        this.roles = roles;
        this.teamPokemon = teamPokemon;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    public Set<TrainerRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<TrainerRole> roles) {
        this.roles = roles;
    }

    @CreatedDate
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @LastModifiedDate
    public LocalDateTime getLastPasswordChangeAt() {
        return lastPasswordChangeAt;
    }

    public void setLastPasswordChangeAt(LocalDateTime lastPasswordChangeAt) {
        this.lastPasswordChangeAt = lastPasswordChangeAt;
    }
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    public List<Pokemon> getTeamPokemon() {
        return teamPokemon;
    }

    public void setTeamPokemon(List<Pokemon> carrito) {
        this.teamPokemon = carrito;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", teamPokemon=" + teamPokemon +
                ", createdAt=" + createdAt +
                ", lastPasswordChangeAt=" + lastPasswordChangeAt +
                '}';
    }
}
