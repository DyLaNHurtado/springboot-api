package es.dylanhurtado.springbootapirestjava.controller;

import es.dylanhurtado.springbootapirestjava.config.APIConfig;
import es.dylanhurtado.springbootapirestjava.dto.PokemonDTO;
import es.dylanhurtado.springbootapirestjava.dto.ResultPokemonDTO;
import es.dylanhurtado.springbootapirestjava.dto.ResultPokemonPageDTO;
import es.dylanhurtado.springbootapirestjava.mappers.PokemonMapper;
import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import es.dylanhurtado.springbootapirestjava.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<ResultPokemonDTO> getAll() {
        List<Pokemon> pokemons = repository.findAll();
        ResultPokemonDTO result = new ResultPokemonDTO(mapper.toDTO(pokemons));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultPokemonDTO> findById(@PathVariable UUID id) {
        Pokemon pokemon = repository.findById(id).orElse(null);
        ResultPokemonDTO result;
        if (pokemon != null) {
            result = new ResultPokemonDTO(mapper.toDTO(List.of(pokemon)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result = new ResultPokemonDTO(mapper.toDTO(new ArrayList<>()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


    @PostMapping("/")
    public ResponseEntity<PokemonDTO> postClient(@RequestBody PokemonDTO pokemonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTO(repository.save(mapper.toModel(pokemonDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultPokemonDTO> putClient(@PathVariable UUID id, @RequestBody PokemonDTO pokemonDTO) {
        Optional<Pokemon> pokemon = repository.findById(id);
        pokemonDTO.setId(id);
        if (pokemon.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResultPokemonDTO(List.of(mapper.toDTO(repository.saveAndFlush(mapper.toModel(pokemonDTO))))));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultPokemonDTO(new ArrayList<>()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultPokemonDTO> delete(@PathVariable UUID id) {
        Optional<Pokemon> pokemon = repository.findById(id);
        if (pokemon.isPresent()) {
            repository.delete(pokemon.get());
            return ResponseEntity.ok(new ResultPokemonDTO(List.of(mapper.toDTO(pokemon.get()))));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultPokemonDTO(new ArrayList<>()));
    }

    //findAll paginado filtrado y ordenado
    @GetMapping("/all")
    public ResponseEntity<ResultPokemonPageDTO> getAllPages(
            // Podemos buscar por los campos que quieramos... nombre, precio... así construir consultas
            @RequestParam(required = false, name = "name") Optional<String> name,
            @RequestParam(required = false, name = "level") Optional<Double> level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size
    ) {
        // Consulto en base a las páginas
        Pageable paging = PageRequest.of(page, size);
        Page<Pokemon> pagedResult;
        if (name.isPresent() && level.isPresent()) {
            pagedResult = repository.findByNameContainsIgnoreCaseAndShinyRateGreaterThanEqualOrderByNameAsc(name.get(), level.get(), paging);
        } else if (name.isPresent()) {
            pagedResult = repository.findByNameContainsIgnoreCase(name.get(), paging);
        } else if (level.isPresent()) {
            pagedResult = repository.findByShinyRateGreaterThanEqualOrderByNameAsc(level.get(), paging);
        } else {
            pagedResult = repository.findAll(paging);
        }
        // De la página saco la lista de productos
        //List<Pokemon> pokemon = pagedResult.getContent();
        // Mapeo al DTO. Si quieres ver toda la info de las paginas pon pageResult.
        ResultPokemonPageDTO listProductoPageDTO = ResultPokemonPageDTO.builder()
                .data(mapper.toDTO(pagedResult.getContent()))
                .totalPages(pagedResult.getTotalPages())
                .totalElements(pagedResult.getTotalElements())
                .currentPage(pagedResult.getNumber())
                .build();
        return ResponseEntity.ok(listProductoPageDTO);
    }
}
