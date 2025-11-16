package org.lyflexi.solutions.binarysearch;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/2 15:13
 * @description
 * 528. 按权重随机选择
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
 *
 * 请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i 的 概率 为 w[i] / sum(w) 。
 *
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * 输出：
 * [null,0]
 * 解释：
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * 示例 2：
 *
 * 输入：
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * 输出：
 * [null,1,1,1,1,0]
 * 解释：
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 *
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 */
public class LC528_Solution {
    int prefix[];
    int total;//初始不知道长度

    //prefix sum + bis
    public LC528_Solution(int[] w) {
        int n = w.length;
        prefix = new int[n];//构造函数这里才知道具体的长度
        int sum = 0;
        for(int i = 0;i<n;i++){
            sum+=w[i];
            prefix[i] = sum;
        }
        total = sum;
    }

    public int pickIndex() {
        int x = (int)(Math.random()*total) + 1;//[0,1)内的随机数*total
        return bis(x);
    }

    // prefix[0]...prefix[n], 单调递增, n为原数组w的长度
    // 其中prefix[n] == sum
    private int bis(int target){
        int l = 0, r = prefix.length;
        while(l<r){
            int midIndex = l + ((r-l)>>1);
            if(prefix[midIndex]>target){
                r = midIndex;
            }else if(prefix[midIndex]<target){
                l = midIndex +1;
            }else {
                //prefix[midIndex] == target
                return midIndex;
            }
        }
        return l;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */