/**
 * Stores the location of a suffix occurrence with sentence and character position.
 */
public class SuffixIndex {

    private final int sentenceIndex;
    private final int charIndex;

    /**
     * Constructor for SuffixIndex.
     * 
     * @param sentenceIndex The index of the sentence (0-based)
     * @param charIndex The starting character index within the sentence (0-based)
     */
    public SuffixIndex(int sentenceIndex, int charIndex) {
        this.sentenceIndex = sentenceIndex;
        this.charIndex = charIndex;
    }

    /**
     * Gets the sentence index.
     * @return The index of the sentence
     */
    public int getSentenceIndex() {
        return sentenceIndex;
    }

    /**
     * Gets the character index within the sentence.
     * @return The index of the starting character
     */
    public int getCharIndex() {
        return charIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuffixIndex that = (SuffixIndex) o;
        return sentenceIndex == that.sentenceIndex && charIndex == that.charIndex;
    }

    @Override
    public int hashCode() {
        int result = sentenceIndex;
        result = 31 * result + charIndex;
        return result;
    }





    /**
     * The sentence and character position in the format sentence.character notation
     * @return the position.
     */
    @Override
    public String toString() {
        // Format as specified: sentence.char (1-based char index for output)
        return sentenceIndex + "." + (charIndex + 1);
    }


}
