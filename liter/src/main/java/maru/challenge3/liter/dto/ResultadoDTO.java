package maru.challenge3.liter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public record ResultadoDTO(
    @JsonAlias("count") int librosEncontrados,
    @JsonAlias("results") List<LibroDTO> datosLibros
) {

}
