package arithmetic.tree;

public class Test {
    public static void main(String[] args) {
        BinaryTree bt=new BinaryTree();
        bt.insert(3);
        bt.insert(2);
        bt.insert(7);
        bt.insert(5);
        bt.findMax().display();
        bt.findMin().display();
        bt.infixOrder(bt.getRoot());
    }
}
