package com.neon.libary;

import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.Vector2i;

import java.util.*;
import java.util.stream.Collectors;

class Node {

    private static List<Vector2i> directions = Arrays.asList(
            new Vector2i(0, 1),
            new Vector2i(0, -1),
            new Vector2i(1, 0),
            new Vector2i(-1, 0)
    );

    private Node parent;
    private Vector2i vector;
    private int gCost = Integer.MAX_VALUE;
    private int fCost = Integer.MAX_VALUE;

    Node(Vector2i vector) {
        this.vector = vector;
    }

    public LinkedList<Vector2f> reconstructPath(Node node) {
        Node current = node;
        LinkedList<Node> path = new LinkedList<>(Collections.singleton(current));
        while (current.parent != null) {
            current = current.parent;
            path.addFirst(current);
        }
        return path.stream()
                .map(Node::getVector)
                .map(World::gridUnproject)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public int heuristicCostEstimate(Node goalState) {
        return manhattanDistance(goalState);
    }

    public int manhattanDistance(Node b) {
        return Math.abs(vector.x - b.vector.x) + Math.abs(vector.y - b.vector.y);
    }

    private Vector2i getVector() {
        return vector;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> neighbors(World world) {
        List<Node> neighbors = new ArrayList<>();
        for (Vector2i direction : directions) {
            if (!world.blocked(vector.x + direction.x, vector.y + direction.y)) {
                neighbors.add(new Node(new Vector2i(vector.x + direction.x, vector.y + direction.y)));
            }
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return vector.equals(node.vector);
    }

    @Override
    public int hashCode() {
        return vector.hashCode();
    }
}
