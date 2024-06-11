import Test.MyLib;
import Test.MyLibExtended;

import java.util.*;
import java.util.stream.Collectors;

import static Test.MyLib.createList;

public class Main {
    public static void main(String[] args) throws Exception {
        List<MyLib.MijlocFix> list = createList("/home/X/Documents/Facultate/Java/seminar5/src/mj.csv");
        //list = MyLibExtended.selectDate(list,new Date(1917, Calendar.NOVEMBER,2));
        list = MyLibExtended.reduceFilter(list,0,100000);
        //MyLibExtended.sortMijloc(list);

        Set<String> mySet = MyLibExtended.extractLocation.apply(list);
        Map<Long,String> myMap = MyLibExtended.reGroup.apply(list);
        Map<MyLib.Categorie,List<MyLib.MijlocFix>> mijlocMap = MyLibExtended.createTable.apply(list);
        Map<MyLib.Locatie,Float> uzuraList = MyLibExtended.uzuraMedieLoc.apply(list);
        Map<Long, MyLibExtended.MijlocSimplified> simplifiedMap = MyLibExtended.simplify.apply(list);
        Map<MyLib.Categorie,List<MyLib.MijlocFix>> collectedMap = list.stream().collect(Collectors.groupingBy(MyLib.MijlocFix::getCategorie));
        Map<MyLib.Locatie, Double> uzuraTest = list.stream().collect(Collectors.groupingBy(MyLib.MijlocFix::getLocatie,Collectors.averagingDouble(MyLib.MijlocFix::calculUzura)));
        for(MyLib.MijlocFix mijloc:list)
        {
            System.out.println(mijloc.toString());
        }

        for(String locatie:mySet)
        {
            System.out.println(locatie);
        }

        for(String locatie: myMap.values())
        {
            System.out.println(locatie);
        }

        for(Double it: uzuraTest.values())
        {
            System.out.println(it);
        }

        for(MyLibExtended.MijlocSimplified it:simplifiedMap.values())
        {
            System.out.println(it.toString());
        }

        for(Map.Entry<MyLib.Categorie, List<MyLib.MijlocFix>> entry:collectedMap.entrySet())
        {
            System.out.println(entry.getKey().getSimbol());
            for(MyLib.MijlocFix it:entry.getValue())
            {
                System.out.println(it.toString());
            }
        }
    }
}