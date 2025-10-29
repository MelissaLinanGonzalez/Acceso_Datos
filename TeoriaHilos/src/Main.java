public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hilo1 hilo = new Hilo1(1);
        Hilo1 hilo2 = new Hilo1(2);

        /*
        * La diferencia entre llamar al metodo start() y al metodo run(), es que start() inicia el nuevo objeto Thread
        * y llama al metodo run() concurrentemente, se ejecutarian ambos hilos a la vez. Sin embargo, si se llama al
        * metodo run() directamente, es como si llamásemos a un metodo de una clase cualquiera, se ejecutaría primero
        * el run() del hilo y cuando finalizace se ejecutará el del hilo2.
        * */
        hilo.start();
        hilo2.start();

//        hilo.run();
//        hilo2.run();

        /*
        * Se instancia un objeto Thread y se le pasa por el constructor la clase en la que se ha implementado la
        * interfaz de Runnable, para así poder llamar al metodo start().
        * */

        Thread hilo3 = new Thread(new HiloRunnable(1, "Runnable 1"));
        Thread hilo4 = new Thread(new HiloRunnable(2, "Runnable 2"));

        hilo3.start();
        hilo4.start();


    }
}
