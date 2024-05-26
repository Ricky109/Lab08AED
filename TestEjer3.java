package avltree;

import arbolesPractica01.ItemDuplicated;
import arbolesPractica01.ItemNoFound;

public class TestEjer3 {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        // Insertamos los valores en el árbol
        try {
            tree.insert(39);
            tree.insert(27);
            tree.insert(50);
            tree.insert(18);
            tree.insert(35);
            tree.insert(46);
            tree.insert(87);
            tree.insert(7);
            tree.insert(24);
            
            
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }try {
        	System.out.println("Recorrido por niveles:");
            tree.breadthFirstTraversal();
        }catch(ItemNoFound e) {
        	System.out.println(e.getMessage());
        }

        // Realizamos el recorrido por niveles
        
    }
}
