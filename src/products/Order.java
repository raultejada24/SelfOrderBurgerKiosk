/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.ArrayList;
import java.util.List;
import aplicaciónmcdonalds.Context;

/**
 * La clase Order gestiona la información sobre el pedido que se está realizando. 
 * Guarda los productos que se han añadido al pedido, así como el precio total.
 * También gestiona el número de pedido.
 * 
 * @author jorge_7wuetpy
 */
public class Order {

    private List<Product> order; // Lista de productos en el pedido
    private int totalAmount; // Precio total del pedido (en céntimos)
    private int orderNumber; // Número único del pedido
    private static int orderCount = 0; // Contador estático para generar números de pedido únicos

    /**
     * Constructor de la clase Order. Inicializa el pedido con un número único
     * y una lista vacía de productos.
     */
    public Order() {
        this.order = new ArrayList<>();
        this.totalAmount = 0;
        this.orderNumber = orderCount++; // Asigna un número de pedido único e incrementa el contador
    }

    /**
     * Añade un producto al pedido y actualiza el precio total.
     *
     * @param product Producto que se añadirá al pedido.
     */
    public void addProduct(Product product) {
        this.order.add(product); // Añadimos el producto a la lista
        this.totalAmount += product.getPrice(); // Sumamos el precio del producto al total
    }

    /**
     * Devuelve la información del pedido como una lista de strings que incluye
     * el número del pedido, los productos y el precio total.
     *
     * @return Lista de strings que contiene el texto con la información del pedido.
     */
    public List<String> getOrderText() {
        List<String> textList = new ArrayList<>();
        textList.add("Nº Pedido: " + orderNumber); // Muestra el número de pedido
        textList.add("Productos:");

        double totalAmountEuros = 0.0;
        for (Product product : order) {
            // Formatea el precio de cada producto y lo añade al listado
            String line = String.format("- %s %s€", product.getName(), priceWithCnts(product.getPrice()));
            textList.add(line);
            totalAmountEuros += product.getPrice() / 100.0; // Acumula el precio total en euros
        }

        // Añade el total del pedido al final de la lista
        textList.add(String.format("Precio total del pedido: %.2f€", totalAmountEuros));
        return textList;
    }

    /**
     * Devuelve el precio total del pedido en céntimos.
     *
     * @return El precio total del pedido en céntimos.
     */
    public int getTotalAmount() {
        return totalAmount;
    }

    /**
     * Devuelve el número único del pedido.
     *
     * @return El número del pedido.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Convierte el precio en céntimos a un formato adecuado con 2 decimales en euros.
     *
     * @param price El precio en céntimos.
     * @return El precio formateado en euros.
     */
    public String priceWithCnts(int price) {
        return String.format("%.2f", price / 100.0); // Convierte el precio a un formato con 2 decimales
    }
}

