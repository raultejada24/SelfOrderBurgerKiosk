/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

/**
 * Clase abstracta de la cual heredan tanto Menu como ProductoIndividual.
 * Define los métodos comunes que deben ser implementados por las clases hijas.
 * 
 * @author jorge_7wuetpy
 */
public abstract class Product {

    /**
     * Método abstracto que debe ser implementado por las clases hijas
     * para obtener el precio del producto en céntimos.
     * 
     * @return El precio del producto en céntimos.
     */
    public abstract int getPrice();
    
    /**
     * Método abstracto que debe ser implementado por las clases hijas
     * para obtener el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public abstract String getName();

    /**
     * Método opcional para representar el producto como una cadena de texto.
     * Puede ser sobrescrito por las clases hijas si se requiere un formato específico.
     * 
     * @return Una representación en cadena del producto.
     */
    @Override
    public String toString() {
        return getName() + " - " + getPrice() / 100.0 + "€";
    }
}

