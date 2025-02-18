/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicaciónmcdonalds;

import java.io.FileNotFoundException;
import java.io.IOException;
import products.MenuCard;
import products.Order;

/**
 * La clase Context es responsable de agrupar toda la información y los objetos
 * necesarios de diversas clases, proporcionando acceso centralizado a ellos a lo largo del programa.
 * Esta clase actúa como un contenedor para evitar la necesidad de múltiples importaciones 
 * en otras clases del programa.
 * @author jorge_7wuetpy
 */
public class Context {

    private final SimpleKiosk kiosk; // Contiene un objeto de la clase SimpleKiosk, que maneja las operaciones del kiosco.
    private final TranslatorManager translator; // Contiene un objeto de la clase TranslatorManager, que maneja la traducción de los textos.
    private Order order; // Contiene un objeto de la clase Order, que representa el pedido actual.
    private final MenuCard menuCard; // Contiene un objeto de la clase MenuCard, que representa el menú de productos disponible.

    /**
     * Constructor de la clase Context.
     * Inicializa los objetos necesarios para el funcionamiento del kiosco.
     * @throws FileNotFoundException si no se encuentra algún archivo necesario durante la carga.
     * @throws IOException si ocurre algún error al cargar archivos o realizar operaciones de entrada/salida.
     */
    public Context() throws FileNotFoundException, IOException {
        this.translator = new TranslatorManager(); // Inicializa el objeto TranslatorManager.
        this.kiosk = new SimpleKiosk(this.translator); // Inicializa el kiosco, pasando el traductor como dependencia.
        this.order = new Order(); // Inicializa un nuevo pedido vacío.
        this.menuCard = MenuCard.loadFromDisk(); // Carga el menú desde el disco.
    }
    
    /**
     * Devuelve el objeto TranslatorManager.
     * Este objeto es responsable de gestionar las traducciones dentro de la aplicación.
     * @return el objeto TranslatorManager.
     */
    public TranslatorManager getTranslator() {
        return this.translator;
    }
    
    /**
     * Cambia el idioma del traductor según la opción proporcionada.
     * @param option la opción de idioma seleccionada por el usuario (por ejemplo, "español", "inglés").
     */
    public void setLanguageTranslator(String option) {
        translator.selectLanguage(option); // Establece el idioma según la opción seleccionada.
    }
    
    /**
     * Devuelve el objeto SimpleKiosk, que gestiona la interacción con el usuario.
     * @return el objeto SimpleKiosk.
     */
    public SimpleKiosk getKiosk () {
        return this.kiosk;
    }
    
    /**
     * Devuelve el objeto Order, que representa el pedido actual del usuario.
     * @return el objeto Order.
     */
    public Order getOrder () {
        return this.order;
    }
    
    /**
     * Establece el pedido actual, permitiendo modificarlo desde otras clases.
     * @param order el nuevo objeto Order que se desea asignar.
     */
    public void setOrder(Order order) {
        this.order = order; // Asigna el nuevo pedido al objeto Context.
    }
    
    /**
     * Reinicia el pedido, creando un nuevo objeto Order vacío.
     * Este método se utiliza cuando se reinicia el pedido al salir de la pantalla de bienvenida.
     * @param order el objeto Order que se desea reiniciar.
     */
    public void resetOrder(Order order) {
        this.order = new Order(); // Reinicia el pedido creando una nueva instancia de Order.
    }

    /**
     * Devuelve el objeto MenuCard, que contiene la lista de productos del menú.
     * @return el objeto MenuCard con los productos disponibles.
     */
    public MenuCard getMenuCard() {
        return this.menuCard;
    }
}
