import java.util.Random;

public class DatasetGenerator {

    // test case 1: hierarchy/chain operators

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
}
