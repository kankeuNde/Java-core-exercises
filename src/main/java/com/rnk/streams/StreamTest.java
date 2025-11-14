package com.rnk.streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        /*Stream<String> streamEmpty = Stream.empty();

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n+2).limit(20);

        IntStream streamOfChars = "abc".chars();
        streamOfChars.forEach(System.out::println);

        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");
        streamOfString.forEach(System.out::println);

        List<String> listOfString = Arrays.asList("Hello", "World", "Here");
        ArrayList<Character> characters = listOfString.stream().flatMap(s -> s.chars().mapToObj(c -> (char) c)).collect(Collectors.toCollection(ArrayList::new));
        characters.stream().forEach(System.out::println);*/

        // Create a stream
        /*List<Integer> numbers = Arrays.asList(1,3,8,4,9,4,5,0,2,6);
        Stream<Integer> streamNumbers = numbers.stream();
        streamNumbers.forEach(System.out::println);
        //Filter even numbers from a list
        List<Integer> evenNumbers = numbers.stream().filter(getEvenNumber()).collect(Collectors.toList());
        evenNumbers.forEach(System.out::println);
        // Convert numbers in list to their squares
        List<Integer> squares = numbers.stream().map(x -> x * x).collect(Collectors.toList());
        squares.forEach(System.out::println);
        //Find the first number greater than 10 from list
        Optional<Integer> firstGreaterThanTen = numbers.stream().filter(x -> x > 10).findFirst();
        firstGreaterThanTen.ifPresent(System.out::println);
        // Count how many numbers are greater than 5 in List
        long count = numbers.stream().filter(x -> x > 5).count();
        System.out.println(count);
        // Find the sum of all numbers in list
        Integer sumNumbers = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sumNumbers);
        // Find the product of all numbers in list
        Integer product = numbers.stream().reduce(1, (x, y) -> x * y);
        System.out.println(product);
        //Find sum of even numbers in list
        Integer sumOfEven = numbers.stream().filter(getEvenNumber()).reduce(0, Integer::sum);
        System.out.println(sumOfEven);
        //Find the maximum number in list
        Integer maximum = numbers.stream().reduce(0, Integer::max);
        System.out.println(maximum);
        //Sum of squares of even numbers in list
        Integer sumOfSquaresOfEven = numbers.stream().filter(getEvenNumber()).map(x -> x * x).reduce(0, Integer::sum);
        System.out.println(sumOfSquaresOfEven);

        String[] anArray = new String[]{"one", "two", "three"};
        Stream<String> stringStream = Arrays.stream(anArray).map(s -> s.toUpperCase());
        stringStream.forEach(System.out::println);

        int[] anotherArray = new int[]{1,2,3,4,5,0};
        int i = Arrays.binarySearch(anotherArray, 4);
        int j = Arrays.binarySearch(anotherArray, 8);
        System.out.println(i);
        System.out.println(j);

        int[] anotherArray2 = new int[]{6,7,8,9,10};
        int[] resultArray = new int[anotherArray.length + anotherArray2.length];
        //add the first array in the result array
        int k=0;
        for(; k<anotherArray.length;k++){
            resultArray[k] = anotherArray[k];
        }
        for(int m=0; m<anotherArray2.length; m++){
            resultArray[k++] = anotherArray2[m];
        }
        for(int el : resultArray) {
            System.out.println(el);
        }*/
        String str = "My name is Robert";
        String[] splitted = str.split(" ", 2);
        for(int i=0; i< splitted.length; i++)
            System.out.println(splitted[i]);
        System.out.println("Does str contains Rober? " + str.contains("Rober"));
        System.out.println("Length of str = "+str.length());

        String str1 = "name";
        String str2 = new String("name").intern();

        if(str1 == str2){
            System.out.println("str1 points to the same reference as str2");
        }
        /**
         *
         */
        List<String> words = new ArrayList<>();
        words.add("the");
        words.add("log");
        words.add("error");
        words.add("warning");
        words.add("info");
        words.add("error");
        words.add("warning");
        words.add("info");
        words.add("error");
        words.add("warning");
        words.add("info");
        List<Integer> freqs = wordFrequency(words);
        freqs.stream().forEach(System.out::println);
    }

    private static Predicate<Integer> getEvenNumber() {
        return x -> x % 2 == 0;
    }

    public Stream<String> streamOf(List<String> list){
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }

    public static List<Integer> wordFrequency(List<String> words){
        Map<String, Long> wordsFreqs = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> listOfFrqs = wordsFreqs.values().stream()
                .map(x -> x.intValue()).collect(Collectors.toList());
        return listOfFrqs;
    }


}
