package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		word = word.toLowerCase();
		TrieNode currNode = root;
		// go through the trie from root and check if the letter is inside.
		for(int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			// if not, insert the letter
			if(currNode.getChild(currChar) == null) {
				currNode.insert(currChar);
			}
			// go to the next node
			currNode = currNode.getChild(currChar);
		}
		// if the last node is already a word, which means we have already
		// inserted the word, return false. boolean isWord is false by default.
		if (currNode.endsWord()) {
			return false;
		}
		currNode.setEndsWord(true);	
		size++;
		//System.out.println(currNode.getText() + " inserted, size is " + size);
		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if (s == "") {
			return false;
		}
		s = s.toLowerCase();
		TrieNode currNode = root;
		// go through the trie from root and check if the letter is inside.
		for(int i = 0; i < s.length(); i++) {
			char currChar = s.charAt(i);
			// if not, insert the letter
			if(currNode.getChild(currChar) == null) {
				//System.out.println(s + " is not in trie");
				return false;
			}
			// go to the next node
			currNode = currNode.getChild(currChar);
		}
		// check if the currNode ends with a word. e.g downhill is a word but downhille and downhil is not.
		if (!currNode.endsWord()) {
			//System.out.println(s + " is not in trie");
			return false;
		}
		//System.out.println(s + " is a word in trie");
		return true;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 ArrayList<String> completion = new ArrayList<String>();
    	 Queue <TrieNode> q = new LinkedList<TrieNode>();
    	 TrieNode currNode = root;
    	 
    	 // if given prefix (stem) is not empty and is not a word in the trie
    	 // return empty completion list.
    	 if (!isWord(prefix) && prefix != "") {
    		 //System.out.println(prefix + "is not in trie, return empty completion list");
    		 return completion;
    	 }
    	 // go through the trie and find the last node of the stem.
    	 for (int i=0; i<prefix.length(); i++) {
    		 Character c = prefix.charAt(i);
    		 currNode = currNode.getChild(c);    		 
    	 }
    	 // add this node to queue
    	 q.add(currNode);
    	 
    	 while (!q.isEmpty() && completion.size() < numCompletions) {
    		 // remove the first node in queue, if the removed node is a word, add this word to completion list.
    		 currNode = q.remove();
    		 if (currNode.endsWord()) {
    			 completion.add(currNode.getText());
    			 //System.out.println(currNode.getText() + "is added");
    			 //System.out.println(completion);
    		 }
    		 // as long as q.remove() returns not null, add children of current node into queue
    		 if (currNode != null) {
    			 Set<Character> keySet = currNode.getValidNextCharacters();
    			 for (Character c : keySet) {
    				 TrieNode tn = currNode.getChild(c);
    				 q.add(tn);
    			 }
    		 }

    	 }
         return completion;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}