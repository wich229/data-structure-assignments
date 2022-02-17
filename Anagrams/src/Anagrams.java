import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** @name Yi-Chun,Liu */
public class Anagrams {
	
	//data field
	final Integer[] primes;
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	//constructor
	public Anagrams () {
		this.primes = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
        this.letterTable = new HashMap<Character,Integer>();
        this.anagramTable = new HashMap<Long,ArrayList<String>>();
        buildLetterTable();
		
	}
	//should be invoked by the constructor for the class Anagrams and 
	//should build the hash table letterTable which consists of the following entries:
	//chars = numbers
	private void buildLetterTable() {
		String alphabets = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < alphabets.length() ; i++) {
			this.letterTable.put(alphabets.charAt(i), primes[i]);
		}
		
	}
	/* This method, given a string s, should compute its hash code. 
	 * A requirement for myHashCode is that all the anagrams of a 
	 * word should receive the same hash code.
	 * 
	 * As an example,the words “alerts” and “alters”should both receive 
	 * the key 2.36204078E8,if we follow the encoding of letters given above.
	 */
	private Long myHashCode(String s) {
		if(s == null) {
			return null;
		}
		else {
			s = s.toLowerCase();
			long key = 1;
			for(int i=0; i<s.length(); i++) {
				int eachPrimeNum = letterTable.get(s.charAt(i));
				key *= eachPrimeNum;
			}
			return key;
		}
	}
	
	//This method should compute the hash code of the string s passed as argument, 
	//and should add this word to the hash table anagramTable.
	private void addWord(String s) {
		if(s == null) {
			 throw new NullPointerException();
		}
		long key = myHashCode(s);
		//using the array list to store and chain the same key with different value.
		//anagranTable is empty / or key not found.
		if(anagramTable.isEmpty()||!anagramTable.containsKey(key)) {
			ArrayList<String> wordsList = new ArrayList<String>();
			wordsList.add(s);
			this.anagramTable.put(key, wordsList);
		}
		//key is found and add the value in the list and replace the value.
		else if (anagramTable.containsKey(key)) {
			ArrayList<String> wordsList = anagramTable.get(key);
			wordsList.add(s);
			this.anagramTable.replace(key, wordsList);
		}
	}

	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader ( new InputStreamReader( fstream ));
		String  strLine ;
		while((strLine = br.readLine())!= null){
			this.addWord ( strLine );
		}
	}
	
	/* This method should return the entries in the anagramTable that have 
	 * the largest number of anagrams. It returns a list of them since there 
	 * may be more than one list of anagrams with a maximal size.
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxList = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int maxSize=0;
		for( Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			int size = entry.getValue().size();
			if(size > maxSize) {
				maxSize = size;
				maxList.clear();
				maxList.add(entry);
			}
			else if(size == maxSize) {
				maxList.add(entry);
			}
		}
		return maxList;
	}


	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		System.out.println("Elapsed Time: "+ seconds);
		System.out.println("Key of max anagrams:  "+ maxEntries.get(0).getKey());
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		System.out.println("Length of max anagrams: "+ maxEntries.get(0).getValue().size()); 

	}

}
