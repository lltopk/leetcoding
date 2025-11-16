package org.lyflexi.solutions.binarysearch;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/18 19:10
 * @description
 * 1539. 第 k 个缺失的正整数
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
 *
 * 请你找到这个数组里第 k 个缺失的正整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [2,3,4,7,11], k = 5
 * 输出：9
 * 解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
 * 示例 2：
 *
 * 输入：arr = [1,2,3,4], k = 2
 * 输出：6
 * 解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
 */
public class LC1539_findKthPositive {
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int l =0 , r = n ;

        //原数组映射为arr[i] - i -1, 含义是到第i个位置, 缺失的元素个数, 它是个非严格递增序列(包含重复元素)
        //目标值为给出的k
        while(l<r){
            int midIndex = l + ((r - l)>>1);
            if(arr[midIndex] - midIndex -1>=k){//求左界
                r = midIndex;
            }else {
                l = midIndex+1;
            }

        }
        //既然求缺失的值, 那么该值一定不存在原数组, 所以我们需要掌握所有的不存在边界情况
        if(l == 0){
            return k;//第一个元素就缺失很多了
        }
        if(l == n){
            return arr[n-1] + k - (arr[n-1] -(n-1+1)); //最后一个元素缺失的还不够
        }
        //在元素组内部缺失
        return arr[l-1] + k - (arr[l-1] -(l-1+1));
    }
}
