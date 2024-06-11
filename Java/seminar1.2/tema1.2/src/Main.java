import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static class BandedMatrix
    {
        public BandedMatrix(int n,int lowerWidth,int upperWidth, double lower,double upper)throws Exception
        {
            this.a=lower;
            this.b=upper;
            this.aWidth=lowerWidth;
            this.bWidth=upperWidth;

            if(lowerWidth>(2*n-1)/2 || upperWidth>(2*n-1)/2)
                throw new Exception("Width cannot be larger than (2*n-1)/2");

            this.mat=new double[n][n];
            for(int i=0;i<n;i++)
            {
                this.mat[i][i]=1;
            }

            int dif=lowerWidth;

            for(int i=1;i<=dif;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(j-i>=0) {
                        this.mat[j][j - i] = lower;
                    }
                }
            }

            dif=upperWidth;

            for(int i=1;i<=dif;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(j+i<n) {
                        this.mat[j][j + i] = upper;
                    }
                }
            }

        }

        public void print()
        {
            for(double[] line:this.mat)
            {
                System.out.println(Arrays.toString(line));

            }

            System.out.println();

        }
        public BandedMatrix copy(BandedMatrix bm)
        {
            int n=bm.mat.length;
            this.mat=new double[n][n];
            this.a=bm.a;
            this.b=bm.b;
            this.aWidth=bm.aWidth;
            this.bWidth=bm.bWidth;

            for(int i=0;i<n;i++)
                System.arraycopy(bm.mat[i],0,mat[i],0,n);

            return this;
        }

        public void insertLines(int number)throws Exception
        {
            BandedMatrix temp=new BandedMatrix(mat.length+number,this.aWidth,this.bWidth,this.a,this.b);
            this.copy(temp);
        }

        public void deleteLines(int number)throws Exception
        {
            if(mat.length-number<1)
                throw new Exception("Deleting too many lines");

            BandedMatrix temp=new BandedMatrix(mat.length-number,this.aWidth,this.bWidth,this.a,this.b);
            this.copy(temp);
        }

        private double[][] mat;
        private double a;
        private int aWidth;
        private int bWidth;
        private double b;
    }

    public static void main(String[] args)throws Exception {

        //1 2
        if(args.length==5)
        {
            int n=Integer.parseInt(args[0]);
            int lowerWidth=Integer.parseInt(args[1]);
            int upperWidth=Integer.parseInt(args[2]);

            double lower=Double.parseDouble(args[3]);
            double upper=Double.parseDouble(args[4]);

            BandedMatrix mat = new BandedMatrix(n, lowerWidth, upperWidth, lower, upper);
            mat.print();

            mat.insertLines(1);
            mat.print();

            mat.deleteLines(2);
            mat.print();

        }

        // 3
        File input=new File("/home/X/Documents/Facultate/Java/seminar1.2/tema1.2/src/input.txt");

        Scanner read = new Scanner(input);

        if(read.hasNextInt())
        {
           int n=read.nextInt();
           read.nextLine();

           int[] group=new int[n];
           double[] average=new double[n];

           for(int i=0;i<n;i++)
           {
               String temp=read.nextLine();
               String[] strUnf=temp.split(",");

               group[i]=Integer.parseInt(strUnf[0]);

               for(int j=1;j<strUnf.length;j++)
               {
                   average[i]+=Double.parseDouble(strUnf[j]);
               }
               average[i]/=strUnf.length-1;
           }

           double generalAvg=0.0;
           for(int i=0;i<n;i++)
           {
               System.out.printf("%d,%f\n",group[i],average[i]);
               generalAvg+=average[i];
           }

           generalAvg/=n;

           System.out.println("Media generala:"+generalAvg);


        }

        read.close();

    }
}