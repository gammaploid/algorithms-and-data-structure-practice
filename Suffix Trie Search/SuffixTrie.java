import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SuffixTrie {

    private final SuffixTrieNode root;


    public SuffixTrie() {
        root = new SuffixTrieNode();
    }

    /**
     * Insert a String into the suffix trie.  For the assignment the string str
     * is a sentence from the given text file.
     *
     * @param str the sentence to insert
     * @param sentenceNum the starting index/position of the sentence
     * @return the final node inserted
     */
    public void insert(String str, int sentenceNum) {

        if (str == null || str.isEmpty()) {
            return;
        }
        

        String lower = str.toLowerCase();
        
        //  create EVERY possible suffix
        for (int i = 0; i < str.length();  i++) {

            SuffixTrieNode node = root;
            // create an index that tells us exactly where this suffix starts
            SuffixIndex idx = new SuffixIndex(sentenceNum,i);
            
            // loop through the suffix
            for (char ch :lower.substring(i).toCharArray()) {

                // Do we already have a path for this character?
                SuffixTrieNode child = node.getChild(ch);

                if (child == null) {
                    // if no path node exists, so we need to create a new node for this character
                    child = new SuffixTrieNode();
                    node.addChild(ch, child);
                }


                // mark  node with our location info
                child.addIndex(idx);
                node = child;
           }
        }
    }

    /**
     * Get the suffix trie node associated with the given (sub)string.
     *
     * @param str the (sub)string to search for
     * @return  the final node in the (sub)string
     */

    public SuffixTrieData get(String str) {
        if (str ==null || str.isEmpty()) {
            return null;
        }

        // Convert to lowercase for case-insensitive matching
        String lower= str.toLowerCase();
        SuffixTrieNode node = root;

        for (char ch : lower.toCharArray()) {
            node = node.getChild(ch);
            // if null then its not in the trie
            if (node == null) {
                return null;
            }
     }

        return node.getData();
    }

    /**
     * Helper/Factory method to build a SuffixTrie object from the text in the
     * given file.  The text file is broken up into sentences and each sentence
     * is inserted into the suffix trie.
     *
     * It is called in the following way
     * <code>SuffixTrie st = SuffixTrie.readInFromFile("Frank01e.txt");</code>
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public void readInFromFile(String fileName) throws IOException {

        StringBuilder content = new StringBuilder();
        
        // buffered reader to read the file line by line without loading the entire file into memory
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                // Remove any leading/trailing whitespace
                content.append(line).append(" ");
            }
        }
//       // now we have the entire content of the file in a single string
        String text = content.toString();

        // char start index, sentance index
        int start = 0;
        int sentenceNum = 0;
        
        //break it into sentences and add each one to our trie
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // found the end of a sentence?
            if (ch == '.' ||ch == '!' || ch == '?') {

                String sentence = text.substring(start, i + 1).trim();
                if (!sentence.isEmpty()) {
                    // insert the sentence into the trie
                    insert(sentence, sentenceNum++);
                }
                
                // Skip any whitespace to get to the start of the next sentence
                int end = i +1;
                while (end < text.length() && Character.isWhitespace(text.charAt(end))) {
                    end++;
                }
                // Update the start index for the next sentence
                start = end;
            }
        }
        
        // case last sentence if it doesn't end with punctuation
        String lastSentence = text.substring(start).trim();
        if (!lastSentence.isEmpty()) {
            insert(lastSentence, sentenceNum);
        }
    }
}