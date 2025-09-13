package org.lyflexi.solutions.rank;

import java.util.*;

/**
 * @Author: ly
 * @Date: 2024/2/10 19:44
 */

/*692. 前K个高频单词
给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。

示例 1：
输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
输出: ["i", "love"]
解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。

示例 2：
输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
输出: ["the", "is", "sunny", "day"]
解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。*/
public class Solution05_TopKWordArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = (String[])Arrays.stream(scanner.nextLine().split(",")).toArray();

        int k = Integer.parseInt(scanner.nextLine());
        System.out.println(topKFrequent(array, k));

    }



    public static List<String> topKFrequent(String[] words, int k) {
        int n = words.length;


        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String key = words[i];
            map.put(key,map.getOrDefault(key,0)+1);
        }

        PriorityQueue<Map.Entry<String,Integer>> priorityQueue = new PriorityQueue<>(
                (entry1,entry2)->{
                    Integer value1 = entry1.getValue();
                    Integer value2 = entry2.getValue();
                    if(value1!=value2){
                        return entry1.getValue()-entry2.getValue();//小顶堆，方便取堆中最小元素进行淘汰
                    }else{
                        return (entry2.getKey()).compareTo(entry1.getKey());//大顶堆，方便取出字典顺序较低的元素进行淘汰
                    }


                }
        );

        for (Map.Entry<String,Integer> entry:map.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (priorityQueue.size()==k){
                //淘汰策略，当堆中最小元素小于当前entry
                Map.Entry<String, Integer> peek = priorityQueue.peek();
                if (peek.getValue()<value){
                    priorityQueue.poll();
                    priorityQueue.add(entry);
                }else if (peek.getValue()==value&&(peek.getKey()).compareTo(key)>0){
                    priorityQueue.poll();
                    priorityQueue.add(entry);
                }

            }else {
                priorityQueue.add(entry);
            }

        }

        List<String> topK = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            topK.add(priorityQueue.poll().getKey());
        }

        Collections.reverse(topK);


        return topK;
    }
}
