package leetcode;

import java.util.*;

public class LeetCode {
    public int[] twoSum(int[] nums, int target){
        ArrayList<Integer> complements = new ArrayList<Integer>();
        int[] ans = new int[2];

        for(int i = 0; i<nums.length; i++){
            ans[0] = i;
            Integer complement = complements.get(target - nums[i]);

            if(complement != null) {
                ans[1] = complement;
                return ans;
            }
            else{
                complements.set(nums[i], i);
            }
        }

        return null;
    }

    public void quickSort(int[] toSort, int low, int high){
        if(low < high){
            //  Role of this method is only to
            //  make it so that each things on side of the pivot are either smaller or bigger
            int partitionIndex = partition(toSort, low, high);

            quickSort(toSort, 0, partitionIndex - 1);
            quickSort(toSort, partitionIndex + 1, toSort.length - 1);

        }
    }

    public int partition(int[] toSort, int low, int high){
        int pivot = toSort[high];
        int firstSmaller = low - 1;

        for(int j = low; j < high; j++){
            if(toSort[j] <= pivot){
                firstSmaller++;
                switchArrElement(toSort, firstSmaller, j);
            }
        }

        switchArrElement(toSort, ++firstSmaller, high);
        return firstSmaller;
    }

    public int[] switchArrElement(int[] arr, int idx1, int idx2){
        int tempSwitch = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tempSwitch;

        return arr;
    }

    public void mergeSort(int[] sort, int n){
        //  Base case: array is already in shambles
        if(n<2) return;

        int mid = n/2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for(int i=0; i<mid; i++){
            l[i] = sort[i];
        }
        for(int j=mid; j<n; j++){
            r[j-mid] = sort[j];
        }

        mergeSort(l, mid);
        mergeSort(r, n-mid);

        mergeArr(sort, l, r);
    }
    /**
     * Merges to sorted arrays together
     * @param sol the initial array we wanted sorted.
     * @param arr1 the first array to merge
     * @param arr2 the second array to merge
     * @return
     */
    public void mergeArr(int[] sol, int[] arr1, int[] arr2){
        int i = 0; int j = 0; int k = 0;
        int maxArr1 = arr1.length; int maxArr2 = arr2.length;

        while(i < maxArr1 || j < maxArr2){
            if(i < maxArr1  && (j >= maxArr2 || arr1[i] < arr2[j])){
                sol[k++] = arr1[i++];
            }
            else {
                sol[k++] = arr2[j++];
            }
        }
    }
}
