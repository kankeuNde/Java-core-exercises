package com.rnk.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Interview_Lambda_1 {

    public List<Employee> sortEmployeesBySalaryAscending(List<Employee> list){
        List<Employee> sortedListBySalary = list.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList();
        return sortedListBySalary;
    }

    public List<Employee> sortEmployeesBySalaryDescending(List<Employee> list){
        List<Employee> sortedListBySalary = list.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .toList();
        return sortedListBySalary;
    }

    public double getAverageAge(List<Person> people) {
        double average = people.stream()
                .mapToInt(Person::getAge)
                .average().orElse(0.0);
        return average;
    }

    public Map<Boolean, List<Integer>> partitionNumber(List<Integer> nums) {
        Map<Boolean, List<Integer>> collect = nums.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0));
        return collect;
    }

    public Map<Integer, List<String>> groupStringByLength(List<String> strs){
        Map<Integer, List<String>> groupedList = strs.stream()
                .collect(Collectors.groupingBy(s -> s.length()));
        return groupedList;
    }

    public Map<String, Long> countOccurrences(List<String> listOfStr) {
        Map<String, Long> countedMap = listOfStr.stream()
                .collect(
                        Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));
        return countedMap;
    }

    public Map<String, Double> computeAvgByDepartment(List<Employee> employeeList){
        Map<String, Double> collect = employeeList.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment(),
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        return collect;
    }

    /**
     * Find the Most common First Letter Among All Employee Names
     * Given a list of integers, compute the average of every 3-element sliding window
     * Find the longest word in a sentence ignoring case and punctuation
     * Find top 3 most frequent words in a paragraph
     * Reverse each word in a sentence using streams
     */

    /**
     * Find the most common first letter among all employee names
     */
    public Optional<Map.Entry<Character, Long>> findMostCommonFistLetterEmployeeName(List<Employee> listOfEmployee){
        Map<Character, Long> collect = listOfEmployee.stream()
                .map(e -> e.getName().charAt(0))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        Optional<Map.Entry<Character, Long>> max =
                collect.entrySet().stream().max(Map.Entry.comparingByValue());
        return max;

    }

    /**
     *  Given a list of integers, compute the average of every 3-element sliding window
     */
    public List<Double> getSlidingWindowAverage(List<Integer> arr){
        List<Integer> input = arr;

        int window = 3;
        List<Double> list = IntStream.range(0, (input.size() - (window - 1)))
                .mapToObj(i -> input.subList(i, i + window))
                .map(w -> w.stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0.0)
                ).toList();
        return list;
    }
}
