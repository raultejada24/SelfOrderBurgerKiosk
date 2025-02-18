/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;

/**
 * Primera pantalla que muestra el programa, donde el usuario puede iniciar un nuevo pedido o seleccionar el idioma.
 * 
 * @author rault
 */
public class WelcomeScreen extends KioskScreen {

    /**
     * Función show() que configura los botones de la pantalla y devuelve la siguiente pantalla
     * según la opción seleccionada por el usuario.
     * 
     * @param context Contexto de la aplicación que contiene el estado del pedido y la interfaz del quiosco.
     * @return La siguiente pantalla a mostrar, dependiendo de la opción seleccionada.
     */
    @Override
    public KioskScreen show(Context context) {
        configureButtons(context);  // Configura los botones de la pantalla
        char resp = context.getKiosk().waitEvent(30); // Espera que el usuario seleccione una opción durante 30 segundos
        return nextScreen(resp, context); // Decide la siguiente pantalla según la opción seleccionada     
    }
    
    /**
     * Método privado donde se configuran los botones que estarán disponibles en la pantalla WelcomeScreen.
     * 
     * @param context Contexto de la aplicación, utilizado para acceder a la interfaz del quiosco y sus opciones.
     */
    private void configureButtons(Context context) {
        context.getKiosk().clearScreen();  // Limpia la pantalla antes de configurar las opciones
        context.getKiosk().setOption('B', "Iniciar nuevo pedido"); // Configura la opción para iniciar un nuevo pedido
        context.getKiosk().setOption('D', "Selecciona idioma"); // Configura la opción para seleccionar el idioma
        context.getKiosk().setTitle("BIENVENIDOS"); // Establece el título de la pantalla
        context.getKiosk().setImage("mcdonalds.png"); // Establece la imagen en la pantalla (logo de McDonald's)
    }
    
    /**
     * Método que decide cuál es la siguiente pantalla a mostrar dependiendo de la opción seleccionada.
     * 
     * @param resp Opción seleccionada por el usuario.
     * @param context Contexto de la aplicación, utilizado para acceder al estado del pedido.
     * @return La siguiente pantalla a mostrar, según la opción seleccionada.
     */
    private KioskScreen nextScreen(char resp, Context context) {
        if (resp == 'B') {
            context.resetOrder(context.getOrder());  // Reinicia el pedido cada vez que se regresa a la pantalla de pedido desde la pantalla de bienvenida
            return new OrderScreen(); // Muestra la pantalla de selección de productos
        } else {
            return new LanguageScreen(); // Si se selecciona el idioma, muestra la pantalla de selección de idioma
        }
    }

}
