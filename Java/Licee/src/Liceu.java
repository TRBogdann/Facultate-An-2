import java.util.Map;

public class Liceu {
    private int cod;
    private String nume;
    private int numar;
    private Map<Integer,Integer> specializari;

    public int getCod() {
        return cod;
    }

    public String getNume() {
        return nume;
    }

    public int getNumar() {
        return numar;
    }

    public Map<Integer, Integer> getSpecializari() {
        return specializari;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public void setSpecializari(Map<Integer, Integer> specializari) {
        this.specializari = specializari;
    }

    @Override
    public String toString() {
        return "Liceu{" +
                "cod=" + cod +
                ", nume='" + nume + '\'' +
                ", numar=" + numar +
                ", specializari=" + specializari +
                '}';
    }

    public Liceu(int cod, String nume, int numar, Map<Integer, Integer> specializari) {
        this.cod = cod;
        this.nume = nume;
        this.numar = numar;
        this.specializari = specializari;
    }
}
