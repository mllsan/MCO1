import java.io.*;
import java.util.*;

public class Calculator {

    public static String convert(Converter c, String e) {
        return c.toPostfix(e);
    }

    public static void main(String[] args) throws FileNotFoundException {

        File cases = new File("testcase.txt");
        Scanner input = new Scanner(cases);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println("Infix: " + line);

            // run time analysis
            long timeStart = System.nanoTime();
            String result = convert(new Converter(), line);
            long timeEnd = System.nanoTime();
            long duration = timeEnd - timeStart;

            System.out.println(result);
            System.out.println("Execution Time: " + duration + " nano seconds");
            System.out.println();
        }

        input.close();
    }
}
