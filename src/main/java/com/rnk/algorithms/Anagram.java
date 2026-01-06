package com.rnk.algorithms;

import java.util.Arrays;

public class Anagram {
    public boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        Arrays.sort(s1Array);
        Arrays.sort(s2Array);

        System.out.println();

        for(int i=0;i<s1Array.length;i++){
            if(s1Array[i]!=s2Array[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        System.out.println(anagram.isAnagram("rat","car"));
    }
}
