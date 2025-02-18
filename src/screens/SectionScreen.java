/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;

/**
 * Clase encargada de mostrar las diferentes opciones de selección de la carta con sus correspondientes botones.
 * 
 * @author jorge_7wuetpy
 */
public class SectionScreen extends KioskScreen {

    /**
     * Este método es el encargado de mostrar la pantalla con las opciones de las secciones del menú.
     * Espera una selección del usuario y decide cuál será la siguiente pantalla en función de la opción seleccionada.
     * 
     * @param context Contexto de la aplicación que contiene las configuraciones y el estado del pedido.
     * @return La pantalla siguiente a mostrar, dependiendo de la opción seleccionada por el usuario.
     */
    @Override
    public KioskScreen show(Context context) {
        configureButtons(context); // Configura los botones en la pantalla
        char option = context.getKiosk().waitEvent(30); // Espera a que el usuario seleccione una opción durante 30 segundos
        int seccion = option - 'A'; // Convertimos la opción seleccionada en un índice de sección
        return nextScreen(seccion, context); // Determinamos la siguiente pantalla según la opción seleccionada
    }

    /**
     * Este método configura los botones y el contenido de la pantalla, mostrando las secciones disponibles
     * en el menú y las opciones de cancelación.
     * 
     * @param context Contexto de la aplicación, utilizado para acceder al quiosco y la carta del menú.
     */
    private void configureButtons(Context context) {
        context.getKiosk().clearScreen(); // Limpiar la pantalla antes de mostrar las opciones
        context.getKiosk().setTitle("Elige tipo de producto"); // Establecer el título de la pantalla
        
        char index = 'A'; // Inicializamos el índice de las secciones con 'A'
        for (int contador = 0; contador < context.getMenuCard().getNumberSections() ; contador++ ) {
            // Para cada sección del menú, mostramos la opción correspondiente
            context.getKiosk().setOption(index, context.getMenuCard().getSection(contador).getName());
            index++; // Incrementamos el índice para la siguiente opción
        }
        
        // Opciones para cancelar producto o pedido
        context.getKiosk().setOption(index++, "Cancelar producto");
        context.getKiosk().setOption(index++, "Cancelar pedido");
        
        // Establecemos una imagen en la pantalla
        context.getKiosk().setImage("mcdonalds.png");
    }
    
    /**
     * Este método determina la siguiente pantalla que se debe mostrar dependiendo de la sección seleccionada por el usuario.
     * 
     * @param seccion El índice de la sección seleccionada por el usuario.
     * @param context Contexto de la aplicación.
     * @return La siguiente pantalla a mostrar según la selección del usuario.
     */
    private KioskScreen nextScreen(int seccion, Context context) {
        // Si el índice de la sección es válido, mostramos la pantalla de productos de esa sección
        if (seccion < context.getMenuCard().getNumberSections()) {
            return new ProductScreen(seccion);
        } 
        // Si la opción es para cancelar un producto, volvemos a la pantalla de pedido
        else if (seccion == context.getMenuCard().getNumberSections()) {
            return new OrderScreen();
        } 
        // Si la opción no es válida, volvemos a la pantalla principal de bienvenida
        else {
            return new WelcomeScreen();
        }
    }
}

