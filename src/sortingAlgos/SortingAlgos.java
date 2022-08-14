package sortingAlgos;

public class SortingAlgos {

    public void quickSort(int[] sort, int low, int high) {
        if (low < high) {
            int partitionIndex = portion(sort, low, high);

            quickSort(sort, low, partitionIndex - 1);
            quickSort(sort, partitionIndex + 1, high);
        }
    }

    private int portion(int[] sort, int low, int high) {
        int pivot = sort[high];
        int k = low - 1;

        for (int i = low; i < high; i++) {
            if (sort[i] < pivot) {
                switchArr(sort, ++k, i);
            }
        }

        switchArr(sort, ++k, high);
        return k;
    }

    private void switchArr(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public void mergeSort(int[] toSort, int n) {
        if (n < 2) return;

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = toSort[i];
        }
        for (int j = mid; j < n; j++) {
            r[j - mid] = toSort[j];
        }

        mergeSort(l, mid);
        mergeSort(r, n - mid);

        mergeArr(toSort, l, r);
    }

    private void mergeArr(int[] sol, int[] arr1, int[] arr2) {
        int maxArr1 = arr1.length;
        int maxArr2 = arr2.length;
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < maxArr1 || j < maxArr2) {
            if (i < maxArr1 && (j >= maxArr2 || arr1[i] < arr2[j])) {
                sol[k++] = arr1[i++];
            } else {
                sol[k++] = arr2[j++];
            }
        }
    }
}
