package com.rnk.algorithms;

import com.rnk.streams.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class Interview_MS_Round2 {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 10, 3, 1, 0, 2, 5};
        int[] res = targetSum(arr, 15);
        System.out.println(res[0] + ", " + res[1]);

        Employee e1 = new Employee(23, "Kankeu", 123245, "IT");
        Employee e2 = new Employee(47, "Kankeu 1", 123245, "IT");
        Employee e3 = new Employee(27, "Kankeu 4", 123245, "IT");
        Employee e4 = new Employee(93, "Kankeu 3", 123245, "IT");
        Employee e5 = new Employee(43, "Kankeu 5", 123245, "IT");

        Employee[] employees = new Employee[]{e1, e2, e3, e4, e5};
        List<Employee> employeeList = new Interview_MS_Round2().sortEmployee(employees);
        for(Employee e: employeeList){
            System.out.println(e);
        }
    }

    /**
     * Given an array and a target, find the indices from the array whose sum is equal to the target in O(n) complexity
     * @param numbers, array of numbers
     * @param target, targeted sum
     * @return an array of the indices arr[i, j] where sum(numbers[i], numbers[j]) = target
     */
    public static int[] targetSum(int[] numbers, int target){
        int i = 0;
        Map<Integer, Integer> numbersHashmap = new HashMap<>();
        for(int num: numbers){
            int searchedNum = target - num;
            if(numbersHashmap.keySet().contains(searchedNum)){
                int searchedindex = numbersHashmap.get(searchedNum);
                return new int[]{searchedindex, i};
            }else{
                numbersHashmap.put(num, i++);
            }
        }
        return null;
    }

    /**
     * Given a list of Employee(id, name, position, salary), sort them based on their ID using Java 8
     * @param employees
     */
    public  List<Employee> sortEmployee(Employee[] employees){
        List<Employee> sortedEmployees = Arrays.stream(employees)
                        .sorted(
                        Comparator.comparing(
                                        (Employee e) -> e.getId())
                                .reversed()).collect(Collectors.toList());
        return sortedEmployees;
    }



}
