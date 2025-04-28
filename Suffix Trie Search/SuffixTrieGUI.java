import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class SuffixTrieGUI extends JFrame {
    private final JTextField filePathField;
    private final JTextField searchField;
    private final JTextArea resultArea;
    private SuffixTrie suffixTrie;

    public SuffixTrieGUI() {
        // Set up the main window
        setTitle("Suffix Trie Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600 , 400);
        setLocationRelativeTo(null);

        // Create components
        // Labels, text fields, buttons
        JLabel filePathLabel = new JLabel("File Path:");
        filePathField = new JTextField("SuffixTrie/data/Frank02.txt", 20);
        JButton loadButton = new JButton("Load File");

        JLabel searchLabel = new JLabel("Search String:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");


        //// result area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // layout setup
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // File path row
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(filePathLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(filePathField, gbc);
        gbc.gridx = 2;
        inputPanel.add(loadButton, gbc);


        // Search row
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(searchLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(searchField, gbc);
        gbc.gridx = 2;
        inputPanel.add(searchButton, gbc);

        // layout for the entire frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // action listeners
        loadButton.addActionListener(e -> loadFile());
        searchButton.addActionListener(e -> performSearch());
    }

    /**
     * Loads the file specified in the file path field into the SuffixTrie.
     */
    private void loadFile() {
        String filePath = filePathField.getText().trim();
        if (filePath.isEmpty()) {
            resultArea.setText("Please enter a file path. \n");
            return;
        }
        resultArea.setText("Loading file : " + filePath + "...\n");
        try {
            suffixTrie = new SuffixTrie();
            suffixTrie.readInFromFile(filePath);
            resultArea.append("File loaded successfully.\n");
        } catch (IOException ex) {
            resultArea.append("Error loading file: " + ex.getMessage() + "\n");
        }
    }
/**
     * Performs a search for the string specified in the search field.
     */
    private void performSearch() {
        if (suffixTrie == null) {
            resultArea.append("Please load a file first.\n");
            return;
         }

        String searchString = searchField.getText().trim();
        if (searchString.isEmpty()) {
            resultArea.append("Please enter a search string.\n");
            return;
        }

        resultArea.append("Searching for: " + searchString + "\n");
        SuffixTrieData results = suffixTrie.get(searchString);
        if (results == null) {
            resultArea.append("No matches found.\n");
        } else {
            resultArea.append("Results: " + results.toString() + "\n");
        }
    }

    public static void main(String[] args) {

        // Run the GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SuffixTrieGUI().setVisible(true));

    }
}