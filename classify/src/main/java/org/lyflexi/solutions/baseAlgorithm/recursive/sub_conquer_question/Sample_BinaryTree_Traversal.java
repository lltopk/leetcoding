package org.lyflexi.solutions.baseAlgorithm.recursive.sub_conquer_question;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Sample_BinaryTree_Traversal {
    /**
     *          1
     *        2     3
     *      4   5  6  7
     *                 8
     *                  9
     *                   10
     *                    11
     *                     12
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);
        root.right.right.right.right.right = new TreeNode(10);
        root.right.right.right.right.right.right = new TreeNode(11);
        root.right.right.right.right.right.right.right = new TreeNode(12);
        orderTraversal(root);
    }
    public static void orderTraversal(TreeNode root) {
        List<Integer> resPreOrder = new ArrayList<Integer>();
        List<Integer> resInOrder = new ArrayList<Integer>();
        List<Integer> resPostOrder = new ArrayList<Integer>();
        order(root, resPreOrder, resInOrder, resPostOrder);


        System.out.println(resPreOrder);
        System.out.println(resInOrder);
        System.out.println(resPostOrder);
    }

    public static void order(TreeNode root, List<Integer> resPreOrder, List<Integer> resInOrder, List<Integer> resPostOrder) {
        if (root == null) {
            return;
        }

        resPreOrder.add(root.val);//前序续遍历
        order(root.left, resPreOrder, resInOrder, resPostOrder);
        resInOrder.add(root.val);//中续遍历
        order(root.right, resPreOrder, resInOrder, resPostOrder);
        resPostOrder.add(root.val);//后续遍历
    }
}
