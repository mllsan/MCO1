import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a calculator for postfix expressions
 * <p>
 *     This class implements the conversion and evaluation of an
 *     arithmetic expression in postfix notation. It is composed
 *     of a Converter class.
 * </p>
 *
 * @author Raphael Romero
 * @author Michaela Santos
 * @version 3.2.0
 */
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
                            break;
                        case '*':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp * rightOp;
                            break;
                        case '/':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp / rightOp;
                            break;
                        case '%':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp % rightOp;
                            break;
                        case '+':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp + rightOp;
                            break;
                        case '-':
                            rightOp = operands.pop();
                            leftOp = operands.pop();
                            result = leftOp - rightOp;
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

       System.out.println("Boot Up Libraries with Example Expression");
       System.out.println("Infix: 1+2+3+4+5");

       long timeStart = System.nanoTime();
       String result = convert(new Converter(), "1+2+3+4+5");
       long timeEnd = System.nanoTime();
       long duration = timeEnd - timeStart;
       double answer = evaluate(result);

       System.out.println("Postfix: " + result);
       System.out.println("Evaluate: " + answer);
       System.out.println("Execution Time: " + duration + " nano seconds");
       System.out.println();

       File cases = new File("testcase.txt");
       Scanner input = new Scanner(cases);

       while (input.hasNextLine()) {
           String line = input.nextLine();
           System.out.println("Infix: " + line);

           // run time analysis
           timeStart = System.nanoTime();
           result = convert(new Converter(), line);
           timeEnd = System.nanoTime();
           duration = timeEnd - timeStart;
           answer = evaluate(result);

           System.out.println("Postfix: " + result);
           System.out.println("Evaluate: " + answer);
           System.out.println("Execution Time: " + duration + " nano seconds");
           System.out.println();
       }
       input.close();
    }
}
