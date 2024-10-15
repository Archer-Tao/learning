package com.tzy.algo;


import java.util.*;

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


    /**
     * 找到字符串中所有字母异位词(https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/)
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     * <p>
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> pMap = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] chars = p.toCharArray();
        for (char c : chars) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int count = 0;

        while (right < s.length()) {
            //移动右指针
            char c = s.charAt(right);
            right++;
            if (pMap.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).intValue() == pMap.get(c).intValue()) {
                    count++;
                }
            }
            if (right - left >= p.length()) {
                if (count == pMap.size()) {
                    result.add(left);
                }
                //移动左指针
                char d = s.charAt(left);
                left++;
                if (pMap.containsKey(d)) {
                    if (window.get(d).intValue() == pMap.get(d).intValue()) {
                        count--;
                    }
                    window.put(d, window.get(d) - 1);
                    if (window.get(d) == 0) {
                        window.remove(d);
                    }
                }
            }
        }
        return result;
    }


    /**
     * 最长无重复子串(https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/)
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     * <p>
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * "bbbb"
     */
    public int lengthOfLongestSubstring(String s) {
        if ("".equals(s) || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (window.containsKey(c)) {
                // 移动左指针到重复字符的下一位，避免窗口中包含重复字符
                left = Math.max(left, window.get(c) + 1);
            }
            window.put(c, right);  // 更新当前字符的位置
            max = Math.max(max, right - left + 1);  // 更新最大长度
            right++;
        }
        return max;
    }





}
