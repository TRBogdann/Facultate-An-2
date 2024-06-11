package Geometrie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaPuncte extends ArrayList<Punct> {
    public ListaPuncte(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        Scanner read = new Scanner(file);
        while(read.hasNextLine())
        {
            String line = read.nextLine();
            String[] unformated = line.trim().split(",");
            this.add(new Punct(unformated[0],unformated[1],Double.parseDouble(unformated[2]),Double.parseDouble(unformated[3])));
        }
    }

    public void print()
    {
        for(var it:this)
        {
            System.out.println(it);
        }
    }
    public int nrPuncte()
    {
        return this.size();
    }


    public void saveDistante(String filename) throws FileNotFoundException {
        this.sort(Punct::compareTo);
        File file = new File(filename);
        PrintWriter printWriter= new PrintWriter(file);
        for(var it:this)
        {
            String line = it.getEtichetaFigura()+","+it.getEtichetaPunct()+","+it.distance();
            printWriter.println(line);
        }
        printWriter.close();
    }



}
