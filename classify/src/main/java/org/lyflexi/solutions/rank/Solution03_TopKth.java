package org.lyflexi.solutions.rank;

import java.util.PriorityQueue;

/**
 * @Author: ly
 * @Date: 2024/3/8 10:17
 */

/*215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5

示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4


*/
public class Solution03_TopKth {
    public int findKthLargest(int[] nums, int k) {

        int n = nums.length;



        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (item1,item2)->item1-item2//小顶堆，方便取堆中最小元素进行淘汰
        );

        for (Integer item:nums){

            if (priorityQueue.size()==k){
                //淘汰策略，当堆中最小元素小于当前entry
                Integer peek = priorityQueue.peek();
                if (peek<item){
                    priorityQueue.poll();
                    priorityQueue.add(item);
                }

            }else {
                priorityQueue.add(item);
            }

        }

        int[] topK = new int[k];

        for (int i = k-1; i >=0; i--) {//堆中确实是前k大的元素集合，但由于是小顶堆，出堆顺序是前key元素的逆序
            topK[i] = priorityQueue.poll();
        }
        return topK[k-1];

    }
}
