import java.io.*;
import java.util.*;


public class Trie {
    private TrieNode root = new TrieNode();

    /**
     * Inserts a string into the trie and returns the last node that was
     * inserted.
     *
     * @param str The string to insert into the trie
     * @param data	The data associated with the string
     * @return The last node that was inserted into the trie
     */
    public TrieNode insert(String str, TrieData data) {
        TrieNode node = root;
        // hint you can use str.toCharArray() to get the char[] of characters

        for (char ch : str.toCharArray()) {
            TrieNode child = node.getChild(ch);
            if (child == null) {
                child = new TrieNode();
                node.addChild(ch, child);
            }
            node = child;
        }
        node.addData(data);  // <- marks node as terminal and stores data
        return node;
    }

    /**
     * Search for a particular prefix in the trie, and return the final node in
     * the path from root to the end of the string, i.e. the node corresponding
     * to the final character. getNode() differs from get() in that getNode()
     * searches for any prefix starting from the root, and returns the node
     * corresponding to the final character of the prefix, whereas get() will
     * search for a whole word only and will return null if it finds the pattern
     * in the trie, but not as a whole word.  A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The string to search for
     * @return the final node in the path from root to the end of the prefix, or
     * null if prefix is not found
     */

    public TrieNode getNode(String str) {

        TrieNode node = root;
        // hint you can use str.toCharArray() to get the char[] of characters

        for (char ch : str.toCharArray()) {
            node = node.getChild(ch);
            // if the child is null, then the prefix is not in the trie
            if (node == null)
                return null;
        }
        return node;
    }

    /**
     * Searches for a word in the trie, and returns the final node in the search
     * sequence from the root, i.e. the node corresponding to the final
     * character in the word.
     *
     * getNode() differs from get() in that getNode() searches for any prefix
     * starting from the root, and returns the node corresponding to the final
     * character of the prefix, whereas get() will search for a whole word only
     * and will return null if it finds the pattern in the trie, but not as a
     * whole word. A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The word to search for
     * @return The node corresponding to the final character in the word, or
     * null if word is not found
     */

    public TrieNode get(String str) {

        TrieNode node = getNode(str);

        // hint you can use str.toCharArray() to get the char[] of characters
        // check if the node is terminal
        if (node != null && node.isTerminal())
            return node;
        return null;
    }

    /**
     * Retrieve from the trie an alphabetically sorted list of all words
     * beginning with a particular prefix.
     *
     * @param prefix The prefix with which all words start.
     * @return The list of words beginning with the prefix, or an empty list if
     * the prefix was not found.
     */

    // first we need a recursive helper method for getAlphabeticalListWithPrefix which takes in prefix string
    // and fetches nodes following the prefix, adjoins nodes till isTerminal(), and updates the result list
    public List<String> getAlphabeticalListWithPrefix(String prefix) {

        // make a list to dump words
        List<String> result = new ArrayList<>();

        TrieNode node = getNode( prefix);
        if (node == null) {
            return result; // empty list returned if prefix not found
        }

        // here the recursion happens via this
        // recursively gather words from the subtree
        getWords(node, prefix, result);

        // sort words
        Collections.sort(result);

        return result;
    }


    // helper method to method above, takes in TrieNode, word(prefix+ node letters), and results List<>
    // recursively calls itself in the same method to make words in the Trie
    private void getWords(TrieNode node, String word, List<String> result) {

        // if node isTerminal() adds that word to results list
        if (node.isTerminal()) {
            result.add(word);
        }

        // new Map<> children of children map
        Map<Character, TrieNode > children = node.getChildren();

        // make a list of the "keys" (i.e. nodes) of Map<> children
        List<Character> nodeLettersList = new ArrayList<>(children.keySet());

        // 1st sort the keys
        //Collections.sort( nodeLettersList);

        // magic happens here, the recursive loop where helper getWords() calls itself recursively
        // taking in: Map<> children key, prefix word adjoined by nodeLetter,
        for ( Character nodeLetter : nodeLettersList) {
            getWords(children.get(nodeLetter), word +nodeLetter, result);
        }
        Collections.sort(result);
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Finds the most frequently
     * occurring word represented in the trie (according to the dictionary file)
     * that begins with the provided prefix.
     *
     * @param prefix The prefix to search for
     * @return The most frequent word that starts with prefix
     */
    public String getMostFrequentWordWithPrefix(String prefix) {
        // first, we need to find the node corresponding to the prefix
        TrieNode prefixNode = getNode(prefix);
        // if the prefix node is null, it means no words exist with this prefix
        if (prefixNode ==null) {

            return null; // No words exist with this prefix
        }
        // Now we need to find the most frequent word starting from this node
        int[] maxFrequency = {Integer.MIN_VALUE};
        // array to hold the most likely word founds by freq
        String[] bestWord = {null};
        // StringBuilder to build the current word as we traverse
        StringBuilder currentWord = new StringBuilder(prefix);

        // calling the helper method to find the most frequent word
        findMostFrequent(prefixNode, currentWord, maxFrequency, bestWord);
        return bestWord[0];
    }

   // helper method
    private void findMostFrequent(TrieNode node, StringBuilder currentWord, int[] maxFrequency, String[] bestWord) {

        if (node.isTerminal()) {
            int freq = node.getData().getFrequency();
            if (freq > maxFrequency[0]) {
                maxFrequency[0] = freq ;
                bestWord[0] = currentWord.toString();
            }
        }

        // Iterate through the children of the current node
        Map<Character, TrieNode> children = node.getChildren();
        // Sort the children by frequency in descending order
        for (Map.Entry<Character, TrieNode> entry :children.entrySet()) {
            // key in key value pair is the character
            char ch = entry.getKey();
            // value in key value pair is the TrieNode
            TrieNode child = entry.getValue();
            // RECURSUIVELY call the method for the child node
            currentWord.append(ch);
            findMostFrequent( child, currentWord, maxFrequency, bestWord);

            currentWord.deleteCharAt(currentWord.length() - 1); // Backtrack
        }
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Reads in a dictionary from file
     * and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    public static Trie readInDictionary(String fileName) {

        Trie trie = new Trie();
        // Use the file name to create a full path
        String fullPath = "Trie" + File.separator + fileName; // e.g. : "Trie/data/word-freq.expanded.trim.txt"
        try (Scanner scanner = new Scanner(new FileInputStream(fullPath))) {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    // Split the line into parts
                    String[] parts = line.split("\\s+");

                    if (parts.length >= 3) {
                        String word = parts[1];
                        int frequency = Integer.parseInt(parts[2]);
                        TrieData data = new TrieData(frequency);
                        trie.insert(word, data);
                    }
                }}
//            } catch (IOException e) {
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fullPath);
        }
        return trie;
    }
}