package es.dylanhurtado.springbootapirestjava.mappers;

import es.dylanhurtado.springbootapirestjava.dto.PokemonDTO;
import es.dylanhurtado.springbootapirestjava.model.Pokemon;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PokemonMapper {
    private final ModelMapper modelMapper;

    public PokemonDTO toDTO(Pokemon item){
        return modelMapper.map(item,PokemonDTO.class);
    }

    public List<PokemonDTO> toDTO(List<Pokemon> items){
        return items.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Pokemon toModel(PokemonDTO item){
        return modelMapper.map(item,Pokemon.class);
    }

    public List<Pokemon> toModel(List<PokemonDTO> items){
        return items.stream().map(this::toModel).collect(Collectors.toList());
    }
}
