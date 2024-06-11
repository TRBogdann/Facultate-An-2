import Matrice.Element;
import Matrice.MatriceRara;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MatriceRara mat = new MatriceRara("matricerara.csv");
        mat.print();
        System.out.println(mat.countNegative());
        Map<Integer,Double> map = mat.stream().collect(Collectors.groupingBy(Element::getCol,Collectors.averagingDouble(Element::getData)));
        for(var it:map.entrySet())
        {
            System.out.println("Index: "+it.getKey()+" Medie: "+it.getValue());
        }
        mat.saveDiagonal("diagonal.dat");
        mat.restoreDiagonal("diagonal.dat");
        mat.print();
    }
}