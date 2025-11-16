package org.lyflexi.solutions.enhanceutils.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/3 17:14
 */
public class LC373_kSmallestPairs {
    /**
     * @description: 所有的数组元素都用来建堆会内存溢出
     * @author: hmly
     * @date: 2025/10/3 17:15
     * @param: [nums1, nums2, k]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     **/
    @Deprecated
    public List<List<Integer>> kSmallestPairsDeprecated(int[] nums1, int[] nums2, int k) {
        //队列中的基本元素是一维数组, 代表长度为2的索引对
         Queue<int[]> q = new PriorityQueue<>(
             (indexPair1,indexPair2)->{
                 return nums1[indexPair1[0]]+nums2[indexPair1[1]] - nums1[indexPair2[0]]-nums2[indexPair2[1]];
             }
         );
         int len1 = nums1.length;
         int len2 = nums2.length;
         for(int i=0;i<len1;i++){
             for(int j=0;j<len2;j++){
                 q.add(new int[]{i,j});
             }
         }

         List<List<Integer>> rs = new ArrayList<>();
         for(int i = 0;i<k;i++){
             int[] indexPair = q.poll();
             rs.add(List.of(nums1[indexPair[0]],nums2[indexPair[1]]));
         }

         return rs;
    }



    /**
     * @description: 按需建堆 && 出堆
     * @author: hmly
     * @date: 2025/10/3 17:16
     * @param: [nums1, nums2, k]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     **/
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        Queue<int[]> q = new PriorityQueue<>(
                (indexPair1,indexPair2)->{
                    return nums1[indexPair1[0]]+nums2[indexPair1[1]] - nums1[indexPair2[0]]-nums2[indexPair2[1]];
                }
        );
        int len1 = nums1.length;
        int len2 = nums2.length;
        //如果全部拿来组合, 堆空间复杂度超限, 所以我们用动态堆解题, 堆大小不用是所有的组合情况, 只需满足出堆次数即可
        //1. 出堆i,j (小根堆)
        //2. 入堆(两个可能性, i+1,j 或者 i,j+1)
        //因此当有类似于出堆(1,2)和出堆(2,1), 入堆的四个可能性中, 会存在重复的(2,2)
        // 我们约定i不要涨, 只让j涨, 这样可以避免重复元素
        for(int i =0;i<Math.min(len1,k);i++){
            //i不涨, 会漏掉答案候选项(i,0), 至于(i,!0)一定大于(i,0)所以不用管
            // 因此用k个或全部的i初始化堆
            q.add(new int[]{i,0});
        }

        List<List<Integer>> rs = new ArrayList<>();
        //循环出堆k次
        while(k>0){
            int[] indexPair = q.poll();
            rs.add(List.of(nums1[indexPair[0]],nums2[indexPair[1]]));
            if(indexPair[1]+1<len2){
                q.add(new int[]{indexPair[0],indexPair[1]+1});
            }
            k--;
        }
        return rs;
    }
}
