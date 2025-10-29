public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hilo1 hilo = new Hilo1(1);
        Hilo1 hilo2 = new Hilo1(2);
        hilo.start();
        hilo2.start();
    }
}
