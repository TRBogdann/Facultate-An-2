public class Cost {
    private int cod;
    private int proiect;
    private String denumire;
    private String unitate;
    private float cantitate;
    private float pret;

    public float getCantitate() {
        return cantitate;
    }

    public int getCod() {
        return cod;
    }

    public String getUnitate() {
        return unitate;
    }

    public float getPret() {
        return pret;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getProiect() {
        return proiect;
    }

    public void setCantitate(float cantitate) {
        this.cantitate = cantitate;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setUnitate(String unitate) {
        this.unitate = unitate;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setProiect(int proiect) {
        this.proiect = proiect;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "cod=" + cod +
                ", proiect=" + proiect +
                ", denumire='" + denumire + '\'' +
                ", unitate='" + unitate + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }

    public Cost(int cod, int proiect, String denumire, String unitate, float cantitate, float pret) {
        this.cod = cod;
        this.proiect = proiect;
        this.denumire = denumire;
        this.unitate = unitate;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public Cost() {
        this.cod = 0;
        this.proiect = 0;
        this.denumire = "";
        this.unitate = "";
        this.cantitate = 0;
        this.pret = 0;
    }
}

