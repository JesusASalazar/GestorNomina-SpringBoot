package Laboral.Util;

import java.io.*;
import java.util.StringTokenizer;

public class FileHandler implements AutoCloseable {
    private final BufferedReader br;
    private final DataOutputStream dos;
    private StringTokenizer st;

    // 🔹 Constructor: abre archivos de lectura y/o escritura
    public FileHandler(String rutaLectura, String rutaEscritura) throws IOException {
        if (rutaLectura == null || rutaLectura.isBlank()) {
            throw new IllegalStateException("Debe proporcionar una ruta de lectura válida.");
        }
        if (rutaEscritura == null || rutaEscritura.isBlank()) {
            throw new IllegalStateException("Debe proporcionar una ruta de escritura válida.");
        }

        this.br = new BufferedReader(new FileReader(rutaLectura));
        this.dos = new DataOutputStream(new FileOutputStream(rutaEscritura));
    }

    // 🔹 Leer siguiente token del archivo de entrada
    public String next() {
        if (br == null) {
            throw new IllegalStateException("Archivo de lectura no inicializado.");
        }
        try {
            while (st == null || !st.hasMoreTokens()) {
                String linea = br.readLine();
                if (linea == null) {
                    return null; // fin del archivo
                }
                st = new StringTokenizer(linea);
            }
            return st.nextToken();
        } catch (IOException e) {
            throw new UncheckedIOException("Error leyendo fichero", e);
        }
    }

    // 🔹 Escribir un int en el archivo de salida
    public void writeInt(int value) {
        if (dos == null) {
            throw new IllegalStateException("Archivo de escritura no inicializado.");
        }
        try {
            dos.writeInt(value);
            dos.flush();
        } catch (IOException e) {
            throw new UncheckedIOException("Error escribiendo int", e);
        }
    }

    // 🔹 Escribir un String en el archivo de salida
    public void writeUTF(String value) {
        if (dos == null) {
            throw new IllegalStateException("Archivo de escritura no inicializado.");
        }
        try {
            dos.writeUTF(value);
            dos.flush();
        } catch (IOException e) {
            throw new UncheckedIOException("Error escribiendo String", e);
        }
    }

    // 🔹 Cerrar recursos automáticamente con try-with-resources
    @Override
    public void close() throws IOException {
        if (br != null) br.close();
        if (dos != null) dos.close();
    }
}
