package com.tzy.algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法
 *
 * @author: TZY
 * @create: 2024/10/1 20:30
 **/
public class Backtracking {

    /**
     * 全排列 （https://leetcode.cn/problems/permutations/description/）
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        perSolution(nums, track);
        return res;
    }

    List<List<Integer>> res = new LinkedList<>();

    private void perSolution(int[] nums, LinkedList<Integer> track) {
        if (nums.length == track.size()) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            perSolution(nums, track);
            track.removeLast();
        }
    }

    private void perSolution1(int[] nums, LinkedList<Integer> track){
        if(track.size()==nums.length){
            res.add(new ArrayList<>(track));
            return;
        }
        for (int num : nums) {
            if(res.contains(num)){continue;}
            track.add(num);
            perSolution1(nums, track);
            track.removeLast();
        }
    }


    /**
     * 下一个排列 https://leetcode.cn/problems/next-permutation/solutions/479151/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 全排列的下一个排列，字典序算法 逆序对
     * 考虑位置对整体数值的影响，高位最小值大于低位最大值
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        // 从后往前找到第一个逆序对，保证了逆序对右边的都是递减的
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 从右向左找到第一个大于逆序点的数，因为右边都是递减的
        if (i >= 0) {
            int j = n - 1;
            while (j > i && nums[i] >= nums[j]) {
                j--;
            }
            //交换位置
            swap(nums, i, j);
        }
        //将i右边（低位）进行翻转，因为交换之后右边也是降序的
        int m = i + 1;
        int k = n - 1;

        while (m < k) {
            swap(nums, m, k);
            m++;
            k--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }


    public static void main(String[] args) {

    }
}
