/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author C. Levallois
 */
public class MultisetMostFrequentFiltering<L> {

    public MultisetMostFrequentFiltering() {

    }

    public List<Multiset.Entry<L>> keepMostfrequent(Multiset<L> multiset, int n) {
        List<Multiset.Entry<L>> toReturn = new ArrayList();

        ImmutableSet<Multiset.Entry<L>> entriesSortedByCount = Multisets.copyHighestCountFirst(multiset).entrySet();
        Iterator<Multiset.Entry<L>> multisetIterator;
        multisetIterator = entriesSortedByCount.iterator();
        int count = 0;
        Multiset.Entry<L> object;
        while (multisetIterator.hasNext()) {
            count++;
            object = multisetIterator.next();
            toReturn.add(object);
            if (count == n) {
                break;
            }
        }
        return toReturn;
    }
}
