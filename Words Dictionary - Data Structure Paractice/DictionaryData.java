
/**
 *
 * @author lewi0146
 */
// JDK 15
public class DictionaryData {

    private String word;
    private int frequency;
    private int rank;

    /**
     * Creates a new DictionaryData object based upon the the String line that
     * contains the data about the new data item.
     *
     * @param line the data about the new data item
     */
    public DictionaryData(String line)
    {
        String[] lineSplit = line.split("\\s+");
        this.rank = Integer.parseInt(lineSplit[0]);
        this.word = lineSplit[1];
        this.frequency = Integer.parseInt(lineSplit[2]);


    }
    public String getWord(){
        return this.word;
    }
    public int getFrequency(){
        return this.frequency;
    }
    public int getRank(){
        return this.rank;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void setRank(int rank){
        this.rank =rank;
    }

    /**
     * A string representation of the DataDictionary object. For example
     *
     *     "orange: frequency = 518"
     *
     * @return a string representation of the DataDictionary object
     */


    @Override
    public String toString() {
        return word + ": frequency = " + frequency;
    }
}
