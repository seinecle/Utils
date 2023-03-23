/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author LEVALLOIS
 */
public class TfIdf {

    /**
     *
     * @param categoriesToTerms
     * @return
     */
    public static Map<String, Map<String, Float>> calculateTopTermsPerCategory(Map<String, Multiset<String>> categoriesToTerms) {

        Map<String, Map<String, Float>> result = new TreeMap();
        
        Multiset<String> termsCount = new Multiset();
        for (Entry<String,Multiset<String>> entry : categoriesToTerms.entrySet()) {
            termsCount.addAllFromMultiset(entry.getValue());
        }

        Multiset<String> termsPerCategory;
        Map<String, Float> termsPerCategoryAndTheirRelativeFrequencies;
        for (String category : categoriesToTerms.keySet()) {
            termsPerCategory = categoriesToTerms.get(category);
            termsPerCategoryAndTheirRelativeFrequencies = new HashMap();
            for (Entry<String,Integer> entry : termsPerCategory.getEntrySet()) {
                int countTermInThisCategory = entry.getValue();
                // count of the word in this doc  / Max (1, count total du term across docs - count du term in this doc)
                float relativeFrequency = (float) (Math.pow(countTermInThisCategory, 1.5) / Math.max(1, termsCount.getCount(entry.getKey()) - countTermInThisCategory));
                termsPerCategoryAndTheirRelativeFrequencies.put(entry.getKey(), relativeFrequency);
            }
            result.put(category, termsPerCategoryAndTheirRelativeFrequencies);
        }

        return result;

    }

}
