package com.gfg.medium;

import com.gfg.model.Node;
import com.gfg.util.NodeUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class TopViewOfBT {
    public static void main(String[] args) {
        //String input = "1 2 3 4 5 6 7";
        String input = "7 5 8 2 6 N 56 1 3 N N 10 57 N N N 4 9 51 N N N N N N 13 52 12 45 N 55 11 N 20 49 54 N N N 15 44 48 50 53 N 14 18 23 N 46 N N N N N N N 16 19 21 30 N 47 N 17 N N N 22 28 34 N N N N N N 24 29";
        Node root = NodeUtil.buildTree(input);
        ArrayList<Integer> arr = topView(root);
        for(Integer i: arr){
            System.out.print(i+ " ");
        }
        System.out.println("\nCorrect output : \n1 2 5 7 8 56 57 55");
    }

    static ArrayList<Integer> topView(Node root)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> viewMap= new HashMap<Integer, Integer>();
        HashMap<String, Integer> proMap= new HashMap<String, Integer>();
        HashMap<String, Integer> levelMap = new HashMap<>();
        int index = 0;
        int level = 1;
        proMap.put("minIndex", index);
        proMap.put("maxIndex", index);
        preOrder(root, viewMap, proMap, levelMap, index, level);
        for(int i = proMap.get("minIndex"); i<=proMap.get("maxIndex"); i++){
            arr.add(viewMap.get(i));
        }
        return arr;
    }

    private static void preOrder(Node node , HashMap<Integer, Integer> viewMap , HashMap<String, Integer> proMap , HashMap<String, Integer> levelMap, int index, int level) {
        if(node!=null){
            if(!viewMap.containsKey(index)){
                viewMap.put(index, node.data);
                //
                String key = createKey(index, node.data);
                levelMap.put(key, level);

                if(index< proMap.get("minIndex")){
                    proMap.put("minIndex", index);
                }
                if(index> proMap.get("maxIndex")){
                    proMap.put("maxIndex", index);
                }
            }else {
                Integer existingData = viewMap.get(index);
                String key= createKey(index, existingData);
                int existingLevel = levelMap.get(key);
                if(existingLevel>level){
                    viewMap.put(index, node.data);
                    String newKey = createKey(index, node.data);
                    levelMap.put(newKey, level);
                    levelMap.remove(key);
                }
            }

            preOrder(node.left, viewMap, proMap, levelMap, index-1, level + 1);
            preOrder(node.right, viewMap, proMap, levelMap, index+1, level+1);
        }
    }

    private static String createKey(Integer index, Integer data){
        return (index.toString()+"#"+data.toString());
    }

}
