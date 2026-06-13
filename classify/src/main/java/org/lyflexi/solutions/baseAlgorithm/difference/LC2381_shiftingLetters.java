package org.lyflexi.solutions.baseAlgorithm.difference;

/**
 * 2381. 字母移位 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个小写英文字母组成的字符串 s 和一个二维整数数组 shifts ，其中 shifts[i] = [starti, endi, directioni] 。对于每个 i ，将 s 中从下标 starti 到下标 endi （两者都包含）所有字符都进行移位运算，如果 directioni = 1 将字符向后移位，如果 directioni = 0 将字符向前移位。
 *
 * 将一个字符 向后 移位的意思是将这个字符用字母表中 下一个 字母替换（字母表视为环绕的，所以 'z' 变成 'a'）。类似的，将一个字符 向前 移位的意思是将这个字符用字母表中 前一个 字母替换（字母表是环绕的，所以 'a' 变成 'z' ）。
 *
 * 请你返回对 s 进行所有移位操作以后得到的最终字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
 * 输出："ace"
 * 解释：首先，将下标从 0 到 1 的字母向前移位，得到 s = "zac" 。
 * 然后，将下标从 1 到 2 的字母向后移位，得到 s = "zbd" 。
 * 最后，将下标从 0 到 2 的字符向后移位，得到 s = "ace" 。
 * 示例 2:
 *
 * 输入：s = "dztz", shifts = [[0,0,0],[1,1,1]]
 * 输出："catz"
 * 解释：首先，将下标从 0 到 0 的字母向前移位，得到 s = "cztz" 。
 * 最后，将下标从 1 到 1 的字符向后移位，得到 s = "catz" 。
 *
 *
 * 提示：
 *
 * 1 <= s.length, shifts.length <= 5 * 104
 * shifts[i].length == 3
 * 0 <= starti <= endi < s.length
 * 0 <= directioni <= 1
 * s 只包含小写英文字母。
 */

/**
 * 差分数组
 */
public class LC2381_shiftingLetters {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        char[] ans = new char[n];
        int[] diff = new int[n+1];
        for(int[] shift: shifts){
            if(shift[2]==1){
                diff[shift[0]]++;
                diff[shift[1]+1]--;
            }else{
                diff[shift[0]]--;
                diff[shift[1]+1]++;
            }
        }

        int sum = 0;
        for(int i = 0; i< n; i++){
            int x = diff[i];
            sum += x;
            char c = s.charAt(i);
            //这里内部先加26是处理负数取模的经典技巧, 当c - 'a' + sum < 0
            int shift = ((c - 'a' + sum) % 26 + 26) % 26;
            ans[i] = (char)(shift + 97);
        }
        return new String(ans);
    }
}
