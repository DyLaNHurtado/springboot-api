package es.dylanhurtado.springbootapirestjava.controller;

import es.dylanhurtado.springbootapirestjava.conf.APIConfig;
import es.dylanhurtado.springbootapirestjava.dto.ResultTrainerDTO;
import es.dylanhurtado.springbootapirestjava.dto.TrainerDTO;
import es.dylanhurtado.springbootapirestjava.mappers.TrainerMapper;
import es.dylanhurtado.springbootapirestjava.model.Trainer;
import es.dylanhurtado.springbootapirestjava.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(APIConfig.API_PATH + "/trainer")
public class TrainerController {

    private final TrainerRepository repository;
    private final TrainerMapper mapper;

    @GetMapping("/")
    public ResponseEntity<ResultTrainerDTO> getAll() {
        List<Trainer> trainers = repository.findAll();
        ResultTrainerDTO result = new ResultTrainerDTO(mapper.toDTO(trainers));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultTrainerDTO> findById(@PathVariable UUID id) {
        Trainer trainer = repository.findById(id).orElse(null);
        ResultTrainerDTO result;
        if (trainer != null) {
            result = new ResultTrainerDTO(mapper.toDTO(List.of(trainer)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result = new ResultTrainerDTO(mapper.toDTO(new ArrayList<>()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PostMapping("/")
    public ResponseEntity<TrainerDTO> postClient(@RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTO(repository.save(mapper.toModel(trainerDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultTrainerDTO> putClient(@PathVariable UUID id, @RequestBody TrainerDTO trainerDTO) {
        Optional<Trainer> trainer = repository.findById(id);
        trainerDTO.setId(id);
        if (trainer.isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResultTrainerDTO(List.of(mapper.toDTO(repository.save(mapper.toModel(trainerDTO))))));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultTrainerDTO(new ArrayList<>()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultTrainerDTO> delete(@PathVariable UUID id) {
        Optional<Trainer> trainer = repository.findById(id);
        if (trainer.isPresent()) {
            repository.delete(trainer.get());
            return ResponseEntity.ok(new ResultTrainerDTO(List.of(mapper.toDTO(trainer.get()))));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultTrainerDTO(new ArrayList<>()));
    }
}
