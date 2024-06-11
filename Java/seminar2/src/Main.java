
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static enum  Categorie{
        TERENURI(211),
        CONSTRUCTII(212),
        ECHIPAMENTE(2131),
        TRANSPORT(2133),
        MOBILIER(214),
        NECUNOSCUT(0);

        private int simbol;

            Categorie(int simbol)
            {
                this.simbol=simbol;
            }

        public int getSimbol() {
            return simbol;
        }

        public static Categorie getByName(String name)
        {
            switch(name)
            {
                case "constructii":
                    return Categorie.CONSTRUCTII;

                case "terenuri":
                    return Categorie.TERENURI;

                case "mijloc_transport":
                    return Categorie.TRANSPORT;

                case "mobilier":
                    return Categorie.MOBILIER;

                case "echipamente":
                    return Categorie.ECHIPAMENTE;

                default:
                    return Categorie.NECUNOSCUT;
            }
        }
    }

    public static class Locatie implements Cloneable,Serializable
    {
        public Locatie() {
            this.denumire = "";
            this.adresa = "" ;
        }

        public Locatie(String denumire,String adresa)
        {
            this.denumire=denumire;
            this.adresa=adresa;
        }

        public void setAdresa(String adresa) {
            this.adresa = adresa;
        }

        public void setDenumire(String denumire) {
            this.denumire = denumire;
        }

        public String getDenumire()
        {
            return this.denumire;
        }

        public String getAdresa()
        {
            return this.adresa;
        }

        private String denumire;
        private String adresa;

        @Override
        public Locatie clone() {
            try {
                Locatie clone = (Locatie) super.clone();
                clone.denumire=this.denumire;
                clone.adresa=this.adresa;
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        @Override
        public String toString()
        {
            return "Denumire Locatie: "+ this.denumire + " Adresa: "+ this.adresa+"\n";
        }
    }
    public static interface iAmortizare
    {
        public float calculUzura();
        public float amortizare();
    }

    public static abstract class ElementPatrimonial implements Comparable,Serializable
    {
        public ElementPatrimonial()
        {
            this.denumire="";
            this.dataAchizitiel=new Date();
            this.locatie=new Locatie();
            this.valoare=0.0f;
            this.nrInventar=0L;
        }

        public ElementPatrimonial(String denumire,float valoare,long nrInventar,Date dataAchizitiel,Locatie locatie)throws Exception
        {
            this.denumire=denumire;
            this.dataAchizitiel= (Date)dataAchizitiel.clone();
            this.locatie=(Locatie)locatie.clone();
            this.valoare=valoare;
            this.nrInventar=nrInventar;
        }



        public int CompareByDate(ElementPatrimonial e)
        {
            return dataAchizitiel.compareTo(e.dataAchizitiel);
        }



        public void setDenumire(String denumire) {
            this.denumire = denumire;
        }

        public void setLocatie(Locatie locatie) {
            this.locatie = (Locatie)locatie.clone();
        }

        public void setNrInventar(long nrInventar) {
            this.nrInventar = nrInventar;
        }

        public void setDataAchizitiel(Date dataAchizitiel) {
            this.dataAchizitiel = (Date)dataAchizitiel.clone();
        }

        public void setValoare(double valoare) {
            this.valoare = valoare;
        }

        public String getDenumire()
        {
            return this.denumire;
        }

        public long getNrInventar() {
            return nrInventar;
        }

        public double getValoare() {
            return valoare;
        }

        public Date getDataAchizitiel()
        {
            return (Date)this.dataAchizitiel.clone();
        }

        public Locatie getLocatie()
        {
            return (Locatie)this.locatie.clone();
        }

        @Override
        public String toString()
        {
            return "Denumire Obiect: "+denumire+"\nNrInvertar: "+Long.toString(nrInventar)+"\nValoare: "+Double.toString(valoare)
                    +"\nData:"+dataAchizitiel.toString()+"\n"+locatie.toString()+"\n";
        }

        @Override
        public int compareTo(Object o)
        {
            return Long.compare(this.nrInventar,((ElementPatrimonial)o).nrInventar);
        }


        private String denumire;
        private long nrInventar;
        private double valoare;
        private Date dataAchizitiel;
        private Locatie locatie;
    }

    public static class MijlocFix extends  ElementPatrimonial implements iAmortizare,Cloneable
    {
        public MijlocFix()
        {
            super();
            this.categorie=Categorie.NECUNOSCUT;
            this.durataNormata=0;
        }

        public MijlocFix(String denumire,float valoare,long nrInventar,Date dataAchizitiel,Locatie locatie,Categorie categorie,int durataNormata) throws Exception {
            super(denumire,valoare,nrInventar,dataAchizitiel,locatie);
            this.categorie=categorie;
            this.durataNormata=durataNormata;
            if(this.durataNormata<0 || this.durataNormata>100)
                throw new Exception("Durata Normata trebuie sa fie in intervalul [0,100]");
        }

        public Categorie getCategorie() {
            return categorie;
        }

        public int getDurataNormata() {
            return durataNormata;
        }

        public void setDurataNormata(int durataNormata)throws  Exception
        {
            this.durataNormata=durataNormata;
            if(this.durataNormata<0 || this.durataNormata>100)
                throw new Exception("Durata Normata trebuie sa fie in intervalul [0,100]");

        }

        public void setCategorie(Categorie categorie)
        {
            this.categorie=categorie;
        }

        @Override
        public float calculUzura()
        {
            return (float)(this.getValoare()/durataNormata);
        }

        @Override
        public float amortizare()
        {
            return 0.0f;
        }

        @Override
        public String toString()
        {
            return super.toString()+"Categorie: "+Integer.toString(categorie.getSimbol())+"\nDurata: "+Integer.toString(durataNormata)+"\n";
        }

        private  Categorie categorie;
        private int durataNormata;

        @Override
        public MijlocFix clone() {
            try {
                MijlocFix clone = (MijlocFix) super.clone();
                clone.setLocatie(this.getLocatie());
                clone.setDataAchizitiel((Date)this.getDataAchizitiel().clone());
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public static class ObiectInventar extends ElementPatrimonial implements iAmortizare
    {
        public ObiectInventar()
        {
            super();
            gestionar="";
        }

        @Override
        public float calculUzura()
        {
            return 0.0f;
        }

        @Override
        public float amortizare()
        {
            return 0.0f;
        }
        public ObiectInventar(String denumire,float valoare,long nrInventar,Date dataAchizitiel,Locatie locatie,String gestionar) throws Exception
        {
            super(denumire,valoare,nrInventar,dataAchizitiel,locatie);
            this.gestionar=gestionar;
        }

        public void setGestionar(String gestionar) {
            this.gestionar = gestionar;
        }

        public String getGestionar() {
            return gestionar;
        }

        @Override
        public String toString()
        {
            return super.toString()+"Gestionar: "+gestionar+"\n";
        }

        private String gestionar;
    }


    static class MyList extends ArrayList<MijlocFix>
    {

        MyList()
        {
            super();
        }
        public MijlocFix getByNrInventar(long nrInventar)
        {
            for(MijlocFix it:this)
            {
                if(it.getNrInventar() == nrInventar)
                {
                    return it.clone();
                }
            }
            return new MijlocFix();
        }

        public MijlocFix getByValInventar(double ValInventar)
        {
            for(MijlocFix it:this)
            {
                if(it.getValoare() == ValInventar)
                {
                    return it.clone();
                }
            }
            return new MijlocFix();
        }

        public MijlocFix getByNrVal(long nrInventar,double valInverntar)
        {
            for(MijlocFix it:this)
            {
                if(it.getNrInventar() == nrInventar && it.getValoare()==valInverntar)
                {
                    return it.clone();
                }
            }
            return new MijlocFix();
        }

        public void printUzura(String fileName) throws Exception
        {
            File output = new File(fileName);
            PrintStream print = new PrintStream(output);
            for(MijlocFix it:this)
            {
                print.printf("Numar Inventar: %d Denumire:%s Uzura:%f\n",it.getNrInventar(),it.getDenumire(),it.calculUzura());
            }
            print.close();
        }

        public void saveToFile(String filePath) throws Exception
        {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (MijlocFix it:this)
            {
                oos.writeObject((Object)it.clone());
            }
            oos.close();
        }
    }
    public static List<MijlocFix> createList(String fileName) throws Exception {
     List<MijlocFix> list = new MyList();

     File input = new File(fileName);
     Scanner read = new Scanner(input);
     while(read.hasNextLine())
     {
         String[] line1=read.nextLine().trim().split(",");
         String[] line2=read.nextLine().trim().split(",");
         Locatie locatie=new Locatie(line2[0],line2[1]);
         String[] dataUnformatted = line1[3].trim().split("\\.");
         Date data = new Date(Integer.parseInt(dataUnformatted[0]),Integer.parseInt(dataUnformatted[1])-1,Integer.parseInt(dataUnformatted[2]));
         Categorie c = Categorie.getByName(line1[4]);

         list.add(new MijlocFix(line1[0],Float.parseFloat(line1[2]),Integer.parseInt(line1[1]),data,locatie,c,Integer.parseInt(line1[5])));
     }
            return list;
    }

    public static List<MijlocFix> createListBin(String filePath) throws Exception
    {
        List<MijlocFix> list = new MyList();
        FileInputStream file = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(file);
        try{
            while(true) {
                MijlocFix temp=(MijlocFix) ois.readObject();
                list.add(temp.clone());
                // do sth with object
            }
        }catch(EOFException e){
            ois.close();
        }
        return list;
    }

    public static void SortList(List<MijlocFix> list)
    {
        for(int i = 0 ; i < list.size()-1;i++ )
            for(int j=i+1; j<list.size(); j++)
            {
                if(list.get(i).compareTo(list.get(j)) > 0)
                {
                    MijlocFix aux = list.get(i).clone();
                    list.set(i,list.get(j).clone());
                    list.set(j,aux);
                }
                else if (list.get(i).getValoare() > list.get(j).getValoare() &&
                        list.get(i).compareTo(list.get(j)) == 0)
                {
                    MijlocFix aux = list.get(i).clone();
                    list.set(i,list.get(j).clone());
                    list.set(j,aux);
                }
                else if(list.get(i).CompareByDate(list.get(j)) > 1 &&
                        list.get(i).getValoare() == list.get(j).getValoare())
                {
                    MijlocFix aux = list.get(i).clone();
                    list.set(i,list.get(j).clone());
                    list.set(j,aux);
                }
            }
    }




    public static void main(String[] args)throws Exception {

            SimpleDateFormat Format= new SimpleDateFormat("dd.MM.yyyy");
            Date data = new Date(2, Calendar.MARCH,2023);
            MijlocFix f=new MijlocFix("Mijloc",12.2f,2L,data,new Locatie("Bucuresti","strada 1"),Categorie.TERENURI,12);
            MijlocFix f2= (MijlocFix)f.clone();
            f.setLocatie(new Locatie("Undeva","Idk"));
            System.out.println(f2.toString());

            List<MijlocFix> list = createList("/home/X/Documents/Facultate/Java/seminar2/src/mj.csv");
            SortList(list);
            for(MijlocFix mijloc:list)
            {
                System.out.println(mijloc.toString());
            }

            System.out.println(((MyList)list).getByNrInventar(2));
            try{
                ((MyList)list).saveToFile("/home/X/Documents/Facultate/Java/seminar2/src/mf.dat");
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }

            List<MijlocFix> newList = createListBin("/home/X/Documents/Facultate/Java/seminar2/src/mf.dat");
            ((MyList)list).printUzura("/home/X/Documents/Facultate/Java/seminar2/src/rezultat.txt");
    }


    }
