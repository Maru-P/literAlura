package maru.challenge3.liter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import maru.challenge3.liter.dto.AutorDTO;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id")
    private Long id;
    @Column(unique=true, name = "nombre")
    private String nombre;
    @Column(name = "año_nacimiento")
    private int anoNacimiento;
    @Column(name = "año_fallecimiento")
    private int anoFallecimiento;

    @ManyToOne
    @JoinColumn(name = "datos_id", nullable = false)
    private Datos datos;

    public Autor() {
    }

    public Autor(AutorDTO autor, Datos datos){
        this.nombre = autor.nombre();
        this.anoNacimiento = autor.anoNacimiento();
        this.anoFallecimiento = autor.anoFallecimiento();
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(int anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public String toStringAutor() {
        return "\n- Autor: [Nombre: " + nombre + ", Año de nacimiento: " + anoNacimiento + ", Año de fallecimiento: " + anoFallecimiento + "]";
    }

}
