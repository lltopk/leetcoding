package org.lyflexi.solutions;

import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/10 21:02
 */


/*7. 整数反转：
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
假设环境不允许存储 64 位整数（有符号或无符号）。



示例 1：
输入：x = 123
输出：321

示例 2：
输入：x = -123
输出：-321

示例 3：
输入：x = 120
输出：21

示例 4：
输入：x = 0
输出：0


*/



/*思路：
基本的除模运算，关键点是越界处理
- 不能计算出结果之后再判断越界，那将没有意义，有可能已经越界了
- 在循环中模拟即将越界前的状态，将该状态与Integer.MAX_VALUE或者Integer.MIN_VALUE做比较*/
public class Solution05_IntegerReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println(reverse(x));

    }

    public static int reverse(int x) {
        int res = 0;

        while (x/10!=0){


            int num = x%10;

            int absNum = Math.abs(num);

            if ((res-absNum) < Integer.MIN_VALUE/10  || (res+absNum) > Integer.MAX_VALUE/10 ) {
                return 0;
            }

            res += num;
            res = res*10;
            x=x/10;
        }
        res += x;

        return res;
    }



}
