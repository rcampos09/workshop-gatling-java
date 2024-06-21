# WorkShop Gatling Java Maven (Performance360Latam)

Este proyecto contiene un script de simulación usando Gatling para realizar pruebas de carga sobre el sitio web de Demo Store.

## Herramientas Utilizadas

- **Gatling**: Herramienta de pruebas de carga y rendimiento.
- **Java Maven**: Gestión de dependencias y construcción del proyecto.

## Configuración del Proyecto

### Requisitos Previos

Asegúrate de tener instalados los siguientes componentes:

- Java JDK
- Maven

### Instalación

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone <URL-del-repositorio>

2. Desde la raíz del proyecto, ejecuta Maven para compilar y empaquetar el proyecto:

   ```bash
   mvn clean package

## Ejecución de las Pruebas

Para ejecutar las pruebas de carga:

1. Asegúrate de estar en el directorio raíz del proyecto.
2. Ejecuta el siguiente comando Maven:

   ```bash
   mvn gatling:test -Dgatling.simulationClass=tu.paquete.TuClaseDeSimulacion

## Descripción del Script de Simulación

El archivo `demostoreSimulation.java` contiene la simulación de carga que realiza las siguientes acciones sobre Demo Store:

- Accede a la página de inicio.
- Accede a la página de inicio de sesión.
- Realiza el inicio de sesión con credenciales obtenidas de un archivo CSV.
- Navega por diferentes categorías de productos.
- Visualiza detalles de un producto específico.
- Agrega un producto al carrito de compras.
- Visualiza el contenido del carrito.
- Realiza el checkout del carrito.
- Confirma la orden de compra.

Cada acción está configurada con pausas aleatorias para simular el comportamiento humano y evitar la sobrecarga del servidor.

### Propiedad y Derechos de Autor
Este código es propiedad de Rodrigo Campos (Dontester). Todos los derechos de autor están reservados por Rodrigo Campos.

© Rodrigo Campos (Dontester)
