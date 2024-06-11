import java.util.ArrayList;
import java.util.List;

public class Produs {
    private int cod;
    private String denumire;
    private float cantitate;
    private String unitate;
    private List<Consumuri> c;

    public void setCantitate(float cantitate) {
        this.cantitate = cantitate;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setUnitate(String unitate) {
        this.unitate = unitate;
    }

    public void setC(List<Consumuri> c) {
        this.c = c;
    }

    public List<Consumuri> getC() {
        return c;
    }

    public float getCantitate() {
        return cantitate;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getUnitate() {
        return unitate;
    }

    public int getCod() {
        return cod;
    }
    public Produs()
    {
        this.cod = 0;
        this.cantitate = 0.0f;
        this.unitate = "";
        this.c = new ArrayList<>();
        this.denumire = "";
    }
    public Produs(int cod,String denumire,float cantitate,String unitate,List<Consumuri> c)
    {
        this.cod = cod;
        this.denumire = denumire;
        this.c = c;
        this.unitate = unitate;
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
       StringBuilder s = new StringBuilder(String.format("Cod: %d Denumire: %s Cantitate: %f%s Consumuri:\n",this.cod,this.denumire,this.cantitate,this.unitate));
       for(Consumuri consum:c)
       {
           s.append(consum.toString()).append("\n");
       }
       return s.toString();
    }
}
