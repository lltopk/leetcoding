package org.lyflexi.solutions.rank;

import java.util.*;

/**
 * @Author: ly
 * @Date: 2024/2/10 17:17
 */

/*347. 前 K 个高频元素
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]

示例 2:
输入: nums = [1], k = 1
输出: [1]*/
public class Solution04_TopKArray {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(scanner.nextLine());

        for (int i : topKByPriotity(nums, k)) {
            System.out.println(i);
        }


    }

    /*优化，不全排序，利用Java的优先队列PriorityQueue-堆结构默认只排序堆中已有的元素个数
    *
    * 条件控制priorityQueue的容量为k即可满足，nlogk的复杂度
    * */
    public static int[] topKByPriotity(int[] nums, int k) {

        int n = nums.length;


        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = nums[i];
            map.put(key,map.getOrDefault(key,0)+1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> priorityQueue = new PriorityQueue<>(
                (entry1,entry2)->entry1.getValue()-entry2.getValue()//小顶堆，方便取堆中最小元素进行淘汰
        );

        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (priorityQueue.size()==k){
                //淘汰策略，当堆中最小元素频次小于当前entry的频次
                Map.Entry<Integer, Integer> peek = priorityQueue.peek();
                if (peek.getValue()<value){
                    priorityQueue.poll();
                    priorityQueue.add(entry);
                }

            }else {
                priorityQueue.add(entry);
            }

        }

        int[] topK = new int[k];

        for (int i = k-1; i >=0; i--) {//堆中确实是前k大的元素集合，但由于是小顶堆，出堆顺序是前key元素的逆序
            topK[i] = priorityQueue.poll().getKey();
        }
        return topK;

    }

    /*使用了排序API，nlogn的复杂度*/
/*    public static int[] topKFrequent(int[] nums, int k) {

        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int key = nums[i];
            map.put(key,map.getOrDefault(key,0)+1);
        }

        ArrayList<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());

        List<Map.Entry<Integer, Integer>> sortedList = list.stream().sorted((item1, item2) -> item2.getValue() - item1.getValue()).collect(Collectors.toList());

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = sortedList.get(i).getKey();
        }
        return topK;
    }*/
}
