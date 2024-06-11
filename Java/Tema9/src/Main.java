
public class Main {
    public static volatile boolean stopScenariu = false;
    public static void main(String[] args) {
        try {
            Muzeu muzeu = new Muzeu(30);
            Intrare intrare = new Intrare(5, muzeu);
            Iesire iesire = new Iesire(3, muzeu);
            iesire.start();
            intrare.start();
            Thread.sleep(50);
            stopScenariu = true;
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
