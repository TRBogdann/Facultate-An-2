package Geometrie;

import java.io.Serializable;

public class Punct implements Comparable, Serializable {
    private String etichetaFigura;
    private String etichetaPunct;
    private double y;
    private double x;
    Punct()
    {
        etichetaFigura ="";
        etichetaPunct ="";
        x=0.0f;
        y=0.0f;
    }
    Punct(String etichetaFigura,String etichetaPunct,double x,double y)
    {
        this.x = x;
        this.y = y;
        this.etichetaPunct  = etichetaPunct;
        this.etichetaFigura = etichetaFigura;
    }

    @Override
    public String toString() {
        return "Figura: "+etichetaFigura+" Punct: "+etichetaPunct+" Coordonate: ("+x+","+y+")";
    }

    public double getX() {
        return x;
    }

    public void setEtichetaFigura(String etichetaFigura) {
        this.etichetaFigura = etichetaFigura;
    }

    public double getY() {
        return y;
    }

    public String getEtichetaPunct() {
        return etichetaPunct;
    }

    public void setEtichetaPunct(String etichetaPunct) {
        this.etichetaPunct = etichetaPunct;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getEtichetaFigura() {
        return etichetaFigura;
    }

    public double distance()
    {
        return Math.sqrt(x*x+y*y);
    }


    @Override
    public int compareTo(Object o) {
        Punct p = (Punct)o;
        return Double.compare(this.distance(),p.distance());
    }
}
