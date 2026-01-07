/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.Ⅲdivideconquer;

import java.util.Arrays;

/**
 *
 * @author hasee
 */
public class LC1818_minAbsoluteSumDiff {
    /**
     * 这个题目没有捷径， 不一定差值最大的pair就是需要替换的位置, 因为可能nums1没有更合适的值能够缩小该pair
     * Testcase
        [1,28,21]
        [9,21,20]
        Answer err
        16
        Expected Answer
        9
     */

    //所以二分一次是不够的， 要二分len次
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int len = nums1.length;
        //注意这里要用long
        long sum = 0;
        for(int i = 0; i<len;i++){
            sum += Math.abs(nums1[i]-nums2[i]);
        }

        int maxDiff = 0;//maxDiff = oldDis - minNewDis

        //不能就地排序， 还需要用到旧数组nums1
        int[] sortedNums1 = Arrays.copyOf(nums1,len);
        Arrays.sort(sortedNums1);
        for(int i = 0; i<len; i++){
            int oldDis = Math.abs(nums1[i]-nums2[i]);
            int l = lowerBound(sortedNums1, nums2[i]);

            //代表隐式替换nums1后的差值
            int minNewDis = 0;
            if(l == 0){//没找到， sortedNums1都大于nums2[i]
                minNewDis = Math.abs(sortedNums1[0]-nums2[i]);
            }else if(l == len){//没找到， sortedNums1都小于nums2[i]
                minNewDis = Math.abs(sortedNums1[len-1]-nums2[i]);
            }else if(sortedNums1[l]!=nums2[i]){//没找到，考虑目标值的左右两侧
                minNewDis = Math.min(Math.abs(sortedNums1[l]-nums2[i]), Math.abs(sortedNums1[l-1]-nums2[i]));
            }

            //循环结构外部定义的maxDiff需要与自身迭代求最值
            maxDiff = Math.max(maxDiff, oldDis - minNewDis);
        }

        return (int)((sum - maxDiff) % (1_000_000_007));
    }

    private int lowerBound(int[] nums, int target){
        int n = nums.length;
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]<target){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
