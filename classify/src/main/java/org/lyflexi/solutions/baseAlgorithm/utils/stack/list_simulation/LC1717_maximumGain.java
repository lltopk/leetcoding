package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 1717. 删除子字符串的最大得分
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
 *
 * 删除子字符串 "ab" 并得到 x 分。
 * 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。
 * 删除子字符串"ba" 并得到 y 分。
 * 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。
 * 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "cdbcbbaaabab", x = 4, y = 5
 * 输出：19
 * 解释：
 * - 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
 * - 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
 * - 删除 "cdbcbbaa" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
 * - 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
 * 总得分为 5 + 4 + 5 + 5 = 19 。
 * 示例 2：
 *
 * 输入：s = "aabbaaxybbaabb", x = 5, y = 4
 * 输出：20
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * 1 <= x, y <= 104
 * s 只包含小写英文字母。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 19,242/31.3K
 * 通过率
 * 61.4%
 */

/**
 * 字符串模拟栈
 */
public class LC1717_maximumGain {
    /**
     * 贪心证明一般都是反证法:
     *
     * 由于每次删除都会少一个a和一个b，所以删除的a和b总数是固定的。
     *
     * 如果x≥y时先删除了一个ba，就可能导致后面某个收益更大的ab删除不了, 导致收益减小。 反过来就是要想收益变大, 必须当x≥y时先删除ab
     *
     * x≤y时证明同理
     *
     * @param s
     * @param x
     * @param y
     * @return
     */
    public int maximumGain(String s, int x, int y) {
        //贪心, 优先删除得分大的策略字符串
        if(x>y){
            return stackCal(s, 'a', 'b', x, y);
        }
        return stackCal(s, 'b', 'a', y, x);
    }

    /**
     c1+c2表示优先删除的目标字符串
     c2+c1表示表示后删除的目标字符串

     v1表示优先删除的价值
     v2表示后删除的价值
     */
    private int stackCal(String s, char c1, char c2, int v1, int v2){
        StringBuilder stack = new StringBuilder();
        int ret = 0;
        for(char c: s.toCharArray()){
            if(! stack.isEmpty() && stack.charAt(stack.length() -1) == c1 && c ==c2){
                stack.setLength(stack.length() - 1);
                ret += v1;
            }else{
                stack.append(c);
            }
        }

        //收集到的stack用于第二轮删除, 第二轮是小价值v2
        StringBuilder stackNew = new StringBuilder();
        for(char c: stack.toString().toCharArray()){
            if(! stackNew.isEmpty() && stackNew.charAt(stackNew.length() -1) == c2 && c ==c1){
                stackNew.setLength(stackNew.length() - 1);
                ret += v2;
            }else{
                stackNew.append(c);
            }
        }

        return ret;
    }
}
