public class Santiere {
    private int cod;
    private String localitate;
    private String strada;
    private String obiectiv;
    private float valoare;

    public Santiere(int cod, String localitate, String strada, String obiectiv, float valoare) {
        this.cod = cod;
        this.localitate = localitate;
        this.strada = strada;
        this.obiectiv = obiectiv;
        this.valoare = valoare;
    }

    public Santiere() {
        this.cod  = 0;
        this.localitate ="";
        this.strada = "";
        this.obiectiv = "";
        this.valoare = 0.0f;
    }

    @Override
    public String toString() {
        return "Santiere{" +
                "cod=" + cod +
                ", localitate='" + localitate + '\'' +
                ", strada='" + strada + '\'' +
                ", obiectiv='" + obiectiv + '\'' +
                ", valoare=" + valoare +
                '}';
    }

    public int getCod() {
        return cod;
    }

    public float getValoare() {
        return valoare;
    }

    public String getLocalitate() {
        return localitate;
    }

    public String getObiectiv() {
        return obiectiv;
    }

    public String getStrada() {
        return strada;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public void setObiectiv(String obiectiv) {
        this.obiectiv = obiectiv;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }

}
