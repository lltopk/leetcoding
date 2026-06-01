package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 3170. 删除星号以后字典序最小的字符串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s 。它可能包含任意数量的 '*' 字符。你的任务是删除所有的 '*' 字符。
 *
 * 当字符串还存在至少一个 '*' 字符时，你可以执行以下操作：
 *
 * 删除最左边的 '*' 字符，同时删除该星号字符左边一个字典序 最小 的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。
 * 请你返回删除所有 '*' 字符以后，剩余字符连接而成的 字典序最小 的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aaba*"
 *
 * 输出："aab"
 *
 * 解释：
 *
 * 删除 '*' 号和它左边的其中一个 'a' 字符。如果我们选择删除 s[3] ，s 字典序最小。
 *
 * 示例 2：
 *
 * 输入：s = "abc"
 *
 * 输出："abc"
 *
 * 解释：
 *
 * 字符串中没有 '*' 字符。
 *
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只含有小写英文字母和 '*' 字符。
 * 输入保证操作可以删除所有的 '*' 字符。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 21,620/42.5K
 * 通过率
 * 50.9%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 列表模拟栈
 */
public class LC3170_clearStars {
    public String clearStars(String s) {
        List<Integer>[] stacks =  new ArrayList[26];
        Arrays.setAll(stacks, i-> new ArrayList<>());
        char[] sa = s.toCharArray();
        for(int i =0 ;i<sa.length; i++){
            //最后要按照原来的顺序还原字符串, 因此栈存的是字符下标
            char c = sa[i];
            if('*' == c ){
                for(List<Integer> stack: stacks){
                    if(!stack.isEmpty()){
                        //这样就是最小字符的栈
                        stack.remove(stack.size()-1);
                        break;
                    }
                }
            }else{
                int idxOfStack = c-'a';
                stacks[idxOfStack].add(i);
            }
        }
        List<Integer> finalList = new ArrayList<>();
        for(List<Integer> stack: stacks){
            finalList.addAll(stack);
        }

        Collections.sort(finalList);
        StringBuilder sb = new StringBuilder();
        for(int i: finalList){
            sb.append(sa[i]);
        }

        return new String(sb);
    }
}
