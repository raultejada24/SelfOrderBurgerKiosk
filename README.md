# 🍔 BurgerScreen
**BurgerScreen** is an interactive application for **self-service kiosks** in a **modern burger restaurant**. Customers can place their orders quickly and intuitively, customizing ingredients, selecting menus, and managing their payment without needing direct staff assistance.
> ⚠️ Make sure to properly configure the development environment and dependencies before running the application.

---

## 📑 Table of Contents
- [Features]
- [User Interface]
- [Roles]
- [Order System]
- [Installation and Configuration]
- [Running the App]
- [FAQ]

---

## 🏆 Features
- **Dynamic Menu**: Wide variety of products, with images, descriptions and up-to-date prices.
- **Customization**: Ability to modify ingredients, drink sizes or cooking preferences.
- **Integrated Payment**: Multiple payment methods, including cards and QR codes.
- **Data Persistence**: Orders are automatically saved to the system database.
- **Accessible Design**: Clear, friendly and inclusive interface for all users.

---

## 🖥️ User Interface
- **Main Screen**: Access to categories (Burgers, Drinks, Desserts...).
- **Order Customization**: Selection and modification of ingredients.
- **Summary and Payment**: Order preview with the option to modify before confirming.

---

## 🛠️ Roles
- **Administrator**: Manages the product catalog, prices, promotions and system configuration.
- **Customer**: Places orders from the touchscreen autonomously.

---

## 🍟 Order System
Each order goes through the following stages:
1. **Product Selection**: The customer chooses what they want to order.
2. **Customization**: They can modify burgers (remove pickles, change the type of bun, etc.).
3. **Order Summary**: The total is displayed, with the option to confirm or go back.
4. **Payment**: Payment is made from the same screen.
5. **Ticket Generation**: A receipt is printed or sent to the internal system for preparation.

---

## ⚙️ Installation and Configuration
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/BurgerScreen.git
   ```
2. **Navigate to the project directory**:
    ```bash
    cd BurguerSelfOrderKiosk
    ```

![imagen](https://github.com/user-attachments/assets/1b3be84b-a580-4e1a-933d-70e4d068f089)

---

## ❓ FAQ
**Can I modify the ingredients of a burger?**  
Yes, when selecting a product you can remove or add ingredients to your liking.

**What payment methods are accepted?**  
Card, NFC and QR codes compatible with mobile applications.

**Does the system require an internet connection?**  
Only if a remote backend or online payments are used. It can run locally if properly configured.

**What happens if the screen turns off during an order?**  
The system temporarily saves the data. If restarted, the ongoing order can be recovered (depending on configuration).

**Can I cancel an order once started?**  
Yes, the system allows you to cancel the order at any time before payment, with a confirmation prompt to avoid mistakes.

**Can more products be added or the app translated into other languages?**  
Yes, both the product catalog and languages are configurable through external files, without needing to modify the source code.
