package es.dylanhurtado.springbootapirestjava.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {
    private UUID id;
    private String name;
    private Double shinyRate;
    private Integer level;
    private String image;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @JsonBackReference
    private TrainerDTO trainer;
}
