/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.Objects;

/**
 * La clase IndividualProduct representa un producto individual en el sistema, como una hamburguesa 
 * o una bebida. Hereda de la clase Product y proporciona detalles específicos sobre el producto 
 * como su nombre, descripción, imagen y precio.
 * 
 * @author jorge_7wuetpy
 */
public class IndividualProduct extends Product implements Comparable<IndividualProduct>, Cloneable {

    private final String name; // Nombre del producto
    private final String description; // Descripción del producto
    private final String imageFileName; // Nombre del archivo de imagen asociado al producto
    private final int price; // Precio del producto en centavos

    /**
     * Constructor de la clase IndividualProduct. Inicializa los valores del producto.
     * 
     * @param name El nombre del producto.
     * @param description La descripción del producto.
     * @param imageFileName El nombre del archivo de imagen asociado al producto.
     * @param price El precio del producto en centavos.
     */
    public IndividualProduct(String name, String description, String imageFileName, int price) {
        this.name = name;
        this.description = description;
        this.imageFileName = imageFileName;
        this.price = price;
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return La descripción del producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto en centavos.
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    @Override
    public String getName() {
        return this.name;  
    }

    /**
     * Obtiene el nombre del archivo de imagen asociado al producto.
     * 
     * @return El nombre del archivo de imagen.
     */
    public String getImage() {
        return imageFileName;
    }

    // Implementación de equals
    // IndividualProduct prod1 = new IndividualProduct("Hamburguesa", "Deliciosa hamburguesa", "burger.jpg", 500);
    // System.out.println("¿Son iguales prod1 y prod2? " + prod1.equals(prod2));

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndividualProduct that = (IndividualProduct) o;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(imageFileName, that.imageFileName);
    }

    // Implementación de hashCode
    // System.out.println("HashCode de prod1: " + prod1.hashCode());

    @Override
    public int hashCode() {
        return Objects.hash(name, description, imageFileName, price);
    }

    // Implementación de compareTo (basado en el nombre)
    // System.out.println("Comparación prod1 vs prod2: " + prod1.compareTo(prod2));

    @Override
    public int compareTo(IndividualProduct otherProduct) {
        return this.name.compareTo(otherProduct.name);
    }

    // Implementación de clone
    // IndividualProduct prod3 = (IndividualProduct) prod1.clone();
    // System.out.println("Copia clonada de prod1: " + prod3);
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Representación en String
    @Override
    public String toString() {
        return "IndividualProduct{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                ", price=" + price +
                '}';
    }
}






