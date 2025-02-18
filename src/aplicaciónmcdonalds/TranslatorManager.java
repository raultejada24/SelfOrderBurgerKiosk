/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicaciónmcdonalds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase TranslatorManager gestiona los objetos Translator, que son responsables de traducir textos 
 * a diferentes idiomas. Contiene un mapa que asocia el nombre del idioma con su correspondiente 
 * objeto Translator.
 * 
 * Esta clase permite seleccionar el idioma actual y obtener traducciones en ese idioma.
 * 
 * @author jorge_7wuetpy
 */
public class TranslatorManager {
    
    private String currentLanguage;  // Idioma actualmente seleccionado
    private final Map <String,Translator> languages; // Mapa que asocia el nombre del idioma con su correspondiente Translator
   
    /**
     * Constructor del TranslatorManager.
     * Inicializa el idioma por defecto como "español" y configura los idiomas disponibles en el mapa.
     * 
     * @throws FileNotFoundException Si no se encuentra el archivo de traducción.
     * @throws IOException Si ocurre un error al leer los archivos de traducción.
     */
    public TranslatorManager() throws FileNotFoundException, IOException {
        currentLanguage = "español"; // Idioma por defecto (español)
        languages = new HashMap <> ();  // Mapa para almacenar los objetos Translator por idioma
        initializeTranslators();  // Inicializa los objetos Translator para los diferentes idiomas
    }

    /**
     * Inicializa los objetos Translator para los idiomas disponibles 
     * y los añade al mapa de idiomas.
     * 
     * @throws FileNotFoundException Si algún archivo de traducción no se encuentra.
     * @throws IOException Si ocurre un error al leer los archivos de traducción.
     */
    private void initializeTranslators() throws FileNotFoundException, IOException {
        // Crea un Translator para cada idioma y lo añade al mapa
        languages.put("español", new Translator("español"));
        languages.put("ingles", new Translator("ingles"));
        languages.put("frances", new Translator("frances"));
        languages.put("aleman", new Translator("aleman"));
    }

    /**
     * Cambia el idioma actual a uno de los idiomas disponibles.
     * 
     * @param languageSelected El idioma a seleccionar (por ejemplo, "ingles", "frances", "aleman").
     */
    public void selectLanguage(String languageSelected)  {
        currentLanguage = languageSelected; // Actualiza el idioma seleccionado
    }
    
    /**
     * Traduce un texto al idioma actualmente seleccionado utilizando el objeto Translator correspondiente.
     * 
     * @param text El texto que se desea traducir.
     * @return La traducción del texto al idioma seleccionado.
     */
    public String translate(String text) {
        // Obtiene el Translator correspondiente al idioma seleccionado
        Translator translator = languages.get(currentLanguage);
        // Devuelve la traducción del texto utilizando el Translator seleccionado
        return translator.translate(text);
    }
}
