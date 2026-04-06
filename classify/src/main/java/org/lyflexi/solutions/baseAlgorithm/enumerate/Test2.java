package org.lyflexi.solutions.baseAlgorithm.enumerate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, ans = 0;
        //技巧1: 想要排序, 又不想要改变原数组的顺序, 创建索引数组, 其值是"原数组升序排列"后的索引顺序
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i->i);
        Arrays.sort(idx, (i, j)-> arr[i] - arr[j]);

        //O(n)
        for(int j: idx){
            List<Integer> sortedLeft = new ArrayList<>();
            //O(n)
            for(int i: idx){
                if(i< j && Math.abs(arr[i] - arr[j]) <=a){
                    sortedLeft.add(arr[i]);
                }
            }
            List<Integer> sortedRight = new ArrayList<>();
            //O(n)
            for(int k: idx){
                if(k> j && Math.abs(arr[k] - arr[j]) <=b){
                    sortedRight.add(arr[k]);
                }
            }

            //技巧2: 从两个有序数组sortedLeft,sortedRight, sortedLeft出个x, 在sortedRight中寻找二者差值的绝对值小于等于c的个数
            //等价于遍历sortedLeft, 求和len(<=x+c) - len(<x-c)
            // 复杂度从O(n^2)->O(2n)
            int k1 = 0;
            int k2 = 0;
            int idxR = 0;
            for(int x: sortedLeft){
                while(idxR<sortedRight.size() && sortedRight.get(k1) <= x+c){
                    k2++;
                }
                while(idxR<sortedRight.size() && sortedRight.get(k1) < x-c){
                    k1++;
                }
            }
            ans+=k2-k1;
        }

        return ans;
    }

    //上述整体复杂度O(n^2)
}
