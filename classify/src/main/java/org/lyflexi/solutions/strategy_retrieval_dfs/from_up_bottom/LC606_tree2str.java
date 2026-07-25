package org.lyflexi.solutions.strategy_retrieval_dfs.from_up_bottom;

import org.lyflexi.common.TreeNode;

/**
 * 606. 根据二叉树创建字符串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
 *
 * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,4]
 * 输出："1(2(4))(3)"
 * 解释：初步转化后得到 "1(2(4)())(3()())" ，但省略所有不必要的空括号对后，字符串应该是"1(2(4))(3)" 。
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3,null,4]
 * 输出："1(2()(4))(3)"
 * 解释：和第一个示例类似，但是无法省略第一个空括号对，否则会破坏输入与输出一一映射的关系。
 *
 *
 * 提示：
 *
 * 树中节点的数目范围是 [1, 104]
 * -1000 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 91,443/146.8K
 * 通过率
 * 62.3%
 */
public class LC606_tree2str {
    StringBuilder buffer = new StringBuilder();
    public String tree2str(TreeNode root) {
        dfs(root);
        return buffer.substring(1, buffer.length() -1);
    }

    //自顶向下
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        buffer.append('(');
        buffer.append(root.val);
        if(root.left == null && root.right!=null){
            buffer.append('(');
            buffer.append(')');
        }
        dfs(root.left);
        dfs(root.right);
        buffer.append(')');
    }
}
