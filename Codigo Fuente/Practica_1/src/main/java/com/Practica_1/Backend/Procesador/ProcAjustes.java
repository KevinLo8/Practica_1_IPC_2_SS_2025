package com.Practica_1.Backend.Procesador;

import java.io.*;

import com.Practica_1.Backend.Analizador.Analizador;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Ajustes;

public class ProcAjustes {

    private Frame_principal frame;
    private IF_Ajustes if_Aju;

    public ProcAjustes(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardaAjustes(IF_Ajustes if_Aju, String pathEntrada, String stringVelocidad, String pathSalida)
            throws ErrProcException {

        this.if_Aju = if_Aju;

        boolean entrada = false, salida = false;

        if (!pathEntrada.isEmpty()) {

            File file = new File(pathEntrada);
            if (file.exists()) {
                String extencion = getFileExtension(pathEntrada);
                if (extencion.equals(".txt")) {
                    try {
                        int velocidad = Integer.parseInt(stringVelocidad);
                        if (velocidad > 0) {
                            entrada = true;
                        } else {
                            throw new ErrProcException("Ingrese un número de velocidad mayor a 0");
                        }
                    } catch (NumberFormatException e) {
                        throw new ErrProcException("Ingrese un número de velocidad válido");
                    }
                } else {
                    throw new ErrProcException("Archivo seleccionado no valido. Seleccione un archivo de texto(.txt)");
                }
            } else {
                throw new ErrProcException("Archivo seleccionada no existe");
            }

        }

        if (!pathSalida.isEmpty()) {
            File file = new File(pathSalida);
            if (file.exists()) {
                if (file.isDirectory()) {
                    frame.setPathSalida(pathSalida);
                    salida = true;
                } else {
                    throw new ErrProcException("Direccion seleccionada no valida");
                }
            } else {
                throw new ErrProcException("Direccion seleccionada no existe");
            }
        }

        if (salida) {
            if (entrada) {
                String texto = extraerTexto(pathEntrada).replaceAll("(\\r\\n|\\n|\\r)", "");

                Analizador analizador = new Analizador(this, frame, texto, Integer.parseInt(stringVelocidad));

                Thread analizadorHilo = new Thread(analizador);
                analizadorHilo.start();

            } else {
                if_Aju.invisible("Se ha guardado la direccion de salida exitosamente.");
            }
        } else {
            if (entrada) {
                throw new ErrProcException("Selecciona alguna carpeta de salida de archivos");
            } else {
                throw new ErrProcException("Llena todos los espacio o selecciona alguna carpeta de salida de archivos");
            }
        }

    }

    public void finalizacion(String mensaje) {
        if_Aju.invisible(mensaje);
    }

    private String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    private String extraerTexto(String path) throws ErrProcException {
        File archivo = new File(path);
        String texto = "";

        if (!archivo.exists()) {
            throw new ErrProcException("Archivo Inexistente");
        }

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), "UTF-8"))) {
            String linea = in.readLine();
            while (linea != null) {
                texto = texto + linea + "\n";
                linea = in.readLine();
            }
        } catch (IOException e) {
            throw new ErrProcException("Error al leer el archivo de entrada");
        }

        return texto;
    }
}
