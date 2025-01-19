package maru.challenge3.liter.model;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import maru.challenge3.liter.dto.LibroDTO;

@Entity
@Table(name = "datos")
public class Datos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "datos_id")
    private Long id;
    @Column(unique=true, name="titulo")
    private String titulo;

    @Column(name="lenguaje")
    private String idioma;

    private int descargas;
    @OneToMany(mappedBy = "datos",cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    private List<Autor> autores;
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    public Datos() {
    }

    public Datos(LibroDTO libroDto, Libro libro) {
        this.titulo = libroDto.titulo();
        this.idioma = libroDto.idioma().stream()
            .collect(Collectors.joining(", "));
        this.descargas = libroDto.descargas();
        this.autores = libroDto.autores().stream()
            .map(autorDTO -> new Autor(autorDTO, this))
            .collect(Collectors.toList());
        this.libro = libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String toStringDatos() {
        String autoresStr = autores.stream()
                                   .map(Autor::toStringAutor)
                                   .collect(Collectors.joining(", "));
        return "-------------------------\nLibro encontrado:\n- Titulo: " + titulo  
            + autoresStr 
            + "\n- Idioma: " + idioma 
            + "\n- Descargas: " + descargas 
            + "\n-------------------------";
    }
}
