/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicaciónmcdonalds;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * La clase BurgerSelfOrderKiosk se encarga de iniciar el programa y crear un objeto de la clase KioskManager.
 * Es el punto de entrada principal de la aplicación.
 * @author jorge_7wuetpy
 */
public class BurgerSelfOrderKiosk {

    /**
     * Método principal que arranca el kiosco de pedidos.
     * Crea una instancia del KioskManager y llama a su método de inicio para iniciar el proceso de pedidos.
     * 
     * @param args los argumentos de línea de comandos, si se usan (no se utilizan en este caso).
     * @throws java.io.FileNotFoundException si no se encuentra un archivo necesario para el funcionamiento del kiosco.
     * @throws java.io.IOException si ocurre algún error relacionado con las entradas/salidas (por ejemplo, errores al leer archivos).
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Crear una instancia de la clase KioskManager, que gestiona las operaciones del kiosco.
        KioskManager kiosk = new KioskManager();  
        
        // Llamar al método start de la clase KioskManager para iniciar el proceso de pedidos en el kiosco.
        kiosk.start();  
    }
}

