package com.tzy.algo;

import com.tzy.entity.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: TZY
 * @create: 2024/10/23 11:34
 **/
public class BFS {


    /**
     * 二叉树的最小深 https://leetcode.cn/problems/minimum-depth-of-binary-tree/
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * <p>
     * 深度优先遍历使用：Stack
     * 广度优先：Queue
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            // 将当前队列中的所有节点向四周扩散
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                // 判断是否到达终点
                if (cur.left == null && cur.right == null)
                    return depth;
                // 将 cur 的相邻节点加入队列
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            // 这里增加步数
            depth++;
        }
        return depth;
    }

    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.right == null && curNode.left == null) {
                    return depth;
                }
                if (curNode.left != null) queue.offer(curNode.left);
                if (curNode.right != null) queue.offer(curNode.right);
            }
            depth++;
        }
        return depth;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (queue != null) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.right == null && curNode.left == null) {
                    return depth;
                }
                if (curNode.left != null) queue.offer(curNode.left);
                if (curNode.right != null) queue.offer(curNode.right);
            }
            depth++;
        }
        return depth;
    }


    /**
     * 打开转盘锁（https://leetcode.cn/problems/open-the-lock/）
     * <p>
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     */
    public int openLock(String[] deadends, String target) {
        //先记录死亡路径
        HashSet<String> deadSet = new HashSet<>();
        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        //记录走过的路径
        HashSet<String> visited = new HashSet<>();//记录所有访问过的路径
        Queue<String> q = new LinkedList<>();//记录当前的路径,在遍历过程中q里的每个节点都是对外扩散的起点

        // 从起点开始启动广度优先搜 索
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()) {
            int size = q.size();
            //从0000开始扩散
            for (int i = 0; i < size; i++) {
                //获取当前节点
                String cur = q.poll();
                //判断是否合法
                if (deadSet.contains(cur)) continue;
                //判断是否到终点
                if (cur.equals(target)) return step;
                //将周围节点加入
                for (int j = 0; j < 4; j++) {
                    String upOne = upOne(cur, j);
                    if(!visited.contains(upOne)){
                        q.offer(upOne);
                        visited.add(upOne);
                    }
                    String downOne = downOne(cur, j);
                    if(!visited.contains(downOne)){
                        q.offer(downOne);
                        visited.add(downOne);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String downOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        char ch = chars[j];
        if(ch=='0'){
            ch='9';
        }else {
            ch-=1;
        }
        chars[j]=ch;
        return new String(chars);
    }


    //向上拨动
    private String upOne(String s, int j) {
        char[] chars = s.toCharArray();

        char a = chars[j];
        if(a=='9'){
            chars[j]='0';
        }else {
            chars[j]+=1;
        }
        return new String(chars);
    }


}
