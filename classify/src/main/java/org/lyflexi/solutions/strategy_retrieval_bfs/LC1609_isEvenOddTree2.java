package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 1609. 奇偶树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 *
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * 输出：true
 * 解释：每一层的节点值分别是：
 * 0 层：[1]
 * 1 层：[10,4]
 * 2 层：[3,7,9]
 * 3 层：[12,8,6,2]
 * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 * 示例 2：
 *
 *
 *
 * 输入：root = [5,4,2,3,3,7]
 * 输出：false
 * 解释：每一层的节点值分别是：
 * 0 层：[5]
 * 1 层：[4,2]
 * 2 层：[3,3,7]
 * 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
 * 示例 3：
 *
 *
 *
 * 输入：root = [5,9,1,3,5,7]
 * 输出：false
 * 解释：1 层上的节点值应为偶数。
 * 示例 4：
 *
 * 输入：root = [1]
 * 输出：true
 * 示例 5：
 *
 * 输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * 输出：true
 *
 *
 * 提示：
 *
 * 树中节点数在范围 [1, 105] 内
 * 1 <= Node.val <= 106
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 42,769/71.7K
 * 通过率
 * 59.6%
 */

/**
 * 空间优化: 每层不用单独开辟List<Integer> list = new ArrayList<>();
 */
public class LC1609_isEvenOddTree2 {
    int layer = 0;
    Deque<TreeNode> deque = new ArrayDeque<>();
    public boolean isEvenOddTree(TreeNode root) {
        if(root == null){
            return false;
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            //空间优化, 每层不用单独开辟List<Integer> list = new ArrayList<>();
            int pre = 0;
            boolean first = true;//每行的第一个值, 不与pre比较
            while(n-- > 0){
                TreeNode node = deque.poll();
                if(layer == 0){//当前是偶层
                    if((node.val & 1) == 0 || (!first && node.val <= pre)) return false;
                }else{//当前是奇层
                    if((node.val & 1) == 1 || (!first && node.val >= pre)) return false;
                }
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
                pre = node.val;
                //第一个结束就修改first为false
                first = false;
            }
            //当前层结束, 才修改layer的奇偶性
            layer ^= 1;
        }
        return true;
    }
}
