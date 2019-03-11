/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author LEVALLOIS
 */
public class TfIdf {

    public static Map<String, Map<String, Float>> calculateTopTermsPerCategory(Map<String, Multiset<String>> categoriesToTerms) {

        Map<String, Map<String, Float>> result = new TreeMap();
        
        Multiset<String> termsCount = HashMultiset.create();
        for (Entry<String,Multiset<String>> entry : categoriesToTerms.entrySet()) {
            termsCount.addAll(entry.getValue());
        }

        Multiset<String> termsPerCategory;
        Map<String, Float> termsPerCategoryAndTheirRelativeFrequencies;
        for (String category : categoriesToTerms.keySet()) {
            termsPerCategory = categoriesToTerms.get(category);
            termsPerCategoryAndTheirRelativeFrequencies = new HashMap();
            for (Multiset.Entry<String> entry : termsPerCategory.entrySet()) {
                int countTermInThisCategory = entry.getCount();
                float relativeFrequency = (float) (Math.pow(countTermInThisCategory, 1.5) / Math.max(1, termsCount.count(entry.getElement()) - countTermInThisCategory));
                termsPerCategoryAndTheirRelativeFrequencies.put(entry.getElement(), relativeFrequency);
            }
            result.put(category, termsPerCategoryAndTheirRelativeFrequencies);
        }

        return result;

    }

}
