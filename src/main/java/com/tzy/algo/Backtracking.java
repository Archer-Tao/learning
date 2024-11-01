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

    private void perSolution1(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int num : nums) {
            if (res.contains(num)) {
                continue;
            }
            track.add(num);
            perSolution1(nums, track);
            track.removeLast();
        }
    }


    /**
     * 下一个排列 https://leetcode.cn/problems/next-permutation/solutions/479151/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 全排列的下一个排列，字典序算法 逆序对
     * 考虑位置对整体数值的影响，高位最小值大于低位最大值
     * 1 2 6 2 4 3   ——>
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        // 从后往前找到第一个顺序对，保证了逆序对右边的都是递减的
        int i = n - 2;// n-1-1获得倒数第2个元素

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 从右向左找到第一个大于逆序点的数，因为右边都是递减的,逆序点最右边的数字应该是最小的
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


    public void nextPermutation1(int[] nums) {
        /**
         * 67321 —> 76123
         * 1 2 6 1 4 3   ——>  126341
         * 思路：全排列说明是字典序
         * 重点是理解字典序的结构：
         *    怎么找到i位置
         *    i位置左边是不需要改变的
         *    i位置右边 是最大排序，完全翻转就是最小排序
         *    在字典序中 i 位置后面的数特点是什么？
         *
         * 找到大于当前的数     1 2 6 1 4 3   ——>  126341
         */
        if (nums.length <= 1) {
            return;
        }
        int n = nums.length;
        int i = n - 2;
        while (i > 0 && nums[i] < nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // i+1 右边是非递增顺序，但是可能大于i，从右向左找到第一个大于i的数就是大于i的最小数
            int j = n - 1;
            while (j > i && nums[j] < nums[i]) {
                j--;
            }
            if (j > i) {
                swap(nums, i, j);
            }
        }
        //翻转i位置右边的数据
        int k = i + 1;
        int j = n - 1;
        while (k < j) {
            swap(nums, k, j);
            k++;
            j--;
        }

    }


    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }


    /**
     * N 皇后 https://leetcode.cn/problems/n-queens/description/
     * <p>
     * 输入：n = 1
     * 输出：[["Q"]]
     * <p>
     * n = 4
     * [
     * [".Q..","...Q","Q...","..Q."],
     * ["..Q.","Q...","...Q",".Q.."]
     * ]
     */
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 每个字符串代表一行，字符串列表代表一个棋盘
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            board.add(".".repeat(n));
        }
        backtrack(board, 0);
        return result;
    }


    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    // 在每次使用回溯或者递归的时候，要考虑是不是应该进行 撤销
    public void backtrack(List<String> board, int row) {
        // 触发结束条件
        if (row == board.size()) {
            result.add(new ArrayList<>(board));
            return;
        }
        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            char[] rowChars = board.get(row).toCharArray();
            rowChars[col] = 'Q';
            board.set(row, new String(rowChars));
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            rowChars[col] = '.';
            board.set(row, new String(rowChars));
        }
    }

    /**
     * for (初始化语句; 条件判断; 每次迭代后的操作) {
     * // 循环体
     * }
     **/
    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        //检查左上方是否冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        //检查右上方是否冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }




}
