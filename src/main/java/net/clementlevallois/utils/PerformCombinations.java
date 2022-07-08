package net.clementlevallois.utils;

/**
 *
 * @author C. Levallois code adapted from a sample found on Internet.
 */
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PerformCombinations {

    private final String[] table;

    public PerformCombinations(String[] table) throws InterruptedException, IOException {
        this.table = table;

    }

    public Set<String> call() throws InterruptedException, IOException {

        int i = 0;
        Set set = new HashSet();

        //finds all pairs (2) of the brands
        int[] indices;

        CombinationGenerator x = new CombinationGenerator(table.length, 2);

        StringBuilder combination;

        while (x.hasMore()) {
            combination = new StringBuilder();
            indices = x.getNext();
            for (int j = 0; j < indices.length; j++) {
                combination.append(table[indices[j]]).append(",");
            }

            // save all these pairs in a set
            set.add(combination.toString());

        }
        return set;

    }
}
