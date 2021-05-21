package interview;

public class LevelOrderSortedBinaryTree {
    public boolean findElement(Node root, int element){
        int level = findLevel(root, element);
        if(level == -1){
            return false;
        }
        return findElement(root, element, level);
    }

    private boolean findElement(Node root, int element, int level){
        int mid = findMid(root, level);
        if(level == 0){
            return mid == element;
        }
        if(mid > element){
            return findElement(root.left, element, level-1);
        } else if(mid < element){
            return findElement(root.right, element, level-1);
        }else{
            return mid == element;
        }
    }



    private int findLevel(Node root, int element){
        if(root.data > element){
            return -1;
        }
        int level = 0;
        Node temp = root;
        while(temp != null){
            if(temp.data < element){
                level++;
            }else{
                return level;
            }
            temp = temp.right;
        }
        return -1;
    }

    private int findMid(Node node, int level){
        if(level == 0){
            return node.data;
        }
        int currentLevel = level-1;
        Node currentNode = node.right;
        while(currentLevel > 0){
            currentNode = currentNode.left;
            currentLevel--;
        }
        return currentNode.data;
    }

    public static void main(String[] args) {
        Node root11 = new Node(null, null, 11);
        Node root12 = new Node(null, null, 12);
        Node root14 = new Node(null, null, 14);
        Node root16 = new Node(null, null, 16);
        Node root17 = new Node(null, null, 17);
        Node root19 = new Node(null, null, 19);
        Node root21 = new Node(null, null, 21);
        Node root23 = new Node(null, null, 23);

        Node root4 = new Node(root11, root12, 4);
        Node root5 = new Node(root14, root16, 5);
        Node root7 = new Node(root17, root19, 7);
        Node root9 = new Node(root21, root23, 9);

        Node root2 = new Node(root4, root5, 2);
        Node root3 = new Node(root7, root9, 3);
        Node root = new Node(root2, root3, 1);

        LevelOrderSortedBinaryTree lv = new LevelOrderSortedBinaryTree();

        System.out.println(lv.findElement(root, 4));

    }
}
