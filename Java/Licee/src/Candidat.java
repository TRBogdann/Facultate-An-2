import java.util.Map;

public class Candidat {
    private int cod;
    private float medie;
    private String nume;
    Map<Integer,Integer> optiuni;

    public int getCod() {
        return cod;
    }

    public float getMedie() {
        return medie;
    }

    public String getNume() {
        return nume;
    }

    public Map<Integer, Integer> getOptiuni() {
        return optiuni;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    public void setOptiuni(Map<Integer, Integer> optiuni) {
        this.optiuni = optiuni;
    }

    @Override
    public String toString() {
        return "Candidat{" +
                "cod=" + cod +
                ", medie=" + medie +
                ", nume='" + nume + '\'' +
                ", optiuni=" + optiuni +
                '}';
    }

    public Candidat(int cod, float medie, String nume, Map<Integer, Integer> optiuni) {
        this.cod = cod;
        this.medie = medie;
        this.nume = nume;
        this.optiuni = optiuni;
    }
}
