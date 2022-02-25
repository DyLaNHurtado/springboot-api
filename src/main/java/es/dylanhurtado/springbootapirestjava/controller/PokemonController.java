package es.dylanhurtado.springbootapirestjava.controller;

import es.dylanhurtado.springbootapirestjava.conf.APIConfig;
import es.dylanhurtado.springbootapirestjava.dto.PokemonDTO;
import es.dylanhurtado.springbootapirestjava.mappers.PokemonMapper;
import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import es.dylanhurtado.springbootapirestjava.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(APIConfig.API_PATH + "/pokemon")
public class PokemonController {
    private final PokemonRepository repository;
    private final PokemonMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<PokemonDTO>> getAll() {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(mapper.toDTO(repository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(mapper.toDTO(repository.findById(id).orElseThrow(NullPointerException::new)));
    }

    @PostMapping("/")
    public ResponseEntity<PokemonDTO> postClient(@RequestBody PokemonDTO pokemonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTO(repository.save(mapper.toModel(pokemonDTO))));
    }

    @PutMapping("/")
    public ResponseEntity<PokemonDTO> putClient(@RequestBody PokemonDTO pokemonDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDTO(repository.save(mapper.toModel(pokemonDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PokemonDTO> delete(@PathVariable UUID id) {
        Pokemon pokemon = repository.findById(id).orElse(null);
        if (pokemon != null) {
            repository.delete(pokemon);
            return ResponseEntity.ok(mapper.toDTO(pokemon));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PokemonDTO());
    }
}
