import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Muzeu {
    private int capacitate;
    private Deque<Integer> vizitarori = new ArrayDeque<Integer>();
    public Muzeu(int capacitate)
    {
        this.capacitate = capacitate;
    }

    public void print()
    {
        System.out.println("Vizitatori in muzeu: ");
        for(int it:vizitarori.toArray(new Integer[0]))
        {
            System.out.println(it);
        }
    }
    public synchronized void add(List<Integer> grup)
    {
        while(grup.size()+vizitarori.size()>capacitate)
        {
            try {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }
        System.out.println("Intra un grup de "+grup.size()+" vizitatori");
        for(int it:grup)
        {
            vizitarori.addFirst(it);
        }
        print();
        notifyAll();
    }
    public synchronized void remove(int num)
    {
        while(vizitarori.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e)
            {

            }
        }
        int iesire = Math.min(num,vizitarori.size());
        System.out.println("Vizitatori Iesiti: ");
        for(int i = 0;i < iesire;i++)
        {

            System.out.println(vizitarori.pollLast());
        }
        print();
        notifyAll();
    }



}
