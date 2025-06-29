package org.lyflexi.solutions.dfs;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: ly
 * @Date: 2024/2/15 17:50
 */

/*LCR 145. 判断对称二叉树
请设计一个函数判断一棵二叉树是否 轴对称 。


答案：我们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，ppp 指针和 qqq 指针一开始都指向这棵树的根，
随后 ppp 右移时，qqq 左移，ppp 左移时，qqq 右移。每次检查当前 ppp 和 qqq 节点的值是否相等，


*/
public class Solution06_IsMirrorTree {

    public boolean checkSymmetricTree(TreeNode root) {

        return checkTwoTree(root, root);
    }


    //    boolean递归函数的返回值，必须考虑true和false两种情况
    private boolean checkTwoTree(TreeNode node1, TreeNode node2) {
//    node1与node2的true或者false组合一共就是下面四种情况


        if (node1 == null && node2 == null) {
            return true;//出口true
        }


        if (node1==null||node2==null){
            return false;//出口false
        }

        //业务逻辑
        if (node1.val == node2.val){
            return checkTwoTree(node1.left, node2.right)
                    && checkTwoTree(node1.right, node2.left);//一种：node1 != null &&node2!=null
        }

        return false;
    }
}


