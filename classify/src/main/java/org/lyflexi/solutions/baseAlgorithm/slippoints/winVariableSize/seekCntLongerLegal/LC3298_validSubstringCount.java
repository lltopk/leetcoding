package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntLongerLegal;

/**
 * 3298. 统计重新排列后包含另一个字符串的子字符串数目 II
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个字符串 word1 和 word2 。
 *
 * 如果一个字符串 x 重新排列后，word2 是重排字符串的 前缀 ，那么我们称字符串 x 是 合法的 。
 *
 * 请你返回 word1 中 合法 子字符串 的数目。
 *
 * 注意 ，这个问题中的内存限制比其他题目要 小 ，所以你 必须 实现一个线性复杂度的解法。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "bcca", word2 = "abc"
 *
 * 输出：1
 *
 * 解释：
 *
 * 唯一合法的子字符串是 "bcca" ，可以重新排列得到 "abcc" ，"abc" 是它的前缀。
 *
 * 示例 2：
 *
 * 输入：word1 = "abcabc", word2 = "abc"
 *
 * 输出：10
 *
 * 解释：
 *
 * 除了长度为 1 和 2 的所有子字符串都是合法的。
 *
 * 示例 3：
 *
 * 输入：word1 = "abcabc", word2 = "aaabc"
 *
 * 输出：0
 *
 *
 *
 * 解释：
 *
 * 1 <= word1.length <= 106
 * 1 <= word2.length <= 104
 * word1 和 word2 都只包含小写英文字母。
 */

/**
 * 不定长滑动窗口求个数, 越长越合法. 同LC76 最小覆盖子串
 */
public class LC3298_validSubstringCount {
    public long validSubstringCount(String word1, String word2) {
        char[] sArray = word1.toCharArray();
        char[] tArray = word2.toCharArray();
        int m = sArray.length;
        int n = tArray.length;

        long ans = 0;
        int l = 0;
        //方法一每次收缩窗口的时候都要循环dict来判断是否覆盖, 总的时间复杂度为O(95*m + n), 其中95是字典字符集的大小
        //判断覆盖能否优化呢?
        //其实如果我们有一个变量geCnt代表窗口内共有geCnt种字符的次数大于等于t中的, 那么当geCnt == kinds即为覆盖, 其中kinds为t的字符种类
        int geCnt = 0;
        int[] dictS = new int[95];
        int[] dictT = new int[95];

        int kinds = 0;
        for(int i = 0; i<n; i++){
            dictT[tArray[i] - ' ']++;
            if(dictT[tArray[i] - ' '] == 1){
                kinds++;
            }
        }

        for(int r = 0; r<m; r++){
            //进入, 维护dictS
            dictS[sArray[r] - ' ']++;

            //当二者出现频率相等, geCnt++
            if(dictS[sArray[r] - ' '] == dictT[sArray[r] - ' ']){//注意不能是>=, 否则会重复计算geCnt
                geCnt++;
            }

            //O(1)判断
            while(geCnt == kinds){

                if(dictS[sArray[l] - ' '] == dictT[sArray[l] - ' ']){// 同理, 只有 == 的时候, 才意味着窗口即将移出有效的sArray[l]
                    geCnt--;
                }
                dictS[sArray[l] - ' ']--;
                l++;
            }
            ans+=l;
        }

        return ans;
    }
}
