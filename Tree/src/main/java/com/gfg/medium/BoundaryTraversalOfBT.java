package com.gfg.medium;

import com.gfg.model.Node;
import com.gfg.util.NodeUtil;

import java.util.ArrayList;

public class BoundaryTraversalOfBT {
    public static void main(String[] args) {
        //String input = "1 2 3 4 5 6 7 N N 8 9";
        String input = "4 10 N 5 5 N 6 7 N 8 8 N 8 11 N 3 4 N 1 3 N 8 6 N 11 11 N 5 8";
        Node root = NodeUtil.buildTree(input);
        System.out.println(NodeUtil.inOrder(root));

        ArrayList<Integer> arr= boundary(root);
        System.out.println("elements form the boundary :");
        for(Integer a: arr){
            System.out.print(" "+ a);
        }
    }

    private static ArrayList<Integer> boundary(Node node) {
        ArrayList<Integer> arr= new ArrayList<Integer>();
        traverse(node, arr, true, true);
        return arr;
    }

    private static void traverse(Node node , ArrayList<Integer> arr, boolean isLeft, boolean isRight) {
        if(node!=null){
            if(isLeft){
                arr.add(node.data);
            } else if (node.left == null && node.right==null && !isRight) {
                arr.add(node.data);
            }


            if(!isLeft && isRight && node.right==null){
                traverse(node.left , arr , isLeft , true);
            }else {
                traverse(node.left , arr , isLeft , false);
            }

            if(!isRight && isLeft && node.left==null){
                traverse(node.right , arr , true , isRight);
            }else {
                traverse(node.right , arr , false , isRight);
            }
            if(isRight && !isLeft){
                arr.add(node.data);
            }
        }
    }
}
