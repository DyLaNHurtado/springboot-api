package es.dylanhurtado.springbootapirestjava.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "TRAINER")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Trainer implements UserDetails {

    @Id
    private UUID id = UUID.randomUUID();
    private String username;
    private String password;
    private String avatar;
    private String fullName;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<TrainerRole> roles;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    private List<Pokemon> teamPokemon;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
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

    public Trainer(String username, String encodePassword, String avatar, String fullname, String email, Set<TrainerRole> roles) {
        this.username = username;
        this.password = encodePassword;
        this.avatar = avatar;
        this.fullName = fullname;
        this.email = email;
        this.roles = roles;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Trainer trainer = (Trainer) o;
        return id != null && Objects.equals(id, trainer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
