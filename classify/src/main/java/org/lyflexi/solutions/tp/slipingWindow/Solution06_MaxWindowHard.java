package org.lyflexi.solutions.tp.slipingWindow;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/31 10:19
 */
/*第一次自己写出来啦*/

/*滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。

示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7



示例 2：
输入：nums = [1], k = 1
输出：[1]



*/

public class Solution06_MaxWindowHard {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        Integer k = Integer.parseInt(scanner.nextLine());
        int[] ans = maxSlidingWindow(array, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }



    // 我们可以想到，对于两个相邻（只差了一个位置）的滑动窗口，它们共用着 k−1个元素，而只有 1个元素是变化的。我们可以根据这个特点进行优化。
    // 因此维护窗口的数据结构就不能是数组了，因为数组需要移动元素
    // 最终想到了优先队列版滑动窗口，由于将一个元素放入优先队列的时间复杂度为 O(logn)，因此总时间复杂度为 O(nlogn)
    public static int[] maxSlidingWindow(int[] nums, int k) {

        //队列中的元素为数组，下标0代表nums[i]，下标1代表在nums中的索引
        PriorityQueue<int []> queue = new PriorityQueue<>(
                (item1,item2)->{return item2[0]-item1[0];}//大顶堆
        );

        int n = nums.length;
        int[] answer = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i],i});
        }
        answer[0] = queue.peek()[0];
        for (int i = 1; i < n - k + 1; i++) {
            //窗口往后走
            queue.offer(new int[]{nums[i+k-1],i+k-1});
            //只负责删除不在窗口内的大元素，小元素放在堆中我不管
            while (!queue.isEmpty()&&queue.peek()[1]<i){
                queue.poll();
            }
            answer[i] = queue.peek()[0];
        }

        return answer;

    }

}
