package com.pathfinding;

import com.neon.libary.World;
import com.neon.libary.interfaces.IPathFindingService;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.Vector2i;

import java.util.*;

public class PathFinder implements IPathFindingService {

    private final World world;

    public PathFinder(World world) {
        this.world = world;
    }

    public Queue<Vector2f> findPath(Vector2i start, Vector2i goal) {

        /* Implementation of the a* algorithm from:
         * https://en.wikipedia.org/wiki/A*_search_algorithm */
        Node goalState = new Node(goal);
        Node initialState = new Node(start);
        initialState.setgCost(0);
        initialState.setfCost(initialState.heuristicCostEstimate(goalState));

        HashSet<Node> closedSet = new HashSet<>();

        // TreeSet<Node> openSet = new TreeSet<>(Comparator.comparing(Node::hashCode).thenComparing(Node::getfCost));
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparing(Node::getfCost));

        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            Node current = openSet.remove();

            if (current.equals(goalState))
                return current.reconstructPath(current);

            openSet.remove(current);
            closedSet.add(current);

            for (Node neighbor : current.neighbors(world)) {
                if (closedSet.contains(neighbor))
                    continue;
                if (!openSet.contains(neighbor))
                    openSet.add(neighbor);

                int tentativeGcost = current.getgCost() + current.manhattanDistance(neighbor);
                if (tentativeGcost >= neighbor.getgCost())
                    continue;

                neighbor.setParent(current);
                neighbor.setgCost(tentativeGcost);
                neighbor.setfCost(neighbor.getgCost() + neighbor.heuristicCostEstimate(goalState));
            }
        }
        return new LinkedList<>();
    }
}
