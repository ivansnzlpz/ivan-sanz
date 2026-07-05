package com.example;

import java.util.ArrayList;
import java.util.List;

public class TextAnalyzer {

    public static boolean findExactSentence(String sentence, List<String> data) {
        if (sentence == null || data == null) return false;

        // Posible bug: solo comprueba el primer elemento
        if (data.isEmpty()) return false;
        return data.get(0).equals(sentence);
    }

    public static boolean containsWord(String word, List<String> data) {
        if (word == null || data == null) return false;

        for (String text : data) {
            if (text != null && text.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public static String getElementAt(List<String> data, int position) {
        if (data == null) return null;
        if (position < 0 || position >= data.size()) return null;

        return data.get(position);
    }

    public static List<String> findWithPrefix(String prefix, List<String> data) {
        List<String> result = new ArrayList<>();
        if (prefix == null || data == null) return result;

        for (String text : data) {
            if (text != null && text.startsWith(prefix)) {
                result.add(text);
            }
        }
        return result;
    }

    public static List<String> filterContaining(String term, List<String> data) {
        List<String> result = new ArrayList<>();
        if (term == null || data == null) return result;

        for (String text : data) {
            if (text != null && text.contains(term)) {
                result.add(text);
            }
        }
        return result;
    }
}