package org.lyflexi.solutions.baseAlgorithm.slipwindows.lrtp;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/6 17:36
 */
public class LC167_twoSum {
    public int[] twoSum(int[] numbers, int target) {
        int p1 = 0;

        int n = numbers.length;
        int p2 = n-1;
        //这里两端指针比快慢指针好
        //原因是两端指针只需移动一个指针即可
        //快慢指针需要同时移动两个指针
        while(p1<p2){
            if(numbers[p1]+numbers[p2]==target){
                return new int[]{p1+1,p2+1};
            }else if(numbers[p1]+numbers[p2]>target){
                p2--;
            }else{
                p1++;
            }
        }
        return new int []{-1,-1};
    }
}
