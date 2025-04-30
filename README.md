# Data Structure Project

This project implements classic data structures and string matching algorithms in Java, and for understanding of space/time complexity of various implementation of data structures on retrieval and searching opreations. Heap memory and CPU usage was profiled and studied in terms of complexity theory for the different data stucture implementation with small to very large inputs. A GUI was devloped for the suffix search function using Java Swing components.
 
## Data Structures

- **Trie:** Supports fast prefix-based word search, autocomplete, and frequency analysis.
- **Suffix Trie:** Enables efficient substring search and indexing within large texts.
- **Dictionary (HashMap/TreeMap):** Stores words with frequency and rank, supports fast lookup, insertion, and spell checking.

## String Matching Algorithms

- **Brute Force:** Compares the pattern to every substring of the target.
- **Boyer-Moore:** Skips sections of the text for faster matching using heuristics.
- **Knuth-Morris-Pratt (KMP):** Uses a partial match table to avoid redundant comparisons.
- **Built-in Java:** Java’s native string search functionality.

## Features

- Fast word and prefix search
- Substring and pattern matching
- Word frequency analysis
- Spell checking

---

## GUI

The project includes graphical user interfaces for interactive exploration:

- **Suffix Trie GUI:**  
  - Lets you load a text file and build a suffix trie.
  - Enter a search string to find all occurrences in the loaded text.
  - Results are displayed in a scrollable text area.
  - File: `Suffix Trie Search/SuffixTrieGUI.java`

- **Prefix Trie GUI:**  
  - TextAreaDemo template provided by Oracle

---

## Usage

1. **Clone the repository** and open it in your preferred IDE. 

2. **Build and run the GUI:**
   - For the Suffix Trie GUI, run the `SuffixTrieGUI`, for the Prefix Trie GUI, run the `TextAreaDemo` class.
   - Use the interface to load a text file and perform substring searches.

3. **Command-line drivers** Otherwise, CLI drivers are also available for benchmarking and testing the data structures and algorithms.
   - Otherwise, run `SuffixTrieDriver` class or `TrieDriver` class


Refer to the source code and comments for more details on available methods and their usage.

## License

This project is for educational purposes only.  
Starter files were provided by instructors at Flinders University.
Do not redistribute or use for commercial purposes.
