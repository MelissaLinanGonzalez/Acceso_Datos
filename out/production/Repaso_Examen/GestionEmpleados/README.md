# 🧾 Proyecto: Gestión de Empleados con Ficheros

### 📅 Fecha
Octubre 2025

### 👩‍💻 Autora
Melissa Liñán

---

## 📘 Descripción general

El objetivo de este proyecto es desarrollar un programa en **Java** que permita gestionar los empleados de una empresa utilizando **archivos de texto** como almacenamiento de datos.

El programa incluye funcionalidades para **añadir**, **buscar**, **modificar**, y **realizar cálculos estadísticos** sobre la información de los empleados.

Se trabaja exclusivamente con clases del paquete `java.io` (`FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`) y colecciones básicas (`StringBuilder`, `ArrayList`, etc.).

---

## 🎯 Objetivos de aprendizaje

- Practicar la **lectura y escritura de ficheros de texto**.
- Trabajar con **datos estructurados** mediante separadores (`|` y `;`).
- Aplicar la **manipulación de cadenas** y el uso de `StringBuilder`.
- Controlar excepciones (`IOException`, `NumberFormatException`, etc.).
- Implementar un **menú interactivo** con múltiples opciones.

---

## 🧩 Funcionalidades del programa

El programa muestra un menú con las siguientes opciones:

1. Añadir empleado
2. Buscar empleado por DNI
3. Modificar salario de un empleado
4. Mostrar empleados con salario superior a una cantidad
5. Calcular el salario medio
6. Salir

---
### 1️⃣ Añadir empleado
Solicitar nombre, apellidos, DNI, puesto y salario. Se debe guardar en el archivo empleados.txt con el siguiente formato:<br>
ID|Nombre|Apellidos|DNI|Puesto|Salario

El ID debe ser autoincrementable, continuando con el útlimo existente.

---

### 2️⃣ Buscar Empleado por DNI
Solicita el DNI del empleado y busca su información, si lo encuentra debe aparecer de la siguiente forma: <br>
ID: 2<br>
Nombre: Mario Ruiz<br>
Puesto: Diseñador<br>
Salario: 1800€<br>

Si no lo encuentra debe mostrar un mensaje de que el empleado con DNI 11111111A no se encuentra en el archivo.

---

### 3️⃣ Modificar salario de un empleado
Permite actualizar el salario de un empleado ya registrado. Piendo el DNI del empleado al que se desea modiciar el salario y el nuevo salario.

---

### 4️⃣ Mostrar empleados con salario superior a una cantidad
Solicita una cantidad y muestra todos los empleados cuyo salario sea mayor.<br>
Salida esperada:<br>
Empleados con salario superior a 2000€<br>
Laura Gómez (Desarrolladora) - 2500€<br>
Sara López (Analista) - 2100€

---

### 5️⃣ Calcular salario medio
Calcula el promedio de los salarios de todos los empleados registrados.

---