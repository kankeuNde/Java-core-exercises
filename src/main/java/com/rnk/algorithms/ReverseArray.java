package com.rnk.algorithms;

public class ReverseArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};

        int start = 0;
        int end = arr.length - 1;
        int temp = 0;

        while(start < end){
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        for(int el: arr)
            System.out.print(el+" ");
    }
}
