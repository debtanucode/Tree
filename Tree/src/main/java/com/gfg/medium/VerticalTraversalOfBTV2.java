package com.gfg.medium;

import com.gfg.model.Node;
import com.gfg.util.NodeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class VerticalTraversalOfBTV2 {
    public static void main(String[] args) {
        String input = "1 2 3 4 5 6 7 N N N N N 8 N 9";
        //String input = "4 10 N 5 5 N 6 7 N 8 8 N 8 11 N 3 4 N 1 3 N 8 6 N 11 11 N 5 8";
        Node root = NodeUtil.buildTree(input);
        System.out.println(NodeUtil.inOrder(root));

        ArrayList<Integer> verticalOrderArray = verticalOrder(root);
        System.out.println("Vertical ordered array :");
        for(Integer element: verticalOrderArray){
            System.out.print(element+" ");
        }

        System.out.println("\nCorrect output : \n4 2 1 5 6 3 8 7 9");
    }

    static ArrayList<Integer> verticalOrder(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, TreeMap<Integer, ArrayList<Integer>>> vMap = new HashMap<>();
        HashMap<String, Integer> proMap = new HashMap<>();
        proMap.put("minVertex", 0);
        proMap.put("maxVertex", 0);
        int v = 0;
        int startingLevel = 1;
        preOrder(root, vMap, proMap, v, startingLevel);

        for(int i= proMap.get("minVertex"); i<= proMap.get("maxVertex");i++){
            TreeMap<Integer, ArrayList<Integer>> verSortedMap = vMap.get(i);
            for(Integer level: verSortedMap.keySet()) {
                arr.addAll(verSortedMap.get(level));
            }
        }
        return arr;
    }

    private static void preOrder(Node node , HashMap<Integer, TreeMap<Integer, ArrayList<Integer>>> vMap , HashMap<String, Integer> proMap , int vertex , int level) {
        if(node!=null){
            if(!vMap.containsKey(vertex)){
                ArrayList<Integer> elements = new ArrayList<Integer>();
                elements.add(node.data);

                TreeMap<Integer, ArrayList<Integer>> verSortedMap = new TreeMap<Integer, ArrayList<Integer>>();
                verSortedMap.put(level, elements);
                vMap.put(vertex, verSortedMap);

                // update vertex value in property map
                updateVertexInPropertyMap(proMap, vertex);
            }else{
                TreeMap<Integer, ArrayList<Integer>> verSortedMap = vMap.get(vertex);
                if(!verSortedMap.containsKey(level)){
                    ArrayList<Integer> elementsAtSameVertex = new ArrayList<>();
                    elementsAtSameVertex.add(node.data);
                    verSortedMap.put(level, elementsAtSameVertex);
                }else{
                    ArrayList<Integer> elementsAtSameVertex = verSortedMap.get(level);
                    elementsAtSameVertex.add(node.data);
                }
            }

            preOrder(node.left, vMap, proMap, vertex-1, level+1);
            preOrder(node.right, vMap, proMap, vertex+1, level+1);

        }
    }

    private static void updateVertexInPropertyMap(HashMap<String, Integer> proMap , int vertex) {
        if(vertex< proMap.get("minVertex")){
            proMap.put("minVertex", vertex);
        }
        if(vertex> proMap.get("maxVertex")){
            proMap.put("maxVertex", vertex);
        }
    }
}
