import java.util.Map;

public class Candidat {
    private String nume;
    private Map<Integer,Double> optiuni;

    public String getNume() {
        return nume;
    }

    public Map<Integer, Double> getOptiuni() {
        return optiuni;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setOptiuni(Map<Integer, Double> optiuni) {
        this.optiuni = optiuni;
    }

    public Candidat(String nume, Map<Integer, Double> optiuni) {
        this.nume = nume;
        this.optiuni = optiuni;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Candidat: " + this.nume + "\nOptiuni:\n");
        for(var e:optiuni.entrySet())
        {
            str.append("Optiune: ").append(e.getKey()).append(" Nota:").append(e.getValue()).append("\n");
        }
        str.append("\n");
        return str.toString();
    }
}
