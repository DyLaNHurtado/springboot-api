package es.dylanhurtado.springbootapirestjava.repositories;


import es.dylanhurtado.springbootapirestjava.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrainerRepository extends JpaRepository<Trainer, UUID> {

    Optional<Trainer> findByUsername(String username);
}
