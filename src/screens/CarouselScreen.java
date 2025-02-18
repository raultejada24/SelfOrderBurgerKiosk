/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

/**
 * Clase abstracta que modela la parte común de los diferentes carruseles en el kiosco.
 * Los carruseles pueden ser utilizados para mostrar productos, categorías u otros tipos
 * de elementos que el usuario puede seleccionar en la interfaz del kiosco.
 * 
 * Permite a las clases MenuScreen y ProductScreen extender del KioskScreen.
 * 
 * @author jorge_7wuetpy
 */
public abstract class CarouselScreen extends KioskScreen {
    
    /**
     * Constructor de la clase CarouselScreen.
     * Este constructor se encarga de inicializar cualquier propiedad común
     * a los carruseles, si es necesario.
     */
    public CarouselScreen() {
        super();  // Llama al constructor de la clase base KioskScreen
        // Inicialización de propiedades comunes a los carruseles (si es necesario)
    }
    
}
