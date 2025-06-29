package org.lyflexi.solutions.rank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @Author: lyflexi
 * @project: leetcode-java
 * @Date: 2024/12/7 12:05
 */



/*
*Question：EN
* The current selected programming language is Java.  We emphasize the submission of a fully working code over partially correct but efficient code.  Once submitted, you cannot review this problern again.  You can use System out printinto debug your code.  The System.out.printin may not work in case of syntax/runtime error.  The version of JDK being used is 1.8. Note: The main dlass name must be "Solution".
*
* Emma wishes to give her father a bouquet for his birthday.
* She asks for help from her mother Rosy.
* Rosy gives N flower sticks numbered 1 to N to Emma and tells her to arrange them in the bouquet in a particular order.
* She asks Emma to arrange the first K fiower sticks in the order of increasing length
* and the remaining sticks in the order of decreasing length.
* Write an algorithm to find the final arrangement of the flower sticks in the bouquec.
*
* Input：
* The first line of the input consists of an integer.  fowerStick size, representing the number of flower sticks (N)
* The second line consists of N space-separated integersflowerStick[1], flowerStick(2)flowerSbck[N], representing the length of the flower sticks.
* The last line consists of an integer- random, representing the number K given by Rosy to Emma.
*
* Output：
* Print N space-separated integers representing the final arrangement of the flower sticks in the bouquet.
*
* Constraints：
* 0<=random<=flowerStick_size
* 0<fowerStick_size <10^5
*
* Example:
* Input:
* 8
* 11 7 5 10 46 23 16 8
* 3
* Output:
* 5 7 11 46 23 16 10 8
*
* Explanation:
* Emma has to arrange the first three flower stides in an increasing order of the length and remaining sticks in the decreasing order of the length.
* The final order of flower stidks in the bouquet is [5, 7.  11, 46,23, 16, 10, 8].
*
* */


/*Question: 中文
* 点位逆转：
*
* 目前选择的编程语言是Java。我们强调提交一个完全工作的代码，而不是部分正确但有效的代码。一旦提交，您就不能再检查这个问题。您可以使用系统输出来调试代码。在语法/运行时错误的情况下，System.out.printin可能无法工作。使用的JDK版本为1.8。注意：主类名称必须为“Solution”。
*
* 埃玛想给她父亲一束花作为生日礼物。她向母亲露丝寻求帮助。
* 罗西给了艾玛N根从1到N的花棒，让她把它们按特定的顺序排列在花束里。
* 她让艾玛按照长度递增的顺序排列前K根花棒，剩下的按长度递减的顺序排列。
* 编写一个算法来找到花束中花棒的最终排列。
*
* 输入：
* 输入的第一行由一个整数组成。
* 第二行由N个空格分隔的整数组成：flowerStick[1]， flowerStick(2) flowerback [N]，表示花棒的长度。
* 最后一行是一个随机的整数，代表罗斯给艾玛的数字K。N个以空格分隔的整数，表示花束中花棒的最终排列。
*
* 约束：
* 0<=random<=flowerStick_size
* 0<fowerStick_size <10^5
*
* Example:
* Input:
* 8
* 11 7 5 10 46 23 16 8
* 3
* Output:
* 5 7 11 46 23 16 10 8
*
* 解释：Emma必须按照长度递增的顺序排列前三个花柱，其余的花柱按照长度递减的顺序排列。花束中花茎的最后顺序是[5,7,11, 46,23, 16, 10, 8]。
* */
public class Solution06_HSBC_PReverse {

    private static int[] funcBouquet(int[] flowerStick, int k){
        int len = flowerStick.length;
        if (k>0){
            //左闭右开
            Arrays.sort(flowerStick,0,k);
        }
        if (k<len){
            Arrays.sort(flowerStick,k,len);
            reverse(flowerStick,k,len);
        }
        return flowerStick;
    }

    /**
     * 逆序，左闭右开
     * @param flowerStick
     * @param start
     * @param end
     */
    private static void reverse(int[] flowerStick, int start, int end) {
        end = end -1;
        while(start < end){
            int temp = flowerStick[start];
            flowerStick[start] = flowerStick[end];
            flowerStick[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] flowerStick = new int[n];
        for (int i = 0; i < n; i++) {
            flowerStick[i] = in.nextInt();
        }
        int random = in.nextInt();
        int[] result = funcBouquet(flowerStick, random);
        System.out.println(Arrays.toString(result));

    }
}
