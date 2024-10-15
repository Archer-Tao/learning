package com.tzy.algo;

/**
 * 递归
 *
 * @author: TZY
 * @create: 2024/9/13 17:07
 **/
public class Recursive {
    //函数、边界、递推公式

    public static void main(String[] args) {
        int jump = jump(4);
        System.out.println("jump: " + jump);
    }

    /**
     * 一只青蛙一次可以跳上 1 级 或者 2 级 台阶，问青蛙跳上一个有 n 级台阶的楼梯，总共有多少种跳法？
     */
    public static int jump(int n) {
        System.out.println("n: " + n);
        // 终止条件
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n < 1) return 0;
        return jump(n - 1) + jump(n - 2);
    }


    /**
     * 斐波那契数列
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
     * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     */
    int fib(int n) {
        if(n==0)return 0;
        if(n==1) return 1;
        return fib(n-1)+fib(n-2);
    }





}
