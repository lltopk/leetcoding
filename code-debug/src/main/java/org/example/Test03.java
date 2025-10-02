package org.example;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Test03 {
    public static void main(String[] args) {

        int[] arr = {1,6,1};
        System.out.println(smallestDistancePair(arr,3));
    }

    public static int smallestDistancePair(int[] nums, int k) {
        Queue<int[]> q = new PriorityQueue<>(
                (indexPair1,indexPair2)->{
                    return Math.abs(nums[indexPair1[1]]-nums[indexPair1[0]]) - Math.abs(nums[indexPair2[1]]-nums[indexPair2[0]]);
                }
        );
        int n = nums.length;
        for(int i=1;i<n;i++){
            q.add(new int[]{0,i});
        }
        int l = 0;
        int r = n-1;
        int rs = Integer.MAX_VALUE;
        while(k>0){
            int[] indexPair = q.poll();
            rs = Math.abs(nums[indexPair[1]]-nums[indexPair[0]]);
            if(r==n-1 && l+2<n){
                l = l+1;
                r = l+1;
                q.add(new int[]{l,r});
            }

            if(r+1<n){
                q.add(new int[]{l,++r});
            }

            k--;
        }
        return rs;
    }

}