package es.dylanhurtado.springbootapirestjava.controller;

import es.dylanhurtado.springbootapirestjava.dto.PokemonDTO;
import es.dylanhurtado.springbootapirestjava.dto.TrainerDTO;
import es.dylanhurtado.springbootapirestjava.mappers.TrainerMapper;
import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import es.dylanhurtado.springbootapirestjava.model.Trainer;
import es.dylanhurtado.springbootapirestjava.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerRepository repository;
    private final TrainerMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<TrainerDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(mapper.toDTO(repository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(mapper.toDTO(repository.findById(id).orElseThrow(NullPointerException::new)));
    }

    @PostMapping("/")
    public ResponseEntity<TrainerDTO> postClient(@RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTO(repository.save(mapper.toModel(trainerDTO))));
    }

    @PutMapping("/")
    public ResponseEntity<TrainerDTO> putClient(@RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDTO(repository.save(mapper.toModel(trainerDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainerDTO> delete(@PathVariable UUID id) {
        Trainer trainer = repository.findById(id).orElse(null);
        if (trainer != null) {
            repository.delete(trainer);
            return ResponseEntity.ok(mapper.toDTO(trainer));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TrainerDTO());
    }
}
