package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 520,237/852.1K
 * 通过率
 * 61.1%
 */

/**
 * 注意这题不能通过“改变入队方向”来实现锯齿
 *
 * 因为改变当前层入队方向, 不光影响的是当前层, 还会影响下一层的顺序
 */
public class LC103_zigzagLevelOrder {
    List<List<Integer>> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();

    /**
     * 改变每层答案layer接收元素的方向即可
     * 使用LinkedList来作为每一层的答案layer, 这样可以根据层号判断 头插 / 尾插
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        deque.offer(root);
        boolean even = false;
        while(! deque.isEmpty()){
            LinkedList<Integer> layer = new LinkedList<>();//由于addFirst是接口Deque的方法, LinkedList实现了Deque, 但这里题目要求接收List, 所以只能使用具体的实现类LinkedList
            int n = deque.size();
            while(n-- > 0){
                TreeNode node = deque.poll();
                if(even){
                    layer.addFirst(node.val);
                }else{
                    layer.add(node.val);
                }
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            even = !even;
            ret.add(layer);
        }
        return ret;
    }
}
