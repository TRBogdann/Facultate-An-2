import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Candidat {
    String nume;
    List<Optiune> optiuni;

    public String getNume() {
        return nume;
    }

    public List<Optiune> getOptiuni() {
        return optiuni;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setOptiuni(List<Optiune> optiuni) {
        this.optiuni = optiuni;
    }
    public Candidat()
    {
        this.nume = "";
        this.optiuni = new ArrayList<>();
    }
    public Candidat(String nume,List<Optiune> optiuni)
    {
        this.optiuni = optiuni;
        this.nume = nume;
    }

    public int getNrOptiuni()
    {
        return optiuni.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Nume: " + nume + " Optiuni:\n");
        for(Optiune o:optiuni)
        {
            str.append(o.toString()).append("\n");
        }
        return str.toString();
    }
}
