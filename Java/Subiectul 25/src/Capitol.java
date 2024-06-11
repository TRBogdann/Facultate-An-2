public class Capitol {
    private int cod;
    private int codSantier;
    private String denumire;
    private String unitate;
    private float pret;
    private float cantitate;

    public String getDenumire() {
        return denumire;
    }

    public int getCod() {
        return cod;
    }

    public float getPret() {
        return pret;
    }

    public String getUnitate() {
        return unitate;
    }

    public int getCodSantier() {
        return codSantier;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
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

    public void setCodSantier(int codSantier) {
        this.codSantier = codSantier;
    }

    public float getCantitate() {
        return cantitate;
    }

    public void setCantitate(float cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Capitol{" +
                "cod=" + cod +
                ", codSantier=" + codSantier +
                ", denumire='" + denumire + '\'' +
                ", unitate='" + unitate + '\'' +
                ", cantitate='" + cantitate + '\'' +
                ", pret=" + pret +
                '}';
    }

    public Capitol(int cod, float pret, int codSantier, String denumire, String unitate,float cantitate) {
        this.cod = cod;
        this.pret = pret;
        this.codSantier = codSantier;
        this.denumire = denumire;
        this.unitate = unitate;
        this.cantitate = cantitate;
    }
    public Capitol()
    {
        this.cod = 0;
        this.pret = 0;
        this.codSantier = 0;
        this.denumire = "";
        this.unitate = "";
        this.cantitate = 0;
    }
}
