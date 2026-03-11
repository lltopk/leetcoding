package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

/**
 * 1234. 替换子串得到平衡字符串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
 *
 * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
 *
 *
 *
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 *
 * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
 *
 * 请返回待替换子串的最小可能长度。
 *
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "QWER"
 * 输出：0
 * 解释：s 已经是平衡的了。
 * 示例 2：
 *
 * 输入：s = "QQWE"
 * 输出：1
 * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
 * 示例 3：
 *
 * 输入：s = "QQQW"
 * 输出：2
 * 解释：我们可以把前面的 "QQ" 替换成 "ER"。
 * 示例 4：
 *
 * 输入：s = "QQQQ"
 * 输出：3
 * 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s.length 是 4 的倍数
 * s 中只含有 'Q', 'W', 'E', 'R' 四种字符
 */

/**
 * 不定尺寸滑动窗口求最小
 */
public class LC1234_balancedString {
    //不定长滑动窗口求最小， 在模板计算过程中我们发现窗口本身并没有意义， 因此这不是个简单题
    public int balancedString(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length, l = 0;
        int ans = n;
        int[] dict = new int[26];//26个大写字母，保存窗口外字符个数

        //提前装填所有元素个数
        for(int i = 0; i<n ;i ++){
            dict[arr[i] - 'A']++;
        }
        if(dict[16] ==n/4 && dict[22] ==n/4 && dict[4] ==n/4 && dict[17] ==n/4){
            return 0;
        }

        //正难则反, 我们关注窗口外元素， 然后动态调整窗口外元素个数
        for(int r= 0; r<n; r++){
            dict[arr[r] - 'A']--;//窗口外元素个数减少

            //窗口收缩条件， 当窗口外元素个数都不超过n/4的时候， 才意味者窗口内可以完成字符替换
            while(dict[16] <=n/4 && dict[22] <=n/4 && dict[4] <=n/4 && dict[17] <=n/4){
                ans = Math.min(ans, r-l + 1);
                dict[arr[l] - 'A']++;//窗口外元素个数增加
                l++;
            }
        }
        return ans;
    }
}
