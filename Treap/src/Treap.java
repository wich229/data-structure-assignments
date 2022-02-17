/** @name Yi-Chun,Liu */

import java.util.Random;
import java.util.Stack;

//E must be Comparable.
public class Treap <E extends Comparable<E>>{

    //Treap class - data fields
    private Random priorityGenerator;
    private Node <E> root;

    private static class Node<E>{
        public E data; // key for the search (BST)
        public int priority; // random heap priority (Max-heap)
        public Node <E> left;
        public Node <E> right;

        //constructor - given data and priority.
        //The pointers to child nodes are null.
        //Throw exceptions if data is null.
        public Node ( E data ,int priority ){
            if (data.equals(null)) {
                throw new NullPointerException("data cannot be null.");
            }
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }

        //returning a reference to the root of the result.
        Node <E> rotateRight(){
            Node <E> tmpNode = this.left;
            this.left = tmpNode.right;
            tmpNode.right = this;
            return tmpNode;
        }

        Node <E> rotateLeft(){
            Node <E> tmpNode = this.right;
            this.right = tmpNode.left;
            tmpNode.left = this;
            return tmpNode;
        }

        //have to add a toString method to the Node class so that it prints
        //a pair consisting of the key and its priority.
        @Override
        public String toString() {
            return "(key="+data.toString()+", priority="+priority+")";
        }

    } // inner class end.


    //constructor - creates an empty treap. Initialize priorityGenerator using new Random().
    public Treap() {
        this.priorityGenerator = new Random();
        this.root = null;
    }

    //constructor - creates an empty treap. Initialize priorityGenerator using new Random(seed).
    public Treap(long seed) {
        this.priorityGenerator = new Random(seed);
        this.root = null;
    }

    /** Add operation
     * similar as insert BST
     * @param key, priority
     * @return returns true, if a node with the key was successfully added to the treap.
     * If there is already a node containing the given key, the method returns false
     * and does not modify the treap.
     **/
    boolean add(E key) {
        return add(key, this.priorityGenerator.nextInt());
    }

    boolean add(E key , int priority) {
        //Insert the new node as a leaf of the tree at the appropriate position
        //according to the ordering on E, just like in any BST.
        if(key == null) {
            return false;
        }

        Node<E> addNode = new Node<E> (key, priority);
        Stack<Node<E>> nodePath = new Stack<Node<E>>();

        if(this.root == null) {
            this.root = addNode;
            // nodePath.push(root);
            return true;
        }
        else {
            //insert BST
            Node<E> currentNode = this.root;
            while(true) {
                // nodePath.push(currentNode);
                int result = currentNode.data.compareTo(addNode.data);
                // if currentNode > addNode => result = 1
                if(result == 0) {
                    return false;
                }
                else if(result>0) {
                    // currentNode doesn't have left.
                    if(currentNode.left==null) {
                        nodePath.push(currentNode);
                        currentNode.left = addNode;
                        break;
                    }
                    else {
                        nodePath.push(currentNode);
                        currentNode = currentNode.left;
                    }
                }
                else {
                	 // currentNode doesn't have right.
                    if(currentNode.right==null) {
                        nodePath.push(currentNode);
                        currentNode.right = addNode;
                        break;
                    }
                    else {
                        nodePath.push(currentNode);
                        currentNode = currentNode.right;
                    }
                }
            }
        }

        reheap(addNode, nodePath);
        return true;
    }

    //helper function (reheap and use stack to track the nodePath)
    public void reheap( Node<E> addNode , Stack<Node<E>> nodePath ) {
        if(nodePath.isEmpty()) {
            this.root = addNode;
        }
        else {
            while(!nodePath.isEmpty()) {
                Node<E> parentNode = nodePath.pop();
                if (addNode.priority > parentNode.priority) {
                    Node<E> parentParentNode = null;
                    if (!nodePath.isEmpty()) {
                        parentParentNode = nodePath.peek();
                    }

                    if (addNode == parentNode.left) {	
                        parentNode.rotateRight();
                    } else {
                        parentNode.rotateLeft();
                    }
                    if (parentParentNode == null) {
                        this.root = addNode;
                        break;
                    } else {
                        if (parentParentNode.left == parentNode) {
                            parentParentNode.left = addNode;
                        } else {
                            parentParentNode.right = addNode;
                        }
                    }
                }
                else{
                    break;
                }
            }
        }
    }

    /** Delete operation
     * @param key, priority
     * @return deletes the node with the given key from the treap and returns true.
     * If the key was not found,the method does not modify the treap and returns false.
     * using rotation until it becomes a leaf and then remove it.
     */
    public boolean delete(E key) {
        if(find(key) == false) {
            return false;
        }
      
            // it a leaf node can remove.
            int result = root.data.compareTo(key);
            if(result > 0) {
            	return find(root.left.data);
            }
            
            else if (result < 0) {
            	return find(root.right.data);
            }
            
            else {
            	
//            	if (root.left==null && root.right==null) {
//            		root = null;
//            		return true;
//            	}
            	
        		if( root.left!=null || root.right==null ) {
    				root = root.rotateRight();
    				delete(root.right.data);
        		}
            	
    			else if(root.left==null || root.right!=null) {
    				root = root.rotateLeft();
    				delete(root.left.data);
    			}
            	
            	else if (root.left!=null && root.right!=null) {
            		
	        		if(root.left.priority > root.right.priority) {
	                    root = root.rotateRight();
	                    delete(root.right.data);
	        		}
	        		else {
	                    root = root.rotateLeft();
	                    delete(root.left.data);
	        		}
            	}
        		root = null;
        		return true;
        		
            }
    }


    /** Find operation
     * Finds a node with the given key in the treap rooted at root
     * and returns true if it finds it and false otherwise.
     * @param root
     * @param key
     * @return returns true if it finds it and false otherwise.
     */

    private boolean find ( Node<E> root, E key ) {
        if(key == null || root == null) {
            return false;
        }

        //Searching a BST.
        int result = root.data.compareTo(key);
        if(result == 0) {
            return true;
        }
        
        else if(result > 0) {
            return find(root.left,key);
        }
        
        else if (result< 0) {
            return find(root.right,key);
        }
        else {
        	return false;
        }
    
    }

    public boolean find ( E key ) {
        return find(root,key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    //test case
    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();
        testTree.add (4 ,19);
        testTree.add (2 ,31);
        testTree.add (6 ,70);
        testTree.add (1 ,84);
        testTree.add (3 ,12);
        testTree.add (5 ,83);
        testTree.add (7 ,26);
        System.out.println(testTree.find(6));
        System.out.println(testTree.find(2));
        System.out.println(testTree.find(1));
        System.out.println(testTree.find(9));
        System.out.println(testTree.delete(8));
        System.out.println(testTree.toString());
    }

}



