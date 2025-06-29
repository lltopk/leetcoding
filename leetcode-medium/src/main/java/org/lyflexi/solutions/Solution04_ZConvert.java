package org.lyflexi.solutions;

import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/10 20:23
 */
/*
* 6. Z 字形变换：属于模拟题
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
请你实现这个将字符串进行指定行数变换的函数：string convert(string s, int numRows);

示例 1：
输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"
*
示例 2：
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
*
*
示例 3：
输入：s = "A", numRows = 1
输出："A"*/
public class Solution04_ZConvert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int numRows = Integer.parseInt(scanner.nextLine());
        System.out.println(convert(s,numRows));
    }

    public static String convert(String s, int numRows) {


        if (numRows==1){
            return s;
        }

        int strLen = s.length();
        int T = 2*numRows-2;
        //列数：周期个数*一个周期所跨的列数
        int n = ((strLen+ T-1 )/T)*(numRows-1);//注意，最后不足一个周期的需要按照一个周期补齐：strLen+ T-1

        Character[][] matrix = new Character[numRows][n];

        int x=0,y = 0;//坐标

        for (int i = 0; i < strLen; i++) {

            if (i%T<numRows-1){//向下移动
                matrix[x][y] = s.charAt(i);
                x++;
            }else {
                matrix[x][y] = s.charAt(i);
                x--;
                y++;
            }
        }


        String ans = "";
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]!=null){
                    ans+=matrix[i][j];
                }
            }

        }


        return ans;



    }
}
