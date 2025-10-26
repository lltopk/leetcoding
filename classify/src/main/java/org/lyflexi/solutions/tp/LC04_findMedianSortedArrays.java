package org.lyflexi.solutions.tp;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/26 13:44
 * @description
 * 4. 寻找两个正序数组的中位数
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 */
public class LC04_findMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k1 = 0, k2 = 0;
        int[] newArr = new int[m+n];
        int cur = 0;
        //o(m+n)
        while(k1<m&&k2<n){
            if(k1<m && k2<n && nums1[k1]<=nums2[k2]){
                newArr[cur++] = nums1[k1];
                k1++;
            }else if(k1<m && k2<n && nums2[k2]<nums1[k1]){
                newArr[cur++] = nums2[k2];
                k2++;
            }
        }

        if(k1 == m){
            while(k2<n){
                newArr[cur++] = nums2[k2];
                k2++;
            }
        }

        if(k2 == n){
            while(k1<m){
                newArr[cur++] = nums1[k1];
                k1++;
            }
        }

        int len = newArr.length;
        if(len%2==0){
            return (newArr[len/2-1]+newArr[len/2])/2.0;
        }

        //奇数长度
        return newArr[len/2];
    }
}
