package com.tzy.algo;

import com.tzy.entity.ListNode;
import com.tzy.entity.TreeNode;

import java.util.*;

/**
 * 动态规划 Dynamic Programming
 *
 * @author: TZY
 * @create: 2024/9/13 18:16
 **/
public class DP {


    /**
     * 重叠子问题：问题可以被分解为多个子问题，并且这些子问题会被多次重复计算。
     * 最优子结构：问题的最优解可以通过子问题的最优解推导出来。
     * 重叠子问题、最优子结构、状态转移方程就是动态规划三要素
     *
     * <p>
     * 打家劫舍问题
     * 背包问题
     * 最长上升子序列
     * 最长公共子序列
     * 编辑距离
     * </p>
     */
    public static void main(String[] args) {

    }

    /**
     * 一只青蛙一次可以跳上 1 级 或者 2 级 台阶，问青蛙跳上一个有 n 级台阶的楼梯，总共有多少种跳法？
     */
    public int jump(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return jump(n - 1) + jump(n - 2);
    }

    /**
     * 递归方式反转单链表
     */
    public ListNode reverseNode(ListNode node) {
        if (node.next == null || node.next.next == null) {
            return node;
        }
        ListNode reverse = reverseNode(node.next);
        node.next.next = node;
        node.next = null;
        return reverse;
    }


    public void dPTree(TreeNode treeNode) {
        if (treeNode.left == null && treeNode.right == null) return;
        System.out.println(treeNode.val);
        dPTree(treeNode.left);
        dPTree(treeNode.right);
    }

    /**
     * 斐波那契数列
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
     * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     */
    int fib1(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 优化1
     * 思路：逆推，从1->n
     */
    int fib2(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int i_2 = 0, i_1 = 1;
        for (int i = 2; i <= n; i++) {
            int dp_i = i_2 + i_1;
            //逆推
            i_2 = i_1;
            i_1 = dp_i;
        }
        return i_1;
    }


    /**
     * 322. 零钱兑换 https://leetcode.cn/problems/coin-change/
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 比如说 k = 3，面值分别为 1，2，5，总金额 amount = 11。那么最少需要 3 枚硬币凑出，即 11 = 5 + 5 + 1。
     * <p>
     * 1、确定「状态」，也就是原问题和子问题中会变化的变量。由于硬币数量无限，硬币的面额也是题目给定的，只有目标金额会不断地向 base case 靠近，
     * 所以唯一的「状态」就是目标金额 amount。
     * <p>
     * 2、确定「选择」，也就是导致「状态」产生变化的行为。目标金额为什么变化呢，因为你在选择硬币，你每选择一枚硬币，就相当于减少了目标金额。
     * 所以说所有硬币的面值，就是你的「选择」。
     * <p>
     * 3、明确 dp 函数/数组的定义。我们这里讲的是自顶向下的解法，所以会有一个递归的 dp 函数，一般来说函数的参数就是状态转移中会变化的量，
     * 也就是上面说到的「状态」；函数的返回值就是题目要求我们计算的量。就本题来说，状态只有一个，
     * 即「目标金额」，题目要求我们计算凑出目标金额所需的最少硬币数量。
     */
    private int k = -1;

    public int coinChange(int[] coins, int amount) {
        /**
         *
         */
        if (amount < 0) return -1;
        if (amount == 0) return k;

        return dpCoin(coins, amount);
    }

    /**
     * 意识到自己的渺小
     * <p>
     * 分解问题
     * <p>
     * 重叠子问题：问题可以被分解为多个子问题，并且这些子问题会被多次重复计算。
     * 最优子结构：问题的最优解可以通过子问题的最优解推导出来。
     * 状态转移方程：
     * 重叠子问题、最优子结构、状态转移方程就是动态规划三要素
     */
    int dpCoin(int[] coins, int amount) {
        if (amount == 0) return -1;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dpCoin(coins, amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    /**
     * 983. 最低票价 https://leetcode.cn/problems/minimum-cost-for-tickets/
     * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，
     * 你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
     * <p>
     * 火车票有 三种不同的销售方式 ：
     * <p>
     * 一张 为期一天 的通行证售价为 costs[0] 美元；
     * 一张 为期七天 的通行证售价为 costs[1] 美元；
     * 一张 为期三十天 的通行证售价为 costs[2] 美元。
     * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，
     * 那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
     * <p>
     * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
     * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
     * 输出：11
     * 解释：
     * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
     * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
     * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
     * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
     * 你总共花了 $11，并完成了你计划的每一天旅行。
     */
    public int mincostTickets(int[] days, int[] costs) {
        /**
         * 思路：
         * 1. 动态规划定义：我们定义dp[i]为到第i天的最小发费
         *
         * 2. 状态转移方程：
         *      购买一天通行证：dp[i] = dp[i-1] + costs[0]
         *      购买7天通行证：dp[i] = dp[i-7] + costs[1]
         *      购买21天通行证：dp[i] = dp[i-30] +costs[2]

         */
        if (days.length == 0) return 0;
        int lastDay = days[days.length - 1];  // 最后一天
        int[] dp = new int[lastDay + 1];
        dp[0]=0;
        Set<Integer> travelDays = new HashSet<>();  // 用来标记需要旅行的天数
        for (int day : days) {
            travelDays.add(day);
        }
        for (int i = 1; i <= lastDay; i++) {
            if (!travelDays.contains(i)) {
                dp[i] = dp[i - 1];
            } else {
                //在第i天的消费
                dp[i] = dp[i - 1] + costs[0]; //购买一天通行证
                dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]); //购买7天通行证，
                dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]); //购买30天通行证，
            }
        }
        return dp[lastDay];
    }


    public int mincostTickets1(int[] days, int[] costs) {
        /**
         * 思路：
         * 定义dp[i]为第i天的最小总开销  dp[i]= dp[i-1]+costs[x]
         */
        if(days.length==0)return 0;
        int lastDay= days.length;
        int[] dp = new int[lastDay + 1];
        dp[0]=0;
        HashSet<Integer> daySet = new HashSet<>();
        for (int day : days) {
            daySet.add(day);
        }
        //定义第i天的消费情况
        for (int i = 1; i < lastDay; i++) {
            if(!daySet.contains(days[i])){
              dp[i]=dp[i-1];
            }else {
                dp[i]=dp[i-1]+costs[0]; //购买一天
                dp[i]=Math.min(dp[i], dp[Math.max(0,i-7)]+costs[1]);
                dp[i]=Math.min(dp[i], dp[Math.max(0,i-30)]+costs[0]);
            }
        }
        return dp[lastDay];
    }

}
