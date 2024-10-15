package com.tzy.algo;

import com.tzy.entity.TreeNode;

/**
 * @author: TZY
 * @create: 2024/9/10 18:03
 **/
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
        /**
         *     1
         *    / \
         *   2   3
         *  / \ /
         * 4  5 6
         */
//        maxDepth(root);
    }

    // 二叉树的遍历框架
    void traverseFrame(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        traverseFrame(root.left);
        // 中序位置
        traverseFrame(root.right);
        // 后序位置
    }

    static void preOrderTraversal(TreeNode node) {
        //根 左 右
        if (node == null) {
            return;
        }
        System.out.println("节点：" + node.val);
        //遍历左子树
        preOrderTraversal(node.left);
        //遍历右子树
        preOrderTraversal(node.right);
    }

    static void midOrderTraversal(TreeNode node) {
        //左 根 右
        if (node == null) {
            return;
        }
        midOrderTraversal(node.left);
        System.out.println("中序遍历" + node.val);
        midOrderTraversal(node.right);
    }

     int curDeep = 0;
     int maxDeep = 0;

    /**
     * 二叉树的最大深度
     */
    public  int maxDepth(TreeNode root) {
        traverse(root);
        return maxDeep;
    }

    public  void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        curDeep++;
        traverse(root.left);
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            maxDeep = Math.max(curDeep, maxDeep);
        }
        traverse(root.right);
        curDeep--;
    }






    /**
     * 通过递归实现循环
     */
    public void  loopArray(int[] arr,int index ){
        if(index>= arr.length){return;}
        System.out.println(arr[index]);
        index++;
        loopArray(arr,index);
    }




}
