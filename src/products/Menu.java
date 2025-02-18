/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Menu representa un menú que contiene varios productos individuales.
 * Esta clase gestiona una lista de productos y proporciona funcionalidades 
 * como calcular el precio total del menú con un posible descuento.
 * 
 * @author jorge_7wuetpy
 */
public class Menu extends Product {

    // Lista de productos individuales que forman parte del menú
    private final List<IndividualProduct> menu;
    private int discount = 0;

    /**
     * Constructor de la clase Menu. Inicializa una lista vacía de productos
     * individuales que se irán añadiendo al menú.
     */
    public Menu() {
        menu = new ArrayList<>();
    }

    /**
     * Función que obtiene el valor del descuento desde un fichero de texto.
     * El descuento se lee como un valor decimal (por ejemplo, 0.2 para un 20%)
     * y se convierte a céntimos (por ejemplo, 0.2 -> 20).
     * 
     * @throws FileNotFoundException Si el archivo de descuento no se encuentra.
     * @throws IOException Si ocurre algún error al leer el archivo.
     */
    private void getDiscountFromDisk() throws FileNotFoundException, IOException {
        String fileName = "Discount.txt";  // Nombre del archivo donde se guarda el descuento
        FileReader fileReader = new FileReader(fileName); // Lector para leer el archivo
        BufferedReader buffer = new BufferedReader(fileReader); // Buffer para leer línea por línea
        String discountString = buffer.readLine(); // Lee la primera línea con el descuento

        if (discountString != null) {
            try {
                // Intentamos convertir el descuento a un valor decimal (por ejemplo, 0.2)
                double discountValue = Double.parseDouble(discountString);
                // Convertimos el descuento a céntimos y lo devolvemos
                this.discount = (int) (discountValue * 100);
            } catch (NumberFormatException e) {
                System.out.println("Error al leer el descuento: " + e.getMessage());
                this.discount = 0;
            }
        } else {
            this.discount = 0;  // Si no hay valor de descuento, no se aplica descuento
        }
    }
    


    /**
     * Función que calcula el precio total del menú. Suma los precios de los
     * productos del menú y luego aplica el descuento (si existe).
     * 
     * @return El precio total del menú en céntimos.
     */
    @Override
    public int getPrice() {
        int price = 0;
        // Suma el precio de todos los productos individuales del menú
        for (IndividualProduct product : menu) {
            price += product.getPrice();  // Suma el precio de cada producto (en céntimos)
        }
        //obtenemos el descuento la primera vez
        if (discount == 0) {
            try {
                getDiscountFromDisk();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Aplica el descuento al precio total y lo devuelve
        return price - (price * discount / 100);
    }

    /**
     * Función que genera y devuelve el nombre del menú concatenando los nombres
     * de todos los productos individuales que contiene.
     * 
     * @return El nombre del menú con los nombres de los productos.
     */
    @Override
    public String getName() {
        StringBuilder menuName = new StringBuilder("Menu: "); // Usamos StringBuilder para concatenar
        for (IndividualProduct product : menu) {
            menuName.append(product.getName()).append(", "); // Añadimos el nombre del producto seguido de una coma
        }
        // Eliminamos la última coma para tener un formato limpio
        if (menuName.length() > 0) {
            menuName.setLength(menuName.length() - 2); // Elimina la última coma y el espacio
        }
        return menuName.toString();  // Devolvemos el nombre del menú como un String
    }

    /**
     * Función que añade un producto individual al menú.
     * 
     * @param product El producto que se va a añadir al menú.
     */
    public void addProductToMenu(IndividualProduct product) {
        menu.add(product);  // Añade el producto a la lista del menú
    }
}
