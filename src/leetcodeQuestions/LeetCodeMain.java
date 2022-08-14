package leetcodeQuestions;

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

        int[] test2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(test.testSet(test2));
    }
}
