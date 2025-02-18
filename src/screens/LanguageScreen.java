package screens;

import aplicaciónmcdonalds.Context;

/**
 * Pantalla que muestra las diferentes opciones de lenguajes que se pueden elegir 
 * @author jorge_7wuetpy
 */
public class LanguageScreen extends KioskScreen {

    /**
     * Método que maneja el funcionamiento de la pantalla.
     * @param context El contexto que contiene la información relevante para la pantalla.
     * @return La siguiente pantalla a mostrar, dependiendo de la opción seleccionada.
     */
    @Override
    public KioskScreen show(Context context) {
        configureButtons(context); // Configuramos los botones
        char option = context.getKiosk().waitEvent(30); // Esperamos respuesta
        return nextScreen(option, context); // Se muestra siguiente pantalla según la opción seleccionada    
    }

    /**
     * Configura los botones de la pantalla, mostrando las opciones de idiomas.
     * @param context El contexto que contiene la información relevante para la pantalla.
     */
    private void configureButtons(Context context) {
        context.getKiosk().clearScreen();
        context.getKiosk().setTitle("Selecciona un idioma");
        context.getKiosk().setOption('A', "English");
        context.getKiosk().setOption('B', "Français");
        context.getKiosk().setOption('C', "Deutsch");
        context.getKiosk().setOption('D', "Español");
        context.getKiosk().setImage("mcdonalds.png");
    }

    /**
     * Selecciona la siguiente pantalla según la opción del usuario.
     * @param option La opción seleccionada por el usuario.
     * @param context El contexto que contiene la información relevante para la pantalla.
     * @return La siguiente pantalla a mostrar.
     */
    private KioskScreen nextScreen(char option, Context context) {
        String languageKey = getLanguageKey(option);
        context.setLanguageTranslator(languageKey); // Establecemos el idioma en el traductor
        return new WelcomeScreen(); // Regresa siempre la misma pantalla de bienvenida
    }

    /**
     * Determina la clave del idioma según la opción seleccionada.
     * @param option La opción seleccionada por el usuario.
     * @return La clave correspondiente al idioma.
     */
    private String getLanguageKey(char option) {
        switch (option) {
            case 'A':
                return "ingles";
            case 'B':
                return "frances";
            case 'C':
                return "aleman";
            case 'D':
            default:
                return "español"; // Por defecto selecciona español si no es una opción válida
        }
    }
}
