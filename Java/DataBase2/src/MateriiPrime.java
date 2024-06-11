import java.util.Calendar;

public class MateriiPrime {
    private int cod;
    private String denumire;
    private float cantitate;
    private float pret;
    private String unitate;

    public MateriiPrime()
     {
        cod = 0 ;
        denumire = "";
        cantitate = 0.0f;
        pret = 0.0f;
        unitate = "";
     }
     public MateriiPrime(int cod,String denumire,float cantitate,float pret,String unitate){
        this.unitate = unitate;
        this.cod =cod;
        this.cantitate = cantitate;
        this.pret = pret;
        this.denumire = denumire;
     }

    public String getDenumire() {
        return denumire;
    }

    public float getCantitate() {
        return cantitate;
    }

    public float getPret() {
        return pret;
    }

    public int getCod() {
        return cod;
    }

    public String getUnitate() {
        return unitate;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setCantitate(float cantitate) {
        this.cantitate = cantitate;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setUnitate(String unitate) {
        this.unitate = unitate;
    }

    @Override
    public String toString() {
        return String.format("Cod: %d Nume: %s Cantitate: %f%s Pret:%f ",this.cod,
                this.denumire,this.cantitate,this.unitate,this.pret);
    }
}
