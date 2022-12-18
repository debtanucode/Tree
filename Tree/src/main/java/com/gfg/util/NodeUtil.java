package com.gfg.util;

import com.gfg.model.Node;

import java.util.LinkedList;
import java.util.Queue;

public class NodeUtil {
    public static Node buildTree(String str){
        Node root = null;
        if((str!=null && !str.isEmpty()) || !str.equals('N') ){
            String[] s = str.split(" ");

            root = new Node(Integer.parseInt(s[0]));
            Queue<Node> q = new LinkedList<Node>();
            q.add(root);

            int i=1;
            while(!q.isEmpty() && i<s.length){
                Node currNode = q.remove();
                String currVal = s[i];
                if(!currVal.equals("N")){
                    currNode.left = new Node(Integer.parseInt(currVal));
                    q.add(currNode.left);
                }

                i++;
                if(i>=s.length){
                    break;
                }
                currVal = s[i];
                if(!currVal.equals("N")){
                    currNode.right = new Node((Integer.parseInt(currVal)));
                    q.add((currNode.right));
                }
                i++;
            }
        }
        return root;
    }

    public static String inOrder(Node node){
        StringBuilder str= new StringBuilder();
        if(node!=null){
            inOrder(node, str);
        }
        return str.toString();
    }

    private static void inOrder(Node node, StringBuilder str){
        if(node!=null){
            inOrder(node.left, str);
            str.append(node.toString());
            inOrder(node.right, str);
        }
    }
}
