package es.dylanhurtado.springbootapirestjava.service;


import es.dylanhurtado.springbootapirestjava.dto.TrainerCreateDTO;
import es.dylanhurtado.springbootapirestjava.model.Trainer;
import es.dylanhurtado.springbootapirestjava.model.TrainerRole;
import es.dylanhurtado.springbootapirestjava.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
// OJO la inyeccion de dependencias es a modo de constructor al poner @RequiredArgsConstructor
public class UsuarioService {
    private final TrainerRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Nos permite buscar un trainer por su nombre de usuario
     */
    public Optional<Trainer> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<Trainer> findUserById(UUID userId) {
        return repository.findById(userId);
    }

    /**
     * Nos permite crear un nuevo trainer con rol NOOB
     */
    public Trainer nuevoTrainer(TrainerCreateDTO newTrainer) throws Exception {
        // System.out.println(passwordEncoder.encode(newTrainer.getPassword()));
        if (newTrainer.getPassword().contentEquals(newTrainer.getPassword2())) {
            Trainer trainer = new Trainer(
                    newTrainer.getUsername(),
                    passwordEncoder.encode(newTrainer.getPassword()),
                    newTrainer.getAvatar(),
                    newTrainer.getFullname(),
                    newTrainer.getEmail(),
                    Stream.of(TrainerRole.NOOB).collect(Collectors.toSet()));
            try {
                return repository.save(trainer);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de trainer ya existe");
            }
        } else {
            throw new Exception();
        }
    }
}