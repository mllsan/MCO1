import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DataSetGenerator {
    public static final String[] operators = {"+", "-", "*", "/", "%", "^"}; 

    // test case 1: hierarchy/chain operators
    public static String Category1(int totalToken) {
        StringBuilder expression = new StringBuilder();
        int currentTokens = 0;
        Random rand = new Random();

        // initialize expression
        int initialOperand = rand.nextInt(9) + 1;
        expression.append(initialOperand);
        currentTokens++;

        while (currentTokens < totalToken) {
            // choose random operator and operand
            String Operator = operators[rand.nextInt(operators.length)];
            int Operand = rand.nextInt(9) + 1;

            // put tgt operator and operand
            expression.append(" ");
            expression.append(Operator);
            expression.append(" ");
            expression.append(Operand);
            
            // changes operator
            currentTokens += 2; 
        }

        return expression.toString();
    }

    // test case 2: nested groupings
    public static String Category2(int tokens) {
        int nests = tokens / 4;

        if (nests < 1)
            nests = 1;

        StringBuilder exp = new StringBuilder();
        Random rand = new Random();
        int min = 1, max = 9, i, term, nextTerm;
        String op;

        for (i = 0; i < nests; i++)
            exp.append("( ");

        term = rand.nextInt((max - min) + 1) + min;
        exp.append(term).append(" ");

        for (i = 0; i < nests; i++) {
            op = operators[rand.nextInt(operators.length)];
            nextTerm = rand.nextInt((max - min) + 1) + min;

            exp.append(op).append(" ").append(nextTerm)
                    .append(" ").append(") ");
        }

        return exp.toString().trim();
    }

    // test case 3: large white spaces, negative results, 5-6 figure tokens
    public static String genWhitespaces() {
        int n;
        StringBuilder spaces = new StringBuilder();
        Random rand = new Random();
        n = rand.nextInt(16);

        for(int i = 0; i < n; i++)
            spaces.append(" ");

        return spaces.toString();
    }

    public static String Category3(int tokens) {
        int operatorCount = (tokens - 1) / 2;
        int operandCount = operatorCount + 1;

        StringBuilder exp = new StringBuilder();
        Random rand = new Random();
        int min = 10000, max = 999999, baseTerm, nextTerm, i = 1, j = 0;
        String[] OPERATORS = {"+", "-", "*", "/"};
        String op;

        baseTerm = rand.nextInt((max - min) + 1) + min;
        exp.append(baseTerm);
        exp.append(" ");

        do {
            op = OPERATORS[rand.nextInt(OPERATORS.length)];
            exp.append(op);
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

            exp.append(DataSetGenerator.genWhitespaces());
            exp.append(nextTerm);
            i++;
            exp.append(DataSetGenerator.genWhitespaces());
            baseTerm = nextTerm;

        } while (i < operandCount && j < operatorCount);

        return exp.toString().trim();
    }

    // test case 4: invalid operations
    public static String Category4(int totalToken, int violationPosition) {
        StringBuilder expression = new StringBuilder();
        Random rand = new Random();
        
        String[] mismatchedParens = {"(", ")", "]", "}"};
        int currentTokens = 0;
        int tokenPosition = 0;
        int Operand = rand.nextInt(9) + 1;

        // where error will be set
        if (violationPosition == 2) {
            tokenPosition = totalToken / 2;
        } 
        else if (violationPosition == 3) {
            tokenPosition = totalToken - 2;
        }

        // initialize expression
        expression.append(Operand);
        currentTokens++;

        while (currentTokens < totalToken) {
            if (currentTokens >= tokenPosition) {
                // 0 = malformed expression, 1 = mismatched parenthesis, 2 = invalid character
                int error = rand.nextInt(3);

                if (error == 0) {
                    String operator1 = operators[rand.nextInt(operators.length)];
                    String operator2 = operators[rand.nextInt(operators.length)];
                    expression.append(" ").append(operator1).append(" ").append(operator2);
                    currentTokens += 2;
                } 
                else if (error == 1) {
                    String operator = operators[rand.nextInt(operators.length)];
                    String parenthesis = mismatchedParens[rand.nextInt(mismatchedParens.length)];
                    expression.append(" ").append(operator).append(" ").append(parenthesis);
                    currentTokens += 2;
                } 
                else if (error == 2) {
                    int invalid = 1;
                    String invalidSymbol = "";
                    String operator = operators[rand.nextInt(operators.length)];
                    do {
                        invalidSymbol = String.valueOf((char)(rand.nextInt(95) + 32));
                        int match = 0;
                        // check that its not operator
                        for (int i = 0; i < 6; i++) {
                            if (operators[i].equals(invalidSymbol)) {
                                match = 1; // It's an operator! We must reject it.
                            }
                        }
                        // check that its not operand
                        if (invalidSymbol.matches("[0-9]")) {
                            match = 1;
                        }
                        
                        if(match == 0)
                            invalid = -1;
                    } while (invalid == 1);
                    
                    expression.append(" ").append(operator).append(" ").append(invalidSymbol);
                    currentTokens += 2;
                }

                tokenPosition = -1;
            }

            // normal expression
            String operator = operators[rand.nextInt(operators.length)];
            int operand = rand.nextInt(9) + 1;
            
            expression.append(" ").append(operator).append(" ").append(operand);
            currentTokens += 2;
        }

        return expression.toString();
    }

    public static void main(String[] args) {
        String file = "testcase.txt";
        String expression = "";
        int choice = -1;
        int position = -1;

        Scanner input = new Scanner (System.in);
        System.out.println("Choose a Category:");
        System.out.println("1 - Operator Hierarchy Rules");
        System.out.println("2 - Nested Groupinds");
        System.out.println("3 - Large White Space, Negative Results, Tokenizer String-Handling");
        System.out.println("4 - Structural Violations");
        System.out.print(">> ");
        choice = input.nextInt();

        if (choice == 4){
            System.out.println();
            System.out.println("Postion of Violation:");
            System.out.println("1 - Front of N-token String");
            System.out.println("2 - Middle of N-token String");
            System.out.println("3 - End of N-token String");
            System.out.print(">> ");
            position = input.nextInt();
        }
        
        // write in file
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int n = 5; n <= 100; n += 1) {
                if (choice == 1)
                    expression = Category1(n);
                else if (choice == 2)
                    expression = Category2(n);
                else if (choice == 3)
                    expression = Category3(n);
                else if (choice == 4)
                    expression = Category4(n, position);

                writer.println(expression);
            } 
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
