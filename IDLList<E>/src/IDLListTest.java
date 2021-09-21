
public class IDLListTest {

		public static void main(String[] args) {
			IDLList<Integer> list = new IDLList<Integer>();
//				list.add(0,35); 
//				System.out.println(list.toString()); //35
				list.append(5);
				System.out.println(list.toString()); //35, 5
				list.add(100);
				System.out.println(list.toString()); //100, 35, 5
				list.add(1, 200);
				System.out.println(list.toString()); //100, 200, 35, 5
				list.add(300);
				System.out.println(list.toString()); //300, 100, 200, 35, 5
				list.append(400);
				System.out.println(list.toString()); //300, 100, 200, 35, 5, 400
				list.add(1, 500);
				System.out.println(list.toString()); //300, 500, 100, 200, 35, 5, 400
				list.add(3, 600);
				System.out.println(list.toString()); //300, 500, 100, 600, 200, 35, 5, 400
				list.append(700);
				System.out.println(list.toString()); //300, 500, 100, 600, 200, 35, 5, 400, 700
				list.add(2, 800);
				System.out.println(list.toString()); //300, 500, 800, 100, 600, 200, 35, 5, 400, 700
				list.add(4, 800);
				System.out.println(list.toString()); //300, 500, 800, 100, 800, 600, 200, 35, 5, 400, 700
				list.add(-1, 12); // Negative index will throw exception
				list.add(12,80);  // Index more than size will throw exception
				list.add(11,55); // Will add at end of List
				System.out.println(list.toString());
				System.out.println(list.remove(300)); // Removes 1st occourance and returns true if not present returns false
				System.out.println(list.toString());
				System.out.println(list.size());
				System.out.println(list.get(7).toString()); //Negative index will throw exception
			    System.out.println("Element at head is "+list.getHead().toString());
                System.out.println("Element at tail is "+list.getLast().toString());
				System.out.println(list.removeAt(4));
				System.out.println(list.toString());
				System.out.println(list.size());
		}


}
