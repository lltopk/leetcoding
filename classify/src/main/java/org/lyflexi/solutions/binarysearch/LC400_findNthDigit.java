package org.lyflexi.solutions.binarysearch;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/15 15:53
 * @description
 * 400. 第 N 位数字
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 */
public class LC400_findNthDigit {
    //1-9   9个  区间位数=1*9
    //10-99 90个 区间位数=2*90
    //100-999 900个 区间位数=3*900
    public int findNthDigit(int n) {
        //二分寻找目标数, 二分索引为数本身
        int l = 1 , r = n;// 上界设为 n 足够（因为 1~n 至少有 n 位）
        while(l<r){
            int midV = l + ((r-l)>>1);
            if(totolBits(midV)>n){
                r = midV;
            }else if(totolBits(midV)<n){
                l = midV +1;
            }else{
                String vStr = String.valueOf(midV);
                return vStr.charAt(vStr.length()-1) - '0';
            }
        }

        //位数之和超过n的第一个数V
        int litterBig = l;
        int preV = l-1;
        //偏移量是从litterBig开始的, 因此下标从0开始, 所以计算过程需要减去1
        int offset = (int)(n- totolBits(preV) -1);

        return String.valueOf(litterBig).charAt(offset) - '0';
    }

    /**
     计算题干序列中某元素的从头到此所有的位数和, 包括此位本身
     */
    private long totolBits(int v){
        long total = 0, start = 1, end = 9,  bitSize = 1;

        if(v<=9) return v;

        while(v>=start * 10){
            total += (end - start +1)*bitSize;
            start = start*10;
            end = end*10+9;
            bitSize++;
        }

        //完整的区间和 加上 多出来的部分
        return total + (v-start+1)*(bitSize);
    }
}
