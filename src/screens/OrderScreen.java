/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;

/**
 * Clase encargada de crear la pantalla de gestión del producto.
 * Esta pantalla permite al usuario elegir entre añadir un menú, añadir un producto individual, 
 * finalizar el pedido o cancelarlo.
 * @author jorge_7wuetpy
 */
public class OrderScreen extends KioskScreen {

    /**
     * Función show() que configura los botones y devuelve la siguiente pantalla.
     * Este método configura las opciones de la pantalla de gestión de productos, 
     * espera una respuesta del usuario y luego determina la siguiente pantalla a mostrar.
     * 
     * @param context El contexto de la aplicación que contiene información sobre el kiosco y el estado actual.
     * @return La siguiente pantalla según la respuesta del usuario.
     */
    @Override
    public KioskScreen show(Context context) {   
        configureButtons(context); // Se configuran los botones de la pantalla de gestión de productos
        char resp = context.getKiosk().waitEvent(30); // Se espera una respuesta del usuario
        return nextScreen(resp); // Se decide cuál es la siguiente pantalla a mostrar
    }

    /**
     * Método privado donde se configuran los botones de la pantalla OrderScreen.
     * En esta pantalla, el usuario puede elegir entre diferentes opciones para gestionar el pedido.
     * 
     * @param context El contexto de la aplicación que contiene información sobre el kiosco.
     */
    private void configureButtons(Context context) {
        context.getKiosk().clearScreen();  // Limpiar la pantalla para mostrar las opciones
        context.getKiosk().setTitle("Elige");  // Establecer el título de la pantalla
        context.getKiosk().setOption('A', "Añadir menú");  // Opción para añadir un menú al pedido
        context.getKiosk().setOption('B', "Añadir producto individual");  // Opción para añadir un producto individual
        context.getKiosk().setOption('C', "Finalizar pedido y pagar");  // Opción para finalizar el pedido y proceder al pago
        context.getKiosk().setOption('D', "Cancelar pedido");  // Opción para cancelar el pedido
        context.getKiosk().setImage("mcdonalds.png");  // Establecer la imagen para la pantalla
    }
    
    /**
     * Método de decisión de la siguiente pantalla.
     * Este método decide qué pantalla se debe mostrar en función de la respuesta del usuario.
     * 
     * @param resp La respuesta del usuario, que indica la opción seleccionada.
     * @return La siguiente pantalla que se debe mostrar en función de la opción elegida por el usuario.
     */
    private KioskScreen nextScreen(char resp) {
        switch (resp) {
            case 'A':
                return new MenuScreen();   // Si el usuario selecciona 'A', se muestra la pantalla de selección de menú
            case 'B':
                return new SectionScreen();     // Si el usuario selecciona 'B', se muestra la pantalla de selección de producto individual
            case 'C':
                return new PurchaseScreen(); // Si el usuario selecciona 'C', se muestra la pantalla para finalizar la compra
            default:
                return new WelcomeScreen();        // Si el usuario selecciona cualquier otra opción (o cancela), se muestra la pantalla de bienvenida
        }
    }
}

