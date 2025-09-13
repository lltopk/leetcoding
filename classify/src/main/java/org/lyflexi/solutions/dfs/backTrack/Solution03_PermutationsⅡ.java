package org.lyflexi.solutions.dfs.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/16 8:21
 */


/*47. 全排列 II
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

示例 1：
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
 
示例 2：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 
 */
public class Solution03_PermutationsⅡ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(permuteUnique(nums));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] flags = new boolean[nums.length];
        Arrays.sort(nums);//对nums数组提前排序，为了支持nums[i] == nums[i - 1]判断
        dfsBackTrace(nums,answer,list,flags);
        return answer;
    }

    private static void dfsBackTrace(int[] nums,List<List<Integer>> answer,List<Integer> list,boolean[] flags){
        int n = nums.length;
        if(list.size()==n){
            answer.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i<n; i++){
            if(flags[i]){//flag为true代表纵向已经遍历过了,纵向去重
                continue;
            }

            if(i>0&&nums[i] == nums[i-1]&&flags[i-1]==false){//横向去重
                continue;
            }

            list.add(nums[i]);
            flags[i] = true;
            dfsBackTrace(nums,answer,list,flags);
            list.remove(list.size()-1);//回溯现场还原
            flags[i] = false;//别忘了相应的标记也要还原
        }


    }
}


