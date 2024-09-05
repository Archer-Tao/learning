package com.tzy.algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 滑动窗口
 * @author: Tao
 * @create: 2024-08-28 11:02
 **/
public class SlideWindow {


    /**
     * 最小覆盖子串 | https://leetcode.cn/problems/minimum-window-substring/
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 隐含条件：子串不重复
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] chars = t.toCharArray();
        for (char aChar : chars) {
            need.put(aChar, need.getOrDefault(aChar, 0) + 1);
        }

        //左右滑动指针
        int left = 0, right = 0;
        int result = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            //向右滑动
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    result++;
                }
            }
            right++;
            //判断left指针是否需要移动
            if (result == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char c1 = s.charAt(left);
                if (need.containsKey(c1)) {
                    window.put(c1, window.get(c1) - 1);
                    result--;
                }
                left++;
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    /**
     * 字符串的排列 (https://leetcode.cn/problems/permutation-in-string/)
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * 示例 1：
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     * <p>
     * 示例 2：
     * 输入：s1= "ab" s2 = "eidboaoo"
     * 输出：false
     * <p>
     * 相当给你一个 S 和一个 T，请问你 S 中是否存在一个子串，包含 T 中所有字符且不包含其他字符
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        //设计一个S1长度的窗口进行滑动，如果窗口内的字符串与S1的字符串想等，那么就符合
        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] chars = s1.toCharArray();
        for (char aChar : chars) {
            s1Map.put(aChar, s1Map.getOrDefault(aChar, 0) + 1);
        }

        int l1 = 0;
        int l2 = 0;
        int result = 0;

        while (l2 < s2.length()) {
            char c = s2.charAt(l2);
            l2++;
            if (s1Map.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).intValue() == s1Map.get(c).intValue()) {
                    result++;
                }
            }
            if (l2 - l1 >= s1.length()) {
                if (result == s1Map.size()) {
                    return true;
                }
                //对窗口数据处理，移除
                char d = s2.charAt(l1);
                l1++;

                // 进行窗口内数据的一系列更新
                if (s1Map.containsKey(d)) {
                    if (window.get(d).intValue() == s1Map.get(d).intValue()) //
                        result--;
                    window.put(d, window.get(d) - 1);
                    if (window.get(d) == 0) {
                        window.remove(d);
                    }
                }
            }
        }
        return false;
    }



}
