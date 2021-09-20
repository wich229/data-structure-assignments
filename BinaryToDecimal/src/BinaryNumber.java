import java.util.Arrays;
import java.lang.Math;

public class BinaryNumber {

	/** @name Yi-Chun,Liu */
	
	private int [] data;
	boolean overflow = false;
	
	/** @param constructor(int length) creating a binary number of length 
	 * length and consisting only of zeros.
	 */
	public BinaryNumber(int length){
		if( length < 0 ) {
			System.out.println("invalid length.");
			return;
		}
		data = new int[length];
		for(int i = 0 ; i < length ; i++) {
			data[i] = 0;
//			System.out.print(data[i]);
		}
//		System.out.println();
	}
	
	/** @param constructor(String str) creating a binary number given a string.
	 */
	public BinaryNumber(String str){
		data = new int[str.length()];
//		System.out.print("binary number: ");
		for(int i = 0 ; i < str.length() ; i++ ) {
			char eachChar = str.charAt(i);
			if( Character.isDigit(eachChar)== false || (eachChar != '0' && eachChar != '1') ) {			   
				   System.out.print("not binary number");
				   System.out.println();
				   break;
			}
			data[i] = Character.getNumericValue(eachChar);
//			System.out.print(data[i]);
		}
	}
	
	/* operator - (determining the length of a binary number.)*/
	public int getLength() {
		return data.length;
	}
	
	/* operator - (obtaining a digit of a binary number given an index.
	 * If the index is out of bounds, then a message should be printed on 
	 * the screen indicating this fact.)
	 */
	public int getDigit(int index) {
		if(index > (data.length - 1) || index < 0) {
			System.out.println(" the index is out of bounds");
			return -1;
		}
		return data[index];	
	}
	
	/*operator - (transforming a binary number to its decimal notation.)*/
	public int toDecimal() {
		int decimalNum = 0;
		int number1 = 0;
		int number2 = 0;
		for(int i = 0 ; i < data.length; i++ ) {
			number1 = data[i];
			number2 = (int)(number1 * (Math.pow(2,i)));
			decimalNum = decimalNum + number2;
		}
		return decimalNum;
	}
	
	/* operator - (shifting all digits in a binary number any number of 
	 * places to the right, as indicated by a parameter amountToShift.
	 */
	public void shiftR(int amount) {
		int newLength = data.length + amount;
		int [] newData = Arrays.copyOf(data, newLength);
		
		if( amount < 0 ) {
			System.out.println("cannot shift.");
			return;
		}
		
		for(int i = 0 ; i < amount ; i++ ) {
			newData[i]= 0;
		}
		
		for(int i = newLength - 1 ; i >= amount ; i-- ) {
				newData[i] = data[i-amount];
		}
		
		System.out.print("shifting result: ");
		for(int i = 0 ; i < newLength ; i++ ) {
			System.out.print(newData[i]);
		}
		System.out.println();	
	}
	
	/* adding two binary numbers, one is the binary number that receives 
	 * the message and the other is given as a parameter. If the lengths 
	 * of the binary numbers do not coincide, then a message should be 
	 * printed on the screen indicating this fact. Otherwise, it modifies 
	 * the receiving binary number with the result of the addition.
	 */
	public void add(BinaryNumber aBinaryNumber) {
		
		if( data.length != aBinaryNumber.getLength() ) {
			System.out.println("the lengths of the binary numbers do not coincide.");
			return;
		}
	
		int sum = 0;
		int carriedDigit = 0;
		for(int i = 0 ; i < data.length; i++ ) {
			sum = data[i]+ aBinaryNumber.getDigit(i)+carriedDigit;
			carriedDigit = sum / 2;
			data[i] = sum % 2;
		}
		
		if (carriedDigit == 1) {
			overflow = true;
//			System.out.println("overflow");
		}
		else {
			overflow = false;
		}

	}
	
	/** for transforming a binary number to a String. If the number is the 
	 * result of an overflow, the string “Overflow” should be returned.
	 */
	@Override
	public String toString() {
		String str = "";
		for(int i = 0 ; i < data.length ; i++ ) {
			 str = str + data[i];
		}

		if(overflow == true) {
			return "overflow";
		}
		else {
			return str;
		}
	}
	
	/* operator that clears the overflow flag.*/
	public void clearOverflow(){
//		if(overflow == true) {
//			int newLength = data.length + 1;
//			int [] newData = Arrays.copyOf(data, newLength);
//			newData [newLength-1] = 1; 
//			System.out.print("clearOverflow result: ");
//			for(int i = 0 ; i < newLength ; i++ ) {
//				System.out.print(newData[i]);
//			}
//			System.out.println();
//		}
		
		overflow = false;
//		System.out.println("clears the overflow flag");	
	}

}
