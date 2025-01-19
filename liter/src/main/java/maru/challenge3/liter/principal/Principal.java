package maru.challenge3.liter.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import maru.challenge3.liter.dto.LibroDTO;
import maru.challenge3.liter.dto.ResultadoDTO;
import maru.challenge3.liter.model.Autor;
import maru.challenge3.liter.model.Datos;
import maru.challenge3.liter.model.Libro;
import maru.challenge3.liter.repository.LibroRepository;
import maru.challenge3.liter.service.ConsumoAPI;
import maru.challenge3.liter.service.ConvierteDatos;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private final LibroRepository libroRepository;
    private List<Datos> datosGuardados;
    private List<Libro> librosGuardados;
    private Map<Autor, Libro> autorLibroMap = new HashMap<>();
    private List<Libro> libros = new ArrayList<>();

    public Principal(LibroRepository lRepository) {
        this.libroRepository = lRepository;

    }

    public void muestraElMenu() throws JsonProcessingException {

        var opcion = -1;

        while (opcion != 0) {
            var menu = """
                    \n
                    Bienvenido/a a LiterAlura, tu biblioteca virtual

                    A continuación se muestran las opciones disponibles:

                    1 - Buscar libro por titulo
                    2 - Muestra libros buscados
                    3 - Muestra autores registrados
                    4 - Muestra autores vivos en un determinado año
                    5 - Muestra libros por idiomas
                    0 - Salir\n
                    """;
            System.out.println(menu + "Ingresa una de las opciones anteriores: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> muestraLibrosBuscados();
                case 3 -> muestraAutoresRegistrados();
                case 4 -> muestraAutoresRegistradosSegunAño();
                case 5 -> muestraLibrosPorIdioma();

                case 0 -> {
                    System.out.println("Cerrando la aplicación...");
                    teclado.close();
                }
                default -> System.out.println("Opción inválida");
            }

        }         
    }

    private String getDatosLibro() {
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine();
        return consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
    }

    private ResultadoDTO getDatosLibro(String json){
        ResultadoDTO datos = conversor.obtenerDatos(json, ResultadoDTO.class);
        return datos;
    }

    private void buscarLibro() {
        var json = getDatosLibro();
        var datos = getDatosLibro(json);
        if (datos.librosEncontrados() != 0) {
            Optional<LibroDTO> libroOptional = datos.datosLibros()
                .stream()
                .findFirst();
                if (libroOptional.isPresent()) {
                    LibroDTO libroDTO = libroOptional.get();
    
                    // Verificar si el libro ya existe en el repositorio
                    Optional<Datos> datosExistente = libroRepository.findByTituloContainingIgnoreCase(libroDTO.titulo());
                    if (datosExistente.isPresent()) {
                        System.out.println("El libro '" + libroDTO.titulo() + "' ya está en el repositorio.");
                        return;
                    }
    
                    // Crear la entidad Libro
                    Libro libro = new Libro();
                    libroRepository.save(libro); // Guardar primero la entidad Libro
    
                    // Crear los Datos asociados al Libro
                    Datos datosLibro = new Datos(libroDTO, libro);
                    List<Datos> listaDatos = List.of(datosLibro);
                    libro.setDatos(listaDatos);
                    libros.add(libro); 
    
                    // Guardar el Libro junto con los Datos
                    libroRepository.save(libro);
    
                    datosLibro.getAutores().forEach(autor -> {
                        autorLibroMap.put(autor, libro);
                    });
    
                    System.out.println(libro.toString());
                } else {
                    System.out.println("No se encontraron libros");
                }
        }else{
            System.out.println("\nLibro no encontrado");
        }
    }

    private void muestraLibrosBuscados() {
        librosGuardados = libroRepository.findAll();
        if (!librosGuardados.isEmpty()) {
            librosGuardados.forEach(libro -> System.out.println(libro.toString()));
        } else {
            System.out.println("\nAún no se han guardado libros en el repositorio");
        }
    }

    private void muestraAutoresRegistrados() {
        librosGuardados = libroRepository.findAll();
        if (!librosGuardados.isEmpty()) {
            librosGuardados.forEach(libro -> {
                libro.getDatos().forEach(datos -> {
                    datos.getAutores().forEach(autor -> {
                        System.out.println(autor.toStringAutor() + " pertenece al libro: " + datos.getTitulo());
                    });
                });
            });
        } else {
            System.out.println("\nAún no se han guardado autores en el repositorio");
        }
    }

    private void muestraAutoresRegistradosSegunAño() {
        System.out.println("Ingrese el año deseado: ");
        int ano = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea

        List<Autor> autoresFiltrados = libroRepository.findByAnoNacimientoAndAnoFallecimiento(ano, ano);
        if (!autoresFiltrados.isEmpty()) {
            System.out.println("Autores vivos en el año " + ano + ":");
            autoresFiltrados.forEach(autor -> {
                System.out.println(autor.toStringAutor() + " pertenece al libro: " + autor.getDatos().getTitulo());
            });
        } else {
            System.out.println("No se encontraron autores vivos en el año " + ano);
        }
    }

    private void muestraLibrosPorIdioma() {
        System.out.println("Ingrese el idioma del libro: ");
        String idioma = teclado.nextLine();

        datosGuardados = libroRepository.findByIdiomaContainingIgnoreCase(idioma);
        if (!datosGuardados.isEmpty()) {
            datosGuardados.forEach(datos -> {
                System.out.println("Título: " + datos.getTitulo() + ", Idioma: " + datos.getIdioma());
            });
        } else {
            System.out.println("No se encontraron libros en el idioma ingresado");
        }
    }
}
