package es.dylanhurtado.springbootapirestjava;

import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import es.dylanhurtado.springbootapirestjava.model.Trainer;
import es.dylanhurtado.springbootapirestjava.repositories.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class SpringBootApiRestJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiRestJavaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PokemonRepository pokemonRepository){
        return(args -> {
            pokemonRepository.deleteAll();
            pokemonRepository.save(
                    new Pokemon("Charmander", 0.12, 100, "https://images.gameinfo.io/pokemon/256/p4f96.webp", new Trainer())
            );

            pokemonRepository.save(
                    new Pokemon("Bulbasaur", 0.12, 100, "https://images.gameinfo.io/pokemon/256/p1f87.webp", new Trainer())
            );
            pokemonRepository.findAll().forEach(System.out::println);
        });



    }
}
