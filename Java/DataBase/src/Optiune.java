public class Optiune {
    public int cod;
    public float punctaj;

    public Optiune(int cod,float punctaj)
    {
        this.cod = cod;
        this.punctaj = punctaj;
    }
    @Override
    public String toString() {
        return "Cod: "+cod+" Punctaj: "+punctaj;
    }
}
