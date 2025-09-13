package org.lyflexi.solutions;

import java.util.Scanner;

/**
 * @Description:
 * @Author: lyflexi
 * @project: leetcode-java
 * @Date: 2024/12/7 17:52
 */

/**
 * Question
 * 捆绑销售
 *
 * <p>
 * josh went to the market to buy appies. He found two shops A and B that sold apples in lots .
 * He can bny any number of completed lots but he cannot buy loose apples .
 * He is confused about the price and needs help to calculate the minimum cost of exactly N apples
 * <p>
 * Input
 * The first line of the input consists of an integer- num, representing total number of apples that josh wishes to buy (N).
 * The second line consists of a positive integer-appleShop1.representing the number of apples in a lot from shop A.
 * The third line consists of a positive integer-priceShop1.representing the price of the lot at shop A.
 * The fourth line consists of a positive integer - appleShop2.representing the number of apples in a lot from shop B.
 * The fifth line consists of a positive integer- priceShop2.representing the price of the lot at shop B.
 * <p>
 * Output Josh can buy N apples.Print a positive integer representing the minimum price at which
 * <p>
 * example:
 * input:
 * 19
 * 3
 * 10
 * 4
 * 15
 * output:
 * 65
 */
public class Extra07_HSBC_BundleSales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt(); // Total number of apples Josh wants to buy
        int appleShop1 = scanner.nextInt(); // Number of apples in a lot from shop A
        int priceShop1 = scanner.nextInt(); // Price of the lot at shop A
        int appleShop2 = scanner.nextInt(); // Number of apples in a lot from shop B
        int priceShop2 = scanner.nextInt(); // Price of the lot at shop B
        int minCost = minPriceV1(num, appleShop1, priceShop1, appleShop2, priceShop2);
        System.out.println(minCost);
        scanner.close();
    }

    /**
     * 策略1：先尽量买捆最小的，最后买捆大的，但有可能捆价贵
     * 策略2：先尽量买捆价最小的，最后买捆价大的，但有可能捆最大
     * 策略3：先尽量买【捆*捆价】最小的，最后买【捆*捆价】最大的，这是最合理的策略，但有可能凑不齐总数
     * <p>
     * 因此无论怎样都不行，所以需要应用排列组合。同时使用最合理的策略3，如下
     * 从1捆appleShop1开始，剩下的所有搭配appleShop2
     * 2捆appleShop1，剩下的所有搭配appleShop2
     * 3捆appleShop1，剩下的所有搭配appleShop2
     * ...
     *
     * @param num
     * @param appleShop1
     * @param priceShop1
     * @param appleShop2
     * @param priceShop2
     * @return
     */
    private static int minPriceV1(int num, int appleShop1, int priceShop1, int appleShop2, int priceShop2) {
        // 边界处理
        if (num == 0){
            return 0;//0成本
        }
        if (num < 0 || appleShop1 < 0 || priceShop1 < 0 || appleShop2 < 0 || priceShop2 < 0) {
            return -1; // or throw an exception, depending on your design choice
        }
        if (num % gcd(appleShop1, appleShop2) != 0) {
            return -1; // Ensure that the total number of apples can be bought with the given lots
        }


        // 策略3：Ensure that appleShop1*priceShop1 is not greater than appleShop2*priceShop2 for simpler iteration logic
        if (appleShop1 * priceShop1 > appleShop2 * priceShop2) {
            int tempApple = appleShop1;
            appleShop1 = appleShop2;
            appleShop2 = tempApple;

            int tempPrice = priceShop1;
            priceShop1 = priceShop2;
            priceShop2 = tempPrice;
        }

        int minCost = Integer.MAX_VALUE;

        //买appleShop1的苹果个数
        int sumShop1 = 0;
        //i捆appleShop1
        for (int i = 0; (sumShop1 = i * appleShop1) <= num; i++) {
            int remainingApples = num - sumShop1;
            if (remainingApples % appleShop2 == 0) {
                //j捆appleShop2
                int j = remainingApples / appleShop2;
                int cost = i * priceShop1 + j * priceShop2;
                if (cost < minCost) {
                    minCost = cost;
                }
            }
        }
        return minCost;
    }

    /**
     * 求解最大公约数, 默认a>b
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b) {
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * TODO
     *
     * @param num
     * @param appleShop1
     * @param priceShop1
     * @param appleShop2
     * @param priceShop2
     * @return
     */
    private static int minPriceV2(int num, int appleShop1, int priceShop1, int appleShop2, int priceShop2) {
        return 0;
    }
}
