# Proyecto Java Maven - [Práctica Software]

Este proyecto es una plantilla base para comenzar a trabajar con aplicaciones Java utilizando Maven. Asegúrate de seguir las instrucciones para configurar tu entorno correctamente y entender cómo contribuir al proyecto.

## Tabla de Contenidos

- [Introducción](#introducción)
- [Requisitos](#requisitos)
- [Configuración del Entorno](#configuración-del-entorno)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Compilación y Ejecución](#compilación-y-ejecución)
- [Contribución](#contribución)
- [Licencia](#licencia)

---

## Introducción

Este es un proyecto base para aprender sobre el uso de **Java** y **Maven**. El objetivo familiarizarse con las buenas prácticas de desarrollo y gestionar dependencias utilizando Maven.

## Requisitos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu sistema:

- **Java 8+** (JDK) - Puedes descargarlo desde [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) o [OpenJDK](https://openjdk.java.net/)
- **Maven** - Puedes instalar Maven siguiendo las instrucciones en su sitio oficial: [https://maven.apache.org/install.html](https://maven.apache.org/install.html)
- **IDE recomendada:** Visual Studio Code o cualquier editor de tu preferencia con soporte para Java.

## Configuración del Entorno

### 1. Instalar Java
Asegúrate de que tienes **Java 8** o una versión superior instalada. Puedes verificarlo con el siguiente comando:

```bash
java -version
```

### 2. Instalar Maven
Una vez que tengas Java instalado, puedes proceder con la instalación de Maven. Para verificar que Maven esté instalado correctamente, ejecuta el siguiente comando:

mvn -version

### 3. Configurar el IDE
Para trabajar con este proyecto, puedes usar cualquier IDE que soporte Java, como Visual Studio Code, IntelliJ IDEA, Eclipse, etc. Si estás usando Visual Studio Code, asegúrate de instalar las siguientes extensiones:

Java Extension Pack (de Microsoft)
Maven for Java

## Estructura del proyecto

```plaintext
[NombreDelProyecto]/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ejemplo/
│                   └── App.java
├── pom.xml
├── target/
└── README.md
```

## Compilación y ejecución

Para compilar y ejecutar el proyecto, sigue estos pasos:

Abre una terminal en la raíz del proyecto.

Ejecuta el siguiente comando para compilar el proyecto:
```bash
mvn clean install
```
Para ejecutar la aplicación, usa el siguiente comando:
```bash
mvn exec:java
```

# Booking App

Aplicación de gestión de reservas de viaje desarrollada en Java con Maven, siguiendo el patrón MVC (Modelo-Vista-Controlador) y una interfaz gráfica con Java Swing.

## Funcionalidades

- **Listado de reservas**: al arrancar, la ventana principal muestra en la izquierda los IDs de todas las reservas cargadas desde `bookings.json`.
- **Búsqueda por ID**: escribe un Booking ID y pulsa "Search" para ver sus servicios y el coste total, con descuento aplicado, en euros y en dólares.
- **Conversión de divisas en tiempo real**: el tipo de cambio EUR→USD se obtiene en cada consulta desde la API pública [open.er-api.com](https://www.exchangerate-api.com/docs/free) (`GET https://open.er-api.com/v6/latest/EUR`), sin necesidad de clave de API. Si la API no responde, se usa un valor de referencia interno para que la aplicación no se bloquee.
- **Crear reserva**: botón "Nueva reserva" abre un formulario para introducir el ID y añadir uno o varios servicios (nombre, cantidad, precio unitario, descuento). Se valida que el ID no exista ya. Al guardar, se sobrescribe `bookings.json` y se actualiza la lista en memoria.
- **Eliminar reserva**: escribe el ID en el campo de búsqueda y pulsa "Eliminar reserva". Se elimina de la lista en memoria y se sobrescribe `bookings.json`.
- Los cambios de creación/eliminación persisten entre reinicios de la aplicación.

## Estructura del proyecto

practica/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── Main.java
│   │   │   ├── MathUtils.java
│   │   │   ├── TextAnalyzer.java
│   │   │   ├── CurrencyConverter.java
│   │   │   ├── BookingRepository.java
│   │   │   ├── model/       (Booking, Service)
│   │   │   ├── view/        (BookingView, BookingFormDialog)
│   │   │   └── controller/  (BookingController)
│   │   └── resources/
│   │       ├── bookings.json
│   │       ├── logback.xml
│   │       ├── images/banner.png
│   │       └── *.puml (diagramas UML)
│   └── test/java/           (MathUtilsTestCase, TextAnalyzerTestCase, ServiceTestCase, BookingTestCase)
├── pom.xml
└── README.md



## Requisitos

- Java 17+
- Maven
- Conexión a internet (para el tipo de cambio en tiempo real)