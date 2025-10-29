import static java.lang.Thread.sleep;

public class HiloRunnable implements Runnable{

    private final int valor;
    private final String nombre;

    public HiloRunnable(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        if (valor == 1){
            for (int i = 0; i < 10; i++) {
                System.out.println("Hilo " + this.nombre + ": " + + i);
                try {
                    sleep(1000);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } else if (valor == 2) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hilo " + this.nombre + ": " + i);
                try {
                    sleep(5000);
                } catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
