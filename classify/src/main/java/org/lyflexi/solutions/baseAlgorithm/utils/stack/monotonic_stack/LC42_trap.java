package org.lyflexi.solutions.baseAlgorithm.utils.stack.monotonic_stack;

/**
 * 42. 接雨水
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,809,700/2.7M
 * 通过率
 * 66.0%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈: 计算水泥板
 *
 * 从左向右遍历, 目的是输出left[]数组
 */
public class LC42_trap {
    //单调栈, 栈顶存储最小元素下标, 如遇大元素, 则循环退栈再入栈
    Deque<Integer> sk = new ArrayDeque<>();
    public int trap(int[] height) {
        int ret = 0;
        for(int i = 0 ; i< height.length; i++){
            while(!sk.isEmpty() && height[sk.peek()] <= height[i]){
                int peek = sk.pop();
                if(sk.isEmpty()){
                    break;
                }
                //高度: 当前柱子和 栈顶的上个柱子的最小值, 再减去底部的高度
                int botoomH = Math.min(height[sk.peek()], height[i]) - height[peek];
                //宽度: 当前柱子索引, 和 栈顶的上个索引 之差-1
                ret +=  botoomH * (i - sk.peek() -1);
            }
            sk.push(i);
        }
        return ret;
    }
}
