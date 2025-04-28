import java.util.HashMap;
import java.util.Map;

public class SuffixTrieNode {
    private final Map<Character, SuffixTrieNode> children = new HashMap<>();
    //private final Map<Character, SuffixTrieNode> children = new TreeMap<>();
    private SuffixTrieData data = null;

    public SuffixTrieNode() {
        // Empty constructor since we initialised fields above ^^
    }

    public SuffixTrieNode getChild(char ch) {
        return children.get(ch);
    }

    public void addChild(char ch, SuffixTrieNode node) {
        children.put(ch, node);
    }

    public SuffixTrieData getData() {
        return data;
    }

    public void addIndex(SuffixIndex index) {
        if (data == null) {
            data = new SuffixTrieData();
        }
        data.addIndex(index);
    }

    public Map<Character, SuffixTrieNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        if (data == null) {
            return "null";
        }
        return data.toString();
    }
}
