package es.dylanhurtado.springbootapirestjava.controller;

import es.dylanhurtado.springbootapirestjava.conf.APIConfig;
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
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(APIConfig.API_PATH+"/trainer")
public class TrainerController {

    private final TrainerRepository repository;
    private final TrainerMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<TrainerDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDTO(repository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> findById(@PathVariable UUID id) {
        if(repository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDTO(repository.findById(id).get()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TrainerDTO());
    }

    @PostMapping("/")
    public ResponseEntity<TrainerDTO> postClient(@RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTO(repository.save(mapper.toModel(trainerDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> putClient(@PathVariable UUID id,@RequestBody TrainerDTO trainerDTO) {
        Optional<Trainer> trainer =repository.findById(id);
        trainerDTO.setId(id);
        if(trainer.isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDTO(repository.save(mapper.toModel(trainerDTO))));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TrainerDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainerDTO> delete(@PathVariable UUID id) {
        Optional<Trainer> trainer = repository.findById(id);
        if (trainer.isPresent()) {
            repository.delete(trainer.get());
            return ResponseEntity.ok(mapper.toDTO(trainer.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TrainerDTO());
    }
}
