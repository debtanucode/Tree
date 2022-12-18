package com.gfg.main;

import com.gfg.medium.util.NodeUtil;
import model.Node;

public class TestNodeUtil {
    public static void main(String[] args) {
        String str= "1 2 3";
        Node root = NodeUtil.buildTree(str);
        System.out.println(NodeUtil.inOrder(root));
    }
}
