package es.dylanhurtado.springbootapirestjava.dto;

import es.dylanhurtado.springbootapirestjava.conf.APIConfig;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ResultPokemonDTO {
    LocalDateTime consulta = LocalDateTime.now();
    String project = "SpringBootAPIRest";
    String version = APIConfig.API_VERSION;
    List<PokemonDTO> data;

    public ResultPokemonDTO(List<PokemonDTO> data) {
        this.data = data;
    }
}
