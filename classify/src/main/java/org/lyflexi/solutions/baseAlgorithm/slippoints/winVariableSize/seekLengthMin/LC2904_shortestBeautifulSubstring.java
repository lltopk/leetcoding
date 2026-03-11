package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

/**
 * 2904. 最短且字典序最小的美丽子字符串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二进制字符串 s 和一个正整数 k 。
 *
 * 如果 s 的某个子字符串中 1 的个数恰好等于 k ，则称这个子字符串是一个 美丽子字符串 。
 *
 * 令 len 等于 最短 美丽子字符串的长度。
 *
 * 返回长度等于 len 且字典序 最小 的美丽子字符串。如果 s 中不含美丽子字符串，则返回一个 空 字符串。
 *
 * 对于相同长度的两个字符串 a 和 b ，如果在 a 和 b 出现不同的第一个位置上，a 中该位置上的字符严格大于 b 中的对应字符，则认为字符串 a 字典序 大于 字符串 b 。
 *
 * 例如，"abcd" 的字典序大于 "abcc" ，因为两个字符串出现不同的第一个位置对应第四个字符，而 d 大于 c 。
 *
 *
 * 示例 1：
 *
 * 输入：s = "100011001", k = 3
 * 输出："11001"
 * 解释：示例中共有 7 个美丽子字符串：
 * 1. 子字符串 "100011001" 。
 * 2. 子字符串 "100011001" 。
 * 3. 子字符串 "100011001" 。
 * 4. 子字符串 "100011001" 。
 * 5. 子字符串 "100011001" 。
 * 6. 子字符串 "100011001" 。
 * 7. 子字符串 "100011001" 。
 * 最短美丽子字符串的长度是 5 。
 * 长度为 5 且字典序最小的美丽子字符串是子字符串 "11001" 。
 * 示例 2：
 *
 * 输入：s = "1011", k = 2
 * 输出："11"
 * 解释：示例中共有 3 个美丽子字符串：
 * 1. 子字符串 "1011" 。
 * 2. 子字符串 "1011" 。
 * 3. 子字符串 "1011" 。
 * 最短美丽子字符串的长度是 2 。
 * 长度为 2 且字典序最小的美丽子字符串是子字符串 "11" 。
 * 示例 3：
 *
 * 输入：s = "000", k = 1
 * 输出：""
 * 解释：示例中不存在美丽子字符串。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * 1 <= k <= s.length
 */

/**
 * 不定尺寸滑动窗口求最小
 */
public class LC2904_shortestBeautifulSubstring {
    public String shortestBeautifulSubstring(String s, int k) {
        //不定尺寸滑动窗口求最小
        char[] arr = s.toCharArray();
        int n = arr.length;
        int checkOneCnt = 0;
        for(int i = 0; i< n;i++){
            checkOneCnt+=arr[i] - '0';
        }
        if(checkOneCnt<k){
            return "";
        }
        int minLen = n;
        int helper = 0;
        int l = 0;
        String ans = s;
        for(int r = 0; r<n; r++){
            helper+=arr[r] - '0';

            //窗口收缩条件
            while(helper>k || arr[l] == '0'){
                helper-=arr[l] - '0';
                l++;
            }

            // 越长越合法，因为是求最小，所以最后需要加额外的判断当满足题意helper == k， 否则会求解错误
            if(helper == k){
                String s0 = s.substring(l, r+1);
                if(s0.length() < ans.length() || (s0.length() == ans.length() && s0.compareTo(ans)==-1)){
                    ans = s0;
                }
            }

        }

        return ans;
    }
}
