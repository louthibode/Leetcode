package leetcode;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        int[] sol = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] sol1 = new int[3];
        int[] test = {1};
        int[] test2 = {3, 5};

        new LeetCode().quickSort(sol, 0, sol.length - 1);
        System.out.println(Arrays.toString(sol));
    }
}
