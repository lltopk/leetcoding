package org.lyflexi.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/2/16 17:08
 */

/*
* 22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
*
示例 2：
输入：n = 1
输出：["()"]

* */
public class Solution10_GenerateParenthesis {

    public List<String> generateParenthesis(int n) {

        ArrayList<String> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        recursion(0,0,answer,sb,n);


        return answer;
    }

    private void recursion(int open, int close, ArrayList<String> answer, StringBuilder sb,int maxPairs) {

        if (sb.length()==maxPairs*2){
            answer.add(sb.toString());
            return;
        }

//        1.路径上选择（的条件，左括号数量小于n就可以无脑加小括号
        if (open<maxPairs){
            sb.append('(');
            recursion(open+1,close,answer,sb,maxPairs);
            sb.deleteCharAt(sb.length()-1);
        }

//        2.路径上选择）的条件，左括号的数量大于右括号的数量
        if (open>close){
            sb.append(')');
            recursion(open,close+1,answer,sb,maxPairs);
            sb.deleteCharAt(sb.length()-1);
        }

    }
}
