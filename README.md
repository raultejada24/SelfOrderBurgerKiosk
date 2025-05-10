# 🍔 BurgerScreen

**BurgerScreen** es una aplicación interactiva para **pantallas de autoservicio** en una **hamburguesería moderna**. Los clientes pueden realizar sus pedidos de forma rápida e intuitiva, personalizando ingredientes, seleccionando menús y gestionando su pago sin necesidad de atención directa.

> ⚠️ Asegúrate de configurar correctamente el entorno de desarrollo y las dependencias antes de ejecutar la aplicación.

---

## 📑 Tabla de Contenidos

- [Características]
- [Interfaz de Usuario]
- [Roles]
- [Sistema de Pedidos]
- [Instalación y Configuración]
- [Ejecución]
- [FAQ]

---

## 🏆 Características

- **Menú Dinámico**: Amplia variedad de productos, con imágenes, descripciones y precios actualizados.
- **Personalización**: Posibilidad de modificar ingredientes, tamaños de bebida o puntos de cocción.
- **Pago Integrado**: Múltiples métodos de pago, incluyendo tarjetas y códigos QR.
- **Persistencia de Datos**: Los pedidos se guardan automáticamente en la base de datos del sistema.
- **Diseño Accesible**: Interfaz clara, amigable e inclusiva para todos los usuarios.

---

## 🖥️ Interfaz de Usuario

- **Pantalla Principal**: Acceso a categorías (Hamburguesas, Bebidas, Postres...).
- **Personalización del Pedido**: Selección y modificación de ingredientes.
- **Resumen y Pago**: Vista previa del pedido con opción a modificar antes de confirmar.

---

## 🛠️ Roles

- **Administrador**: Gestiona el catálogo de productos, precios, promociones y configuración del sistema.
- **Cliente**: Realiza pedidos desde la pantalla táctil de forma autónoma.

---

## 🍟 Sistema de Pedidos

Cada pedido pasa por las siguientes etapas:

1. **Selección de Productos**: El cliente elige lo que desea consumir.
2. **Personalización**: Puede modificar hamburguesas (eliminar pepinillos, cambiar el tipo de pan, etc.).
3. **Resumen del Pedido**: Se muestra el total, con opción a confirmar o volver atrás.
4. **Pago**: Se realiza el pago desde la misma pantalla.
5. **Generación del Ticket**: Se imprime o se envía al sistema interno para preparación.

---

## ⚙️ Instalación y Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/BurgerScreen.git

2. **Acceder al directorio del proyecto**:
    ```bash
    cd BurguerSelfOrderKiosk
    ```
    
![imagen](https://github.com/user-attachments/assets/1b3be84b-a580-4e1a-933d-70e4d068f089)

---

## ❓ FAQ

**¿Puedo modificar los ingredientes de una hamburguesa?**  
Sí, al seleccionar un producto puedes quitar o añadir ingredientes a tu gusto.

**¿Qué métodos de pago se aceptan?**  
Tarjeta, NFC y códigos QR compatibles con aplicaciones móviles.

**¿El sistema requiere conexión a internet?**  
Solo si se usa un backend remoto o para pagos online. Puede funcionar en local si está correctamente configurado.

**¿Qué pasa si se apaga la pantalla durante un pedido?**  
El sistema guarda los datos temporalmente. Si se reinicia, se puede recuperar el pedido en curso (según configuración).

**¿Puedo cancelar un pedido una vez iniciado?**  
Sí, el sistema permite cancelar el pedido en cualquier momento antes del pago, con confirmación para evitar errores.

**¿Se pueden añadir más productos o traducir la app a otros idiomas?**  
Sí, tanto la carta de productos como los idiomas son configurables mediante archivos externos, sin necesidad de modificar el código fuente.
