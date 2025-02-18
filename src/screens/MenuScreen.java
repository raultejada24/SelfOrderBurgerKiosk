/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;
import products.IndividualProduct;
import products.Menu;

/**
 * Clase encargada de mostrar la pantalla para seleccionar un menú completo (principal, bebida, complemento)
 * y aplicar el descuento correspondiente.
 * @author jorge_7wuetpy
 */
public class MenuScreen extends CarouselScreen {

    /**
     * Muestra la pantalla para seleccionar un menú completo y gestionar el pedido.
     * En este método, se recorren las secciones del menú (principal, bebida, complemento)
     * y el usuario selecciona un producto en cada una de las secciones.
     * Después, se calcula el precio total del menú con el descuento aplicado
     * y se muestra la opción para confirmar o cancelar el pedido.
     * 
     * @param context El contexto de la aplicación que contiene información sobre el kiosco y el menú.
     * @return La siguiente pantalla según la elección del usuario (Orden o Bienvenida).
     */
    @Override
    public KioskScreen show(Context context) {
        Menu menu = new Menu();  // Creamos un nuevo objeto de tipo Menu para almacenar los productos seleccionados
        int numberSections = context.getMenuCard().getNumberSections();
        // Recorrer cada sección del menú (Principal, Bebida, Complemento)
        for (int sectionIndex = 0; sectionIndex < numberSections; sectionIndex++) {
            boolean productSelected = false;  // Variable que controla si el producto de la sección ha sido seleccionado
            int productIndex = 0;  // Índice inicial del producto dentro de la sección
            int maxProducts = context.getMenuCard().getSection(sectionIndex).getNumberProducts(); // Número de productos en la sección
            
            // Navegación dentro de una sección para seleccionar un producto
            while (!productSelected) {
                // Mostrar el producto actual de la sección
                displayProduct(context, sectionIndex, productIndex);
                // Esperar la interacción del usuario
                char option = context.getKiosk().waitEvent(30);
                
                switch (option) {
                    case 'H': // Opción para ir al siguiente producto
                        productIndex = (productIndex + 1) % maxProducts;  // Incrementar el índice y ciclar al primer producto si es el último
                        break;
                    case 'G': // Opción para ir al producto anterior
                        productIndex = (productIndex - 1 + maxProducts) % maxProducts;  // Decrementar el índice y ciclar al último producto si es el primero
                        break;
                    case 'A': // Opción para seleccionar el producto
                        // Obtener el producto seleccionado de la sección actual
                        IndividualProduct selectedProduct = context.getMenuCard().getSection(sectionIndex).getProduct(productIndex);
                        // Añadir el producto al menú
                        menu.addProductToMenu(selectedProduct);
                        productSelected = true;  // Indicar que ya se ha seleccionado un producto
                        break;
                    case 'C': // Opción para cancelar la selección y volver a la pantalla de bienvenida
                        return new WelcomeScreen();
                }
            }
        }

        // Calcular el precio final del menú con descuento
        int finalPrice = menu.getPrice();  // Obtener el precio total en céntimos
        // Mostrar el precio final y el nombre del menú en la pantalla
        context.getKiosk().setDescription("Menú seleccionado: " + menu.getName() + "\nPrecio final: " + formatPrice(finalPrice));
        context.getKiosk().setOption('A', "Confirmar pedido");  // Opción para confirmar el pedido
        char finalOption = context.getKiosk().waitEvent(30);  // Esperar la opción final del usuario

        // Dependiendo de la opción seleccionada, se confirmará el pedido o se volverá a la pantalla de bienvenida
        if (finalOption == 'A') {
            context.getOrder().addProduct(menu);  // Añadir el menú a la orden
            return new OrderScreen();  // Redirigir a la pantalla de la orden
        } else {
            return new WelcomeScreen();  // Si el usuario cancela, redirigir a la pantalla de bienvenida
        }
    }

    /**
     * Muestra en pantalla el producto actual de la sección.
     * Este método se encarga de mostrar la descripción, imagen y opciones de navegación
     * para cada producto dentro de la sección seleccionada.
     * 
     * @param context El contexto de la aplicación.
     * @param sectionIndex Índice de la sección (principal, bebida, complemento).
     * @param productIndex Índice del producto dentro de la sección.
     */
    private void displayProduct(Context context, int sectionIndex, int productIndex) {
        // Obtener el producto actual de la sección seleccionada
        IndividualProduct product = context.getMenuCard().getSection(sectionIndex).getProduct(productIndex);
        context.getKiosk().clearScreen();  // Limpiar la pantalla para mostrar el siguiente producto
        context.getKiosk().setTitle(context.getMenuCard().getSection(sectionIndex).getName());  // Establecer el título en la pantalla
        context.getKiosk().setDescription(product.getDescription());  // Mostrar la descripción del producto
        context.getKiosk().setImage(product.getImage());  // Mostrar la imagen del producto
        // Configurar las opciones del usuario para seleccionar el producto, navegar entre productos y cancelar
        context.getKiosk().setOption('A', "Seleccionar");
        context.getKiosk().setOption('H', ">");  // Opción para ir al siguiente producto
        context.getKiosk().setOption('G', "<");  // Opción para ir al producto anterior
        context.getKiosk().setOption('C', "Cancelar pedido");  // Opción para cancelar el pedido
    }

    /**
     * Formatea el precio en céntimos a un formato de euros con dos decimales.
     * Este método convierte el precio en céntimos a un formato adecuado para mostrarlo en euros.
     * 
     * @param price Precio en céntimos
     * @return String formateado del precio en euros (con el símbolo €).
     */
    private String formatPrice(int price) {
        // Formatear el precio en céntimos a un formato en euros (por ejemplo: 500 céntimos -> 5.00€)
        return String.format("%.2f€", price / 100.0);  // Dividir entre 100 para convertir a euros y formatear con dos decimales
    }
}
