# Challenge ONE Hotel Alura


_Este proyecto surgido del challege proporciado por Alura Latam. Este proyecto tiene el proposito de afianciar nuestro conocimiento en el uso de una base de datos simulado en la vida real

## Caracteristicas 🚀

:El programa permite una solución integral para la administración de un hotel o establecimiento de alojamiento. Esta aplicación aborda diversos aspectos clave, como la gestión de reservas, la información de los huéspedes, las tarifas y opciones de pago, y más. Proporciona a los administradores y empleados del hotel una plataforma eficiente para llevar un seguimiento de las reservas, ofrecer un servicio de calidad a los huéspedes y mantener un control preciso sobre la operación del hotel.

_Posee una sistema de Login pero solo esta integraado los datos dentro del proytecto en vez de una consulta a una base de datos para mantener la practisidad ya que no es el foco central del proyecto_




### Funcionalidades 📋

* _Guardar reservas y huespedes_
* _Manejo de base de datos_
* _Manejo de errores_
* _Gestion de huespedes y reservas_
* _Opciones de eliminacion, edicion de datos_



### Como usar 🔧

_El proyecto necesita de una base de datos por los que debemos configurarla de la siguiente manera_

![Diagrama de la Base de Datos](https://user-images.githubusercontent.com/134095107/277779187-4662c783-0bad-4a05-a64b-c3eb12e06fa8.png)

_Tener en cuenta que nuestra calve forenea en huespedes no este restringida (podemos usar la opcion en cascada para que puede eliminar datos relacionados en continuidad) ya que no permitira eliminar valores relacionados y nos mandara un error, como en este caso las reservas con huespedes. En este caso en MySqL_ 

![Clave foranea](https://user-images.githubusercontent.com/134095107/277779822-ffaa0821-28db-4963-b631-9e836d2d1293.png)

_Con la base de datos lista solo debemos tener que configurar el archivor  __persistence.xml__  para colocar la conxion a la base de datos_

![Persistence](https://user-images.githubusercontent.com/134095107/277784218-34569699-2ab5-46ee-9b08-3f6f0743ef80.png)

_Tener en cuenta la configuracion del build path para evitar errores en las dependencias_

![Buildpath](https://user-images.githubusercontent.com/134095107/277785778-8362c0d5-6a08-4489-9192-591e1e2e7cc2.png)

_Login por defecto para acceder_

![Login](https://user-images.githubusercontent.com/134095107/277787701-5bc36fe0-d857-4291-908e-5065364add69.png)
Usuario = admin
Contraseña = admin

## Requisitos ⚙️

* _Java Runtime Environment 17_
* _Java SE Development Kit (JDK) 17_
* _Entorno de desarrollo Java (como Eclipse o NetBeans)
* _Base de Datos: MySQL_

  

## Construido con 🛠️


* _Lenguaje de Programación: Java_
* _Interfaz de Usuario: Librería Swing_
* _IDE Eclipse_
*  _JPA: Hibernate_
*  _Lombok_
  


## Autores ✒️


* **Vincent Stephan** - *Todo el proyecto en general* 



## Licencia 📄

_Este proyecto está bajo la Licencia MIT._


