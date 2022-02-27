package es.dylanhurtado.springbootapirestjava.dto;

import es.dylanhurtado.springbootapirestjava.conf.APIConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ListPokemonDTOPage {
    private final String consulta = LocalDateTime.now().toString();
    private final String project = "SpringBootAPIRest";
    private final String version = APIConfig.API_VERSION;
    private List<PokemonDTO> data;

    private int currentPage;

    private long totalElements;

    private int totalPages;
    private String sort;
}
