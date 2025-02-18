/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import aplicaciónmcdonalds.Context;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import javax.naming.CommunicationException;
import urjc.UrjcBankServer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta clase es se encarga de crear la pantalla de pago.
 *
 * @author jorge_7wuetpy
 */
public class PurchaseScreen extends KioskScreen {

    private final UrjcBankServer urjcBank; //objeto de la clase UrjcBankServer
    private static final Lock writeLock = new ReentrantLock(); // Lock para garantizar la escritura atómica en el archivo

    /**
     * Constructor de la clase PurchaseScreen Inicializa el objeto
     * UrjcBankServer para la comunicación con el sistema bancario.
     */
    public PurchaseScreen() {
        this.urjcBank = new UrjcBankServer();
    }

    /**
     * Metodo show que se encarga de la funcionalidad de la pantalla de pago.
     * Configura los botones y maneja el flujo de la pantalla, incluyendo el
     * manejo de las excepciones.
     *
     * @param context El contexto que mantiene el estado actual de la pantalla
     * @return La siguiente pantalla a mostrar según la opción seleccionada.
     */
    @Override
    public KioskScreen show(Context context) {
        try {  // Se hace try/catch para manejar excepciones en la configuración y operación de la pantalla.
            configureButtons(context);
        } catch (IOException ex) {
            Logger.getLogger(PurchaseScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        char option = context.getKiosk().waitEvent(30);
        try {  // Manejo de excepciones al interactuar con los archivos y la operación de pago.
            return nextScreen(option, context);
        } catch (IOException ex) {
            System.out.println("ERROR EN EL PROCESO");
        }
        return null;
    }

    /**
     * Metodo privado que configura los botones y texto que se imprime por
     * pantalla. También muestra el resumen del pedido.
     *
     * @param context El contexto que contiene los datos de la orden y la
     * pantalla.
     * @throws IOException Si ocurre un error al intentar mostrar el resumen del
     * pedido.
     */
    private void configureButtons(Context context) throws IOException {
        context.getKiosk().clearScreen();
        context.getKiosk().setMessageMode();
        context.getKiosk().setOption('A', "Añadir más productos al pedido");
        context.getKiosk().setOption('B', "Cancelar Pedido");
        context.getKiosk().setTitle("RESUMEN DEL PEDIDO");
        showOrderSummary(context); // Muestra el resumen de la orden
    }

    /**
     * Función que se encarga de mostrar en la descripción el resumen del
     * pedido. Muestra los productos en el pedido o un mensaje si no hay
     * productos seleccionados.
     *
     * @param context El contexto que contiene la lista de productos del pedido.
     * @throws IOException Si ocurre un error al intentar acceder al contenido
     * del pedido.
     */
    private void showOrderSummary(Context context) throws IOException {
        List<String> order = context.getOrder().getOrderText();  // Obtenemos el resumen de productos
        if (order.isEmpty()) {  // Si no hay productos en el pedido
            context.getKiosk().setDescription("No hay productos seleccionados");
        } else {  // Si hay productos en el pedido
            StringBuilder orderSummary = new StringBuilder();  // Usamos StringBuilder para evitar crear muchos objetos String innecesarios

            // Añadimos la lista de productos
            for (String product : order) {
                orderSummary.append(product).append('\n');
            }

            // Añadimos el mensaje de pago
            orderSummary.append("\nIntroduzca la tarjeta para pagar");

            // Establecemos la descripción final
            context.getKiosk().setDescription(orderSummary.toString());
        }
    }

    /**
     * Función que se encarga de devolver la siguiente pantalla. La opción del
     * usuario determina la pantalla a la que se va a redirigir.
     *
     * @param option Opción seleccionada por el usuario.
     * @param context El contexto que mantiene el estado actual de la pantalla.
     * @return La siguiente pantalla a mostrar según la opción seleccionada.
     * @throws IOException Si ocurre un error al manejar los archivos o el
     * proceso de pago.
     */
    private KioskScreen nextScreen(char option, Context context) throws IOException {
        context.getKiosk().setMenuMode(); // Configura la pantalla para el siguiente modo
        switch (option) {
            case 'A':
                return new OrderScreen();  // Si elige añadir más productos, se muestra la pantalla de pedido.
            case 'B':
                return new WelcomeScreen();  // Si elige cancelar el pedido, se muestra la pantalla de bienvenida.
            default:
                KioskScreen nextScreen = InteractionWithBank(context); // Interacción con el banco para procesar el pago.
                increaseOrderNumber(); // Incrementar el número de pedido
                WriteOrderToFile(context); // Escribir el pedido en el archivo de cocina.
                checkAndRotateOrdersFile(); // Comprobar y rotar el archivo de pedidos a las 5:00 AM.
                return nextScreen; // Dependiendo del resultado del pago, se redirige a la siguiente pantalla.
        }
    }

    /**
     * Método que contiene toda la lógica de interacción con el URJCBank y
     * devuelve la siguiente pantalla dependiendo si la operación ha tenido
     * éxito o no.
     *
     * @param context El contexto con los datos del pedido y la tarjeta.
     * @return La siguiente pantalla dependiendo del resultado de la operación
     * de pago.
     */
    private KioskScreen InteractionWithBank(Context context) {
        context.getKiosk().retainCreditCard(false);
        long cardNumber = context.getKiosk().getCardNumber(); // Obtiene el número de la tarjeta.
        while (!urjcBank.comunicationAvaiable()) { // Espera hasta que se establezca la conexión con el banco.
            context.getKiosk().setDescription("CONEXIÓN FALLIDA");
        }
        context.getKiosk().setDescription("CONEXIÓN CON EXITO"); // Mensaje de éxito si se establece la conexión.

        int totalAmount = context.getOrder().getTotalAmount(); // Obtiene el monto total del pedido.

        // Intentamos realizar la operación bancaria.
        try {
            if (urjcBank.doOperation(cardNumber, totalAmount)) { // Si el pago es exitoso.
                context.getKiosk().print(context.getOrder().getOrderText());  // Imprime el recibo del pedido.
                context.getKiosk().expelCreditCard(30); // Expulsa la tarjeta después de 30 segundos.
                return new WelcomeScreen(); // Regresa a la pantalla principal.
            } else {
                context.getKiosk().setDescription("ERROR, EL PAGO NO HA SIDO REALIZADO"); // Mensaje de error.
                context.getKiosk().expelCreditCard(30); // Expulsa la tarjeta en caso de error.
                return new PurchaseScreen(); // Vuelve a la pantalla de pago si ocurre un error.
            }
        } catch (CommunicationException ex) {
            return new PurchaseScreen(); // Si hay un error de comunicación, vuelve a la pantalla de pago.
        }
    }

    /**
     * Metodo que permite escribir en un fichero de texto el pedido realizado.
     * Asegura la escritura atómica usando un lock para evitar problemas de
     * concurrencia.
     *
     * @param context El contexto que contiene el pedido.
     * @throws IOException Si ocurre un error al intentar escribir en el
     * archivo.
     */
    private void WriteOrderToFile(Context context) throws IOException {
        String filename = "cocina_orders.txt";
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();  // Si el archivo no existe, lo crea.
        }

        List<String> orderText = context.getOrder().getOrderText();  // Obtiene los detalles del pedido.

        // Usamos el lock para garantizar la escritura atómica y mejora la experiencia. (Sino, pueden ocurrir errores indeseados)
        writeLock.lock();
        try {
            // Abrimos el archivo en modo append (añadir al final del archivo).
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribimos el número de pedido y los productos.
            bufferedWriter.newLine();  // Nueva línea después del número de pedido.
            for (String product : orderText) {
                bufferedWriter.write(product);  // Escribe cada producto del pedido.
                bufferedWriter.newLine();  // Añade una nueva línea después de cada producto.
            }

            // Escribe un separador entre pedidos.
            bufferedWriter.write("--------------");
            bufferedWriter.newLine();

            bufferedWriter.close();  // Cierra el archivo después de escribir.
        } finally {
            writeLock.unlock();  // Asegura liberar el lock una vez completada la escritura.
        }
    }

    /**
     * Metodo que permite incrementar el número de pedido (de uno en uno).
     *
     * @return El número de pedido incrementado.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private int increaseOrderNumber() throws FileNotFoundException, IOException {
        int orderNumber = getOrderNumber();  // Obtiene el número actual del pedido.
        setOrderNumber(orderNumber + 1); // Incrementa el número de pedido en 1.
        return orderNumber;  // Devuelve el número de pedido antes de incrementar.
    }

    /**
     * Función que permite obtener el numero del fichero. Lee el número de
     * pedido desde el archivo.
     *
     * @return El número de pedido actual.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws NumberFormatException Si el número de pedido no es válido.
     */
    private int getOrderNumber() throws FileNotFoundException, IOException, NumberFormatException {
        String filename = "OrderNumber.txt";
        FileReader fileReader = new FileReader(filename);
        BufferedReader buffer = new BufferedReader(fileReader);
        String orderNumber = buffer.readLine();  // Lee el número de pedido desde el archivo.
        if (orderNumber != null) {
            return Integer.parseInt(orderNumber);  // Si hay un número de pedido, lo devuelve como entero.
        }
        return 0;  // Si no existe un número de pedido, comienza desde 0.
    }

    /**
     * Función que permite escribir en el fichero el numero del pedido
     * incrementado.
     *
     * @param orderNumber El número de pedido incrementado.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en
     * el archivo.
     */
    private void setOrderNumber(int orderNumber) throws IOException {
        String filename = "OrderNumber.txt";
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(String.valueOf(orderNumber));  // Escribe el número de pedido incrementado como cadena.
    }

    /**
     * Comprobar y rotar el archivo a las 5:00 AM. Si es la hora indicada,
     * renombra el archivo de pedidos con un timestamp único.
     */
    private void checkAndRotateOrdersFile() {
        // Obtener la hora actual.
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        int currentHour = Integer.parseInt(sdf.format(new Date()));

        // Si es las 5:00 AM, renombramos el archivo de pedidos.
        if (currentHour == 5) {
            String filename = "cocina_orders.txt";
            File oldFile = new File(filename);
            File newFile = new File("cocina_orders_" + System.currentTimeMillis() + ".txt");
            oldFile.renameTo(newFile);  // Renombra el archivo de pedidos con un timestamp único.
        }
    }
}
