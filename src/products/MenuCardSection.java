/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase MenuCardSection representa una sección del menú, que puede contener 
 * una lista de productos de esa categoría (por ejemplo, hamburguesas, bebidas, etc.).
 * También incluye información sobre el nombre de la sección y una imagen asociada.
 * 
 * @author jorge_7wuetpy
 */
public class MenuCardSection {
    
    private String sectionName;  // Nombre de la sección (por ejemplo, "Bebidas", "Hamburguesas")
    private String imageFileName;  // Nombre del archivo de imagen asociado a la sección
    private final List<IndividualProduct> productList;  // Lista de productos de la sección

    /**
     * Constructor de la clase MenuCardSection que inicializa la sección con un nombre, 
     * una imagen y una lista de productos.
     * 
     * @param sectionName Nombre de la sección (por ejemplo, "Bebidas")
     * @param imageFileName Nombre del archivo de la imagen asociada a la sección
     * @param productList Lista de productos que pertenecen a esta sección
     */
    public MenuCardSection(String sectionName, String imageFileName, List<IndividualProduct> productList) {
        this.sectionName = sectionName;
        this.imageFileName = imageFileName;
        this.productList = productList;
    }

    /**
     * Constructor sin parámetros, inicializa solo la lista de productos.
     * Se utilizará en casos donde no se necesiten los parámetros de nombre o imagen
     * en el momento de la creación.
     */
    public MenuCardSection() {
        this.productList = new ArrayList<>();
    }

    /**
     * Método que permite obtener el nombre de la sección (por ejemplo, "Hamburguesas", "Bebidas").
     * 
     * @return El nombre de la sección.
     */
    public String getName() {
        return sectionName;
    }

    /**
     * Método que permite obtener un producto específico dentro de esta sección, dado su índice.
     * 
     * @param number El índice del producto en la lista de productos.
     * @return El producto en la posición indicada.
     */
    public IndividualProduct getProduct(int number) {
        return productList.get(number);
    }

    /**
     * Método que devuelve el número total de productos en la sección.
     * 
     * @return El número de productos en la lista.
     */
    public int getNumberProducts() {
        return productList.size();
    }
}
