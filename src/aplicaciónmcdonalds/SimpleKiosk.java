/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicaciónmcdonalds;

import java.util.List;
import sienens.BurgerSelfOrderKiosk;

/**
 * La clase SimpleKiosk amplía la funcionalidad de la clase BurgerSelfOrderKiosk,
 * proporcionando un conjunto simplificado de operaciones que gestionan tanto el kiosco de pedidos
 * como las traducciones de los textos mostrados en la interfaz.
 * 
 * Esta clase maneja la creación de un kiosco con traducción, permitiendo cambiar la interfaz según el idioma.
 * @author rault
 */
public class SimpleKiosk {
    
    private final BurgerSelfOrderKiosk kiosk; // Objeto de la clase BurgerSelfOrderKiosk que gestiona la interfaz del kiosco.
    private final TranslatorManager translator; // Objeto de la clase TranslatorManager que maneja la traducción de los textos.

    /**
     * Constructor de la clase SimpleKiosk.
     * Inicializa los objetos necesarios, creando el kiosco y asignando el traductor.
     * 
     * @param translator El objeto TranslatorManager para manejar las traducciones.
     */
    public SimpleKiosk(TranslatorManager translator) {
        this.kiosk = new BurgerSelfOrderKiosk(); // Crea una nueva instancia de BurgerSelfOrderKiosk.
        this.translator = translator; // Asigna el traductor recibido como parámetro.
    }
    
    /**
     * Función que limpia la pantalla, eliminando todas las opciones, título, descripción e imagen.
     * Esto restablece la pantalla del kiosco a un estado vacío.
     */
    public void clearScreen () {
        // Elimina las opciones del kiosco, dejándolas en nulo.
        kiosk.setOption('A', null);
        kiosk.setOption('B', null);
        kiosk.setOption('C', null);
        kiosk.setOption('D', null);
        kiosk.setOption('E', null);
        kiosk.setOption('F', null);
        kiosk.setOption('G', null);
        kiosk.setOption('H', null);
        kiosk.setTitle(""); // Limpia el título de la pantalla.
        kiosk.setDescription(""); // Limpia la descripción de la pantalla.
        kiosk.setImage(""); // Limpia la imagen mostrada en la pantalla.
    }
    
    /**
     * Función que establece un texto traducido para un botón en particular.
     * El texto del botón se traduce antes de ser asignado al kiosco.
     * 
     * @param X El identificador del botón (por ejemplo, 'A', 'B', etc.).
     * @param text El texto a traducir y asignar al botón.
     */
    public void setOption (char X, String text) {
        // Establece el texto traducido para un botón específico.
        kiosk.setOption(X, translator.translate(text));
    }
    
    /**
     * Función que establece el título de la pantalla, traducido.
     * 
     * @param title El título a traducir y mostrar en la pantalla.
     */
    public void setTitle (String title) {
        // Establece el título traducido en el kiosco.
        kiosk.setTitle(translator.translate(title)); 
    }

    /**
     * Función que establece la descripción de la pantalla, traducida.
     * 
     * @param description La descripción a traducir y mostrar en la pantalla.
     */
    public void setDescription (String description) {
        // Establece la descripción traducida en el kiosco.
        kiosk.setDescription(translator.translate(description));
    }
    
    /**
     * Activa el modo menú en la pantalla del kiosco, mostrando los botones a la izquierda.
     */
    public void setMenuMode (){
        // Configura el kiosco para mostrar el menú con los botones.
        kiosk.setMenuMode();
    }
    
    /**
     * Activa el modo mensaje en la pantalla del kiosco, utilizado cuando se muestra el resumen en la pantalla de compra.
     */
    public void setMessageMode (){
        // Configura el kiosco para mostrar un mensaje de resumen (modo mensaje).
        kiosk.setMessageMode();
    }
    
    /**
     * Función que espera a que un botón sea pulsado y devuelve el botón correspondiente.
     * Este método bloquea el programa hasta que se detecta la pulsación de un botón.
     * 
     * @param number El número de botones a verificar (usualmente 4, 6, etc.).
     * @return El carácter del botón pulsado (por ejemplo, 'A', 'B', etc.).
     */
    public char waitEvent(int number) {
        char option = 0;
        // Espera hasta que se pulse un botón, luego retorna el identificador del botón.
        while (option == 0) {
            option = kiosk.waitEvent(number);
        }
        return option;
    }

    /**
     * Función que imprime el ticket, utilizando una lista de cadenas de texto.
     * 
     * @param text La lista de textos que conforman el ticket a imprimir.
     */
    public void print (List<String> text) {
        // Llama a la función de impresión del kiosco para imprimir el ticket.
        kiosk.print(text);
    }

    /**
     * Función que retiene la tarjeta de crédito del usuario.
     * 
     * @param bool Indica si la tarjeta debe ser retenida (true) o liberada (false).
     */
    public void retainCreditCard (boolean bool){
        // Retiene o libera la tarjeta de crédito según el valor de 'bool'.
        kiosk.retainCreditCard(bool);
    }
    
    /**
     * Función que expulsa la tarjeta de crédito, devolviéndola al usuario.
     * 
     * @param number El número de la tarjeta que debe ser expulsado.
     */
    public void expelCreditCard (int number) {
        // Expulsa la tarjeta de crédito, entregándola al usuario.
        kiosk.expelCreditCard(number);
    }
    
    /**
     * Función que muestra una imagen en la pantalla del kiosco.
     * 
     * @param text El texto relacionado con la imagen que se mostrará.
     */
    public void setImage (String text) {
        // Establece una imagen en el kiosco.
        kiosk.setImage(text);
    }
    
    /**
     * Función que devuelve el número de la tarjeta de crédito insertada en el kiosco.
     * 
     * @return El número de la tarjeta de crédito.
     */
    public long getCardNumber () {
        // Retorna el número de la tarjeta de crédito detectada por el kiosco.
        return kiosk.getCardNumber();
    }
}
