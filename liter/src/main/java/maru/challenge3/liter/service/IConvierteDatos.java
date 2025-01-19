package maru.challenge3.liter.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
