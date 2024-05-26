package avltree;

import arbolesPractica01.ItemDuplicated;
import arbolesPractica01.ItemNoFound;

public class TestEjer2 {
    public static void main(String[] args) {
        AVLTree<Integer> treeAVL = new AVLTree<>();
        try {
            // Insertar elementos en el árbol AVL
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
            
            // Mostrar el árbol AVL antes de eliminar un elemento
            System.out.println("Árbol AVL antes de eliminar el elemento:");
            System.out.println(treeAVL);
            
            // Eliminar el elemento 14 del árbol AVL
            System.out.println("Eliminar el elemento 14 del árbol AVL...");
            treeAVL.remove(14);
            
            // Mostrar el árbol AVL después de eliminar un elemento
            System.out.println("Árbol AVL después de eliminar el elemento:");
            System.out.println(treeAVL);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
    }
}