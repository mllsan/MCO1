import java.io.*;
import java.util.*;
import java.lang.*;

public class Calculator {

    public static String convert(Converter c, String e) {
        return c.toPostfix(e);
    }

    public static double evaluate(String postfx) {
        Stack<Double> operands = new Stack<>();
        String token;
        double ans;

        if (postfx != null) {
            String[] tokens = postfx.trim().split("\\s+");
            for (int i = 0; i < tokens.length; i++) {
                token = tokens[i];

                if (token.equals("^") || token.equals("*") || token.equals("/") ||
                        token.equals("%") || token.equals("+") || token.equals("-")) {
                    double leftOp, rightOp, result = 0;

                    switch (token.charAt(0)) {
                        case '^':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = Math.pow(leftOp, rightOp);
                            operands.push(result);
                            break;
                        case '*':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp * rightOp;
                            operands.push(result);
                            break;
                        case '/':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp / rightOp;
                            operands.push(result);
                            break;
                        case '%':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp % rightOp;
                            operands.push(result);
                            break;
                        case '+':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp + rightOp;
                            operands.push(result);
                            break;
                        case '-':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp - rightOp;
                            operands.push(result);
                            break;
                        default:
                            break;
                    }
                    operands.push(result);
                }
                else
                    operands.push(Double.parseDouble(token));
            }
            ans = operands.pop();
        }
        else
            ans = -999.999;

        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {

//        System.out.println("Boot Up Libraries with Example Expression");
//        System.out.println("Infix: 1+2+3+4+5");
//
//        long timeStart = System.nanoTime();
//        String result = convert(new Converter(), "1+2+3+4+5");
//        long timeEnd = System.nanoTime();
//        long duration = timeEnd - timeStart;
//
//        System.out.println(result);
//        System.out.println("Execution Time: " + duration + " nano seconds");
//        System.out.println();
//
//        File cases = new File("testcase.txt");
//        Scanner input = new Scanner(cases);
//
//        while (input.hasNextLine()) {
//            String line = input.nextLine();
//            System.out.println("Infix: " + line);
//
//            // run time analysis
//            timeStart = System.nanoTime();
//            result = convert(new Converter(), line);
//            timeEnd = System.nanoTime();
//            duration = timeEnd - timeStart;
//
//            System.out.println(result);
//            System.out.println("Execution Time: " + duration + " nano seconds");
//            System.out.println();
//        }
//        input.close();
        DatasetGenerator g = new DatasetGenerator();
        double result = evaluate(convert(new Converter(), "(1 + 2]"));
        System.out.println(result);
    }
}
