package leetcodeQuestions;

import java.util.Arrays;

public class LeetCodeMain {
    public static void main(String[] args) {

        LeetCodeSolvers test = new LeetCodeSolvers();
        /*
        int[] toBuild = {2, 4, 3};
        int[] toBuildAsWell = {5, 6, 4};

        ListNode head1 = test.listBuilder(toBuild, 0);
        ListNode head2 = test.listBuilder(toBuildAsWell, 0);
        //int number = test.buildNumber(head);

        test.addLists(head1, head2);
        //System.out.println(number);
        */

        String s = "abcabcbb";
        // System.out.println(test.lengthOfLongestSubstring(s));

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        // System.out.println(test.mergeArr(nums1, nums2));

        /*
        String[] arr1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(test.betterSol(arr1));
        */

        //  Finally, well this is embarassing
        int[] tfGoinOn = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        // System.out.println(test.testSet(test2));

        int[] nums = {-1, 0, 1, 2, -1, -4};
        //System.out.println(test.threeSum(nums));
        // Arrays.stream(nums).sorted().toArray();
        //System.out.println(Arrays.toString(nums));

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        // System.out.println(test.maxArea(height));

        int[] prices = {5, 5, 4, 9, 3, 8, 5, 5, 1, 6, 8, 3, 4};
        System.out.println(test.maxProfitSmart(prices));
    }
}
