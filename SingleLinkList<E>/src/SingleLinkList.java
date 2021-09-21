
/* <E> type - generics
 *  can be created one class and share with different
 *  data type lists.
 */
public class SingleLinkList<E> {
	
	private Node<E> head = null;
	private int size = 0;
	
	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node (E elem){
			data = elem;
			next = null;
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
	}
	
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

	public static void main(String[] args) {
		SingleLinkList<String> sll = new SingleLinkList<String>();
		Node<String> Apple = new Node<String> ("Apple");
		Node<String> Orange = new Node<String> ("Orange");
		Node<String> Blueberry = new Node<String> ("Blueberry");
		
		Apple.next = Orange;
		Orange.next = Blueberry;
		
		sll.head = Apple;
		
		System.out.println(sll);
        
	}

}
