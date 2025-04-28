import java.util.ArrayList;
import java.util.List;

/**
 * Data structure stored at nodes of the SuffixTrie.
 * It holds a list of indices indicating positions where the corresponding suffix occurs.
 */
public class SuffixTrieData {
    private final List<SuffixIndex> indices;

    public SuffixTrieData() {
        this.indices = new ArrayList<>();
    }

    /**
     * Adds a SuffixIndex to the list of indices.
     * @param index The SuffixIndex to add.
     */
    public void addIndex(SuffixIndex index) {
        this.indices.add(index);
    }

    /**
     * Gets the list of indices.
     * @return The list of SuffixIndex objects.
     */
    public List<SuffixIndex> getIndices() {
        return indices;
    }

    @Override
    public String toString() {
        return indices.toString();
    }
}
