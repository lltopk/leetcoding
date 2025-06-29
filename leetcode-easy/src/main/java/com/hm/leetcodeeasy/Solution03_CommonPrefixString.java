package com.hm.leetcodeeasy;

/**
 * @Author: ly
 * @Date: 2024/2/11 18:23
 */

/*14. 最长公共前缀

编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。


示例 1：
输入：strs = ["flower","flow","flight"]
输出："fl"

示例 2：
输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
*/
public class Solution03_CommonPrefixString {

    public static String longestCommonPrefix(String[] strs) {

        int n = strs.length;

        String firstWord = strs[0];
        int firstWordLen = firstWord.length();
        String answer = null;

        int i;
        for (i = 0; i < firstWordLen; i++) {
            char c = firstWord.charAt(i);

            for (int j = 1; j <n ; j++) {

                if (i>strs[j].length()-1){
                    return firstWord.substring(0,i);
                }

                if (strs[j].charAt(i)!=c){
                    return firstWord.substring(0,i);
                }



            }

        }

        return firstWord;

    }
}
