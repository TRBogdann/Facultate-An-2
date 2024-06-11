public class Produs {
    private int cod;
    private String denumire;
    private  float pret;

    public int getCod() {
        return cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    public Produs()
    {
        this.pret = 0;
        this.cod = 0;
        this.denumire = "";
    }
    public Produs(int cod,String denumire,float pret)
    {
        this.cod = cod;
        this.denumire = denumire;
        this.pret = pret;
    }

    @Override
    public String toString() {
        return String.format("Cod:%d Denumire:%s Pret:%.2f\n",this.cod,this.denumire,this.pret);
    }
}
