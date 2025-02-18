/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;

/**
 * Clase abstracta KioskScreen es la plantilla base para todas las pantallas del kiosco.
 * Cada pantalla del kiosco debe implementar su propia versión de cómo se debe mostrar
 * la pantalla y realizar las interacciones con el usuario, pero todas deben seguir
 * esta estructura básica.
 * 
 * @author jorge_7wuetpy
 */
public abstract class KioskScreen {

    /**
     * Constructor de la clase KioskScreen.
     * Se encarga de inicializar cualquier propiedad común a las pantallas del kiosco.
     */
    public KioskScreen() { }

    /**
     * Esta función es común para todas las pantallas, y se encarga de mostrar la pantalla
     * actual a partir de la información contenida en el objeto Context.
     * Cada tipo de pantalla implementará su propia versión de cómo se debe mostrar.
     * 
     * @param context Objeto que contiene toda la información relevante para mostrar la pantalla.
     * @return KioskScreen La siguiente pantalla que debe mostrarse después de la actual.
     */
    public abstract KioskScreen show(Context context);

}

