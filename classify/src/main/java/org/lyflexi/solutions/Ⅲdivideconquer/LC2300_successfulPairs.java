package org.lyflexi.solutions.Ⅲdivideconquer;

import java.util.Arrays;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/26 15:38
 * @description
 * 2300. 咒语和药水的成功对数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 *
 * 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 *
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 输出：[4,0,3]
 * 解释：
 * - 第 0 个咒语：5 * [1,2,3,4,5] = [5,10,15,20,25] 。总共 4 个成功组合。
 * - 第 1 个咒语：1 * [1,2,3,4,5] = [1,2,3,4,5] 。总共 0 个成功组合。
 * - 第 2 个咒语：3 * [1,2,3,4,5] = [3,6,9,12,15] 。总共 3 个成功组合。
 * 所以返回 [4,0,3] 。
 * 示例 2：
 *
 * 输入：spells = [3,1,2], potions = [8,5,8], success = 16
 * 输出：[2,0,2]
 * 解释：
 * - 第 0 个咒语：3 * [8,5,8] = [24,15,24] 。总共 2 个成功组合。
 * - 第 1 个咒语：1 * [8,5,8] = [8,5,8] 。总共 0 个成功组合。
 * - 第 2 个咒语：2 * [8,5,8] = [16,10,16] 。总共 2 个成功组合。
 * 所以返回 [2,0,2] 。
 *
 */
public class LC2300_successfulPairs {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length;
        int n = potions.length;
        //对能量数组进行排序
        Arrays.sort(potions);
        //答案的个数是咒语的个数
        int [] rs = new int[m];
        for(int i = 0;i<m;i++){
            //对potions数组进行二分
            int l = 0, r = n;
            while(l<r){
                int midIndex = l + ((r-l)>>1);
                if((long)spells[i]*potions[midIndex]>=success){
                    r = midIndex;
                }else{
                    l = midIndex+1;
                }
            }
            rs[i] = n-r;
        }

        return rs;
    }
}
