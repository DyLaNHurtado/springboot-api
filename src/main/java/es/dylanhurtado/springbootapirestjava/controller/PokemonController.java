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
import java.util.Optional;
import java.util.UUID;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping(APIConfig.API_PATH + "/pokemon")
public class PokemonController {
    private final PokemonRepository repository;
    private final PokemonMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<PokemonDTO>> getAll() {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mapper.toDTO(repository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> findById(@PathVariable UUID id) {
        if(repository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDTO(repository.findById(id).get()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PokemonDTO());
    }

    @PostMapping("/")
    public ResponseEntity<PokemonDTO> postClient(@RequestBody PokemonDTO pokemonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTO(repository.save(mapper.toModel(pokemonDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDTO> putClient(@PathVariable UUID id ,@RequestBody PokemonDTO pokemonDTO) {
        Optional<Pokemon> pokemon =repository.findById(id);
        pokemonDTO.setId(id);
        if(pokemon.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mapper.toDTO(repository.saveAndFlush(mapper.toModel(pokemonDTO))));}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PokemonDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PokemonDTO> delete(@PathVariable UUID id) {
        Optional<Pokemon> pokemon = repository.findById(id);
        if (pokemon.isPresent()) {
            repository.delete(pokemon.get());
            return ResponseEntity.ok(mapper.toDTO(pokemon.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PokemonDTO());
    }
}
