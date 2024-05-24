package avltree;

import arbolesPractica01.BSTree;
import arbolesPractica01.ItemDuplicated;

public class AVLTree<E extends Comparable<E>> extends BSTree<E>{
	class NodeAVL extends Node<E> {
		protected int bf;
		@SuppressWarnings("unchecked")
		public NodeAVL(E data) {
			super(data);
			this.bf=0;
		}
		public String toString() {
			return "NodeAVL{" + "data=" + data + ", bf=" + bf + '}';
                    
                   
		}
	}
	private boolean height; //indicador de cambio de altura 
	
	public AVLTree() {
        super();
    }
	
	public void insert(E x) throws ItemDuplicated {
		this.height = false;
		this.root = insert(x, (NodeAVL)this.root);
	}
		
	protected Node insert(E x, NodeAVL node)throws ItemDuplicated{ 
		NodeAVL fat = node;
		if (node == null){
			this.height = true;
			fat = new NodeAVL (x);
		}else {
			int resC = node.data.compareTo(x);
			if(resC == 0)throw new ItemDuplicated();
			if(resC < 0) {
				fat.right = insert(x, (NodeAVL)node.right);
				if(this.height)
					switch(fat.bf) {
					case -1: 
						fat.bf = 0;
						this.height = false;
						break;
					case 0: fat.bf = 1;
						this.height = true;
						break;
					case 1: //bf = 2
						fat = balanceToLeft(fat);
						this.height = false;
						break;
					}
			}else {
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
	
	private NodeAVL balanceToLeft(NodeAVL node){ 
		NodeAVL hijo = (NodeAVL)node.right;
		switch(hijo.bf) {
		case 1:
			node.bf = 0;
			hijo.bf = 0;
			node = rotateSL(node);
			break;
		case -1:
			NodeAVL nieto = (NodeAVL)hijo.left;
			switch(nieto.bf) {
			case -1: 
				node.bf = 0; hijo.bf = 1; 
				break;
			case 0: 
				node.bf = 0; hijo.bf = 0; 
				break;
			case 1: 
				node.bf = 1; hijo.bf = 0; 
				break;
			}
			nieto.bf =0;
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
	
	private NodeAVL rotateSL(NodeAVL node){ 
		NodeAVL p = (NodeAVL)node.right;
		node.right = p.left;
		p.left = node;
		node = p;
		return node;
	}
	
	private NodeAVL rotateSR(NodeAVL node) {
	    NodeAVL p = (NodeAVL) node.left;
	    node.left = p.right;
	    p.right = node;
	    node=p;
	    return p;
	}
}	

