# cola_operaciones

## Requerimiento Funcional

Se debe implementar un conjunto de servicios para calcular: suma, resta, multiplicación, división y potenciación de un conjunto de números.

Se espera tener mínimo tres servicios:
* Nueva sesión (opcional): El servidor crea un nuevo ambiente donde empezar a trabajar y retorna un identificador de este.
*	Agregar operando: se agrega un operando con el identificador retornado por el servicio anterior.
*	Realizar operación: se envía la operación a realizar (suma, resta, multiplicación, división, potenciación) con el identificador que se aplicará en cadena a todos los operados previamente agregados

Los servicios deben responder al siguiente flujo:
•	El cliente llama un servicio para empezar (Nueva sesión).
•	El cliente llama un servicio para agregar los operandos (Agregar operando), tantas veces como se quiera.
•	El cliente llama un servicio para ejecutar una operación matemática (Realizar operación).

Por ejemplo:
•	Cliente ------------------> nueva operación ----------------------> servidor
•	Cliente <------------------ id <---------------------- servidor
•	Cliente ------------------> 2 ----------------------> servidor
•	Cliente <------------------ ok<---------------------- servidor
•	Cliente ------------------> 3 ----------------------> servidor
•	Cliente <------------------ ok<---------------------- servidor
•	Cliente ------------------> suma ----------------------> servidor
•	Cliente <------------------ 5<---------------------- servidor



## Debe tener en cuenta que:
•	Se pueden agregar “n” operandos antes de realizar un calculo.
•	El resultado de un cálculo puede agregarse como operando para el siguiente calculo.
•	El diseño de los servicios, incluyendo el manejo de errores y formato de los datos queda a discreción del desarrollador.
•	Se debe entregar la documentación mínima para probar el funcionamiento de los servicios, algo básico para saber como hacer pruebas de que el ejercicio esta funcionando completo.

## Requerimientos No Funcionales
1.	Exponer la anterior lógica de negocio como un servicio Rest.
2.	Generar pruebas unitarias para los casos que considere necesarios. Puede usan un framework como junit, testNG, si no conoce ninguno puede usar clases planas en java desde un main.
3.	Desplegar la solución como un contenedor de Docker.
4.	Automatizar el testeo y la construcción de la imagen de Docker con un Pipeline de integración continua.
5.	La entrega se hará por medio de un repositorio GIT público de su elección (por ejemplo, github), el único requisito es que nos envíe la URL y que no esté protegido para que lo podamos clonar.   Por favor, provea todo el historial de commit y no solo un commit al final. Adjuntar las evidencias que considere necesarias para mostrar el funcionamiento del pipeline (screenshots, video, etc).
6.	Debe asegurarse que la solución sea thread-safety, ya que es un servidor y por lo tanto puede recibir peticiones concurrentes.
7.	Adicionar un archivo de texto ReadMe que indique la forma que el servicio elaborado sea de alta disponibilidad y elástico.

## Requerimientos opcionales
Los siguientes puntos son OPCIONALES y darán puntos extra, pero si no tiene tiempo no es requerido hacerlos.
•	Implemente una interfaz de usuario simple para ejecutar uno de los servicios y presentar en pantalla el resultado.
•	Si lo considera conveniente puede agregar más servicios para que aplicación funcione de una mejor forma (más eficiente, usable o versátil por parte de los clientes).
•	Implementar un sistema de logs, de forma que cuando se coloque en producción ayude a dar soporte del sistema.
•	Llevar la auditoria en base de datos (se puede implementar en cualquier motor) de lo que sucede en cada sesión, para una posterior consulta. Se deja a discreción del desarrollador el modelo de base de datos para la auditoria. Se debe habilitar por medio de un servicio Rest la consulta de esa información suponiendo que en una iteración subsiguiente del producto se va a implementar una interfaz gráfica para hacer auditoria de las transacciones. 
