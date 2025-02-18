/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * La clase MenuCard representa la carta de productos, que contiene una lista de secciones
 * (MenuCardSection). Cada sección puede tener diferentes productos o menús.
 * Esta clase también se encarga de deserializar la información desde un archivo XML.
 * 
 * @author jorge_7wuetpy
 */
public class MenuCard {
    
    // Lista que contiene las diferentes secciones de la carta de productos
    private final List<MenuCardSection> sectionList;
   
    /**
     * Constructor de la clase MenuCard. Recibe una lista de secciones, que representan
     * las diferentes categorías o grupos de productos en el menú.
     * 
     * @param sectionList Lista de secciones (MenuCardSection) de la carta.
     */
    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
    
    /**
     * Método estático que carga la carta de productos desde un archivo XML mediante deserialización.
     * Utiliza el archivo "Catalog.xml" para cargar la información.
     * 
     * @return La instancia de MenuCard con la información deserializada.
     * @throws FileNotFoundException Si el archivo "Catalog.xml" no se encuentra en el sistema.
     */
    public static MenuCard loadFromDisk() throws FileNotFoundException {
        try {
            // Intentamos abrir y leer el archivo XML con los productos
            FileInputStream inputStream = new FileInputStream("Catalog.xml");
            XMLDecoder decoder = new XMLDecoder(inputStream);
            // Deserializamos el contenido del archivo XML en un objeto MenuCard
            MenuCard carta = (MenuCard) decoder.readObject();
            return carta;
        } catch (FileNotFoundException e) {
            // Si el archivo no se encuentra, se maneja la excepción y se imprime un mensaje de error
            System.out.println("Error: El archivo Catalog.xml no se encuentra.");
            throw e;  // Lanzamos de nuevo la excepción para que el llamador lo maneje
        } catch (Exception e) {
            // En caso de cualquier otro error durante la deserialización
            System.out.println("Error al deserializar el archivo Catalog.xml: " + e.getMessage());
            return null;  // Si ocurre un error, devolvemos null
        }
    }
    
    /**
     * Función que devuelve la sección del menú correspondiente al número dado.
     * 
     * @param number El índice de la sección en la lista.
     * @return La sección correspondiente a ese número.
     */
    public MenuCardSection getSection(int number) {
        return sectionList.get(number);  // Retorna la sección correspondiente al índice
    }
    
    /**
     * Función que devuelve el número total de secciones en la carta.
     * 
     * @return El número total de secciones en la lista.
     */
    public int getNumberSections() {
        return sectionList.size();  // Devuelve el tamaño de la lista de secciones
    }
}
