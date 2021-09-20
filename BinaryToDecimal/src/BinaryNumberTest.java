//import java.util.Scanner;

//public class BinaryNumberTest {
//
//	public static void main(String[] args) {	
//	   Scanner scnr = new Scanner( System.in );
//	   int bitsNum;
//	   String binaryString;

//	   System.out.println("Enter bits number: ");
	   
	   // get the binaryNumLength and create a bitsNum-size of array and store 0.
//	   if(scnr.hasNextInt()) {
//		   bitsNum =scnr.nextInt();
//		   BinaryNumber binaryNumLength = new BinaryNumber(bitsNum);
//		   System.out.println(bitsNum);
//		}
	   
	   //get the binary Number and store to the array.
//	   System.out.println("Enter a binary number: ");
//	   while(scnr.hasNext()) {
//		   binaryString = scnr.next();
//		   for(int i = 0 ; i < binaryString.length(); i++) {
//			   char eachDigit = binaryString.charAt(i);
//			   if(Character.isDigit(eachDigit)== false) {			   
//				   System.out.println("Enter a binary number: ");
//				   binaryString = scnr.next(); 
//			   }
//			   else if( eachDigit != '0' && eachDigit != '1' ) {
//				   System.out.println("Enter a binary number: ");
//				   binaryString = scnr.next();
//			   }
//		   }
//		   BinaryNumber binaryNumString = new BinaryNumber(binaryString);
//		   System.out.println(binaryString);
//		   break;
//	   }
	
	
	//
//	BinaryNumber number3 = new BinaryNumber("1101");
//	System.out.println(number3.toDecimal());
//	
//	}
	
	public class BinaryNumberTest {
		public static void main(String[] args) {
			BinaryNumber b1 = new BinaryNumber("011");
			BinaryNumber b2 = new BinaryNumber("100");
			b2.shiftR(3);
			b1.add(b2);
			System.out.println(b1.toString()); // 111

			BinaryNumber b3 = new BinaryNumber("0110");
			BinaryNumber b4 = new BinaryNumber("1100");
			b3.add(b4);
			System.out.println(b3.toString()); // 1001

			BinaryNumber b5 = new BinaryNumber("00010011010111000110100110110011010");
			BinaryNumber b6 = new BinaryNumber("11110001010001010000110001100000001");
			b5.add(b6);
			System.out.println(b5.toString()); // 11101010101110110110001111001011011

			BinaryNumber b7 = new BinaryNumber("111");
			BinaryNumber b8 = new BinaryNumber("100");
			b7.add(b8);
			System.out.println(b7.toString()); // Overflow
			
//			BinaryNumber binarynumberObj = new BinaryNumber(5);
//			BinaryNumber binarynumberObj1 = new BinaryNumber("10110");
//			System.out.println("Length of Binary number = "+binarynumberObj1.getLength());
//			System.out.println("Digit at index is " +binarynumberObj1.getDigit(4));
//			System.out.println("Decimal value of Binary number = " +binarynumberObj1.toDecimal());
//			System.out.print("Result of Shift Right = ");
//			binarynumberObj1.shiftR(4);
//			BinaryNumber aBinaryNumber = new BinaryNumber("11101");
//			binarynumberObj1.add(aBinaryNumber);
//			System.out.println("To string method result " +binarynumberObj1.toString());
//			binarynumberObj1.clearOverflow();
		}
	}


