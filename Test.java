package avltree;

import avltree.AVLTree;
import arbolesPractica01.BSTree;
import arbolesPractica01.ItemDuplicated;
import arbolesPractica01.ItemNoFound;

public class Test {
    public static void main(String[] args) {
    	AVLTree<Integer> treeAVL = new AVLTree<>();
    	try {
    		treeAVL.insert(10);
    		treeAVL.insert(5);
    		treeAVL.insert(3);
    		treeAVL.insert(20);
    		treeAVL.insert(14);
    		treeAVL.insert(1);
    		treeAVL.insert(28);
    		treeAVL.insert(25);
    		treeAVL.insert(4);
    		treeAVL.insert(2);
    		treeAVL.insert(16);
    		treeAVL.insert(19);
    		System.out.println(treeAVL);
    	}catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    	try {
    		treeAVL.remove(14);
    		System.out.println(treeAVL);
    	}catch (ItemNoFound e){
    		System.out.println(e.getMessage());
    	}
    } 
}
