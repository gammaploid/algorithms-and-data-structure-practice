/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Scanner;

/**
 * This is an example class to driver the Suffix Trie project.  You can use this a starting point
 * to test your Suffix Trie implementation.
 *
 * It expects user input of the file to processes as the first line and then the subsequent lines are
 * the words/phrases/suffixes to search for with an empty line terminating the user input. For example:
 *
 * java cp3.ass01.suffixtrie.SuffixTrieDriver
 * data/Frank02.txt
 * and
 * the
 * , the
 * onster
 * ngeuhhh
 * ing? This
 *
 * @author lewi0146
 */
public class SuffixTrieDriver {
    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            String filename = input.nextLine();
            
            SuffixTrie suffixTrie = new SuffixTrie() ;
            
            try {suffixTrie.readInFromFile(filename);
                while (true)  {
                    String query = input.nextLine();
                    if (query.isEmpty() ) {
                        break;
                     }
                    SuffixTrieData result = suffixTrie.get(query);

                    System.out.println("[" + query + "]: " + (result != null ? result.toString() :"null"));
               }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
  }

}
