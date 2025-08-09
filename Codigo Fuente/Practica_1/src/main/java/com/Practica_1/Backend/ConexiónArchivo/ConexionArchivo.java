package com.Practica_1.Backend.ConexiónArchivo;

import java.io.*;

import com.Practica_1.Backend.Exception.ArchivoExistenteException;

public class ConexionArchivo {

    public static void guardarArchivo(String path, String data, String nombre) throws IOException, ArchivoExistenteException {

        String pathName = path + "/" + nombre;
        File file = new File(pathName);

        if (file.exists()) {
            throw new ArchivoExistenteException();
        }

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.append(data);
        writer.close();
        fileWriter.close();
    }

    public static String GenerarNombre(String path, String nombre) {

        File file;
        int numero = 0;

        do {
            numero++;
            String pathName = path + "/" + nombre + "_" + String.valueOf(numero) + ".html";
            file = new File(pathName);
        } while (file.exists());

        return nombre + "_" + String.valueOf(numero) + ".html";
    }
}
