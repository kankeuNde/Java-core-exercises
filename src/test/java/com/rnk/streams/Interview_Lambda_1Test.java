package com.rnk.streams;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Interview_Lambda_1Test {

    private static Interview_Lambda_1 interviewLambda1;

    private static List<Employee> listOfEmployees = new ArrayList<>();
    private List<Person> people = Arrays.asList(
            new Person("Marc",  76),
            new Person("John", 65),
            new Person("Peter", 45),
            new Person("Millar", 39),
            new Person("Elisabeth", 50)
    );

    @BeforeAll
    public static void setUp(){
        interviewLambda1 = new Interview_Lambda_1();

        Employee emp1 = new Employee(121, "Robert", 3000, "HR");
        Employee emp2 = new Employee(122, "Serge", 4000, "Finance");
        Employee emp3 = new Employee(123, "Raymond", 5000, "IT");
        Employee emp4 = new Employee(124, "Alain", 3900, "Finance");
        Employee emp5 = new Employee(125, "Cyrille", 3500, "IT");
        listOfEmployees.add(emp1);
        listOfEmployees.add(emp2);
        listOfEmployees.add(emp3);
        listOfEmployees.add(emp4);
        listOfEmployees.add(emp5);
    }

    @Test
    public void testSortEmployeesBySalaryAscending(){
        Interview_Lambda_1 interviewLambda1 = new Interview_Lambda_1();
        List<Employee> sortedBySalary = interviewLambda1.sortEmployeesBySalaryAscending(listOfEmployees);
        for (Employee emp: sortedBySalary)
            System.out.println(emp);
        assertTrue(sortedBySalary.get(0).getSalary() < sortedBySalary.get(4).getSalary());
    }

    @Test
    public void testSortEmployeesBySalaryDescending(){
        Interview_Lambda_1 interviewLambda1 = new Interview_Lambda_1();
        List<Employee> sortedBySalary = interviewLambda1.sortEmployeesBySalaryDescending(listOfEmployees);
        for (Employee emp: sortedBySalary)
            System.out.println(emp);
        assertTrue(sortedBySalary.get(0).getSalary() > sortedBySalary.get(4).getSalary());
    }

    @Test
    public void testGetAverageAge(){
        Interview_Lambda_1 interviewLambda1 = new Interview_Lambda_1();
        double avgAge = interviewLambda1.getAverageAge(people);
        assertEquals(55.0, avgAge, 0.0);
    }

    @Test
    public void testPartitionNumbers(){
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Interview_Lambda_1 interviewLambda1 = new Interview_Lambda_1();
        Map<Boolean, List<Integer>> map = interviewLambda1.partitionNumber(nums);
        assertEquals(4, map.get(true).size());
        assertEquals(5, map.get(false).size());
    }

    @Test
    public void testGroupStringByLength(){
        List<String> listOfStr = Arrays.asList("apple", "bat", "ball", "cat", "banana", "dog", "goat");
        Map<Integer, List<String>> groupedStringByLength = interviewLambda1.groupStringByLength(listOfStr);
        System.out.println(groupedStringByLength);
        assertEquals(4, groupedStringByLength.size());
    }

    @Test
    public void testCountOccurrences(){
        List<String> listOfStr = Arrays.asList("apple", "bat", "ball", "cat", "banana", "dog", "goat", "ball");
        Map<String, Long> map = interviewLambda1.countOccurrences(listOfStr);
        System.out.println(map);
        assertEquals(2, map.get("ball"));
    }

    @Test
    public void testComputeAvgByDepartment(){
        Map<String, Double> avgByDepartment = interviewLambda1.computeAvgByDepartment(listOfEmployees);
        System.out.println(avgByDepartment);
        assertTrue(avgByDepartment.size() == 3);
        assertEquals(3950.0, avgByDepartment.get("Finance"));
    }

    @Test
    public void testFindFirstMostCommonLetter(){
        Optional<Map.Entry<Character, Long>> mostCommonFistLetterEmployeeName =
                interviewLambda1.findMostCommonFistLetterEmployeeName(listOfEmployees);
        assertEquals('R', mostCommonFistLetterEmployeeName.get().getKey());
        assertEquals(2, mostCommonFistLetterEmployeeName.get().getValue());

    }

    @Test
    public void testSlidingWindowAverage(){
        List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Double> slidingWindowAverage = interviewLambda1.getSlidingWindowAverage(input);
        System.out.println(slidingWindowAverage);
    }
}
