package org.lyflexi.solutions.baseAlgorithm.utils.stack.pair;

/**
 * 394. 字符串解码
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 测试用例保证输出的长度不会超过 105。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 653,768/1.1M
 * 通过率
 * 61.7%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * pair栈: 每一层内的字符串 以及 每一层内的有效k
 *
 * 然后枚举右维护左
 */
public class LC394_decodeString {
    /**
     prefix: 每一层内的前缀字符串
     k: 每一层内有效的k

     所以当遇到新的'['的时候要重置变量
     */
    private record Pair(StringBuilder prefix, Integer k){}

    /**
     枚举右, 用栈维护左
     */
    public String decodeString(String s) {
        //当前层的string
        StringBuilder ret = new StringBuilder();
        //当前层的k
        int k = 0;
        Deque<Pair> stack = new ArrayDeque<>();
        char[] sa = s.toCharArray();
        for(int i = 0; i<sa.length; i++){
            if(! stack.isEmpty() && sa[i] == ']'){
                Pair pair = stack.pop();
                for(int j= 0; j<pair.k; j++){
                    pair.prefix.append(ret);
                }
                ret = pair.prefix;
                continue;
            }
            if(Character.isDigit(sa[i])){
                k = k*10 + sa[i] - '0';
            }else{
                if(sa[i] == '['){//栈顶永远是'([, k)'
                    stack.push(new Pair(ret, k));

                    //重置临时变量ret | k
                    ret = new StringBuilder();//注意 这里一定是新创建对象, 而不是修改原内存空间的内容
                    k = 0;
                }else{//一定是普通字母了
                    ret.append(sa[i]);
                }
            }

        }
        return ret.toString();
    }
}
