/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lyflexi.solutions.strategy_divide;

import java.util.ArrayList;
import java.util.List;

import org.lyflexi.common.TreeNode;

/**
 *
 * @author hasee
 * 2476. 二叉搜索树最近节点查询
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。

请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：

mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
返回数组 answer 。

 

示例 1 ：



输入：root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
输出：[[2,2],[4,6],[15,-1]]
解释：按下面的描述找出并返回查询的答案：
- 树中小于等于 2 的最大值是 2 ，且大于等于 2 的最小值也是 2 。所以第一个查询的答案是 [2,2] 。
- 树中小于等于 5 的最大值是 4 ，且大于等于 5 的最小值是 6 。所以第二个查询的答案是 [4,6] 。
- 树中小于等于 16 的最大值是 15 ，且大于等于 16 的最小值不存在。所以第三个查询的答案是 [15,-1] 。
示例 2 ：



输入：root = [4,null,9], queries = [3]
输出：[[-1,4]]
解释：树中不存在小于等于 3 的最大值，且大于等于 3 的最小值是 4 。所以查询的答案是 [-1,4] 。
 

提示：

树中节点的数目在范围 [2, 105] 内
1 <= Node.val <= 106
n == queries.length
1 <= n <= 105
1 <= queries[i] <= 106
 */

/**
 * 思路是先中序遍历, 将树转为有序数组, 然后对每个query[i]使用二分查找
 */
public class LC2476_closestNodes {

    /**
     * Integer不能使用 != 比较, 因为只有当 x1, x2 在 [-128, 127] 范围内时：返回的才是缓存中的同一个 Integer 对象, 此时x1 == x2
     * 
     * 
     * 超过这个范围, 会创建新的new Integer()引用, 导致比较错误
     */
//    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
//         List<Integer> sortedArray = new ArrayList<>();
//         inOrderDfs(root, sortedArray);
//         int n = sortedArray.size();
//         List<List<Integer>> rs = new ArrayList<>();
//         for(int i = 0; i<queries.size();i++){
//             int l = lowerBound(sortedArray, queries.get(i));
//             int maxi = l==n? -1: sortedArray.get(l);
//             //未找到, (Integer不能使用 != 比较, 因为只有当 x 在 [-128, 127] 范围内时：返回的才是缓存中的同一个 Integer 对象)
//             if(l == n || !sortedArray.get(l).equals(queries.get(i))){
//                 l--;
//             }
//             int mini = l < 0? -1: sortedArray.get(l);
//             rs.add(List.of(mini, maxi));//如果二分查找成功, 则mini == maxi
//         }
//         return rs;
//     }
//     private int lowerBound(List<Integer> arr, int target){
//         int n = arr.size();
//         int l = 0, r = n ;
//         while(l<r){
//             int mid = l + ((r-l)>>1);
//             if(arr.get(mid)< target){
//                 l = mid +1;
//             }else{
//                 r = mid;
//             }
//         }
//         return l;
//     }
//     private void inOrderDfs(TreeNode root, List<Integer> sortedArray){
//         if(root == null){
//             return ;
//         }
//         inOrderDfs(root.left, sortedArray);
//         sortedArray.add(root.val);
//         inOrderDfs(root.right, sortedArray);
//     }

    /**
     * 优化: 使用数组存储中序遍历结果, 避免Interger(List)装箱拆箱开销
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<>();
        dfs(root, arr);

        int n = arr.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = arr.get(i); // 转成数组，效率更高
        }

        List<List<Integer>> ans = new ArrayList<>(queries.size()); // 预分配空间
        for (int q : queries) {
            int j = lowerBound(a, q);
            int mx = j == n ? -1 : a[j];
            if (j == n || a[j] != q) { // a[j]>q, a[j-1]<q
                j--;
            }
            int mn = j < 0 ? -1 : a[j];
            ans.add(List.of(mn, mx));
        }
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> a) {
        if (node == null) {
            return;
        }
        dfs(node.left, a);
        a.add(node.val);
        dfs(node.right, a);
    }

    private int lowerBound(int[] a, int target) {
        int n = a.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
