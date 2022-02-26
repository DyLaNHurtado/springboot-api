package es.dylanhurtado.springbootapirestjava;

import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import es.dylanhurtado.springbootapirestjava.model.Trainer;
import es.dylanhurtado.springbootapirestjava.model.TrainerRole;
import es.dylanhurtado.springbootapirestjava.repositories.PokemonRepository;
import es.dylanhurtado.springbootapirestjava.repositories.TrainerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SpringBootApiRestJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiRestJavaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PokemonRepository pokemonRepository, TrainerRepository trainerRepository){
        return(args -> {
            //StackOverFlowException: Recursion infinita -> https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
            Pokemon p1 = new Pokemon("Charmander", 0.12, 100, "https://images.gameinfo.io/pokemon/256/p4f96.webp");
            Pokemon p2 = new Pokemon("Bulbasaur", 0.12, 100, "https://images.gameinfo.io/pokemon/256/p1f87.webp");
            pokemonRepository.deleteAll();
            trainerRepository.deleteAll();
            Trainer t1= new Trainer("Rojo","1234","exampleImage","Manolo Garcia","manolitogarcia@email.com", Collections.singleton(TrainerRole.ADMIN), List.of(p1));
            Trainer t2= new Trainer("Gary","2345","exampleImage","sdf dsfdf","sadfsdd@email.com", Collections.singleton(TrainerRole.NOOB), List.of(p2));
            p1.setTrainer(t1);
            p2.setTrainer(t2);
            pokemonRepository.save(p1);
            pokemonRepository.save(p2);
            trainerRepository.save(t1);
            trainerRepository.save(t2);
            pokemonRepository.findAll().forEach(System.out::println);
        });



    }
}
