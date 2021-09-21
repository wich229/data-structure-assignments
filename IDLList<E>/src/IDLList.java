/** @name Yi-Chun,Liu */
import java.util.ArrayList;

public class IDLList<E> {
	// inner class
	private class Node<E> {
		//three data fields.
		E data;
		Node<E> next;
		Node<E> prev;
		
		//a constructor that creates a node holding elem.
		Node (E elem){
			data = elem;
			next = null;
			prev = null;
		}
		/* a constructor that creates a node holding elem, 
		 * with next as next and prev as prev.
		 */
		Node (E elem, Node<E> prev, Node<E> next){
			this.data = elem;
			this.prev = prev;
			this.next = next;	
		}
	} // inner class end
		
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//a constructor that creates an empty double-linked list.
	public IDLList () {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	/* that adds elem at position index (counting from wherever head is).
	 * It uses the index for fast access.
	 */
	public boolean add (int index, E elem) {
		if( index < 0 || ( index > size && index!= 0 ) ) { 
			System.out.println("invaild to add in the list");
			return false;
		}
		if(index == 0) {
			add(elem);
		}
		else if(index == size ) {
			append(elem);
		}
		else {
			//inserAfter (dll)
			Node<E> newNode = new Node<E> (elem);
			Node<E> curNode = indices.get(index-1);
			Node<E> sucNode = curNode.next;
			newNode.next = sucNode;
			newNode.prev = curNode;
			curNode.next = newNode;
			sucNode.prev = newNode;
            size++;
//            System.out.println("size: "+size);
            indices.add(index,newNode);
		}
		return true;
	}
	
	/* that adds elem at the head (i.e. it becomes the first element of the list).
	 */
	public boolean add (E elem) {
		Node<E> newNode = new Node<E> (elem,null,null);
		if (head == null) {
			//append
			head = newNode;
			tail = newNode;
			size++;
//			System.out.println("size: "+size);
			indices.add(newNode);
		}
		else {
			//prepend
			newNode.next = head; 
			head.prev = newNode;
			head = newNode;
			size++;
//			System.out.println("size: "+size);
			indices.add(0,newNode);
		}
		return true;
	}
	
	/* that adds elem as the new last element of the list (i.e. at the tail).
	 */
	public boolean append (E elem) {
		if( head == null ) {
			add(elem);
		}
		else {
			Node<E> newNode = new Node<E> (elem);
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			size++;
			indices.add(size-1,newNode);
		}	
		return true;
	}
	
	/* that returns the object at position index from the head. It uses the index 
	 * for fast access. Indexing starts from 0, thus get(0) returns the head element 
	 * of the list.
	 */
	public E get (int index) {
		if( index < 0 || ( index >= size && index!= 0 ) ) { 
			System.out.println("invaild to add in the list");
			return null;
		}
		return (E)indices.get(index).data;
	}
	
	/* that returns the object at the head.
	 */
	public E getHead () {
		return (E)head.data;
	}
	
	/* that returns the object at the tail.
	 */
	public E getLast () {
		return (E)tail.data;
	}
	
	/* that returns the list size.
	 */
	public int size() {
		return size;
	}
	
	/* that removes and returns the element at the head.
	 */
	public E remove() {
		if(size==0) { 
			System.out.println("the list is empty.");
			return null;
		}
		else {
			Node<E> curNode = head;
			Node<E> sucNode = curNode.next;
//			Node<E> prevNode = curNode.prev;
			if(sucNode!= null) {
				sucNode.prev = null;
				head = sucNode;
				size--;
				indices.remove(0);
			}
			else if( size == 1 ||(sucNode == null)){
				head = sucNode;
//				tail = prevNode;
				size--;
				indices.remove(0);
			}
		}
		return (E)indices.get(0).data;
	}
	
	/* that removes and returns the element at the tail.
	 */
	public E removeLast() {
		if(size==0) { 
			System.out.println("the list is empty.");
			return null;
		}
		else {
			Node<E> curNode = indices.get(size-1);
//			Node<E> sucNode = curNode.next;
			Node<E> prevNode = curNode.prev;
			if(prevNode != null) {
				prevNode.next = null;
				tail = prevNode;
				indices.remove(size-1);
				size--;
			}
//			else if(size == 1){
//				head = null;
//				tail = null;
//				indices.remove(size-1);
//				size--;
//			}
		}
		return (E)indices.get(size-1).data;
	}
	
	/* that removes and returns the element at the index index. 
	 * Use the index for fast access.
	 */
	public E removeAt (int index) {
		if( index < 0 || ( index >= size && index!= 0 ) ) { 
			System.out.println("invaild index number");
			return null;
		}
		if( index == 0 ) {
			remove();
			return null;
		}
		else if(index == size-1){
			removeLast();
			return null;
		}
		else if ( index < size) {
			Node<E> curNode = indices.get(0);
			Node<E> searchNode = indices.get(index);
			while( searchNode!= curNode) {
				curNode = curNode.next;
			}
			Node<E> sucNode = curNode.next;
			Node<E> predNode = curNode.prev;
			sucNode.prev = predNode;
			predNode.next = sucNode;
			size--;
			indices.remove(index);
		}
		return (E)indices.get(index).data;
	}
	
	/* that removes the first occurrence of elem in the list and returns true. 
	 * Return false if elem was not in the list.
	 */
	public boolean remove (E elem) {
		//use list search(list, key)
		Node<E> curNode = head;
		while(curNode!= null) {
			if(curNode.data.equals(elem)) {
				int key_index = indices.indexOf(curNode);
				removeAt(key_index);
				break;
			}
			curNode = curNode.next;
		}
		if(curNode == null) {
			System.out.println("not in the list.");
			return false;
		}
		return true;
	}
	
	/* That presents a string representation of the list. 
	 */
	@Override
	public String toString() {
		String tmp = "";
		Node<E> nodeRef = head;
		while(nodeRef != null) {
			 tmp = tmp + nodeRef.data;
			 if(nodeRef.next != null) {
				 tmp = tmp + ", ";
			 }
			 nodeRef = nodeRef.next;
		}
		return tmp;
	}
	

}
