package com.pathfinding;

import com.library.interfaces.IPathFindingService;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;

import java.util.*;

public class PathFinder implements IPathFindingService {

    private IWorldService world;

    @Override
    public Queue<Vector2f> findPath(Vector2i start, Vector2i goal) {


        Node goalState = new Node(goal);
        Node initialState = new Node(start);
        initialState.setGCost(0);
        initialState.setFCost(initialState.heuristicCostEstimate(goalState));

        HashSet<Node> closedSet = new HashSet<>();

        // TreeSet<Node> openSet = new TreeSet<>(Comparator.comparing(Node::hashCode).thenComparing(Node::getFCost));
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparing(Node::getFCost));

        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            Node current = openSet.remove();

            if (current.equals(goalState)) {
                return current.reconstructPath(current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Node neighbor : current.neighbors(world)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }

                int tentativeGcost = current.getGCost() + current.manhattanDistance(neighbor);
                if (tentativeGcost >= neighbor.getGCost()) {
                    continue;
                }

                neighbor.setParent(current);
                neighbor.setGCost(tentativeGcost);
                neighbor.setFCost(neighbor.getGCost() + neighbor.heuristicCostEstimate(goalState));
            }
        }
        return new LinkedList<>();
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }
}
