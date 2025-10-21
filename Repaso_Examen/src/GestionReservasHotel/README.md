# 🏨 Gestión de reservas de hotel

---
Crear un programa en Java que permita gestionar las reservas de un hotel. El programa tendrá un menú con las siguientes opciones.
1. Añadir reserva
2. Buscar reserva por nombre
3. Modificar los días de la estancia
4. Calcular el total a pagar de una reserva
5. Salir
---
1. Añadir reserva. El programa debe pedir los siguientes datos por consola:
- Nombre del cliente.
- Número de habitación.
- Días de estancia.
- Precio por noche

Y almacenarlos en un archivo llamado reservas.txt con el siguiente formato: <br>

ID|Nombre|Habitación|Días|PrecioPorNoche

El ID será autonumérico y consecutivo, continuando el último del archivo. Si el archivo no existe, debe crearse automáticamente.

---
2. Buscar reserva por nombre. Pide el nombre del cliente y busca en el archivo. Si existe, muestra sus datos completos. Si no existe, lanza una excepción controlada con el mensaje: <br>
"La reserva de <nombre> no se encuentra en el archivo"
---
3. Modificar los días de estancia. Permite introducir el nombre del cliente y los nuevos días de estancia. Luego, lee todo el archivo, modifica solo esa línea y vuelve a sobreescribir el archivo completo.
---
4. Pide el nombre del cliente, busca su registro y calcula el precio total de su estancia.