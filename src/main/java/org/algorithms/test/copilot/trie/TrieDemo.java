package org.algorithms.test.copilot.trie;

import java.util.*;

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    // Search for an exact word
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return false;
        }
        return node.isEndOfWord;
    }

    // Find words with a given prefix
    public List<String> getWordsWithPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return new ArrayList<>();
        }
        return collectWords(node, prefix, new ArrayList<>());
    }

    private List<String> collectWords(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) results.add(prefix);
        for (char ch : node.children.keySet()) {
            collectWords(node.children.get(ch), prefix + ch, results);
        }
        return results;
    }
}

public class TrieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.insert("cart");

        System.out.println("Search 'car': " + trie.search("car")); // True
        System.out.println("Search 'cat': " + trie.search("cat")); //  False
        System.out.println("Autocomplete 'car': " + trie.getWordsWithPrefix("car")); // ["car", "card", "care", "cart"]

        CompressedTrie compressedTrie = new CompressedTrie();
        compressedTrie.insert("bar");
        compressedTrie.insert("bard");
        compressedTrie.insert("bare");
        compressedTrie.insert("bart");

        System.out.println("Search 'bar': " + compressedTrie.search("bar")); // True
        System.out.println("Search 'bat': " + compressedTrie.search("bat")); //  False

        DAWG dawg = new DAWG();
        dawg.insert("ant");
        dawg.insert("anti");
        dawg.insert("ante");
        dawg.insert("anthology");

        System.out.println("Search 'anthology': " + dawg.search("anthology")); // True
        System.out.println("Search 'act': " + dawg.search("act")); //  False
    }
}

class CompressedTrieNode {
    HashMap<String, CompressedTrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

class CompressedTrie {
    private final CompressedTrieNode root = new CompressedTrieNode();

    public void insert(String word) {
        CompressedTrieNode node = root;
        //noinspection LoopStatementThatDoesntLoop
        for (int i = 0; i < word.length(); ) {
            String key = word.substring(i);
            node = node.children.computeIfAbsent(key, k -> new CompressedTrieNode());
            node.isEndOfWord = true;
            break; // Stop after insertion as it's a compressed node
        }
    }

    public boolean search(String word) {
        CompressedTrieNode node = root;
        //noinspection LoopStatementThatDoesntLoop
        for (int i = 0; i < word.length(); ) {
            String key = word.substring(i);
            node = node.children.get(key);
            if (node == null) return false;
            return node.isEndOfWord; // Single lookup in compressed path
        }
        return false;
    }
}

class DAWGNode {
    HashMap<Character, DAWGNode> children = new HashMap<>();
    Set<String> words = new HashSet<>();
}

class DAWG {
    private final DAWGNode root = new DAWGNode();

    public void insert(String word) {
        DAWGNode node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.children.computeIfAbsent(word.charAt(i), k -> new DAWGNode());
            node.words.add(word);
        }
    }

    public boolean search(String word) {
        DAWGNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return false;
        }
        return node.words.contains(word);
    }
}
