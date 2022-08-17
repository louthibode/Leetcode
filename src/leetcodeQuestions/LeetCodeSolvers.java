package leetcodeQuestions;

import com.sun.source.tree.Tree;

import java.sql.Array;
import java.util.*;


import static java.lang.Math.max;
import static java.lang.Math.toIntExact;

public class LeetCodeSolvers {
    public long buildNumber(ListNode l1) {
        int i = 0;
        long number = 0;

        // Iterates through the list
        // Sum up these items
        while (l1 != null) {

            number += l1.val * Math.pow(10, i++);
            l1 = l1.next;
        }

        return number;
    }

    /**
     * Recursive list builder from number
     *
     * @param number the number left to be put to paper
     * @return
     */
    public ListNode listBuilder(long number, boolean first) {
        if (number == 0) {
            if (first) return new ListNode(0);
            else return null;
        } else {
            return new ListNode(toIntExact(number % 10), listBuilder(number / 10, false));
        }
    }

    public ListNode listBuilder(int[] arr, int idx) {
        if (idx == arr.length) return null;
        else {
            return new ListNode(arr[idx], listBuilder(arr, ++idx));
        }
    }

    public ListNode addLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode curr = head;
        int carry = 0;
        int curValL1 = 0;
        int curValL2 = 0;

        while (l1 != null || l2 != null || carry != 0) {
            curValL1 = getNodeValue(l1);
            curValL2 = getNodeValue(l2);
            int curSum = curValL1 + curValL2 + carry;

            carry = curSum / 9;

            curr.next = new ListNode(curSum % 10);
            curr = curr.next;

            l1 = l1.next;
            l2 = l2.next;

            System.out.println(curSum);
        }

        return head;
    }

    public int getNodeValue(ListNode node) {
        if (node != null) return node.val;
        else return 0;
    }

    /*
    public int lengthOfLongestSubstring(String s) {
        int[] observed = new int[127];
        int streak = 0;
        int maxStreak = 0;

        for (int i = 0; i < s.length(); i++) {
            if (observed[(int) s.charAt(i)] == 1) {
                if (maxStreak < streak) maxStreak = streak;
                streak = 0;
                observed = new int[127];
            }
            streak++;
        }

        return maxStreak;

    }
    */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int l = 0;
        int maxStreak = 0;

        for (int r = 0; r < s.length(); r++) {
            System.out.println(set.contains(s.charAt(r)));
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l++));
            }

            set.add(s.charAt(r));
            if (maxStreak < r - l + 1) maxStreak = r - l + 1;
        }

        return maxStreak;
    }

    public double mergeArr(int[] arr1, int[] arr2) {
        int maxArr1 = arr1.length;
        int maxArr2 = arr2.length;
        int[] sol = new int[maxArr1 + maxArr2];

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

        System.out.println(Arrays.toString(sol));
        return findMedian(sol);
    }

    private double findMedian(int[] sol) {
        int n = sol.length;
        if (sol.length % 2 == 1) return sol[sol.length / 2];
        else {
            return (sol[sol.length / 2] + sol[sol.length / 2 - 1]) / 2.0;
        }
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            else {
                set.add(nums[i]);
            }
        }

        return false;
    }

    public boolean isAnagram(String s, String t) {
        int[] chars1 = new int[26];
        int[] chars2 = new int[26];

        for (int i = 0; i < Math.max(s.length(), t.length()); i++) {
            if (i < s.length()) chars1[(int) s.charAt(i) - 97]++;
            if (i < t.length()) chars2[(int) t.charAt(i) - 97]++;
        }

        return Arrays.equals(chars1, chars2);
    }

    public boolean isAnagramHash(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<Character, Integer>();
        Map<Character, Integer> mapT = new HashMap<Character, Integer>();

        if (s.length() != t.length()) return false;
        else {
            for (int i = 0; i < s.length(); i++) {
                char currS = s.charAt(i);
                char currT = t.charAt(i);

                mapS.put(currS, mapS.get(currS) + 1);
                mapT.put(currT, mapT.get(currT) + 1);
            }
        }

        return mapS.equals(mapT);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        boolean[] isGrouped = new boolean[strs.length];
        List<List<String>> anagramGrouped = new ArrayList<List<String>>();

        for (int i = 0; i < strs.length; i++) {
            if (!isGrouped[i]) {
                List<String> toBunch = new ArrayList<String>();
                toBunch.add(strs[i]);
                isGrouped[i] = true;

                for (int j = i + 1; j < strs.length; j++) {
                    if (!isGrouped[j]) {
                        if (isAnag(strs[i], strs[j])) {
                            isGrouped[j] = true;
                            toBunch.add(strs[j]);
                        }
                    }
                }

                anagramGrouped.add(toBunch);
            }

        }

        return anagramGrouped;
    }

    private boolean isAnag(String s, String t) {
        int[] sChars = new int[26];
        int[] tChars = new int[26];

        if (s.length() != t.length()) return false;
        else {
            for (int i = 0; i < s.length(); i++) {
                sChars[(int) s.charAt(i) - 97]++;
                tChars[(int) t.charAt(i) - 97]++;
            }
        }

        return Arrays.equals(sChars, tChars);
    }

    public List<List<String>> betterSol(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        List<List<String>> bunched = new ArrayList<List<String>>(strs.length);

        for (int i = 0; i < strs.length; i++) {
            int[] count = new int[26];
            String curString = strs[i];

            for (int j = 0; j < curString.length(); j++) count[(int) curString.charAt(j) - 97]++;

            List<String> mapItem = map.get(Arrays.toString(count));

            if (mapItem != null) mapItem.add(curString);
            else {
                List<String> newList = new ArrayList<String>();
                newList.add(curString);
                map.put(Arrays.toString(count), newList);
            }

        }

        for (String key : map.keySet()) {
            bunched.add(map.get(key));
        }

        return bunched;
    }

    public int testSet(int[] array) {
        SortedSet<Integer> orderedSet = new TreeSet<Integer>();

        for (int i = 0; i < array.length; i++) {
            if (!orderedSet.contains(array[i])) orderedSet.add(array[i]);
        }

        Iterator<Integer> ite = orderedSet.iterator();
        int cur = ite.next();
        int maxSeq = 0;
        int seq = 1;

        while (ite.hasNext()) {
            int next = ite.next();
            if (next == cur + 1) {
                seq++;
            } else {
                if (maxSeq < seq) maxSeq = seq;
                seq = 1;
            }
            cur = next;
        }

        if (maxSeq < seq) maxSeq = seq;

        return maxSeq;
    }

    public boolean isPalindrome(String s) {
        int j = s.length() - 1;
        int i = 0;
        while (j >= i) {
            int back = s.charAt(j);
            int forw = s.charAt(i);

            if (!isAlphaNumerical(back)) j--;
            else if (!isAlphaNumerical(forw)) i++;
            else {

                System.out.println("j: " + j + " " + back);
                System.out.println("i: " + i + " " + forw);

                if (!charEquals(back, forw)) return false;
                j--;
                i++;
            }
        }

        return true;
    }

    private boolean isAlphaNumerical(int check) {
        return (check > 47 && check < 58) || (check > 64 && check < 91) || (check > 96 && check < 123);
    }

    private boolean charEquals(int char1, int char2) {
        return char1 == char2 || (char1 > 64 && char2 > 64 && (char1 == char2 + 32 || char1 == char2 - 32));
    }

    public void quickSort(int[] toSort, int low, int high) {
        if (low < high) {


            int middleIndex = partition(toSort, low, high);

            quickSort(toSort, low, middleIndex - 1);
            quickSort(toSort, middleIndex + 1, high);
        }
    }

    private int partition(int[] toSort, int low, int high) {
        int pivot = toSort[high];
        int k = low - 1;

        for (int i = low; i < high; i++) {
            if (toSort[i] < pivot) {
                switchElements(toSort, ++k, i);
            }
        }

        switchElements(toSort, ++k, high);
        return k;
    }

    public void switchElements(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public List<ArrayList<Integer>> twoSum(int[] arr, int idxStart, int baseVal) {
        //  Stores the matches for twoSum;
        HashMap<Integer, Integer> complements = new HashMap<Integer, Integer>();
        List<ArrayList<Integer>> sols = new ArrayList<ArrayList<Integer>>();

        for (int i = idxStart; i < arr.length; i++) {
            if (complements.containsKey(-baseVal - arr[i])) {
                ArrayList<Integer> sol = new ArrayList<>(Arrays.asList(baseVal,
                        arr[complements.get(-baseVal - arr[i])], arr[i]
                ));
                if (!sols.contains(sol)) sols.add(sol);
            } else {
                complements.put(arr[i], i);
            }
        }
        return sols;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        List<List<Integer>> sols = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) i++;
            else {
                sols.addAll(twoSum(nums, i + 1, nums[i]));
            }
        }

        return sols;
    }

    public List<List<Integer>> threeSumSmarter(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        quickSort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int l = i + 1;
                int r = nums.length - 1;
                int sum = nums[i] + nums[l] + nums[r];

                while (l < r) {
                    if (sum > 0) {
                        r--;
                    } else if (sum < 0) {
                        l++;
                    } else {
                        result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[l], nums[r])));
                        do {
                            l++;
                        } while (nums[l] == nums[l - 1]);
                    }
                }
            }
        }
        return result;
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int maxHeight = 0;

        while (l < r) {
            int curHeight = (r - l) * Math.min(height[l], height[r]);

            if (curHeight > maxHeight) maxHeight = curHeight;

            if (height[l] > height[r]) r--;
            else l++;
        }

        return maxHeight;
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int minLow = prices[0];
        int currHigh = 0;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (i == prices.length - 1 || prices[i + 1] < prices[i]) {
                int currProfit = prices[i] - minLow;
                if (currProfit > maxProfit) maxProfit = currProfit;
            } else {
                if (minLow > prices[i]) minLow = prices[i];
            }
        }
        return maxProfit;
    }

    public int maxProfitSmart(int[] prices) {
        int l = 0;
        int r = 0;
        int maxProfit = 0;

        while (r < prices.length) {
            if (prices[r] < prices[l]) {
                l++;
            } else {
                int curProfit = prices[r] - prices[l];
                if (maxProfit < curProfit) maxProfit = curProfit;
                r++;
            }
        }

        return maxProfit;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) return 0;

        Set<Character> observed = new HashSet<Character>();

        int currLength = 1;
        int maxLength = 0;
        int l = 0;

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            if (observed.contains(currChar)) {
                if (maxLength < currLength) maxLength = currLength;
                while (observed.contains(currChar)) {
                    currLength--;
                    observed.remove(s.charAt(l++));
                }
            } else {
                observed.add(currChar);
                currLength++;
            }

            System.out.println("Index: " + i + "  CurrLength: " + currLength);
        }
        return maxLength;
    }

    public int characterReplacement(String s, int k) {
        int maxStreak = 0;
        int currStreak = 0;
        int lastA = 0;

        for (int r = 0; r < s.length(); r++) {
            if (s.charAt(r) != 'A') {
                if (k == 2) currStreak++;
                if (k-- == 1) {
                    lastA = r;
                    currStreak++;
                }
                if (k == 0) {
                    currStreak = r - lastA;
                    lastA = r;
                }
            } else {
                currStreak++;
            }

            if (maxStreak < currStreak) maxStreak = currStreak;
        }

        return maxStreak;
    }

    public int characterReplacementSmart(String s, int k) {
        Map<Character, Integer> count = new HashMap<>(26);
        int res = 0;
        int l = 0;
        int maxFreq = 0;

        for (int i = 0; i < s.length(); i++) {
            //  Inputs the quantity
            int curCount = count.getOrDefault(s.charAt(i), 0) + 1;
            count.put(s.charAt(i), curCount);
            maxFreq = Math.max(maxFreq, curCount);

            //  Make sure that the window is valid
            while (i - l + 1 - maxFreq > k) {
                count.put(s.charAt(l), count.getOrDefault(s.charAt(l), 0) - 1);
                l++;
            }

            //  Updates the result
            res = Math.max(res, i - l + 1);
        }
        return res;
    }

    public int maxCountHashMap(Map<Character, Integer> map) {
        Integer max = 0;
        for (Character key : map.keySet()) {
            if (max < map.get(key)) max = map.get(key);
        }
        return max;
    }

    public int findPivot(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        //  Will this end?
        while (l < r) {
            int pivot = (l + r) / 2;

            if (nums[pivot] > nums[r]) l = pivot + 1;
            else r = pivot;
        }

        return l;
    }

    public int search(int[] nums, int target) {
        int splitOrdered = findPivot(nums);
        return Math.max(binarySearch(nums, target, 0, splitOrdered - 1),
                binarySearch(nums, target, splitOrdered, nums.length - 1));
    }

    public int binarySearch(int[] nums, int target, int low, int high) {
        int targetIdx = -1;
        int l = low;
        int r = high;

        while (l <= r) {
            int pivot = (l + r) / 2;

            //  Binary search algorithm;
            if (nums[pivot] > target) r = pivot - 1;
            else if (nums[pivot] < target) l = pivot + 1;
            else return pivot;
        }

        return targetIdx;
    }

    public int findMinimum(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        //  Will this end?
        while (l < r) {
            int pivot = (l + r) / 2;

            if (nums[pivot] > nums[r]) l = pivot + 1;
            else r = pivot;
        }

        return nums[l];
    }

    public ListNode reverseListRec(ListNode curr, ListNode last) {
        if (curr.next == null) return new ListNode(curr.val, last);
        else {
            return reverseListRec(curr.next, new ListNode(curr.val, last));
        }
    }

    /*
    public void reorderList(ListNode head) {
        if (head == null) return;

        Pair<ListNode, Integer> revList = reverseListRec(head, null, 0);
        mergeList(head, revList.getKey(), revList.getValue());
    }

    private Pair<ListNode, Integer> reverseListRec(ListNode curr, ListNode passed, int k) {
        if (curr.next == null) return new Pair(new ListNode(curr.val, passed), ++k);
        else return reverseListRec(curr.next, new ListNode(curr.val, passed), ++k);
    }
    */
    private void mergeList(ListNode forw, ListNode rev, int k) {
        if (k <= 2) return;

        ListNode forwRef = forw;
        ListNode f1Ref = forw.next;

        for (int i = 0; i < k - 1; i++) {
            if (i % 2 == 0) {
                forwRef.next = rev;
                rev = rev.next;
            } else {
                forwRef.next = f1Ref;
                f1Ref = f1Ref.next;
            }
            forwRef = forwRef.next;
        }

        forwRef.next = null;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;

        ListNode headCop = new ListNode(0, head);

        removeRec(headCop, headCop.next, n);

        return headCop.next;
    }

    public int removeRec(ListNode prev, ListNode curr, int n) {
        int k = -1;

        if (curr.next == null) k = 1;
        else k = removeRec(curr, curr.next, n);

        if (k == n) prev.next = curr.next;
        return ++k;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        else return (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return root == subRoot;
        else
            return (root.val == subRoot.val && isSameTree(root, subRoot)) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> queue = new LinkedList<>();
        List<TreeNode> tempQueue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> sol = new ArrayList<List<Integer>>();
        List<Integer> currList = new ArrayList<Integer>();

        while (queue.size() > 0) {
            TreeNode curr = queue.remove(0);
            if (curr != null) {
                tempQueue.add(curr.left);
                tempQueue.add(curr.right);
                currList.add(curr.val);
            }

            if (queue.size() == 0) {
                sol.add(currList);
                queue = tempQueue;
                tempQueue = new LinkedList<>();
                currList = new ArrayList<>();
            }
        }
        return sol;
    }

    public int kthSmallest(TreeNode root, int k) {
        SortedSet<Integer> set = new TreeSet<>();
        dfs(root, set);
        Iterator<Integer> ite = set.iterator();
        int i = 0;
        int kth = 0;

        while (ite.hasNext() && i < k) kth = ite.next();
        return kth;
    }

    public void dfs(TreeNode root, Set<Integer> set) {
        if (root != null) {
            set.add(root.val);
            dfs(root.left, set);
            dfs(root.right, set);
        }
    }

}
