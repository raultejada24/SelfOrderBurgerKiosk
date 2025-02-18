/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicaciónmcdonalds;

import java.io.FileNotFoundException;
import java.io.IOException;
import screens.KioskScreen;
import screens.WelcomeScreen;

/**
 * La clase KioskManager es responsable de iniciar y gestionar el algoritmo de pedidos del kiosco.
 * Controla el flujo de pantallas que el usuario ve mientras interactúa con el sistema.
 * Su función principal es gestionar la navegación entre las distintas pantallas del kiosco.
 * @author rault
 */
public class KioskManager {

    /**
     * Constructor de la clase KioskManager.
     * Este constructor no requiere parámetros, ya que la inicialización del kiosco se realiza en el método start.
     */
    public KioskManager() {
    }
    
    /**
     * Método que inicia el proceso de gestión de pedidos del kiosco.
     * Crea el contexto necesario para el funcionamiento del kiosco, inicializa la pantalla de bienvenida
     * y luego navega entre las pantallas hasta que el programa se apaga.
     * 
     * @throws FileNotFoundException si no se encuentra un archivo necesario durante la carga.
     * @throws IOException si ocurre algún error al cargar archivos o realizar operaciones de entrada/salida.
     */
    public void start () throws FileNotFoundException, IOException {
        // Crear un objeto Context que agrupa toda la información y objetos necesarios para el kiosco.
        Context context = new Context();  
 
        // La primera pantalla que se muestra al usuario es la WelcomeScreen (pantalla de bienvenida).
        KioskScreen nextScreen = new WelcomeScreen(); 
        
        // El bucle continuará ejecutándose hasta que el programa se apague.
        while(true) {  
            // La siguiente pantalla a mostrar es determinada por la función show() de la pantalla actual.
            nextScreen = nextScreen.show(context); 
        }
    }
}
