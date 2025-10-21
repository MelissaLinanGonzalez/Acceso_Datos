# Gestión de inventario de productos
Crear un programa en Java que gestione un pequeño inventario de productos de una tienda. El programa debe mostrar un menú con las siguientes opciones:
1. Añadir producto.
2. Buscar producto por nombre
3. Modificar el precio de un producto
4. Calcular el valor total del inventario
---
1. Solicitar al usuario el nombre, categoría, precio y cantidad.
Guardar los datos en un fichero llamado productos.txt con el siguiente formato: <br>
ID|Nombre|Categoría|Precio|Cantidad;
- El ID será autonumérico y continuará el último existente en el archivo.
- Si el archivo no existe, créalo.
---
2. Buscar producto por nombre. Pide al usuario el nombre de un producto y busca en el fichero.
- Si existe, muestra todos sus datos.
- Si no existe, lanza una excepción controlada: "El producto >nombre< no se encuentra en el archivo."
---
3. Modificar el precio de un producto. Permite introducir el nombre de un producto y un nuevo precio. El programa debe leer el archivo, actualizar el precio y reescribir el contenido actualizado en el mismo fichero.
---
4. Calcular el valor total del inventario. Debe sumar (precio * cantidad) de todos los productos y mostrar el valor total.
