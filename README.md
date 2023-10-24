# Challenge ONE Hotel Alura


_Este proyecto surgido del challege proporciado por Alura Latam. El programa permite una solución integral para la administración de un hotel o establecimiento de alojamiento. Esta aplicación aborda diversos aspectos clave, como la gestión de reservas, la información de los huéspedes, las tarifas y opciones de pago, y más. Proporciona a los administradores y empleados del hotel una plataforma eficiente para llevar un seguimiento de las reservas, ofrecer un servicio de calidad a los huéspedes y mantener un control preciso sobre la operación del hotel.

## Caracteristicas 🚀


_El programa permite a los usuarios convertir entre diferentes tipos de monedas, proporcionando una interfaz amigable donde pueden ingresar la cantidad de moneda a convertir_




### Funcionalidades 📋

* _Guardar reservas y huespedes_
* _Manejo de base de datos_
* _Manejo de errores_
* _Gestion de huespedes y reservas_
* _Opciones de eliminacion, edicion de datos_



### Como usar 🔧

_El proyecto necesita de una base de datos por los que debemos configurarla de la siguiente manera_

![Diagrama de la Base de Datos](https://user-images.githubusercontent.com/134095107/277779187-4662c783-0bad-4a05-a64b-c3eb12e06fa8.png)

_Tener en cuenta que nuestra calve forenea en huespedes no este restringida (podemos usar la opcion en cascada para que puede eliminar datos relacionados en continuidad) ya que no permitira eliminar valores relacionados y nos mandara un error, como en este caso las reservas con huespedes_

![Clave foranea](https://user-images.githubusercontent.com/134095107/277779822-ffaa0821-28db-4963-b631-9e836d2d1293.png)

_Con la base de datos lista solo debemos tener que configurar el archivor  __persistence.xml__  para colocar la conxion a la base de datos_


## Requisitos ⚙️

* _Java Runtime Environment 17_
* _Java SE Development Kit (JDK) 17_
* _Entorno de desarrollo Java (como Eclipse o NetBeans) o simplemente puedes compilar y ejecutar el código usando la línea de comandos_

## Construido con 🛠️


* _Lenguaje de Programación: Java_
* _Interfaz de Usuario: Librería Swing_
* _IDE NetBeans_


## Autores ✒️


* **Vincent Stephan** - *Todo el proyecto en general* 



## Licencia 📄

_Este proyecto está bajo la Licencia MIT._


