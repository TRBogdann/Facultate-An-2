public class Consumuri {
    public int cod;
    public float cantitate;
    public Consumuri(int cod,float cantitate)
    {
        this.cod = cod;
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Cod: "+cod+" Cantitate: "+cantitate;
    }
}
