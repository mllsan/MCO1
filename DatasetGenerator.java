import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DatasetGenerator {

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
    public String category2(int tokens) {
        int nests = tokens / 4;

        if (nests < 1)
            nests = 1;

        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int min = 1, max = 9, i, term, nextTerm;
        String[] operators = {"+", "-", "*", "/", "%", "^"};
        String op;

        for (i = 0; i < nests; i++)
            sb.append("( ");

        term = rand.nextInt((max - min) + 1) + min;
        sb.append(term).append(" ");

        for (i = 0; i < nests; i++) {
            op = operators[rand.nextInt(operators.length)];
            nextTerm = rand.nextInt((max - min) + 1) + min;

            sb.append(op).append(" ").append(nextTerm)
                    .append(" ").append(") ");
        }

        return sb.toString().trim();
    }

    // test case 3: large white spaces, negative results, 5-6 figure tokens
    public String genWhitespaces() {
        int n;
        StringBuilder spaces = new StringBuilder();
        Random rand = new Random();
        n = rand.nextInt(16);

        for(int i = 0; i < n; i++)
            spaces.append(" ");

        return spaces.toString();
    }

    public String category3(int tokens) {
        int operatorCount = (tokens - 1) / 2;
        int operandCount = operatorCount + 1;

        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int min = 10000, max = 999999, baseTerm, nextTerm, i = 1, j = 0;
        String[] operators = {"+", "-", "*", "/"};
        String op;

        baseTerm = rand.nextInt((max - min) + 1) + min;
        sb.append(baseTerm);
        sb.append(" ");

        do {
            op = operators[rand.nextInt(operators.length)];
            sb.append(op);
            j++;

            if (op.equals("-")) {
                nextTerm = baseTerm + (100000 + rand.nextInt(400001));

                if (nextTerm > 999999) {
                    nextTerm = 999999;
                    if (baseTerm >= 999999)
                        baseTerm = 500000;
                }
            }
            else
                nextTerm = rand.nextInt((max - min) + 1) + min;

            sb.append(this.genWhitespaces());
            sb.append(nextTerm);
            i++;
            sb.append(this.genWhitespaces());
            baseTerm = nextTerm;

        } while (i < operandCount && j < operatorCount);

        return sb.toString().trim();
    }

    // test case 4: invalid operations
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
