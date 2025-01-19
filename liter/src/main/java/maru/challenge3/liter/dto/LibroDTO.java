package maru.challenge3.liter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public record LibroDTO(
    @JsonAlias("title") String titulo,
    @JsonAlias("languages") List<String> idioma,
    @JsonAlias("authors") List<AutorDTO> autores,
    @JsonAlias("download_count") int descargas
) {

}
