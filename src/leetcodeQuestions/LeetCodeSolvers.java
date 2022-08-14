package leetcodeQuestions;

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
}
