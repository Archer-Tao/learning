package com.tzy.algo;

import com.tzy.entity.ListNode;
import com.tzy.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯
 * @author: TZY
 * @create: 2024/10/30 10:30
 **/
public class ReCall {

    public void treeTest(TreeNode node){
        System.out.println(node.val);
        treeTest(node.left);
        treeTest(node.right);
    }









    /**
     * 子集（https://leetcode.cn/problems/subsets/）
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 示例 2：
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    public List<List<Integer>> subsets1(int[] nums){
        solutipn2(0,nums);
        return res;
    }

    void  solutipn2(int start,int [] nums){
        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            solutipn2(i,nums);
            res.add(track);
            track.removeLast();
        }
    }


    private List<List<Integer>> res = new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        solution(0,nums);
        return res;
    }
    private void solution(int start,int[] nums){
        res.add(new LinkedList<>(track));//前序遍历
        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            solution(i+1,nums);
            track.removeLast();
        }
    }









}
