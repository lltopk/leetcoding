package org.lyflexi.solutions.dict;

import java.util.HashMap;

/**
 * @Author: ly
 * @Date: 2024/3/19 14:26
 */
/*
454. 四数相加 II
给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

示例 1：
输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

示例 2：
输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
输出：1
* */
public class Solution04_FourArrays {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int answer = 0;
        //a+b为键，计数为value
        HashMap<Integer, Integer> mapAB = new HashMap<>();
        for(int a : nums1){
            for (int b : nums2){
                mapAB.put(a+b,mapAB.getOrDefault(a+b,0)+1);
            }
        }



        //a+b+c+d = 0
        //把数组nums1和nums2分为一组，把数组nums3和nums4分为一组
        for(int c : nums3){
            for (int d : nums4){
                if (mapAB.containsKey(0-c-d)){
                    answer+= mapAB.get(-c-d);
                }
            }
        }


        return answer;

    }
}

