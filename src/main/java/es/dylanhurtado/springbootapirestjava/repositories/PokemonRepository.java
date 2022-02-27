package es.dylanhurtado.springbootapirestjava.repositories;

import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
    //Paginar
    //http://localhost:8080/rest/pokemons?page=0&size=1

    //http://localhost:8080/rest/pokemons/search/nombreComienzaPor?name=c -> Charmander
    Page<Pokemon> findByNameStartsWithIgnoreCase(@Param("name") String nombre, Pageable pageable);

    //http://localhost:8080/rest/pokemons/search/nombreContiene?name=ander -> -> Charmander
    Page<Pokemon> findByNameContainsIgnoreCase(@Param("name") String nombre, Pageable pageable);

    Page<Pokemon> findByShinyRateGreaterThanEqualOrderByNameAsc(double shinyRate, Pageable pageable);

    //http://localhost:8080/rest/pokemons/search/findByNameContainsIgnoreCaseAndShinyRateGreaterThanEqualOrderByNameAsc?nombre=saur&shinyRate=0 -> Bulbasaur
    Page<Pokemon> findByNameContainsIgnoreCaseAndShinyRateGreaterThanEqualOrderByNameAsc(String nombre, double shinyRate, Pageable pageable);

}
