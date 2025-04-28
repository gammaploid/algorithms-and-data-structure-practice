
import java.io.*;
import java.util.*;
// JDK 15
/**
 *
 * @author lewi0146
 */


public class Dictionary {

    private Map<String, DictionaryData> dictionaryMap;

    public Dictionary() {
        dictionaryMap = new HashMap<>();
        // dictionaryMap = new TreeMap<>();  // alternative data structure (sorted alphabatically - space efficient )
    }

    /**
     * Extends this dictionary by adding the new word
     * identified with <code>word</code> and the data <code>data</code>.
     * @param word the word to add to the <code>Dictionary</code>.
     * @param data the data about the word s
     */

    public void insert(String word, DictionaryData data) {

        dictionaryMap.put(word.toLowerCase(), data);
    }

    /**
     * Removes the word identified by <code>word</code> from this Dictionary.
     * @param word the word to remove to the <code>Dictionary</code>.
     * @return
     */

    public DictionaryData remove(String word) {
        return dictionaryMap.remove(word.toLowerCase());
    }

    /**
     * Look up the dictionary entry for a particular word
     *
     * @param word the particular word to look up.
     * @return the data associated with the word identified by <code>word</code>.
     */

    public DictionaryData lookup(String word) {
        return dictionaryMap.get(word.toLowerCase());
    }

    /**
     * Check to see whether a word is in the dictionary or not (returns true/false)
     * @param word the word to check
     * @return <code>true</code> if in this <code>Dictionary</code>, <code>false</code> otherwise.
     */

    public boolean contains(String word) {
    if (dictionaryMap.containsKey(word.toLowerCase()))
    return true;
    else {
        return false;}
    }

    /**
     * Builder method for the <code>Dictionary</code> class that builds
     * a dictionary from the given file name.
     * It is expected that each entry is on a seperate line in the form
     * <pre>
     *     862 buddy 2743
     * </pre>
     * where 862 is the rank, buddy is the word, and 2743 is the frequency.
     *
     * @param fileName the file to load the dictionary data from
     * @return the created <code>Dictionary</code> or <code>null</code> on error.
     */


    public static Dictionary readInDictionary(String fileName) {
        Dictionary d = new Dictionary();

        try (Scanner fileScanner = new Scanner(new FileInputStream("data" + File.separator + fileName))) {

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                DictionaryData data = new DictionaryData(nextLine);
                d.insert(data.getWord(), data);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + "in the data directory!");
            return null;
        }

        return d;
    }


    /**
     * Read in a file and list all the words not found in this dictionary.
     * @param fileName the file to read and check.
     * @return List of words not found in this <code>Dictionary</code>.
     */

    public List<String> spellCheck(String fileName) {
        List<String> notFound = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new FileInputStream("data" + File.separator + fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] words = line.split("\\s+");

                for (String originalWord : words) {

                // regex:\p{Punct} = all punctuation (\ before for charachter escape), + = multiple*
                    if (originalWord.isEmpty() || originalWord.matches("\\p{Punct}+")) {
                        continue;}

                    if (!contains(originalWord)) {
                        notFound.add(originalWord);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + "in the data directory!");
            return null;
        }

        return notFound;
    }


    /**
     * Creates a list of the words in this dictionary by alphabetical order.
     * @return the list of alphabetical sorted dictionary words.
     */

    public List<DictionaryData> alphabeticalList() {
        // could use ArrayList<> or LinkedList<>, preformance was slightly better on ArrayList<> while main dictionary was HashMap<>, best preformance was TreeMap<>
        List<DictionaryData> list = new ArrayList<>(dictionaryMap.values());

        // comparator comparing DictionaryData objects words
        Collections.sort(list, (d1, d2) -> d1.getWord().toLowerCase().compareTo(d2.getWord().toLowerCase())) ;

        return list;
    }

    /* if dictionaryMap = TreeMap<> was used then just use this */

//    public List<DictionaryData> alphabeticalList() {
//        return new ArrayList<>(dictionaryMap.values());
//    }



    /**
     * Creates a list of the words in this dictionary by ascending order of
     * frequency (those of the same frequency should be
     * then ordered in reverse alphabetical order).
     * @return the list of frequency sorted dictionary words.
     */


    public List<DictionaryData> frequencyOrderedList() {
        List<DictionaryData> list = new ArrayList<>(dictionaryMap.values());
        list.sort((d1, d2) ->{

            if (d1.getFrequency() != d2.getFrequency()) {
                return Integer.compare(d1.getFrequency(), d2.getFrequency());
            }
            else {

                return d2.getWord().compareTo(d1.getWord()); // reverse alphabetical
            }
        } );
        return list;
    }

}



