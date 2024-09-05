package com.tzy.algo;

/**
 * @description: 数组相关：快慢指针、左右指针
 * @author: Tao
 * @create: 2024-08-23 17:55
 **/
public class ArrayTest {

    /**
     * 删除有序数组中的重复项,返回非重复的个数
     * 使用快慢指针
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int fast = 1;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    /**
     * 移除元素
     * https://leetcode.cn/problems/remove-element/
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
     * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
     * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
     * 返回 k。
     * [3,2,2,3] ,3
     * [2,2]
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


    /**
     * 移动零 https://leetcode.cn/problems/move-zeroes/
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     */
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }

    }


    /**
     * // 滑动窗口算法框架伪码
     * int left = 0, right = 0;
     * while (right < nums.size()) {
     *     // 增大窗口
     *     window.addLast(nums[right]);
     *     right++;
     *     while (window needs shrink) { //窗口需要收缩
     *         // 缩小窗口
     *         window.removeFirst(nums[left]);
     *         left++;
     *     }
     * }
     */

    /**
     * 两数之和 II - 输入有序数组 https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
     * 给你一个下标从 1 开始的整数数组 numbers，该数组已按非递减顺序排列，
     * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
     * 则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2
     * <p>
     * [5,25,75] 100
     * <p>
     * [-1,-1,1,1] -2
     */
    public int[] twoSum0(int[] numbers, int target) {

        for (int index1 = 0; index1 < numbers.length - 1; index1++) {

            int index2 = index1 + 1;
            while (index2 < numbers.length) {
                if (numbers[index1] + numbers[index2] < target) {
                    index2++;
                    continue;
                }
                if (numbers[index1] + numbers[index2] > target) {
                    break;
                }

                if (numbers[index1] + numbers[index2] == target) {
                    return new int[]{index1 + 1, index2 + 1};
                }
            }
        }
        return null;
    }

    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = numbers.length - 1;
        while (index1 < index2) {
            if (numbers[index1] + numbers[index2] == target) {
                return new int[]{index1 + 1, index2 + 1};
            }
            if (numbers[index1] + numbers[index2] < target) {
                index1++;
                continue;
            }
            if (numbers[index1] + numbers[index2] > target) {
                index2--;
                continue;
            }
        }
        return null;
    }


    /**
     * 最长回文子串 https://leetcode.cn/problems/longest-palindromic-substring
     * 输入：s = "babad"
     * 输出："bab"
     * 输入：s = "cbbd"
     * 输出："bb"
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    //获取回文字符串
    public String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }


}
