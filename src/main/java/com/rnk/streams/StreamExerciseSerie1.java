package com.rnk.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class StreamExerciseSerie1 {
    public static void main(String[] args) {
        StreamExerciseSerie1 serie1 = new StreamExerciseSerie1();

        String sentence = "I am learning streams API in Java";
//        String result = serie1.getHighestLengthWord(sentence);
//         System.out.println(String.format("Highest length word in the sentence: %s", result));
//
//        System.out.println(serie1.removeDuplicateChars("dabcadefg"));
//        System.out.println(serie1.getSecondHighestLengthWord(sentence));
//        System.out.println(serie1.getSecondHighestLengthWordLength(sentence));
//
//        String anotherSentcence = "I am learning Java Streams in Java";
//        System.out.println(serie1.getWordFrequencies(anotherSentcence));
//
//        System.out.println(serie1.getWordsWithXVowels(anotherSentcence, 2));

        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,0,9,9};
        System.out.println(serie1.listSeparator(numbers));

        String word = "Mississipi";
        System.out.println(serie1.getCharactersFrequency(word));

        System.out.println(serie1.sortNumbers(numbers));
        System.out.println(serie1.sumUniqueElts(numbers));
    }

    public String getHighestLengthWord(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .max(comparing(String::length)).get();
    }

    public String removeDuplicateChars(String word){
//        String result = word.chars()
//                .distinct()
//                .mapToObj(c -> String.valueOf((char) c))
//                .reduce(String::concat).get();

        String result = Arrays.stream(word.split(""))
                .distinct()
                .reduce(String::concat).get();
        return result;
    }

    /**
     * Given a sentence, find the word that has the 2nd highest length
     */
    public String getSecondHighestLengthWord(String sentence){
//        Map<String, Integer> lengths = Arrays.stream(sentence.split(" "))
//                .collect(Collectors.toMap(s -> s, String::length));
//        String result = lengths.keySet()
//                .stream()
//                .sorted(comparing(lengths::get).reversed())
//                .limit(2)
//                .toList().get(1);
        String result = Arrays.stream(sentence.split(" "))
                .sorted(comparing(String::length).reversed())
                .skip(1).findFirst().get();
        return result;
    }

    /**
     * Find the second highest length word in a sentence
     */
    public int getSecondHighestLengthWordLength(String sentence){
//        int length = Arrays.stream(sentence.split(" "))
//                .sorted(comparing(String::length).reversed())
//                .skip(1).findFirst().get().length();
        Integer length = Arrays.stream(sentence.split(" "))
                .map(x -> x.length())
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();
        return length;
    }

    /**
     * Given a sentence, find the occurence of each word
     */
    public Map<String, Long> getWordFrequencies(String sentence){
        Map<String, Long> freqs =
                Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return freqs;
    }

    /**
     * Given a word, find the words with a specified number of vowels
     */
    public List<String> getWordsWithXVowels(String sentence, int xVowels){
        List<String> filteredWords = Arrays.stream(sentence.split(" "))
                .filter(word -> word.replaceAll("[^aeiouAEIOU]", "").length() == xVowels)
                .collect(Collectors.toList());
        return filteredWords;
    }

    /**
     * Given a list of integers, divide it into two lists
     * one having an even number and the other having an odd number
     */
    public List<List<Integer>> listSeparator(int[] numbers){
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        List<List<Integer>> seperatedList = list.stream()
                .collect(Collectors.groupingBy(num -> num % 2 == 0, Collectors.toList()))
                .values().stream().collect(Collectors.toList());
        return seperatedList;
    }

    /**
     * Given a word, find the occurence of each character
     */

    public Map<String, Long> getCharactersFrequency(String word){
        Map<String, Long> wordFreqs = Arrays.stream(word.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return wordFreqs;
    }

    /**
     * Given an array of numbers, arrange the numbers in descending/ascending order
     */
    public List<Integer> sortNumbers(int[] numbers){
        List<Integer> sortedList = Arrays.stream(numbers)
                .mapToObj(x -> x)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        return sortedList;
    }

    /**
     * Given an array, find the sum of unique elements
     */
    public Integer sumUniqueElts(int[] numbers){
        int sum = Arrays.stream(numbers)
                .distinct()
                .sum();
        return sum;
    }
}
