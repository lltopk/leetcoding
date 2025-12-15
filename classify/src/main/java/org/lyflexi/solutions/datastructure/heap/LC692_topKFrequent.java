package org.lyflexi.solutions.datastructure.heap;

import java.util.*;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/9/13 19:40
 */
public class LC692_topKFrequent {
    public static void main(String[] args) {
        List<String> strings = topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
        System.out.println(strings);
    }
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        if(words.length == 0){
            return ans;
        }
        Map<String, Integer> word2cnt = createMap(words);
        MaxHeap maxHeap = new MaxHeap(word2cnt);

        for(int i = 0;i<k;i++){
            ans.add(maxHeap.pop());
        }
        return ans;
    }

    private static Map<String, Integer> createMap(String[] words){
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            if(map.containsKey(word)){
                map.put(word,map.get(word)+1);
                continue;
            }
            map.put(word,1);

        }
        return map;
    }

    static class MaxHeap{
        Integer i;
        String[] collection;
        Map<String, Integer> word2cnt;
        public MaxHeap(Map<String, Integer> word2cnt){
            this.collection = word2cnt.keySet().toArray(new String[word2cnt.size()]);
            this.word2cnt = word2cnt;
            createMaxHeap(word2cnt);
        }
        private void createMaxHeap(Map<String, Integer> word2cnt){
            Integer n = word2cnt.keySet().size();
            for(int i = parent(n-1);i>=0;i--){
                shiftDown(i);
            }
        }
        private Integer left(Integer i){
            return 2*i+1;
        }
        private Integer right(Integer i){
            return 2*i+2;
        }
        public Integer parent(Integer i){
            return (i-1)/2;
        }
        public String peek(){
            return collection[0];
        }
        public void shiftDown(Integer i){
            while(true){
                Integer l = left(i);
                Integer r = right(i);
                Integer nextIndex = i;
                if (l < collection.length && word2cnt.get(collection[nextIndex]) < word2cnt.get(collection[l])) {
                    nextIndex = l;
                }
                if (l < collection.length && word2cnt.get(collection[nextIndex]) == word2cnt.get(collection[l])&&collection[nextIndex].compareTo(collection[l])>0) {
                    nextIndex = l;
                }
                if (r < collection.length && word2cnt.get(collection[nextIndex]) < word2cnt.get(collection[r])) {
                    nextIndex = r;
                }
                if (r < collection.length && word2cnt.get(collection[nextIndex]) == word2cnt.get(collection[r])&&collection[nextIndex].compareTo(collection[r])>0) {
                    nextIndex = r;
                }
                if(nextIndex == i){
                    break;
                }
                swap(i,nextIndex);
                i = nextIndex;
            }
        }
        private void swap(Integer i,Integer j){
            String temp = collection[i];
            collection[i] = collection[j];
            collection[j] = temp;
        }
        private Boolean isEmpty(){
            return this.collection.length ==0;
        }
        public String pop(){
            if (isEmpty())
                throw new IndexOutOfBoundsException();
            String value = this.collection[0];
            swap(0,collection.length-1);
            String[] newArray = new String[collection.length-1];
            for(int i =0;i<collection.length-1;i++){
                newArray[i] = collection[i];
            }
            this.collection = newArray;
            shiftDown(0);
            return value;
        }
    }
}