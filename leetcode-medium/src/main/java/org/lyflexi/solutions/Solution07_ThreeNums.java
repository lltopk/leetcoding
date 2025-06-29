package org.lyflexi.solutions;

import java.util.*;

/**
 * @Author: ly
 * @Date: 2024/2/11 20:58
 */

/*15. 三数之和
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
请你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。


示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。

示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。


示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
 */
public class Solution07_ThreeNums {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(threeSumOptimize(nums));
    }

    /*优化的思路来自于可不可以不用每次都从最两端重新夹闭？
    * a = nums[i](i->0-n-3)  left = i+1  right = n-1
    * 不超时，复杂度为n^2,因为base是a,for循环可以跳过base，因此也无需判断contains少了个logn
    *
    * 而超时解法，由于base是b，for循环不能跳过base，导致了代码中需要进一步判断contains增加了logn的复杂度*/
    public static List<List<Integer>> threeSumOptimize(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ansList = new ArrayList<>();

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (nums[i]!=0){
                flag = false;
            }
        }
        if (flag){
            Integer[] ints = {0,0,0};
            ansList.add(Arrays.asList(ints));
            return ansList;
        }


        Arrays.sort(nums);

        for (int i = 0; i < n-2; i++) {
            int left = i+1,right = n-1;
            int a = nums[i];
            int b = nums[left];
            int c = nums[right];

            if (i>0&&a==nums[i-1]){//跳过重复元素用continue.退出循环使用break
                continue;
            }

            while(left<right){

                int target = a+b+c;
                if (target==0){
                    Integer[] ints = {a,b,c};
                    List<Integer> list = Arrays.asList(ints);
                    ansList.add(list);
                    while (b == nums[++left]&&left<right){
                    }//跳过重复元素
                    while (c == nums[--right]&&left<right){
                    }//跳过重复元素
                    b = nums[left];
                    c = nums[right];
                }else if (target<0){
                    while (b == nums[++left]&&left<right){
                    }//跳过重复元素
                    b = nums[left];
                }else {
                    while (c == nums[--right]&&right>i){
                    }//跳过重复元素
                    c = nums[right];
                }
            }
        }
        return ansList;
    }

    /*
    left=0,   b = nums[i](i->1~n-2)   right=n-1
    超时，复杂度：n^2logn, 第一个n是for循环，第二个n是while循环，logn是contains判断
    */
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ansList = new ArrayList<>();


        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (nums[i]!=0){
                flag = false;
            }
        }
        if (flag){
            Integer[] ints = {0,0,0};
            ansList.add(Arrays.asList(ints));
            return ansList;
        }


        Arrays.sort(nums);

        for (int i = 1; i < n-1; i++) {
            int left = 0,right = n-1;
            int b = nums[i];
            int a = nums[left];
            int c = nums[right];


            while(left<i&&right>i){

                int target = a+b+c;
                if (target==0){
                    Integer[] ints = {a,b,c};
                    List<Integer> list = Arrays.asList(ints);
                    if (!ansList.contains(list)){
                        ansList.add(list);
                    }
                    ansList.add(list);
                    while (a == nums[++left]&&left<i){
                    }//跳过重复元素
                    while (c == nums[--right]&&right>i){
                    }//跳过重复元素
                    a = nums[left];
                    c = nums[right];
                }else if (target<0){
                    while (a == nums[++left]&&left<i){
                    }//跳过重复元素
                    a = nums[left];
                }else {
                    while (c == nums[--right]&&right>i){
                    }//跳过重复元素
                    c = nums[right];
                }
            }
        }
        return ansList;
    }
}
