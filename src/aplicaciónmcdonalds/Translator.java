/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicaciónmcdonalds;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase Translator gestiona la traducción de los textos al idioma seleccionado.
 * Utiliza un archivo de texto con las traducciones y las carga en un mapa.
 * Cada objeto Translator corresponde a un idioma específico.
 * 
 * El mapa contiene las traducciones, donde la clave es una frase en español
 * y el valor es su traducción al idioma seleccionado.
 * 
 * @author jorge_7wuetpy
 */
public class Translator {
    
    private final Map <String,String> translations; // Mapa que almacena las traducciones: clave (texto en español) y valor (texto traducido)
    private final String language; // Idioma al que se traducirán los textos

    /**
     * Constructor de la clase Translator.
     * Inicializa el idioma y carga las traducciones desde un archivo en disco.
     * 
     * @param language El idioma al que se realizará la traducción (por ejemplo, "en" para inglés).
     * @throws FileNotFoundException Si el archivo de traducciones no se encuentra.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public Translator(String language) throws FileNotFoundException, IOException {
        this.language = language;
        translations = new HashMap <> (); // Inicializa el mapa de traducciones
        loadTranslationsFromDisk(); // Carga las traducciones desde el archivo correspondiente
    }
    

    /**
     * Carga las traducciones desde un archivo en disco.
     * El archivo tiene el nombre del idioma y contiene pares de líneas:
     * la primera línea es el texto original en español, y la segunda línea es su traducción.
     * 
     * @throws FileNotFoundException Si el archivo de traducciones no se encuentra.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void loadTranslationsFromDisk() throws FileNotFoundException, IOException {
        String fileName = language + ".txt"; // El archivo tiene el nombre del idioma (por ejemplo, "en.txt" para inglés)
        FileReader reader = new FileReader (fileName); // Crea un lector de archivos
        BufferedReader buffer = new BufferedReader(reader); // Crea un buffer para leer línea por línea
        
        String originalString = buffer.readLine(); // Lee la primera línea (texto original)
        String translatedString = buffer.readLine(); // Lee la segunda línea (traducción)
        
        // Mientras haya líneas para leer en el archivo
        while (originalString != null) {  
            translations.put(originalString, translatedString); // Añade el par de traducción al mapa
            originalString = buffer.readLine(); // Lee la siguiente línea (nuevo texto original)
            translatedString = buffer.readLine(); // Lee la siguiente línea (nueva traducción)
        } 
    }
        
    /**
     * Traduce un texto al idioma seleccionado utilizando el mapa de traducciones.
     * Si el texto no tiene traducción, devuelve el mismo texto original.
     * 
     * @param text El texto que se desea traducir.
     * @return La traducción del texto, o el mismo texto si no tiene traducción.
     */
    public String translate(String text) {
        String translation = translations.get(text); // Busca la traducción en el mapa
        if (translation != null)
            return translation; // Si se encuentra la traducción, la devuelve
        else
            return text; // Si no se encuentra la traducción, devuelve el texto original
    }
}

