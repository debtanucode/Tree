package com.gfg.medium;

import com.gfg.model.Node;
import com.gfg.util.NodeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;

public class BottomViewOfBT {
    public static void main(String[] args) {
        //String input = "1 2 3 4 5 6 7";
        String input = "14 14 3 N 8 8 12 N 6 17 3 N 1 11 10 N 6 6 13 N 10 17 7 N 11 7";
        Node root = NodeUtil.buildTree(input);
        ArrayList<Integer> arr = topView(root);
        for(Integer i: arr){
            System.out.print(i+ " ");
        }
        System.out.println("\nCorrect output : \n7 6 7 13 11 10");
    }

    static ArrayList<Integer> topView(Node root)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> indexMap= new HashMap<Integer, Integer>();
        HashMap<String, Integer> proMap= new HashMap<String, Integer>();
        HashMap<String, Integer> levelMap = new HashMap<>();
        int index = 0;
        int level = 1;
        proMap.put("minIndex", index);
        proMap.put("maxIndex", index);
        preOrder(root, indexMap, proMap, levelMap, index, level);
        for(int i = proMap.get("minIndex"); i<=proMap.get("maxIndex"); i++){
            arr.add(indexMap.get(i));
        }
        return arr;
    }

    private static void preOrder(Node node , HashMap<Integer, Integer> indexMap , HashMap<String, Integer> proMap , HashMap<String, Integer> levelMap, int index, int level) {
        if(node!=null){
            if(!indexMap.containsKey(index)){
                indexMap.put(index, node.data);
                String key = createKey(index, node.data);
                levelMap.put(key, level);
                updateIndex(proMap, index);
            }else{
                int existingData = indexMap.get(index);//3
                String existingKey = createKey(index, existingData);//0#3
                int existingLevel = levelMap.get(existingKey);//3
                if(level>= existingLevel){// 3>=3
                    indexMap.put(index, node.data);// 0, 4
                    levelMap.remove(existingKey);// 0#3 remove
                    String newKey = createKey(index, node.data);//0#4
                    levelMap.put(newKey, level);// 0#4 3 add
                }
            }
            preOrder(node.left, indexMap, proMap,levelMap, index-1, level+1);
            preOrder(node.right, indexMap, proMap,levelMap, index+1, level+1);
        }
    }

    private static void updateIndex(HashMap<String, Integer> proMap , int index) {
        if(index< proMap.get("minIndex")){
            proMap.put("minIndex", index);
        }

        if(index> proMap.get("maxIndex")){
            proMap.put("maxIndex", index);
        }
    }

    private static String createKey(Integer index, Integer data){
        return (index.toString()+"#"+data.toString());
    }

}
