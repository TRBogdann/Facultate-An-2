import java.io.Serializable;

public class Trotineta implements Comparable, Serializable {

    Trotineta()
    {
        id ="";
        distanta = 0.0f;
        vitezaMaxima =0.0f;
        vitezaMedie = 0.0f;
    }
    Trotineta(String id ,float distanta,float vitezaMedie,float vitezaMaxima)
    {
        this.id = id;
        this.vitezaMedie = vitezaMedie;
        this.distanta = distanta;
        this.vitezaMaxima = vitezaMaxima;
    }

    @Override
    public String toString() {
        return "Id: "+id+" Distanta: "+Float.toString(distanta)+" Viteza Medie: "+Float.toString(vitezaMedie)+" Viteza Maxima: "+Float.toString(vitezaMaxima);
    }

    public float getDistanta() {
        return distanta;
    }

    @Override
    public boolean equals(Object obj) {
        Trotineta t = (Trotineta)obj;
        return Math.abs(t.distanta-distanta)<=10;
    }



    public float getVitezaMaxima() {
        return vitezaMaxima;
    }

    public String getId() {
        return id;
    }

    public float getVitezaMedie() {
        return vitezaMedie;
    }

    public void setDistanta(float distanta) {
        this.distanta = distanta;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVitezaMaxima(float vitezaMaxima) {
        this.vitezaMaxima = vitezaMaxima;
    }

    public void setVitezaMedie(float vitezaMedie) {
        this.vitezaMedie = vitezaMedie;
    }

    private String id;
    private float distanta;
    private float vitezaMedie;
    private float vitezaMaxima;

    @Override
    public int compareTo(Object o) {
        Trotineta t = (Trotineta) o;
        if(this.equals(t))
            return 0;
        else return Float.compare(this.distanta,t.distanta);
    }
}
