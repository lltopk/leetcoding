package org.lyflexi.solutions.dfs.backTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/15 10:11
 */


/*17. 电话号码的字母组合
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下图（与电话按键相同）。注意 1 不对应任何字母。

        digitToBox.put('2',"abc");
        digitToBox.put('3',"def");
        digitToBox.put('4',"ghi");
        digitToBox.put('5',"jkl");
        digitToBox.put('6',"mno");
        digitToBox.put('7',"pqrs");
        digitToBox.put('8',"tuv");
        digitToBox.put('9',"wxyz");

示例 1：
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

示例 2：
输入：digits = ""
输出：[]

示例 3：
输入：digits = "2"
输出：["a","b","c"]

* */
public class Solution04_Mobile {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String digits = scanner.nextLine();
        System.out.println(letterCombinations(digits));

    }

    public static List<String> letterCombinations(String digits) {
        ArrayList<String> answer = new ArrayList<>();
        if ("".equals(digits)) {
            return new ArrayList<>();
        }

        int n = digits.length();

        HashMap<Character, String> digitToBox = new HashMap<>();
        digitToBox.put('2', "abc");
        digitToBox.put('3', "def");
        digitToBox.put('4', "ghi");
        digitToBox.put('5', "jkl");
        digitToBox.put('6', "mno");
        digitToBox.put('7', "pqrs");
        digitToBox.put('8', "tuv");
        digitToBox.put('9', "wxyz");


        StringBuilder sb = new StringBuilder();

        backTrace(answer, digitToBox, digits, sb, 0);//回溯初始backIndex为0

        return answer;

    }

    private static void backTrace(ArrayList<String> answer, HashMap<Character, String> digitToBox, String digits, StringBuilder sb, int backIndex) {
        int n = digits.length();

        if (sb.length() == n) {
            answer.add(sb.toString());
            return;
        }


        char c = digits.charAt(backIndex);//backIndex当前回溯深度

        String s = digitToBox.get(c);
        for (int i = 0; i < s.length(); i++) {//此处遍历的是char集合，不可能有重复

            sb.append(s.charAt(i));
            backTrace(answer, digitToBox, digits, sb, backIndex + 1);
            sb.deleteCharAt(sb.length() - 1);

        }

    }


}
