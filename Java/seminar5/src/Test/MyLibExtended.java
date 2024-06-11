package Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyLibExtended {

    public static class MijlocSimplified
    {
        MijlocSimplified()
        {
            data = new Date();
            denumire = "";
        }

        MijlocSimplified(Date data, String denumire)
        {
            this.data = (Date) data.clone();
            this.denumire = denumire;
        }


        @Override
        public String toString() {
            return "Denumiure: "+ this.denumire + " Data: " + data.toString();
        }

        private Date data;
        private String denumire;

    }

    public static List<MyLib.MijlocFix> reduce(List<MyLib.MijlocFix> list,int lower,int upper)
    {
        List<MyLib.MijlocFix> newList= new MyLib.MyList();
        for(MyLib.MijlocFix it:list)
        {
            if(it.getValoare() >= lower && it.getValoare() <=upper)
            {
                newList.add(it);
            }
        }
        return newList;
    }

    //vs
    public static List<MyLib.MijlocFix> reduceFilter(List<MyLib.MijlocFix> list,int lower,int upper)
    {
        return list.stream().filter(it -> it.getValoare() >= lower && it.getValoare() <=upper).collect(Collectors.toList());
    }

    public static List<MyLib.MijlocFix> selectLocation(List<MyLib.MijlocFix> list,MyLib.Locatie locatie)
    {
        List<MyLib.MijlocFix> newList= new MyLib.MyList();
        for(MyLib.MijlocFix it:list)
        {
            if(it.getLocatie().getAdresa().equals(locatie.getAdresa())
                    && it.getLocatie().getDenumire().equals(locatie.getDenumire()) )
            {
                newList.add(it);
            }
        }
        return newList;
    }

    public static List<MyLib.MijlocFix> selectDate(List<MyLib.MijlocFix> list, Date date)
    {
        List<MyLib.MijlocFix> newList= new MyLib.MyList();
        for(MyLib.MijlocFix it:list)
        {
            if(it.getDataAchizitiel().equals(date))
            {
                newList.add(it);
            }
        }
        return newList;
    }

    public static class MijlocComparator implements Comparator<MyLib.MijlocFix>
    {

        @Override
        public int compare(MyLib.MijlocFix t0, MyLib.MijlocFix t1) {
            return Float.compare(t0.calculUzura(), t1.calculUzura());
        }
    }
    public static void sortMijloc(List<MyLib.MijlocFix> list)
    {
        list.sort(new MijlocComparator());
    }


    public static Function<List<MyLib.MijlocFix>,Set<String>> extractLocation = (new Function<List<MyLib.MijlocFix>, Set<String>>() {
        @Override
        public Set<String> apply(List<MyLib.MijlocFix> list) {
            Set<String> newSet = new HashSet<String>();
            for(MyLib.MijlocFix it:list)
            {
                newSet.add(it.getLocatie().toString());
            }
            return newSet;
        }
    });

    public static Function<List<MyLib.MijlocFix>,Map<Long,String>> reGroup = (new Function<List<MyLib.MijlocFix>, Map<Long, String>>() {
        @Override
        public Map<Long, String> apply(List<MyLib.MijlocFix> list) {
            Map<Long,String> newMap = new HashMap<Long,String>();
            for(MyLib.MijlocFix it:list)
            {
                newMap.put(it.getNrInventar(),it.getLocatie().toString());
            }
            return newMap;
        }
    });

    public static Function<List<MyLib.MijlocFix>,Map<MyLib.Categorie,List<MyLib.MijlocFix>>> createTable = (new Function<List<MyLib.MijlocFix>, Map<MyLib.Categorie, List<MyLib.MijlocFix>>>() {
        @Override
        public Map<MyLib.Categorie, List<MyLib.MijlocFix>> apply(List<MyLib.MijlocFix> list) {
            Map<MyLib.Categorie, List<MyLib.MijlocFix>> newMap = new HashMap<MyLib.Categorie, List<MyLib.MijlocFix>>();
            for(MyLib.MijlocFix it:list)
            {
                if(!newMap.containsKey(it.getCategorie()))
                {
                    List<MyLib.MijlocFix>newList = new MyLib.MyList();
                    newList.add(it);
                    newMap.put(it.getCategorie(),newList);
                }
                else
                {
                    newMap.get(it.getCategorie()).add(it);
                }
            }
            return newMap;
        }
    });

    public static Function<List<MyLib.MijlocFix>,Map<MyLib.Categorie,List<String>>> createTableString = (new  Function<List<MyLib.MijlocFix>,Map<MyLib.Categorie,List<String>>>() {
        @Override
        public Map<MyLib.Categorie, List<String>> apply(List<MyLib.MijlocFix> list) {
            Map<MyLib.Categorie, List<String>> newMap = new HashMap<MyLib.Categorie, List<String>>();
            for(MyLib.MijlocFix it:list)
            {
                if(!newMap.containsKey(it.getCategorie()))
                {
                    List<String>newList = new ArrayList<String>();
                    newList.add(it.getDenumire());
                    newMap.put(it.getCategorie(),newList);
                }
                else
                {
                    newMap.get(it.getCategorie()).add(it.getDenumire());
                }
            }
            return newMap;
        }
    });

    public static Function<List<MyLib.MijlocFix>,Map<MyLib.Locatie,Float>> uzuraMedieLoc = (new Function<List<MyLib.MijlocFix>, Map<MyLib.Locatie, Float>>() {
        @Override
        public Map<MyLib.Locatie, Float> apply(List<MyLib.MijlocFix> list) {
            Map<MyLib.Locatie,Float> newMap = new HashMap<MyLib.Locatie,Float>();
            for(MyLib.MijlocFix it:list)
            {
                if(!newMap.containsKey(it.getLocatie()))
                {
                    float sum =0.0f;
                    float num =0.0f;
                    for(MyLib.MijlocFix newIt:list)
                    {
                        if(it.getLocatie().compareTo(newIt.getLocatie()) == 0)
                        {
                            sum += newIt.calculUzura();
                            num += 1.0f;

                    }
                    }
                    sum /= num;
                    newMap.put(it.getLocatie(),sum);
                }
            }
            return newMap;
        }
    });

    public static Function<List<MyLib.MijlocFix>,Map<Long,MijlocSimplified>> simplify= (new Function<List<MyLib.MijlocFix>, Map<Long, MijlocSimplified>>() {
        @Override
        public Map<Long, MijlocSimplified> apply(List<MyLib.MijlocFix> list) {
            Map<Long, MijlocSimplified> newMap = new HashMap<Long, MijlocSimplified>();
            for(MyLib.MijlocFix it:list)
            {
                if(!newMap.containsKey(it.getNrInventar()))
                {
                    newMap.put(it.getNrInventar(),new MijlocSimplified((Date) it.getDataAchizitiel().clone(),it.getDenumire()));
                }
            }
            return newMap;
        }
    });
    }
