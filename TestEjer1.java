package avltree;

import arbolesPractica01.BSTree;
import arbolesPractica01.ItemDuplicated;
import arbolesPractica01.ItemNoFound;

public class TestEjer1 {
    public static void main(String[] args) {

        //EJERCICIO 1

        // Crear instancias de BSTree y AVLTree
        BSTree<Integer> bst = new BSTree<>();
        AVLTree<Integer> avl = new AVLTree<>();

        // Caso de prueba 1: Insertar elementos en orden ascendente
        System.out.println("Caso de prueba 1: Insertar elementos en orden ascendente");

        int[] elementsAsc = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int elem : elementsAsc) {
            try {
                bst.insert(elem);
                avl.insert(elem);
            } catch (ItemDuplicated e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("BST in-order traversal: " + bst);
        System.out.println("AVL in-order traversal: " + avl);

        try {
            System.out.println("Altura del BST: " + bst.searchHeight(1));
            System.out.println("Altura del AVL: " + avl.searchHeight(1));
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }

        // Caso de prueba 2: Insertar elementos en orden aleatorio
        System.out.println("\nCaso de prueba 2: Insertar elementos en orden aleatorio");

        bst = new BSTree<>();
        avl = new AVLTree<>();

        int[] elementsRandom = { 4, 7, 2, 9, 1, 5, 3, 8, 6, 10 };

        for (int elem : elementsRandom) {
            try {
                bst.insert(elem);
                avl.insert(elem);
            } catch (ItemDuplicated e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("BST in-order traversal: " + bst);
        System.out.println("AVL in-order traversal: " + avl);

        try {
            System.out.println("Altura del BST: " + bst.searchHeight(4));
            System.out.println("Altura del AVL: " + avl.searchHeight(4));
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }

        // Búsqueda de un elemento común en ambos árboles
        try {
            System.out.println("\nBuscar 5 en BST: " + bst.search(5));
            System.out.println("Buscar 5 en AVL: " + avl.search(5));
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }

        // Búsqueda de un elemento no existente en ambos árboles
        try {
            System.out.println("Buscar 11 en BST: " + bst.search(11));
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Buscar 11 en AVL: " + avl.search(11));
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
    }
}
