# Prueba práctica Ficheros - 22/10/2025

---
Crear un programa Java que gestione pedidos simples usando ficheros de texto.
Se trabajará con dos archivos:
- productos.txt (catálogo de productos)
- pedidos.txt (pedidos realizados)

---

### 1. Cargar productos
Crea o actualiza el fichero productos.txt con el siguiente formato: <br>
ID|Nombre|Precio|Stock; <br>
Se pedirán todos los datos por teclado. En caso de que el ID del producto exista - se
lanzará una excepción controlada llamada StockCollitionException (definida por el usuario).

---

### 2. Cargar Pedidos
Cada pedido se guarda en pedidos.txt con el formato: <br>
IDPedido|IDProducto|Cantidad|Fecha(DD-MM-AAAA);
- Validar que exista el ID de producto.
- Verificar que haya suficiente stock antes de registrar.
- Si el pedido es válido, restar la cantidad al stock del producto en productos.txt.
- Si no hay stock suficiente → lanzar excepción controlada - StockCollitionException
  con mensaje "Stock insuficiente para el producto <nombre>."
- No se puede repetir el ID del pedido. En caso de introducir un ID de pedido existente
  se lanzará una excepción controlada llamada StockCollitionException.

--- 

### 3. Borrar producto 
Borrar producto se encargará de borrar todos los pedidos asociados a ese producto y el producto en el archivo productos.txt.

--- 

### 4. Calcular ingresos
Calcula para cada producto los ingresos totales (unidades vendidas x precio). La salida
tiene el siguiente formato: <br>
ID - Nombre producto : Ingresos € <br>
Ejemplo: <br>
productos.txt <br>
1|Teclado|10|20; <br>
pedidos.txt <br>
1|1|4|21-10-2025 <br>
2|1|4|21-10-2025 <br>
-______- <br>
Salida: <br>
Ingresos totales: <br>
1 - Teclado : 80 €