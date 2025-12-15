package org.lyflexi.solutions.Ⅲdivideconquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hasee
 * @description V1.0
 * 436. 寻找右区间
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 *
 * 区间 i 的 右侧区间 是满足 startj >= endi，且 startj 最小 的区间 j。注意 i 可能等于 j 。
 *
 * 返回一个由每个区间 i 对应的 右侧区间 下标组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * 示例 2：
 *
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1,0,1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * 示例 3：
 *
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1,2,-1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点
 * @create 2025/10/7 18:08
 */
public class LC436_findRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        int m = intervals.length;
        if(m==1){
            return intervals[0][0]>=intervals[0][1]?new int[]{0}:new int[]{-1};
        }
        int[] rs = new int[m];
        int[] colume = new int[m];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i= 0;i<m;i++){
            colume[i] = intervals[i][0];
            map.put(intervals[i][0], i);
        }
        //二分前置排序
        Arrays.sort(colume);
        for(int i= 0;i<m;i++){
            Integer target = findTarget(colume,intervals[i][1]);
            if(target == null){
                rs[i] = -1;
            }else{
                rs[i] = map.get(target);
            }
        }
        return rs;

    }

    private Integer findTarget(int[] colume, int target){
        int l = 0;
        int n = colume.length;
        int r = n;
        int midIndex = -1;
        while(l<r){
            midIndex = l+((r-l)>>1);
            if(colume[midIndex]>target){
                r = midIndex;
            }else if(colume[midIndex]<target){
                l = midIndex +1;
            }else{
                l = r = midIndex;
                return colume[l];
            }
        }
        //没有找到, 都比target小, 则l==n
        if(l==n){
            return null;
        }
        return colume[l]>target?colume[l]:null;
    }
}
