package maru.challenge3.liter.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public record AutorDTO(
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") int anoNacimiento,
    @JsonAlias("death_year") int anoFallecimiento
) {

}
