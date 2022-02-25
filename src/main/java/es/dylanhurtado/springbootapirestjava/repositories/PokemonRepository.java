package es.dylanhurtado.springbootapirestjava.repositories;

import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
}
