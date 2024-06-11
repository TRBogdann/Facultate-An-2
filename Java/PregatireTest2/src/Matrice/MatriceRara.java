package Matrice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatriceRara extends ArrayList<Element> {
    public MatriceRara()
    {
        super();
    }
    public MatriceRara(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        Scanner read = new Scanner(file);
        while(read.hasNextLine())
        {
            String line = read.nextLine();
            String[] unformatted = line.trim().split(",");
            this.add(new Element(Integer.parseInt(unformatted[0]),Integer.parseInt(unformatted[1]),Float.parseFloat(unformatted[2])));
        }
    }
    public void print()
    {
        for(var it:this)
        {
            System.out.println(it);
        }
    }

    public int countNegative()
    {
        int count = 0;
        for(var it:this)
        {
            if(it.getData()<0)
                count++;
        }
        return count;
    }

    public void saveDiagonal(String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream save = new ObjectOutputStream(file);
        MatriceRara temp = new MatriceRara();
        for(var it:this)
        {
            if(it.getCol() == it.getLinie())
            {
                temp.add(it);
            }
        }
        save.writeObject((Object)temp);
        save.close();
        file.close();
    }

    public void restoreDiagonal(String pathname) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(pathname);
        ObjectInputStream restore = new ObjectInputStream(file);
        MatriceRara temp = (MatriceRara)restore.readObject();
        //temp.print();
        this.clear();
        this.addAll(temp);
        restore.close();
        file.close();
    }

}
