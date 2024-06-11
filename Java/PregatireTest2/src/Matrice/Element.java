package Matrice;

import java.io.Serializable;

public class Element implements Comparable, Serializable
{
    Element(int col,int linie,float data)
    {
        this.col = col;
        this.linie = linie;
        this.data = data;
    }

    Element()
    {
        this.col = -1;
        this.linie = -1;
        this.data = 0.0f;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public float getData() {
        return data;
    }

    public int getLinie() {
        return linie;
    }

    public void setData(float data) {
        this.data = data;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    @Override
    public boolean equals(Object obj) {
        Element e =  (Element) obj;
        return e.data == this.data && e.linie == this.linie && e.col == this.col;
    }



    @Override
    public String toString() {
        return "["+linie+"]["+col+"]: "+data;
    }

    private int col;
    private int linie;
    private float data;

    @Override
    public int compareTo(Object o) {
        Element e = (Element)o;
        return Float.compare(this.data,e.data);
    }
}

