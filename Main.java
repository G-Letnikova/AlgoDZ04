class Tree{
    private Node root;
    class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color{
        BLACK,
        RED
    }

    public void insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
        }else{
            insert(root, value);
            root = reBalance(root);
        }
        root.color = Color.BLACK;
    }

    private void insert(Node node, int value){
        if(node.value != value){
            if(node.value < value){
                if(node.right == null){
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                }else{
                    insert(node.right, value);
                    node.right = reBalance(node.right);
                }
            }else{
                if(node.left == null){
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                }else{
                    insert(node.left, value);
                    node.left = reBalance(node.left);
                 }
            }
        }
    }

    private Node reBalance(Node node) {
        Node result = node;
        boolean needBalance;
        do {
            needBalance = false;
            if (result.right != null && result.right.color == Color.RED 
                && (result.left == null || result.left.color == Color.BLACK)) {
                needBalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED 
                && result.left.left != null && result.left.left.color == Color.RED) {
                needBalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED 
                && result.right != null && result.right.color == Color.RED) {
                needBalance = true;
                colorSwap(result);
            }
        }
        while (needBalance);
        return result;
    }

    private Node leftSwap(Node node){
        Node left = node.left;
        Node tempNode = left.right;
        left.right = node;
        node.left = tempNode;
        left.color = node.color;
        node.color = Color.RED;
        return left;
        }
    
    private Node rightSwap(Node node){
        Node right = node.right;
        Node tempNode = right.left;
        right.left = node;
        node.right = tempNode;
        right.color = node.color;
        node.color = Color.RED;
        return right;
        }

    private void colorSwap(Node node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
        }
}

public class Main {
    public static void main(String[] args) {
    Tree rbt = new Tree();
    rbt.insert(1);
    rbt.insert(10);
    rbt.insert(5);
    rbt.insert(4);
    rbt.insert(8);
    rbt.insert(2);
    }
}