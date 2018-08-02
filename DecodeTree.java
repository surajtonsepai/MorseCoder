package edu.metrostate.ics240.p5.STP059.morse;

import java.util.Map;

import edu.metrostate.ics240.p5.STP059.morse.TreeNode;


public class DecodeTree {	
	
	static class MorseNode implements TreeNode<Character>{
		protected MorseNode parent;
		private TreeNode<Character> leftChild;
		private TreeNode<Character> rightChild;
		private Character value;

		private void setParent (MorseNode n) {
	        parent = n;
	    }

		private void setLeftChild(TreeNode<Character> newNode) {
			this.leftChild = newNode;
		}

		private void setRightChild(TreeNode<Character> newNode) {
			this.rightChild = newNode;
		}
		
		/*
		 * checks to see if tree has a left child
		 */
		public boolean hasLeftChild () {
           		return (leftChild != null);
        	}
		
		/*
		 * checks to see if a tree has a right child
		 */
        	public boolean hasRightChild () {
            		return (rightChild != null);
        	}     
		
		public TreeNode<Character> getLeftChild() {
			return this.leftChild;
		}

		public TreeNode<Character> getRightChild() {
			return this.rightChild;
		}
		
		private void setValue (Character key) {
            		value = key;
        	}		
		/*
		 * returns value of character(non-Javadoc)
		 * @see edu.metrostate.ics240.p5.STP059.morse.TreeNode#getValue()
		 */
		public Character getValue() {
			return this.value;
		}
		/*
		 * Takes in a code as an input and traverses the decodetree to convert it into real text
		 */
	 	public String decodeLetter(String code, StringBuffer sb) {
	 		TreeNode<Character> currNode = getRoot();
	 		String mLetter = code;
	 		String key = "";

	 		for (int i = 0; i < mLetter.length(); i++) {
	 			key = mLetter.substring(i, i + 1);
	 			if (key.equals("-")) {
	 				if (((MorseNode) currNode).hasLeftChild()) {
	 					currNode = currNode.getLeftChild();
	 				} 
					else {
	 					throw new IllegalArgumentException(
	 					String.format("Invalid code encountered %s[%s]", sb.toString().trim(),code));
	 				}
	 			} else if (key.equals("*")) {
	 				if (((MorseNode) currNode).hasRightChild()) {
	 					currNode = currNode.getRightChild();
	 				} else {
	 					throw new IllegalArgumentException(
	 					String.format("Invalid code encountered %s[%s]", sb.toString().trim(),code));
	 				}
	 			}
	 		}
	 		return currNode.getValue().toString();
	 	}
	}
	
    static MorseNode root = new MorseNode();
    DecodeTree decodeTree;
    
    public static MorseNode getRoot() {
        return root;
    }
    
    private void insert (Character character, String code) {
        Character value = character;
        String path = code;
        String nKey = "";
        MorseNode currNode = getRoot();
        MorseNode tempNode = new MorseNode();

        for (int i=0; i<path.length(); i++) {
            nKey = path.substring(i,i+1);
            if (nKey.equals("-")) {
                if (currNode.hasLeftChild()) {
                    currNode = (MorseNode) currNode.getLeftChild();
                } else {
                    currNode.setLeftChild(new MorseNode());
                    tempNode = currNode;
                    currNode = (MorseNode) currNode.getLeftChild();
                    currNode.setParent(tempNode);
                }
            } else {
                if (currNode.hasRightChild()) {
                    currNode = (MorseNode) currNode.getRightChild();
                } else {
                    currNode.setRightChild(new MorseNode());
                    tempNode = currNode;
                    currNode = (MorseNode) currNode.getRightChild();
                    currNode.setParent(tempNode);
                }
            }
        } 
        currNode.setValue(value);
    }
    /*
     * Takes the encodemap that was built from the morseCode.txt file and creates a tree from it to be used for decoding
     */
    public DecodeTree decodeTreeBuilder(Map<Character, String> encoderString) {
    	Map<Character, String> dictionary = encoderString;
		DecodeTree decodeTree = new DecodeTree();
		for(Map.Entry<Character, String> entry : dictionary.entrySet()) {
			(decodeTree).insert(entry.getKey(), entry.getValue());
		}
		return decodeTree;
	}
}
