
/** @name Yi-Chun,Liu */
public class Complexity {
	
	//a method that has time complexity O(n);
	public static void method0(int n) { 
		int counter=1; 
		for (int i=0; i<n; i++) {
			System.out.println("Operation "+counter); 
			counter++;
		}
	}
	
	//a method that has time complexity O(n^2);
	public static void method1(int n) {
		int counter=1; 
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.println("Operation "+counter); 
				counter++;
			}
		}
		
	}
	
	//a method that has time complexity O(n^3);
	public static void method2(int n) {
		int counter=1; 
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int k=0; k<n; k++) {
					System.out.println("Operation "+counter); 
					counter++;
				}
			}
		}
	}
	
	//a method that has time complexity O(logn);
	public static void method3(int n) {
		int counter=1; 
		for (int i=n; i>1; i/=2) {
			System.out.println("Operation "+counter); 
			counter++;
		}
	}
	
	//a method that has time complexity O(n * logn);
	public static void method4(int n) {
		int counter=1; 
		for (int i=0; i<n; i++) {
			for (int j=n; j>1; j/=2) {
				System.out.println("Operation "+counter); 
				counter++;
			}
		}	
	}
	//a method that has time complexity O(log logn);
    //n = 2^k; k = log2n; √65,536 = √2^16 = (2^16)^1/2 = 2^8 = 256; 
	//log(√n) = log(n^1/2) = (1/2);
	public static void method5(int n) {
		int counter = 1; 
		int counter1 = 0;
		int i;
//		n = (int)Math.sqrt(n);
		for(i = n; i>1; i/=2) {
//			System.out.println("Operation "+counter); 
			counter1 = counter++;
		}
		
		for( i = counter1; i>1; i/=2) {
			System.out.println("Operation "+counter); 
			counter++;
		}

	}
	
	//(Optional) a method that has time complexity O(2^n);
	public static int method6(int n) {
//		System.out.println(n);
//		method6(n-1);
		int num = 0;
	    if(n>=0) {
			num = (int)Math.pow(2, n); // num = 2^n;
			System.out.println("Operation number "+ num);
			method6(n-1);
			method6(n-1);
		}
		return num;
	}
	public static void main(String[] args) {
		System.out.println("test for method 0 complexity O(n)");
		method0(2);
		System.out.println("test for method 1 complexity O(n^2)");
		method1(2);//0--3=4
		System.out.println("test for method 2 complexity O(n^3)");
		method2(2);//0--7=8
		System.out.println("test for method 3 complexity O(logn)");
		method3(64);//0--5=6
		System.out.println("test for method 4 complexity O(n*logn)");
		method4(8);//0--23=24
		System.out.println("test for method 5 complexity O(log logn)");
		method5(256);//0--2=3
		System.out.println("test for method 6 complexity O(2^n)");
		method6(10);//0--1022=1023

	}
}
