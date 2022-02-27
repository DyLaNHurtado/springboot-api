package es.dylanhurtado.springbootapirestjava.dto;

import es.dylanhurtado.springbootapirestjava.conf.APIConfig;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ResultTrainerDTO {

    LocalDateTime consulta = LocalDateTime.now();
    String project = "SpringBootAPIRest";
    String version = APIConfig.API_VERSION;
    List<TrainerDTO> data;

    public ResultTrainerDTO(List<TrainerDTO> data) {
        this.data = data;
    }
}
