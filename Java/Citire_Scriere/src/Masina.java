import java.io.Serializable;

public class Masina implements Serializable {
    private String nume;
    private int serie;
    private float cost;
    private String numar;
    public Masina()
    {
        nume = "";
        serie = 0;
        cost = 0.0f;
        numar = "";
    }
    public Masina(int serie,String nume,float cost,String numar)
    {
        this.serie =serie;
        this.nume = nume;
        this.cost = cost;
        this.numar = numar;
    }

    public String getNume() {
        return nume;
    }

    public float getCost() {
        return cost;
    }

    public int getSerie() {
        return serie;
    }

    public String getNumar() {
        return numar;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return String.format("Serie:%d Nume:%s Numar%s Cost:%f",this.serie,this.nume,this.numar,this.cost);
    }
}
