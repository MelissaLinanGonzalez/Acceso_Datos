import java.beans.Introspector;
import java.io.*;
import java.util.Formattable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        Scanner entrada = new Scanner(System.in);
        File productos = new File("Examen_22_10/src/productos.txt");
        File pedidos = new File("Examen_22_10/src/pedidos.txt");

        while (!salir){
            System.out.println(" ");
            System.out.print("""
                    *** Gestión de productos y pedidos ***
                    \t1. Añadir producto
                    \t2. Añadir pedido
                    \t3. Borrar producto
                    \t4. Calcular ingresos
                    \t5. Salir
                    Ingrese la opción deseada:\s""");
            int opcion = entrada.nextInt();
            switch (opcion){
                case 1:
                    entrada.nextLine();
                    System.out.println("*** Añadir producto ***");
                    agregarProducto(productos);
                    break;
                case 2:
                    entrada.nextLine();
                    System.out.println("*** Añadir pedido ***");
                    agregarPedido(productos, pedidos);
                    break;
                case 3:
                    entrada.nextLine();
                    System.out.println("*** Borrar productos ***");
                    borrarProducto(productos, pedidos);
                    break;
                case 4:
                    entrada.nextLine();
                    System.out.println("*** Calcular ingresos ***");
                    calcularIngreso(productos, pedidos);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese una opción entre 1 y 5");
                    break;
            }
        }
    }

    public static void agregarProducto(File archivoProductos){
        Scanner entrada = new Scanner(System.in);
        boolean existe = false;
        System.out.print("Introduce el ID del producto: ");
        int id = entrada.nextInt();

        try {
            BufferedReader lectorProductos = new BufferedReader(new FileReader(archivoProductos));
            String linea;
            while ((linea = lectorProductos.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) == id) {
                    existe = true;
                    throw new StockCollitionException("El ID del producto introducido ya está en uso");
                }
            }
            lectorProductos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (!existe){
            entrada.nextLine();
            System.out.print("Introduce el nombre del producto: ");
            String nombre = entrada.nextLine();
            System.out.print("Introduce el precio por unidad del producto: ");
            double precio = entrada.nextDouble();
            System.out.print("Introduce la cantidad de stock del producto: ");
            int stock = entrada.nextInt();

            try {
                BufferedWriter escritorProductos = new BufferedWriter(new FileWriter(archivoProductos, true));
                escritorProductos.write(id + "|" + nombre + "|" + precio + "|" + stock);
                escritorProductos.newLine();
                System.out.println("Producto añadido correctamente");
                escritorProductos.close();
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void agregarPedido(File archivoProductos, File archivoPedidos){
        Scanner entrada = new Scanner(System.in);
        boolean existeIdPedido = false;

        // Validamos que el ID del producto existe
        System.out.print("Introduce el ID del producto: ");
        int idProducto = entrada.nextInt();
        boolean existeProducto = comprobarIdProducto(idProducto, archivoProductos);
        if (existeProducto){
            System.out.print("Introduce el ID del pedido: ");
            int idPedido = entrada.nextInt();
            // Comprobamos que el idPedido no existe
            if (archivoPedidos.exists()){
                try {
                    BufferedReader lectorPedidos = new BufferedReader(new FileReader(archivoPedidos));
                    String linea;
                    while ((linea = lectorPedidos.readLine()) != null) {
                        String[] datos = linea.split("\\|");
                        if (Integer.parseInt(datos[0]) == idPedido) {
                            existeIdPedido = true;
                            throw new StockCollitionException("El ID del pedido introducido ya está en uso");
                        }
                    }
                    lectorPedidos.close();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (!existeIdPedido){
                System.out.print("Introduce la cantidad a comprar: ");
                int cantidad = entrada.nextInt();
                // Comprobamos que la cantidad a comprar sea igual o inferior a la cantidad de stock de dicho producto
                try {
                    boolean hayStock = comprobarStock(archivoProductos, cantidad, idProducto);
                    if (hayStock){
                        entrada.nextLine();
                        System.out.print("Introduce la fecha del pedido(DD-MM-AAAA): ");
                        String fecha = entrada.nextLine();

                        BufferedWriter escritorPedidos = new BufferedWriter(new FileWriter(archivoPedidos, true));
                        escritorPedidos.write(idPedido + "|" + idProducto + "|" + cantidad + "|" + fecha);
                        escritorPedidos.newLine();
                        System.out.println("Pedido añadido correctamente");
                        escritorPedidos.close();
                        restarStock(archivoProductos, cantidad, idProducto);

                    } else {
                        String nombre = devolverNombre(archivoProductos, idProducto);
                        throw new StockCollitionException("Stock insuficiente para el producto " + nombre);
                    }
                } catch (StockCollitionException e){
                    System.out.println("Error: " + e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println("El ID del producto introducido no existe");
        }
    }

    public static String devolverNombre(File archivoProducto, int idProducto){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoProducto));
            String linea;
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) == idProducto){
                    return datos[1];
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return "Producto no encontrado";
    }

    public static boolean comprobarStock(File archivoProductos, int cantidad, int idProducto){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
            String linea;
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) == idProducto && Integer.parseInt(datos[3]) >= cantidad){
                    return true;
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public static boolean comprobarIdProducto(int idProducto, File archivoProductos){
        if (archivoProductos.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (Integer.parseInt(datos[0]) == idProducto){
                        return true;
                    }
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }

    public static void restarStock(File archivoProductos, int cantidad, int idProducto){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
            String linea;
            StringBuilder sb = new StringBuilder();
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) == idProducto){
                    int stock = Integer.parseInt(datos[3]);
                    stock -= cantidad;
                    datos[3] = String.valueOf(stock);
                }
                sb.append(String.join("|", datos)).append("\n");
            }
            lector.close();

            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoProductos, false));
            escritor.write(sb.toString());
            System.out.println("Cantidad restada al stock");
            escritor.close();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void borrarProducto(File archivoProducto, File archivoPedidos){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca el ID del producto que desea eliminar: ");
        int idProducto = entrada.nextInt();

        try {
            BufferedReader lectorProductos = new BufferedReader(new FileReader(archivoProducto));
            StringBuilder sBuilderProductos = new StringBuilder();
            String linea;
            while ((linea = lectorProductos.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) != idProducto) {
                    sBuilderProductos.append(String.join("|", datos)).append("\n");
                }
            }
            lectorProductos.close();

            BufferedWriter escritorProductos = new BufferedWriter(new FileWriter(archivoProducto, false));
            escritorProductos.write(sBuilderProductos.toString());
            System.out.println("Producto borrado del archivo productos.txt");
            escritorProductos.close();

            BufferedReader lectorPedidos = new BufferedReader(new FileReader(archivoPedidos));
            StringBuilder sBuilderPedidos = new StringBuilder();
            String linea2;
            while ((linea2 = lectorPedidos.readLine()) != null){
                String[] datos2 = linea2.split("\\|");
                if (Integer.parseInt(datos2[1]) != idProducto){
                    sBuilderPedidos.append(String.join("|", datos2)).append("\n");
                }
            }
            lectorPedidos.close();
            BufferedWriter escritorPedidos = new BufferedWriter(new FileWriter(archivoPedidos,false));
            escritorPedidos.write(sBuilderPedidos.toString());
            System.out.println("Pedidos del producto elimano del archivo pedidos.txt");
            escritorPedidos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void calcularIngreso(File archivoProductos, File archivoPedidos){
        System.out.println("Ingresos totales:");
        double totalTodo = 0;
        try {
            BufferedReader lectorPedidos = new BufferedReader(new FileReader(archivoPedidos));
            String lina;
            while ((lina = lectorPedidos.readLine()) != null){
                String[] datos = lina.split("\\|");
                int idProducto = Integer.parseInt(datos[1]);
                String nombre = devolverNombre(archivoProductos, idProducto);
                double precio = devolverPrecio(archivoProductos, idProducto);
                int cantidad = Integer.parseInt(datos[2]);
                double total = cantidad * precio;
                System.out.println(idProducto + " - " + nombre + ": " + total + "€");
                totalTodo += total;
            }
            System.out.println("Suma total de todos los productos: " + totalTodo);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static double devolverPrecio(File archivoProductos, int idProducto){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
            String linea;
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) == idProducto){
                    return Double.parseDouble(datos[2]);
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
}
