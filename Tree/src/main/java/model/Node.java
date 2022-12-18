package model;

public class Node {
    public int data;
    public Node left,right;

    public Node(int data){
        this.data = data;
        this.left= null;
        this.right = null;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return data+" ";
    }
}
