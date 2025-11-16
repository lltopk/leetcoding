package org.lyflexi.solutions.enhanceutils.heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/9/23 21:37
 */
public class LC1985_kthLargestNumber {
    public static void main(String[] args) {
        String s = kthLargestNumber(new String[]{"3", "6", "7", "10"}, 4);
        System.out.println(s);
    }
    public static String kthLargestNumber(String[] nums, int k) {
        MaxHeap maxheap = new MaxHeap(nums,new CustomComparator());
        if(k>nums.length){
            k = nums.length;
        }
        String result = "";
        for(int i = 0;i<k;i++){
            result = maxheap.pop();
        }
        return result;
    }

    static class MaxHeap{
        private String[] values;
        private Comparator<String> comparator;

        public MaxHeap(String[] nums,Comparator<String> comparator){
            this.values = nums;
            this.comparator = comparator;
            createHeap(nums);
        }

        private int left(int i){
            return 2*i+1;
        }
        private int right(int i){
            return 2*i+2;
        }
        private int parent(int i){
            return (i-1)/2;
        }
        private void createHeap(String[] values){
            int len = values.length;
            for(int i = parent(len-1);i>=0;i--){
                shiftDown(i);
            }
        }
        private void shiftDown(int i){
            int len = values.length;
            int index = i;
            for(;;){
                int l = left(i);
                int r = right(i);
                if(l<len && comparator.compare(values[index], values[l])<0){
                    index = l;
                }
                if(r<len && comparator.compare(values[index], values[r])<0){
                    index = r;
                }
                if(index == i){
                    break; //局部成堆
                }
                swap(index,i);
                i = index;
            }
        }

        private Boolean compare(String s1, String s2){

            // return Integer.valueOf(s1) - Integer.valueOf(s2) < 0;//越界了
            // 首先比较字符串长度
            if (s1.length() < s2.length()){
                return true;
            }
            else if (s1.length() > s2.length()){
                return false;
            }
            else{
                // 长度相等时比较字符串字典序大小
                return s1.compareTo(s2) < 0;
            }
        }

        private void swap(int i, int j){
            String temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }

        public String pop(){
            if(isEmpty()){
                return "";
            }
            String result = values[0];
            swap(0,values.length-1);
//            String[] newValues = new String[values.length-1];
//            for(int i = 0; i<values.length-1;i++){
//                newValues[i] = values[i];
//            }
            this.values = Arrays.copyOf(values,values.length-1);//Arrays.copyOf支持泛型, 并且底层使用的native方法性能较好
            shiftDown(0);
            return result;
        }
        public Boolean isEmpty(){
            return this.values.length == 0;
        }
    }

    /**
     * @description: 比较函数返回-1 0 1 , 只需要这三个数就可以了
     * 因为传给框架用的时候 , 框架只会关注大于0  等于0  小于0  三种情况, 因此 -1 0 1 这三个数足够
     * @author: hmly
     * @date: 2025/9/24 16:18
     * @param:
     * @return:
     **/
    static class CustomComparator implements Comparator<String>{

        /**
         * 大根堆, 返回值小于0, 代表向下移动
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return
         */
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() < o2.length()){
                return -1;
            }
            else if (o1.length() > o2.length()){
                return 1;
            }
            else{
                // 长度相等时比较字符串字典序大小
                return o1.compareTo(o2);
            }
        }
    }

}
