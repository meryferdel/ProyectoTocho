# Proyecto colaborativo FFEOE - COMPANY DB

Aplicación de escritorio desarrollada en **Java** con **Swing** para trabajar con la base de datos **COMPANY DB** mediante una arquitectura por capas.

El objetivo del proyecto es resolver diferentes ejercicios formativos relacionados con el acceso, consulta y modificación de datos en una base de datos relacional.

## Tecnologías utilizadas

- Java
- Java Swing
- NetBeans
- Maven
- MariaDB
- HeidiSQL
- Git y GitHub

## Arquitectura del proyecto

El proyecto se organiza en tres capas:

- **Presentación:** ventanas e interfaz gráfica con Swing.
- **Negocio / control:** validaciones y lógica de funcionamiento.
- **Persistencia:** conexión con la base de datos y clases DAO.

La capa de persistencia utiliza el patrón **DAO** para realizar operaciones sobre la base de datos y gestionar los posibles errores mediante una excepción propia `DAOException`.

## Funcionalidades del proyecto

El proyecto debe implementar al menos **4 de los 6 ejercicios formativos** propuestos:

- Alta de nueva categoría de producto.
- Alta de nuevo producto.
- Alta de nuevo almacén.
- Listado del inventario de un almacén.
- Aplicar descuento por categoría.
- Traspaso y cierre de un almacén.

## Estructura recomendada

```text
src/
├── aplicacion/
│   └── Main.java
├── persistencia/
│   ├── ConexionBD.java
│   ├── DAOException.java
│   └── ...
├── negocio/
│   ├── MainController.java
│   └── ejercicio1/
│       └── Ejer1Controller.java
└── presentacion/
    ├── bienvenida/
    │   └── VentanaBienvenida.java
    └── ejercicio1/
        └── VentanaEjer1.java
