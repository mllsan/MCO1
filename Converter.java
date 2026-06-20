public class Converter {
    private String notation;

    public Converter() {
    }

    private int precedence(String token) {
        int level = -1;
        if (token.equals("(") || token.equals(")")) {
            level = 4;
        } else if (token.equals("^")) {
            level = 3;
        } else if (token.equals("*") || token.equals("/") || token.equals("%")) {
            level = 2;
        } else if (token.equals("+") || token.equals("-")) {
            level = 1;
        }
        return level;
    }

    public String toPostfix(String ntn) {
        Stack tempS = new Stack();
        Queue tempQ = new Queue();
        boolean isError = false;
        int i = 0;
        String errorMessage = "";
        String prevToken = "None";

        while (i < ntn.length() && !isError) {
            char c = ntn.charAt(i);
            String currentToken = Character.toString(c);

            // ignore white spaces
            if (c == ' ') {
                i++;
            }

            // operand
            else if (Character.isDigit(c)) {
                StringBuilder PostFix = new StringBuilder();

                // if number is not single digit
                while (i < ntn.length() && Character.isDigit(ntn.charAt(i))) {
                    PostFix.append(ntn.charAt(i));
                    i++;
                }

                tempQ.enqueue(PostFix.toString());
                prevToken = "Operand";
            }

            // parenthesis
            else if (currentToken.equals("(")) {
                tempS.push(currentToken);
                prevToken = "OpenParen";
                i++;
            }

            else if (currentToken.equals(")")) {
                if (prevToken.equals("OpenParen")) {
                    isError = true;
                    errorMessage = "Empty Parenthesis";
                }

                // pops and enqueues equation inside parenthesis
                while (!tempS.isEmpty() && !tempS.top().equals("(") && !isError) {
                    tempQ.enqueue(tempS.pop());
                }
                
                if (!tempS.isEmpty() && tempS.top().equals("(")) {
                    tempS.pop();
                } 
                else {
                    isError = true;
                    errorMessage = "Missing Parenthesis";
                }

                prevToken = "CloseParen";
                i++;
            }

            // operator
            else if (precedence(currentToken) > 0) {
                // Check for errors
                if (prevToken.equals("Operator") || prevToken.equals("OpenParen") || prevToken.equals("None")) {
                    isError = true;
                    errorMessage = "Invalid expression";
                }
                
                // Check division by zero
                if ((currentToken.equals("/") || currentToken.equals("%")) && !isError) {
                    int nextToken = i + 1;
                    while (nextToken < ntn.length() && ntn.charAt(nextToken) == ' ') {
                        nextToken++;
                    }
                    if (nextToken < ntn.length() && ntn.charAt(nextToken) == '0') {
                        isError = true;
                        errorMessage = "Operation is undefined";
                    }
                }

                while (!tempS.isEmpty() && !tempS.top().equals("(") && precedence(tempS.top()) >= precedence(currentToken) && !isError) {
                    tempQ.enqueue(tempS.pop());
                }
                
                tempS.push(currentToken);
                prevToken = "Operator";
                i++;
            }

        else {
            isError = true;
            errorMessage = "Invalid character token";
        }
    }

        // Check for extra operators at end
        if (prevToken.equals("Operator") && !isError) {
            isError = true;
            errorMessage = "Invalid Expression";
        }

        while (!tempS.isEmpty() && !isError) {
            String topToken = tempS.pop();
            // check for unclosed parenthesis
            if (topToken.equals("(") || topToken.equals(")")) {
                isError = true;
                errorMessage = "Missing Parenthesis";
            } 
            else {
                tempQ.enqueue(topToken);
            }
        }

        // create string to store postfix equation
        StringBuilder Equation = new StringBuilder();
        String result = "";
        if (!isError) {
            while (!tempQ.isEmpty()) {
                Equation.append(tempQ.dequeue()).append(" ");
                result = "Postfix: " + Equation.toString().trim();
            }
        } 
        else {
            result = "Error: " + errorMessage;
        }

        return result;
    }
}