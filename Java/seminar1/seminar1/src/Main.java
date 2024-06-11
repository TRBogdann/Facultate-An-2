import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void printMat(int[][][] mat) {
        for (int[][] m2d : mat)
            for (int[] line : m2d)
                System.out.println(Arrays.toString(line));
    }

    static void printMat(double[][] mat) {
        /* C style
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<sizes[i];j++)
            {
                System.out.printf("%f ",mat[i][j]);
            }
            System.out.println();
        }
        */

        for (double[] line : mat) {
            System.out.println(Arrays.toString(line));
        }
    }

    static double[][] deleteLine(double[][] mat, int pos) throws Exception {
        int col = mat.length;
        if (pos < 0 || pos >= col) {

            throw new Exception("Position is Invalid");
        }

        double[][] copy = new double[col - 1][];
        int i = 0;
        int j = i;
        while (i < col) {
            if (i != pos) {
                copy[j] = new double[mat[i].length];
                System.arraycopy(mat[i], 0, copy[j], 0, mat[i].length);
                j++;
            }
            i++;
        }

        return copy;
    }

    static double[][] insertLine(double[][] mat, double[] line, int pos) throws Exception {
        int col = mat.length;
        if (pos < 0 || pos >= col) {

            throw new Exception("Position is Invalid");
        }

        double[][] copy = new double[col + 1][];
        int i = 0;
        int j = 0;
        while (i < col + 1) {
            if (i == pos) {
                copy[i] = new double[line.length];
                System.arraycopy(line, 0, copy[i], 0, line.length);
            } else {
                copy[i] = new double[mat[j].length];
                System.arraycopy(mat[j], 0, copy[i], 0, mat[j].length);
                j++;
            }
            i++;
        }


        return copy;
    }

    static double[][] overwriteLine(double[][] mat, double[] line, int pos) throws Exception {
        int col = mat.length;
        if (pos < 0 || pos >= col) {

            throw new Exception("Position is Invalid");
        }

        double[][] copyMat = new double[col][];


        int i = 0;

        while (i < col) {
            if (i != pos) {
                copyMat[i] = new double[mat[i].length];
                System.arraycopy(mat[i], 0, copyMat[i], 0, mat[i].length);
            } else {
                copyMat[i] = new double[line.length];
                System.arraycopy(line, 0, copyMat[i], 0, line.length);
            }

            i++;
        }


        return copyMat;
    }

    static double[][] concatLine(double[][] mat, double[] line, double pos) throws Exception {
        int col = mat.length;

        if (pos < 0 || pos >= col) {

            throw new Exception("Position is Invalid");
        }

        double[][] copyMat = new double[col][];

        int i = 0;

        while (i < col) {
            if (i != pos) {
                copyMat[i] = new double[mat[i].length];
                System.arraycopy(mat[i], 0, copyMat[i], 0, mat[i].length);
            } else {
                copyMat[i] = new double[mat[i].length + line.length];
                System.arraycopy(mat[i], 0, copyMat[i], 0, mat[i].length);
                System.arraycopy(line, 0, copyMat[i], mat[i].length, line.length);
            }

            i++;
        }


        return copyMat;
    }

    public static int[][][] generate(String filename)throws Exception {
        int[][][] resultMat = new int[0][][];
        File input = new File(filename);

        if(input.exists()) {
            Scanner read = new Scanner(input);


            if (read.hasNextInt()) {
                int matNum = read.nextInt();
                resultMat = new int[matNum][][];

                read.nextLine();

                int i = 0;
                while (read.hasNext()) {
                    String line = read.nextLine();
                    String[] numbersUnf = line.split(",");


                    int col = numbersUnf.length;
                    resultMat[i] = new int[col][];

                    for (int j = 0; j < col; j++) {
                        int n = Integer.parseInt(numbersUnf[j]);

                        resultMat[i][j] = new int[n];
                        for (int k = 0; k < n; k++) {
                            resultMat[i][j][k] = i;
                        }

                    }
                    i++;
                }
            }
            read.close();
        }


        return resultMat;
    }



    public static void main(String[] args) throws Exception {

        //1

        //2

        if(args.length>1) {
            int col= Integer.parseInt(args[0]);
            int[] sizes = new int[col];
            double[][] mat = new double[col][];
            int current=1;
            for(int i=0;i<col;i++)
            {
                sizes[i]=Integer.parseInt(args[current]);
                mat[i]=new double[sizes[i]];
                current++;
                for(int j=0;j<sizes[i];j++)
                {
                    mat[i][j]=Double.parseDouble(args[current]);
                    current++;
                }
            }


            for(int i=0;i<col;i++)
            {
                System.out.println(sizes[i]);
            }

            double[] line={1.0f,2.0f,1.0f};

            System.out.println("Original: ");
            printMat(mat);
            System.out.println();

            System.out.println("Delete: ");
            printMat(deleteLine(mat,2));
            System.out.println();

            System.out.println("Insert: ");
            printMat(insertLine(mat,line,0));
            System.out.println();

            System.out.println("Overwrite: ");
            printMat(overwriteLine(mat,line,1));
            System.out.println();

            System.out.println("Concatenation: ");
            printMat(concatLine(mat,line,3));
            System.out.println();

        }

        //3
        printMat(generate("/home/X/Documents/Facultate/Java/seminar1/seminar1/src/input.txt"));

    }
}