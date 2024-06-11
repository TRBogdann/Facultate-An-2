public class Carte {
    private String cota;
    private String nume;
    private String autor;
    private int an;

    public String getNume() {
        return nume;
    }

    public int getAn() {
        return an;
    }

    public String getAutor() {
        return autor;
    }

    public String getCota() {
        return cota;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Carte(int an, String autor, String nume, String cota) {
        this.an = an;
        this.autor = autor;
        this.nume = nume;
        this.cota = cota;
    }

    @Override
    public String toString() {
        return "Carti{" +
                "cota='" + cota + '\'' +
                ", nume='" + nume + '\'' +
                ", autor='" + autor + '\'' +
                ", an=" + an +
                '}';
    }
    public Carte()
    {
        an =0;
        autor = "";
        nume = "";
        cota = "";
    }
}
