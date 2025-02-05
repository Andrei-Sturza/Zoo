package org.example;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    TrieNode failureLink; // Failure link for Aho-Corasick
    List<String> output = new ArrayList<>(); // Patterns ending at this node
}

