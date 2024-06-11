public class Program {
    private int cod;
    private String denumire;
    private int nrLocuri;

    public String getDenumire() {
        return denumire;
    }

    public int getCod() {
        return cod;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    @Override
    public String toString() {
        return "Program{" +
                "cod=" + cod +
                ", denumire='" + denumire + '\'' +
                ", nrLocuri=" + nrLocuri +
                '}';
    }

    public Program(int cod, String denumire, int nrLocuri) {
        this.cod = cod;
        this.denumire = denumire;
        this.nrLocuri = nrLocuri;
    }
}
