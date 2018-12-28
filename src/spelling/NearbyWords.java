/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			// convert character to ASCII numeric value a=97 b=98 ...z=122 so that we can loop
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the letters in the String
				StringBuffer sb = new StringBuffer(s);
				// replace alphabet at index position with a-z
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in currentList && isn't the original string &&
				// (if wordsOnly is true) is a real word, add to the list
				// if wordsOnly is true, then !wordsOnly is false. if s is not a real word, 
				// !wordsOnly||dict.isWord(sb.toString()) returns false, so s will not be added to list.
				// if wordsOnly is false, then !wordsOnly is true. No matter s is real word or not, it will
				// be added to list.
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		for (int index = 0; index < s.length() + 1; index++) {
			for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				StringBuffer sb = new StringBuffer(s);
				sb.insert(index, (char)charCode);
				
				if (!currentList.contains(sb.toString()) && 
						(!wordsOnly || dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		for (int index = 0; index < s.length(); index++) {
			StringBuffer sb = new StringBuffer(s);
			sb.delete(index, index + 1);
			
			if (!currentList.contains(sb.toString()) && 
					(!wordsOnly || dict.isWord(sb.toString())) &&
					!s.equals(sb.toString())) {
				currentList.add(sb.toString());
			}
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// Create a queue to hold words to explore
		List<String> queue = new LinkedList<String>();
		// Create a visited set to avoid looking at the same String repeatedly. HashSet ensures no duplicates
		HashSet<String> visited = new HashSet<String>(); 
		// Create list of real words to return when finished												   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		 		
		// Add the initial word to the queue and visited
		queue.add(word);
		System.out.println(queue.toString());
		visited.add(word);
					
		// TODO: Implement the remainder of this method, see assignment for algorithm	
		// while the queue has elements and we need more suggestions
		while (!queue.isEmpty() && retList.size() < numSuggestions) {
			// remove the word from the start of the queue and assign to curr
			//System.out.println("queue = " + queue.toString());
			String curr = queue.remove(0); // why cannot use remove() like autoCompleteDictionaryTrie?
			//System.out.println(curr + " removed from queue");
			// get a list of neighbors (strings one mutation away from curr)
			for(String n : distanceOne(curr, true)) {				
				//for each n in the list of neighbors if n is not visited and still need more suggestions
				if (!visited.contains(n) && retList.size() < numSuggestions) {
					// add n to the visited set
					// add n to the back of the queue
					visited.add(n);
					queue.add(n);							
					retList.add(n);
				}
			}
		}		
		return retList;
	}	

   public static void main(String[] args) {
//	   // basic testing code to get started
//	   String word = "i";
//	   // Pass NearbyWords any Dictionary implementation you prefer
//	   Dictionary d = new DictionaryHashSet();
//	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
//	   NearbyWords w = new NearbyWords(d);
//	   List<String> l = w.distanceOne(word, true);
//	   System.out.println("One away word Strings for for \""+word+"\" are:");
//	   System.out.println(l+"\n");
//
//	   //word = "tailo";
//	   word = "appls";
//	   List<String> suggest = w.suggestions(word, 100);
//	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
//	   System.out.println(suggest);
   }

}
