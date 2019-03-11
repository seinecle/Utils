/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author LEVALLOIS
 */
public class NGramFinderBis {
    
    
    
    public Multiset<String> generateNgramsUpto(String str, int maxGramSize, boolean binary) {

    List<String> sentence = Arrays.asList(str.split("[\\W+]"));

    Multiset<String> ngrams = HashMultiset.create(sentence.size()*maxGramSize* maxGramSize);
    int ngramSize = 0;
    StringBuilder sb = null;

    //sentence becomes ngrams
    for (ListIterator<String> it = sentence.listIterator(); it.hasNext();) {
        String word = it.next();

        //1- add the word itself
        sb = new StringBuilder(word);
        ngrams.add(word);
        ngramSize=1;
        it.previous();

        //2- insert prevs of the word and add those too
        while(it.hasPrevious() && ngramSize<maxGramSize){
            sb.insert(0,' ');
            sb.insert(0,it.previous());
            ngrams.add(sb.toString());
            ngramSize++;
        }

        //go back to initial position
        while(ngramSize>0){
            ngramSize--;
            it.next();
        }                   
    }
    
    
    if (binary){
        ngrams = HashMultiset.create(ngrams.elementSet());
    }
    
    return ngrams;
}
    
}
