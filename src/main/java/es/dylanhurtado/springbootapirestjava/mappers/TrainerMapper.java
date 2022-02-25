package es.dylanhurtado.springbootapirestjava.mappers;

import es.dylanhurtado.springbootapirestjava.dto.TrainerDTO;
import es.dylanhurtado.springbootapirestjava.model.Trainer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrainerMapper {
    private final ModelMapper modelMapper;

    public TrainerDTO toDTO(Trainer item) {
        return modelMapper.map(item, TrainerDTO.class);
    }

    public List<TrainerDTO> toDTO(List<Trainer> items) {
        return items.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Trainer toModel(TrainerDTO item) {
        return modelMapper.map(item, Trainer.class);
    }

    public List<Trainer> toModel(List<TrainerDTO> items) {
        return items.stream().map(this::toModel).collect(Collectors.toList());
    }
}