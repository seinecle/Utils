/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author C. Levallois
 */
public class MultisetMostFrequentFiltering<L> {

    Comparator<Multiset.Entry<L>> byCountDesc;
    Comparator<Multiset.Entry<L>> byCountAsc;

    public MultisetMostFrequentFiltering() {
        this.byCountDesc = (Multiset.Entry<L> e1, Multiset.Entry<L> e2) -> e2.getCount() - e1.getCount();
        this.byCountAsc = (Multiset.Entry<L> e1, Multiset.Entry<L> e2) -> e1.getCount() - e2.getCount();

    }

    public List<Multiset.Entry<L>> sortDesc(Multiset<L> multiset) {
        List<Multiset.Entry<L>> toReturn = new ArrayList(multiset.entrySet());
        Collections.sort(toReturn, byCountDesc);
        return toReturn;
    }

    public List<Multiset.Entry<L>> sortDesckeepMostfrequent(Multiset<L> multiset, int n) {
        List<Multiset.Entry<L>> toReturn = new ArrayList();

        List<Multiset.Entry<L>> input = sortDesc(multiset);

        Iterator<Multiset.Entry<L>> iterator = input.iterator();

        int count = 0;
        Multiset.Entry<L> object;
        while (iterator.hasNext()) {
            object = iterator.next();

            toReturn.add(object);

            if (count == n) {
                break;
            }
            count++;

        }
        return toReturn;
    }

    public List<Multiset.Entry<L>> sortDesckeepAboveMinFreq(Multiset<L> multiset, int n) {
        List<Multiset.Entry<L>> toReturn = new ArrayList();

        List<Multiset.Entry<L>> input = sortDesc(multiset);

        Iterator<Multiset.Entry<L>> iterator = input.iterator();

        Multiset.Entry<L> object;
        while (iterator.hasNext()) {
            object = iterator.next();
            if (object.getCount() > n) {
                toReturn.add(object);
            }

        }
        return toReturn;
    }
}
