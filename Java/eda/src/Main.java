import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static int argumentType(String s) {
        if (s.charAt(0) == '/')
            return 0;
        if (s.equals("vlog") || s.equals("vhdl"))
            return 1;
        if (s.charAt(0) == '"') {
            String[] s1 = s.split(",");
            for (int i = 0; i < s1.length; i++) {
                if (s1[i].charAt(0) != '"' || s1[i].charAt(1) != '/')
                    return 4;
                return 2;
            }
            return 4;
        }

        return 4;

    }

    public static String getName(int num) {
        if (num == 0)
            return "workspace";
        if (num == 1)
            return "language";
        if (num == 2)
            return "file";

        return "unk";
    }

    public static int commandType(String s) {
        if (s.equals("-help"))
            return 3;
        if (s.equals("-workspace"))
            return 0;
        if (s.equals("-lang"))
            return 1;
        if (s.equals("-openFile"))
            return 2;

        return 4;
    }

    public static String checkString(String[] s) {

        if (!s[0].equals("dvt_cli.sh"))
            return "Unknown Command";

        String[] str = new String[4];
        int[] order = {4, 4, 4, 4};

        str[0] = "workspace: ";
        str[1] = "lang: ";
        str[2] = "openFile: ";
        str[3] = "help:";
        int k = 0;
        boolean[] found = new boolean[4];
        boolean last_type = true;
        int last_command = 0;
        for (int i = 1; i < s.length; i++) {
            int indCom = commandType(s[i]);
            int indArg = argumentType(s[i]);
            if (indCom == indArg || (indCom < 4 && !last_type) || (indArg < 4 && last_type)) {
                if (!last_type) {
                    return "Invalid " + getName(last_command) + " '" + s[i] + "'.";
                }

                return "Invalid argument '" + s[i] + "'.";

            }

            if (indCom < 4 && last_type) {
                last_command = indCom;
                last_type = false;
                if (!found[last_command]) {
                    order[k] = last_command;
                    k++;
                }
                found[last_command] = true;

                if (indCom == 3) {
                    last_type = true;

                }
            }

            if (indArg < 4 && !last_type) {
                if (indArg != last_command)
                    if (indArg != 0 && last_command != 2) {
                        return "Invalid " + getName(last_command) + "'" + s[i] + "'.";
                    }
                if (last_command == 0 && !str[last_command].equals("workspace: "))
                    str[last_command] += ",";
                if (last_command == 1 && !str[last_command].equals("lang: "))
                    str[last_command] += ",";
                if (last_command == 2 && !str[last_command].equals("openFile: "))
                    str[last_command] += ",";

                str[last_command] += s[i];
                last_type = true;
            }
        }

        if (last_type == false) {
            String st = "Not enough arguments for ";
            if (last_command == 0)
                st += "'-workspace'.";
            if (last_command == 1)
                st += "'-lang'.";
            if (last_command == 2)
                st += "'-openFile'.";

            return st;
        }

        if (!found[0]) {
            return "Missing mandatory argument '-workspace'.";
        }
        if (!found[1]) {
            return "Missing mandatory argument '-lang'.";
        }

        String fin = "";
        for (int j = 0; j < 4; j++) {
            k = order[j];
            if (k != 4) {
                if (!fin.equals("") && j != 0)
                    fin += "\n";
                fin += str[k];
            }
        }
        return fin;
    }

    public static void main(String[] args) throws IOException {
        // Reading input in the field input
        //BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
        //String input = inputBuffer.lines().collect(Collectors.joining("\n"));
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        String[] parsed = input.trim().split(" ");
        String result = null;

        // This is a debug message
        //System.err.println(input);


        // TODO: Add logic here

        // Print result
        System.out.println(checkString(parsed));

    }

}

