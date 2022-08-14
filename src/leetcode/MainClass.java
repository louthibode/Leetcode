package leetcode;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args){
        int[] sol = {1,5,3,8,5};
        int[] sol1 = new int[3];
        int[] test = {1};
        int[] test2 = {3,5};

        new LeetCode().mergeSort(sol, sol.length);
        System.out.println(Arrays.toString(sol));
    }
}
