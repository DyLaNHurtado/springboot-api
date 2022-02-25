package es.dylanhurtado.springbootapirestjava.controller;

import es.dylanhurtado.springbootapirestjava.dto.TrainerDTO;
import es.dylanhurtado.springbootapirestjava.mappers.TrainerMapper;
import es.dylanhurtado.springbootapirestjava.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    @GetMapping("/all")
    public List<TrainerDTO> getAll() {
        return trainerMapper.toDTO(trainerRepository.findAll());
    }
}
