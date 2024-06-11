public class Program implements  Comparable {
    int CodProgram;
    String CodFacultate;
    String Denumire;
    int NumarLocuri;

    public Program()
    {
        CodProgram = 0;
        CodFacultate = "";
        Denumire = "";
        NumarLocuri = 0;
    }
    public Program(int CodProgram,String CodFacultate,String Denumire,int NumarLocuri)
    {
        this.CodProgram = CodProgram;
        this.CodFacultate = CodFacultate;
        this.Denumire = Denumire;
        this.NumarLocuri = NumarLocuri;
    }

    public int getCodProgram() {
        return CodProgram;
    }

    public int getNumarLocuri() {
        return NumarLocuri;
    }

    public String getCodFacultate() {
        return CodFacultate;
    }

    public String getDenumire() {
        return Denumire;
    }

    public void setCodFacultate(String codFacultate) {
        CodFacultate = codFacultate;
    }

    public void setCodProgram(int codProgram) {
        CodProgram = codProgram;
    }

    public void setDenumire(String denumire) {
        Denumire = denumire;
    }

    public void setNumarLocuri(int numarLocuri) {
        NumarLocuri = numarLocuri;
    }

    @Override
    public String toString() {
        return String.format("Cod: %d Cod Facultate: %s Denumire: %s NumarLocuri: %d",
                this.CodProgram,this.CodFacultate,this.Denumire,this.NumarLocuri);
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.getNumarLocuri(),((Program)o).getNumarLocuri());
    }

}
