import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class Main {

    public static class Student implements Comparable<Student>
    {
        private String nume;
        private double medie;

        public Student(String nume,double medie)
        {
            this.nume = nume;
            this.medie = medie;
        }

        @Override
        public String toString() {
            return STR."Nume: \{nume} Medie:\{Double.toString(medie)}";
        }

        @Override
        public int compareTo(Student student) {
            return Double.compare(this.medie,student.medie);
        }
    }


    public static class myMat<T  extends Comparable<T>>
    {
        public myMat(int lines,int columns)
        {
            data = new ArrayList<List<T>>();
            for(int i = 0; i < lines; i++)
            {
                List<T> elements = new ArrayList<T>();
                for(int j=0; j< columns;j++)
                {
                    elements.add(null);
                }
                data.add(elements);
            }
        }
        public  void setData(T[] data)
        {
            for(int k = 0; k<data.length;k++)
            {
                int i = k / this.data.getFirst().size();
                int j = k % this.data.size();
                setElement(i,j,data[k]);
            }
        }

        public void printData()
        {
            for(List<T> li:data)
            {
                System.out.println(li);
            }
        }

        public  void setElement(int i,int j,T element)
        {
            this.data.get(i).set(j,element);
        }

        public T getElement(int i,int j)
        {
            return this.data.get(i).get(j);
        }

        public void fill(T element)
        {
            for(List<T> li:data)
            {
                li.replaceAll(ignored -> element);
            }
        }

        private List<List<T>> data;
    }

    public static class myArray<T extends Comparable<T>> extends ArrayList<T>
    {
        private  int pivot(int left,int right)
        {
            int i = left;
            int j= right-1;
            int dirI = 0;
            int dirJ = 1;
            while(j != i)
            {
                if(this.get(j).compareTo(this.get(i)) < 0 )
                {
                    T temp = this.get(i);
                    this.set(i,this.get(j));
                    this.set(j,temp);
                    dirI = 1-dirI;
                    dirJ = 1-dirJ;
                }
                i += dirI;
                j -= dirJ;
            }
            return i;
        }

        public void QuickSort(int left,int right) throws InterruptedException {
            if(left >= right) return;

            int mid = pivot(left,right);
            Thread leftThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        QuickSort(left,mid);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread rightThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        QuickSort(mid+1,right);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            leftThread.start();
            rightThread.start();
            leftThread.join();
            rightThread.join();
        }
    }

    public static class myVector<T extends Comparable<T>>
    {
        public myVector(int size)
        {
            data = new ArrayList<T>();
            for(int i = 0; i < size;i++)
            {
                data.add(null);
            }
        }

        public void addData(T[] newData)
        {
            for(int i = 0; i<newData.length;i++)
            {
                this.data.set(i,newData[i]);
            }
        }

        public void fill(T element)
        {
            for(int i = 0; i< this.data.size();i++)
            {
                this.data.set(i,element);
            }
        }

        public void set(int i,T element)
        {
            int n = data.size();
            if(n<=i)
                    return;
            this.data.set(i,element);
        }

        public T get(int i)
        {
            int n =  data.size();
            if(n<=i)
                return null;
            else return data.get(i);
        }

        public void printData()
        {
            System.out.println(data);
        }

        public myVector<T> multiply(myMat<T> mat)
        {
            myVector<T> res = new myVector<T>(data.size());
            if(mat.data.size() != data.size())
                return res;
            return res;
        };
        private List<T> data;
    }

    public static void main(String[] args) throws InterruptedException {
        //7.1
            myArray<Integer> testList = new myArray<Integer>();
            testList.add(4);
            testList.add(10);
            testList.add(1);
            testList.add(2);
            testList.add(6);
            testList.add(11);
            testList.add(7);
            myArray<Student> studenti = new myArray<Student>();
            studenti.add(new Student("Stud1",5.5));
            studenti.add(new Student("Stud2",7.5));
            studenti.add(new Student("Stud3",2.5));
            studenti.add(new Student("Stud4",1.5));
            studenti.add(new Student("Stud4",10));
            studenti.add(new Student("Stud4",9.5));
            studenti.add(new Student("Stud4",9.8));
            testList.QuickSort(0,testList.size());
            studenti.QuickSort(0,studenti.size());
            System.out.println(testList);
            System.out.println(studenti);
       //7.2

        myMat<Float> mat = new myMat<Float>(5,5);
        mat.fill(1.0f);
        myVector<Float> vec = new myVector<>(5);
        vec.fill(5.0f);
        mat.printData();
        vec.printData();
        }
    }
