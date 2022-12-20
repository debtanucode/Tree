package com.gfg.medium;

import com.gfg.model.Node;
import com.gfg.util.NodeUtil;

import java.util.HashMap;

public class SumTree {
    public static void main(String[] args) {
        String input = "3 1 2";
        Node root = NodeUtil.buildTree(input);
        System.out.println(NodeUtil.inOrder(root));

        System.out.println("Is a sum tree : "+ isSumTree(root));
    }

    private static boolean isSumTree(Node root){

        Integer sum = preOrder(root);
        if(sum == null){
            return false;
        } else {
            return  true;
        }
    }

    private static Integer preOrder(Node node) {
        if(node!=null){
            Integer leftValue = 0;
            Integer rightValue = 0;

            if(node.left!=null){
                leftValue= preOrder(node.left);
            }

            if(node.right!=null){
                rightValue= preOrder(node.right);
            }

            if(leftValue == null || rightValue == null){
                return null;
            }

            if(node.left==null && node.right==null){
                return node.data;
            }else if((leftValue+rightValue)==node.data){
                return leftValue+rightValue+node.data;
            }else{
                return null;
            }
        }
        return 0;
    }
}
