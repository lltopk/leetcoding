package org.lyflexi.solutions.baseAlgorithm.diff;

/**
 * 2848. 与车相交的点
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。对于任意下标 i，nums[i] = [starti, endi] ，其中 starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。
 *
 * 返回数轴上被车 任意部分 覆盖的整数点的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [[3,6],[1,5],[4,7]]
 * 输出：7
 * 解释：从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。
 * 示例 2：
 *
 * 输入：nums = [[1,3],[5,8]]
 * 输出：7
 * 解释：1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * nums[i].length == 2
 * 1 <= starti <= endi <= 100
 */

import java.util.List;

/**
 * 差分数组
 */
public class LC2848_numberOfPoints {
    public int numberOfPoints(List<List<Integer>> nums) {
        //1. 确定差分数组大小
        int maxEnd = 0;
        for(List<Integer> interval: nums){
            maxEnd = Math.max(maxEnd, interval.get(1));
        }
        int len = maxEnd+1;
        int[] diff = new int[len+1];//再加1是因为下面要用到差分数组性质2, 唯二差分值中第二个值是j+1;

        //2. 计算差分数组
        for(List<Integer> interval: nums){
            diff[interval.get(0)]++;
            diff[interval.get(1) + 1]--;
        }


        //3. 还原原数组
        int s = 0, ans = 0;
        for(int x: diff){
            s+=x;
            if(s>0){
                ans++;
            }
        }
        return ans;
    }
}
