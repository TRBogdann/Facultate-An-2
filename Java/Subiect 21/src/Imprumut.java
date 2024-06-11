public class Imprumut {
    private String nume;
    private String cota;
    private int zile;

    public String getCota() {
        return cota;
    }

    public String getNume() {
        return nume;
    }

    public int getZile() {
        return zile;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setZile(int zile) {
        this.zile = zile;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "nume='" + nume + '\'' +
                ", cota='" + cota + '\'' +
                ", zile=" + zile +
                '}';
    }

    public Imprumut(String nume, String cota, int zile) {
        this.nume = nume;
        this.cota = cota;
        this.zile = zile;
    }

    public Imprumut() {
        this.cota = "";
        this.zile = 0;
        this.nume = "";
    }
}
