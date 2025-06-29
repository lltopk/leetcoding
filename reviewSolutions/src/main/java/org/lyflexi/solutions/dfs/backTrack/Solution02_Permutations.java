package org.lyflexi.solutions.dfs.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/16 8:21
 */


/*46. 全排列
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]

示例 3：
输入：nums = [1]
输出：[[1]]
 */
public class Solution02_Permutations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();//回溯发动机，内部元素list


        dfsBackTrace(nums,answer,list);//list本身通过增减操作就可以控制DFS深度，所以回溯函数中省去了DFS深度控制参数

        return answer;

    }

    private static void dfsBackTrace(int[] nums, List<List<Integer>> answer, ArrayList<Integer> list) {

        int n = nums.length;
        if (list.size()==n){
            answer.add(new ArrayList<>(list));//这个地方不可以直接添加list，必须使用ArrayList构造函数，否则list引用会影响answer的结果
            return;
        }

        for (int i = 0; i < n; i++) {
            int item = nums[i];

            if (list.contains(item)){//每次都从头开始遍历nums,所以这里是在纵向去重，而且省略了标记数组flag
                continue;
            }

            list.add(item);

            dfsBackTrace(nums,answer,list);

            list.remove(list.size()-1);//回溯清除
        }

    }
}


