package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @description V1.0
 * 744. 寻找比目标字母大的最小字母
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
 *
 * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
 *
 *
 *
 * 示例 1：
 *
 * 输入: letters = ['c', 'f', 'j']，target = 'a'
 * 输出: 'c'
 * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。
 * 示例 2:
 *
 * 输入: letters = ['c','f','j'], target = 'c'
 * 输出: 'f'
 * 解释：letters 中字典顺序上大于 'c' 的最小字符是 'f'。
 * 示例 3:
 *
 * 输入: letters = ['x','x','y','y'], target = 'z'
 * 输出: 'x'
 * 解释：letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。
 * @create 2025/10/7 17:00
 */
public class LC744_nextGreatestLetter {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0;
        int r = n;
        int rBoard = -1;
        while(l<r){
            int midIndex = l + ((r-l)>>1);
            if(letters[midIndex]>target){
                r = midIndex;
            }else if(letters[midIndex]<=target){
                l = midIndex+1;
                rBoard = midIndex;//有重复元素, 求右值
            }
        }
        return rBoard>=0&&rBoard<n-1?letters[rBoard+1]:letters[0];
    }
}
