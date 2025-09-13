package org.lyflexi.solutions.rank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/11 16:50
 */
public class Solution01_QuickSort {

//    2024.1.11题解
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        quickSort(array,0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
    //形参是数组，相当于引用，无需返回值
    public static void quickSort(int[] nums,int start,int end){
        //妈的，这里也别忘记判断if (start<end)
        if (start<end){
            int partationIndex = partation(nums,start,end);
            quickSort(nums,start,partationIndex-1);
            quickSort(nums,partationIndex+1,end);
        }
    }

    public static int partation(int[] nums,int start,int end){
        //定第一个元素为基准
        int base = nums[start];
        //双指针
        int left = start, right = end;

        while (left<right){
            while(left<right&&nums[right]>=base) right--;//因为外层的while条件无法传递到内层,所以这里还需要加上left<right判断
            while(left<right&&nums[left]<=base) left++;//因为外层的while条件无法传递到内层,所以这里还需要加上left<right判断
            if (left<right){
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }

        System.out.println("left="+left+",right="+right+",nums[left]="+nums[left]);
        //i==j的处理：单独处理中间元素，其肯定小于base，手动移到最左边，第一次left++正好跳过了nums[start]，因此nums[start]没有此时还没有被处理
        nums[start] = nums[left];
        //将base覆盖中间元素，结束
        nums[left] = base;
        return left;
    }

}
