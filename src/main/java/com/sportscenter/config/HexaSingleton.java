package com.sportscenter.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum HexaSingleton {
    INSTANCIA;

    private final Properties propiedades = new Properties();

    HexaSingleton() {
        cargarConfiguraciones("config.properties");
    }

    // private void cargarConfiguraciones(String rutaArchivo) {
    // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    // try (FileInputStream archivo = new
    // FileInputStream(classLoader.getResource(rutaArchivo).getFile())) {
    // propiedades.load(archivo);
    // } catch (IOException e) {
    // System.err.println("X Error cargando configuración: " + e.getMessage());
    // }
    // }
    private void cargarConfiguraciones(String rutaArchivo) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            if (inputStream == null) {
                System.err.println("X Archivo de configuración no encontrado: " + rutaArchivo);
                return;
            }
            propiedades.load(inputStream);
        } catch (IOException e) {
            System.err.println("X Error cargando configuración: " + e.getMessage());
        }
    }

    public String get(String clave) {
        return propiedades.getProperty(clave, "No encontrado");
    }
}