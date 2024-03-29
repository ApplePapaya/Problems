package com.example.main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchWithStreams {

    public static void main(String[] args) {

        String s1 = new String(new char[10]);
        System.out.println(":" + s1.replace('\0', 'q')+":");
        s1.replace('\0', 'p');
        System.out.println(":"+ new String(new char[15]).replace('\0', 'p')+":");

       // Node root = Node.createTree();

      //  System.out.println("DFS Nodes: ".concat(root.searchByDepth().toString()));
       // System.out.println("BFS Nodes: ".concat(root.searchByBreadth().toString()));
    }

}

class Node {

    private Node left;
    private Node right;
    private String label;

    public Node( String label,  Node left,  Node right) {
        this.left = left;
        this.right = right;
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public List<Node> getChildren() {
        return Stream.of(left, right)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Node> searchByDepth() {
        List<Node> visitedNodes = new LinkedList<>();
        List<Node> unvisitedNodes = new LinkedList<>();
        unvisitedNodes.add(this);

        while(!unvisitedNodes.isEmpty()) {
            Node currNode = unvisitedNodes.remove(0);

            List<Node> newNodes = currNode.getChildren()
                    .stream()
                    .filter(node -> !visitedNodes.contains(node))
                    .collect(Collectors.toList());

            unvisitedNodes.addAll(0, newNodes);
            visitedNodes.add(currNode);
        }

        return visitedNodes;
    }

    public List<Node> searchByBreadth() {
        List<Node> visitedNodes = new LinkedList<>();
        List<Node> unvisitedNodes = Arrays.asList(this);

        while(!unvisitedNodes.isEmpty()) {
            List<Node> newNodes = unvisitedNodes
                    .stream()
                    .map(Node::getChildren)
                    .flatMap(List::stream)
                    .filter(node -> !visitedNodes.contains(node))
                    .collect(Collectors.toList());

            visitedNodes.addAll(unvisitedNodes);
            unvisitedNodes = newNodes;
        }

        return visitedNodes;
    }

    public static Node createTree() {
       
        Node grandchildOfLeft = new Node("grandchild of left", null, null);
        Node grandRightchildOfLeft = new Node("grandRightchild of left", null, null);
        Node grandchildOfRight = new Node("grandchild of right", null, null);
        Node childOfLeft = new Node("child of left", grandchildOfLeft, grandRightchildOfLeft);
        Node childOfRight = new Node("child of right", grandchildOfRight, null);
        Node left = new Node("left", childOfLeft, null);
        Node right = new Node("right", childOfRight, null);
        return new Node("root", left, right);
    }

}
