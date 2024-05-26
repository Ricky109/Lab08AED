package avltree;

import java.util.LinkedList;
import java.util.Queue;
import arbolesPractica01.*;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
    class NodeAVL extends Node<E> {
        protected int bf;

        @SuppressWarnings("unchecked")
        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        public String toString() {
            return "NodeAVL{" + "data=" + data + ", bf=" + bf + '}';

        }
    }

    private boolean height; // indicador de cambio de altura

    public AVLTree() {
        super();
    }

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected Node insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;
        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            int resC = node.data.compareTo(x);
            if (resC == 0)
                throw new ItemDuplicated();
            if (resC < 0) {
                fat.right = insert(x, (NodeAVL) node.right);
                if (this.height)
                    switch (fat.bf) {
                        case -1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = 1;
                            this.height = true;
                            break;
                        case 1: // bf = 2
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
            } else {
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height)
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.height = true;
                            break;
                        case -1: // bf = -2
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
            }
        }
        return fat;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) {
            case 1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;
            case -1:
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL child = (NodeAVL) node.left;
        switch (child.bf) {
            case -1:
                node.bf = 0;
                child.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL grandchild = (NodeAVL) child.right;
                switch (grandchild.bf) {
                    case 1:
                        node.bf = 0;
                        child.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        child.bf = 0;
                        break;
                    case -1:
                        node.bf = -1;
                        child.bf = 0;
                        break;
                }
                grandchild.bf = 0;
                node.left = rotateSL(child);
                node = rotateSR(node);
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.right;
        node.right = p.left;
        p.left = node;
        node = p;
        return node;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.left;
        node.left = p.right;
        p.right = node;
        node = p;
        return p;
    }

    public int height(Node<E> node) {
        if (node == null) {
            return -1; // considera la altura de un árbol vacío como -1
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    @Override
    public int searchHeight(E x) throws ItemNoFound {
        NodeAVL node = (NodeAVL) searchNode((NodeAVL) root, x);
        return height(node);
    }

    private NodeAVL searchNode(NodeAVL node, E x) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound();
        }
        int cmp = x.compareTo(node.data);
        if (cmp < 0) {
            return (NodeAVL) searchNode((NodeAVL) node.left, x);
        } else if (cmp > 0) {
            return (NodeAVL) searchNode((NodeAVL) node.right, x);
        } else {
            return node;
        }
    }

    private int height(NodeAVL n) {
        if (n == null) {
            return -1; // Altura de un árbol vacío es -1
        }
        int leftHeight = height((NodeAVL) n.left);
        int rightHeight = height((NodeAVL) n.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // EJERCICIO 2

    public void remove(E x) throws ItemNoFound {
        root = remove((AVLTree<E>.NodeAVL) root, x);
    }

    private NodeAVL remove(NodeAVL node, E x) throws ItemNoFound {
        if (isEmpty()) {
            throw new ItemNoFound();
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            node.left = remove((AVLTree<E>.NodeAVL) node.left, x);
            node = balanceToRight(node); // Balance after removing from left
        } else if (compareResult > 0) {
            node.right = remove((AVLTree<E>.NodeAVL) node.right, x);
            node = balanceToLeft(node); // Balance after removing from right
        } else {
            // Node with two children
            if (node.left != null && node.right != null) {
                NodeAVL min = findMin((AVLTree<E>.NodeAVL) node.right);
                node.data = min.data;
                node.right = remove((AVLTree<E>.NodeAVL) node.right, node.data);
                node = balanceToLeft(node); // Balance after removing successor
            } else {
                node = (node.left != null) ? (AVLTree<E>.NodeAVL) node.left : (AVLTree<E>.NodeAVL) node.right;
            }
        }

        return node;
    }

    private NodeAVL findMin(NodeAVL node) {
        while (node.left != null) {
            node = (AVLTree<E>.NodeAVL) node.left;
        }
        return node;
    }

    // EJERCICIO 3

    public void breadthFirstTraversal() throws ItemNoFound{
        if (isEmpty()) {
            throw new ItemNoFound();
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        breadthFirstTraversal(queue);
    }

    private void breadthFirstTraversal(Queue<Node<E>> queue) {
        if (queue.isEmpty()) {
            return;
        }

        Node<E> node = queue.poll();
        System.out.print(node.data + " ");

        if (node.left != null) {
            queue.add(node.left);
        }

        if (node.right != null) {
            queue.add(node.right);
        }

        breadthFirstTraversal(queue);
    }
}