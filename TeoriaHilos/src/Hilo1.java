public class Hilo1 extends Thread{
    private final int valor;

    public Hilo1(int valor){
        this.valor = valor;
    }
    @Override
    public void run(){
        if (valor == 1){
            for (int i = 0; i < 10; i++) {
                System.out.println("Hilo 1 " + i);
                try {
                    sleep(1000);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } else if (valor == 2) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hilo 2 " + i);
                try {
                    sleep(5000);
                } catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
