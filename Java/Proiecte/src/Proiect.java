public class Proiect {
    private int cod;
    private String denumiure;
    private String descriere;
    private String manager;
    private float buget;
    public int getCod() {
        return cod;
    }

    public float getBuget() {
        return buget;
    }

    public String getDenumiure() {
        return denumiure;
    }

    public String getManager() {
        return manager;
    }

    public void setBuget(float buget) {
        this.buget = buget;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDenumiure(String denumiure) {
        this.denumiure = denumiure;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Proiect{" +
                "cod=" + cod +
                ", denumiure='" + denumiure + '\'' +
                ", descriere='" + descriere + '\'' +
                ", manager='" + manager + '\'' +
                ", buget=" + buget +
                '}';
    }

    public Proiect(int cod, String denumiure, String descriere, String manager, float buget) {
        this.cod = cod;
        this.denumiure = denumiure;
        this.descriere = descriere;
        this.manager = manager;
        this.buget = buget;
    }

    public Proiect() {
        this.cod = 0;
        this.denumiure = "";
        this.manager = "";
        this.buget = 0;
        descriere = "";
    }
}
