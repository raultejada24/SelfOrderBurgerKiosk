/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;

/**
 * Clase encargada de mostrar la pantalla para seleccionar cada producto
 * En esta pantalla, el usuario puede ver los productos de una sección y elegir entre añadir uno al pedido,
 * navegar por los productos disponibles o cancelar el producto o el pedido.
 * @author jorge_7wuetpy
 */
public class ProductScreen extends CarouselScreen {

    private final int section;  // Número de la sección seleccionada para mostrar los productos de esa sección.
    
    /**
     * Constructor de ProductScreen que recibe el número de la sección para mostrar el carrusel de productos correspondiente.
     * Este constructor permite especificar qué sección de productos se desea visualizar (por ejemplo: bebidas, menú principal, complementos).
     * 
     * @param section El número de la sección a mostrar.
     */
    public ProductScreen(int section) {
        this.section = section; 
    }

    /**
     * Muestra la pantalla con los productos de la sección seleccionada.
     * En este método, se configuran los botones, se navega entre los productos de la sección,
     * se muestran las descripciones e imágenes, y se selecciona el producto o se toma alguna otra acción.
     * 
     * @param context El contexto de la aplicación que contiene información sobre el kiosco y el estado actual.
     * @return La siguiente pantalla a mostrar según la opción seleccionada por el usuario.
     */
    @Override
    public KioskScreen show(Context context) {
        
        configureButtons(context);  // Configura los botones disponibles en la pantalla de selección de producto
        int index = 0;  // Índice inicial del producto mostrado
        int max = context.getMenuCard().getSection(section).getNumberProducts();  // Número total de productos en la sección seleccionada
        
        // Muestra la descripción e imagen del primer producto
        context.getKiosk().setDescription(context.getMenuCard().getSection(section).getProduct(index).getDescription());
        context.getKiosk().setImage(context.getMenuCard().getSection(section).getProduct(index).getImage());

        char option = context.getKiosk().waitEvent(30);  // Espera la respuesta del usuario (evento de los botones)
        
        //no se puede crear una función que sea del funcionamiento del carrousel porque devuelve demasiados parametros
        // Mientras el usuario navegue entre los productos (usando 'H' o 'G'), se actualiza la vista
        while (option == 'H' || option == 'G') {
            if (option == 'H') {
                index++;  // Avanzar al siguiente producto
            } else if (option == 'G') {
                index--;  // Volver al producto anterior
            } 
            
            // Si el índice es fuera de rango, se ajusta al primer o último producto
            if (index < 0) {
                index = max - 1;  // Ciclar al último producto si el índice es negativo
            } else if (index > max - 1) {
                index = 0;  // Ciclar al primer producto si el índice es mayor al máximo
            }
            
            // Actualizar la descripción y la imagen del producto actual
            context.getKiosk().setDescription(context.getMenuCard().getSection(section).getProduct(index).getDescription());
            context.getKiosk().setImage(context.getMenuCard().getSection(section).getProduct(index).getImage());
            option = context.getKiosk().waitEvent(30);  // Espera la siguiente respuesta del usuario
        }
        
        int value = index;  // Guardamos el índice del producto seleccionado
        return nextScreen(option, context, value);  // Determina la siguiente pantalla según la opción seleccionada por el usuario
    }

    /**
     * Configura los botones disponibles en la pantalla de selección de producto.
     * En esta pantalla, el usuario puede elegir entre añadir el producto al pedido, 
     * cancelar el producto o cancelar todo el pedido, además de navegar entre los productos.
     * 
     * @param context El contexto de la aplicación que contiene información sobre el kiosco.
     */
    private void configureButtons(Context context) {
        context.getKiosk().clearScreen();  // Limpiar la pantalla para mostrar las opciones actualizadas
        context.getKiosk().setTitle(context.getMenuCard().getSection(section).getName());  // Título de la pantalla
        context.getKiosk().setOption('A', "Añadir producto");  // Opción para añadir el producto al pedido
        context.getKiosk().setOption('B', "Cancelar Producto");  // Opción para cancelar el producto seleccionado
        context.getKiosk().setOption('C', "Cancelar Pedido");  // Opción para cancelar todo el pedido
        context.getKiosk().setOption('H', ">");  // Opción para ir al siguiente producto
        context.getKiosk().setOption('G', "<");  // Opción para ir al producto anterior
    }
    
    /**
     * Determina cuál será la siguiente pantalla según la opción seleccionada por el usuario.
     * Dependiendo de la acción tomada (añadir el producto, cancelar el producto o el pedido),
     * se decide si se va a la pantalla de gestión de pedidos, la pantalla de bienvenida, etc.
     * 
     * @param option La opción seleccionada por el usuario (A, B, C, etc.).
     * @param context El contexto de la aplicación.
     * @param value El índice del producto seleccionado.
     * @return La siguiente pantalla a mostrar en función de la opción seleccionada.
     */
    private KioskScreen nextScreen(char option, Context context, int value) {
        switch (option) {
            case 'A':
                // Si el usuario elige añadir el producto, se agrega el producto al pedido
                context.getOrder().addProduct(context.getMenuCard().getSection(section).getProduct(value));
                return new OrderScreen();  // Después de añadir el producto, se muestra la pantalla de gestión del pedido
            case 'B':
                return new OrderScreen();  // Si el usuario cancela el producto, se regresa a la pantalla de gestión del pedido
            default:
                return new WelcomeScreen();  // Si el usuario cancela el pedido, se regresa a la pantalla de bienvenida
        }
    }
}
