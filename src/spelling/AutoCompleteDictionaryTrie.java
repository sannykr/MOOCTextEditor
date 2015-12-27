package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size=0;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		boolean result = false;
	    TrieNode prevN = root;
	    TrieNode nextN = null;
		word = word.toLowerCase();
		for (char ch:word.toCharArray()){
			nextN = prevN.insert(ch);
			if (nextN==null){
				nextN = prevN.getChild(ch);
			}
			prevN = nextN;
		}
		if (!nextN.endsWord()) {
			nextN.setEndsWord(true);
			size++;result = true;
		}
	return result;
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
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		s=s.toLowerCase();
		TrieNode cur=root;
		for(char ch:s.toCharArray()){
			cur=cur.getChild(ch);
		}
		if(cur!=null && cur.endsWord() && cur.getText().equals(s)){
			return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
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
    	 prefix=prefix.toLowerCase();
    	 TrieNode cur=root;
    	 for(char ch:prefix.toCharArray()){
    		 cur=cur.getChild(ch);
    		 if(cur==null) break;
    	 }
    	 
    	 List<TrieNode> queue=new LinkedList<TrieNode>();
    	 List<String> completions=new LinkedList<String>();
    	 if(cur==null) return completions;
    	 queue.add(cur);
    	 while(queue.size()>0 && numCompletions>0){
    		 TrieNode temp= queue.remove(0);
    		 if(temp.endsWord()){
    			 completions.add(temp.getText());
    			 numCompletions--;
    		 }
    		 for(char ch:temp.getValidNextCharacters()){
    			 queue.add(queue.size(), temp.getChild(ch));
    		 }
    		 
    	 }
    	 
    	 
         return completions;
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