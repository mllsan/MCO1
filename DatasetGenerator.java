import java.util.Random;

public class DatasetGenerator {

    // test case 1: hierarchy/chain operators

    // test case 2: nested groupings
    public String generateCase2(int tokens) {
        int nests = tokens / 4;

        if (nests < 1)
            nests = 1;

        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int min = 1, max = 9, i, term, nextTerm;
        String[] operators = {"+","-","*","/","%","^"};
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

    // test case 3: large white spaces, negative results

    // test case 4: invalid operations
}