import Geometrie.ListaPuncte;
import Geometrie.Punct;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<Punct> list = new ListaPuncte("data/puncte.csv");

        System.out.println("Nr Puncte: "+((ListaPuncte)list).nrPuncte());
        ((ListaPuncte) list).print();

       Map<String, Long> map =  list.stream().collect(Collectors.groupingBy(Punct::getEtichetaFigura,Collectors.counting()));
       for(var it:map.entrySet())
       {
           System.out.println("Figura: "+it.getKey()+" Nr Puncte: "+it.getValue());
       }
       ((ListaPuncte)list).saveDistante("data/distante.csv");
    }
}