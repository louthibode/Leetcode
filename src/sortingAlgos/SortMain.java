package sortingAlgos;

import java.util.Arrays;

public class SortMain {
    public static void main(String[] args){
        //  Chaboy we ball for quicksort;
        /*
        int[] sol2 = {1,5,3,8,5};
        new SortingAlgos().quickSort(sol2, 0, sol2.length-1);
        System.out.println(Arrays.toString(sol2));
        */


        //  Now for mergesort;
        int[] sol = {1,5,3,8,5};
        new SortingAlgos().mergeSort(sol, sol.length);
        System.out.println(Arrays.toString(sol));
    }
}
