import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataSetGenerator {

    // test case 1: hierarchy/chain operators
    public static String Category1(int totalToken) {
        StringBuilder expression = new StringBuilder();
        String[] operators = {"+", "*", "/", "-", "%", "^"};
        
        int operandCount = 1;
        int operatorIndex = 0;
        int currentTokens = 0;

        // initialize expression
        expression.append(operandCount);
        currentTokens++;
        operandCount++;

        while (currentTokens < totalToken) {
            // put tgt operator and operand
            expression.append(" ");
            expression.append(operators[operatorIndex]);
            expression.append(" ");
            expression.append(operandCount);
            
            // changes operator
            currentTokens += 2; 
            operatorIndex = (operatorIndex + 1) % operators.length;
            
            // changes operand (only single digits)
            operandCount++;
            if (operandCount > 9) {
                operandCount = 1; 
            }
        }

        return expression.toString();
    }

    // test case 2: nested groupings

    // test case 3: large white spaces, negative results

    // public static String Category4(int totalToken) {
        
    //     // test case 4: invalid operations
    // }

    public static void main(String[] args) {
        String file = "testcase.txt";
        
        // write in file
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int n = 5; n <= 10000; n += 2) {
                String expression = Category1(n);
                writer.println(expression);
            } 
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
