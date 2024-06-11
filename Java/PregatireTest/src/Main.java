import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static class myHashMap extends HashMap<String,Trotineta>
    {
        myHashMap(String pathname) throws FileNotFoundException {
            File file = new File(pathname);
            Scanner read = new Scanner(file);
            while(read.hasNextLine())
            {
                String line =  read.nextLine();
                String[] unformatted = line.trim().split("\\s+");
                this.put(unformatted[0], new Trotineta(unformatted[0],Float.parseFloat(unformatted[1]),Float.parseFloat(unformatted[2]),Float.parseFloat(unformatted[3])));
            }
        }
        myHashMap()
        {
            super();
        }

        public void saveToBin(String filename) throws IOException {
            FileOutputStream file= new FileOutputStream(filename);
            ObjectOutputStream save = new ObjectOutputStream(file);
            save.writeObject((Object)this);
            save.close();
            file.close();
        }

        public void restoreBin(String pathname) throws IOException, ClassNotFoundException {
            FileInputStream file = new FileInputStream(pathname);
            ObjectInputStream restore = new ObjectInputStream(file);
            myHashMap h = (myHashMap)restore.readObject();
            this.clear();
            this.putAll(h);
        }

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Map<String,Trotineta> myMap= new myHashMap("Input/trote.txt");
        for(var it:myMap.entrySet())
        {
            System.out.println("Key: "+it.getKey()+" "+it.getValue());
        }
        Map<Float, Double> newMap = myMap.values().stream().
                collect(Collectors.groupingBy(Trotineta::getVitezaMedie,Collectors.summingDouble(Trotineta::getDistanta)));
        ((myHashMap)myMap).saveToBin("Input/trotinete.dat");
        Map<String,Trotineta> mapRestored = new myHashMap();
        ((myHashMap)mapRestored).restoreBin("Input/trotinete.dat");
        for(var it:mapRestored.entrySet())
        {
            System.out.println("Key: "+it.getKey()+" "+it.getValue());
        }
    }

}