package es.dylanhurtado.springbootapirestjava.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import es.dylanhurtado.springbootapirestjava.model.TrainerRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private UUID id = UUID.randomUUID();
    private String username;
    private String password;
    private String avatar;
    private String fullName;
    private String email;
    private Set<TrainerRole> roles;
    @JsonManagedReference
    private List<PokemonDTO> teamPokemon;
    private LocalDateTime createdAt;

}
