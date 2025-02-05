package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class AhoCorasick {
    private final TrieNode root = new TrieNode();

    // Add a pattern to the Trie
    public void addPattern(String pattern) {
        TrieNode current = root;
        for (char c : pattern.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.output.add(pattern);
    }

    // Build failure links
    public void buildFailureLinks() {
        Queue<TrieNode> queue = new LinkedList<>();
        root.failureLink = root;

        // Initialize failure links for the immediate children of root
        for (TrieNode node : root.children.values()) {
            node.failureLink = root;
            queue.add(node);
        }

        // Process nodes level by level
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char c = entry.getKey();
                TrieNode child = entry.getValue();
                TrieNode failure = current.failureLink;

                // Follow failure links until a matching character is found or root is reached
                while (failure != root && !failure.children.containsKey(c)) {
                    failure = failure.failureLink;
                }

                // Set failure link for the child
                child.failureLink = failure.children.getOrDefault(c, root);

                // Merge output patterns from the failure link
                child.output.addAll(child.failureLink.output);

                queue.add(child);
            }
        }
    }

    // Search for patterns in the given text and return positions
    public Map<String, List<Integer>> search(String text) {
        TrieNode current = root;
        Map<String, List<Integer>> result = new HashMap<>();

        // Traverse through the text character by character
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Follow failure links for mismatches
            while (current != root && !current.children.containsKey(c)) {
                current = current.failureLink;
            }

            // Move to the next node if a match is found
            current = current.children.getOrDefault(c, root);

            // Record all patterns found at this position
            for (String pattern : current.output) {
                result.computeIfAbsent(pattern, k -> new ArrayList<>())
                        .add(i - pattern.length() + 1); // Adjust for actual position of match
            }
        }

        return result;
    }

    // TrieNode for the Aho-Corasick algorithm
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        TrieNode failureLink;
        List<String> output = new ArrayList<>();
    }
}


