package com.pathfinding;

import com.library.World;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;
import com.library.vectors.VectorUtils;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toList;

public class Node {

    private final static List<Vector2i> DIRECTIONS = Arrays.asList(
            new Vector2i(0, 1),
            new Vector2i(0, -1),
            new Vector2i(1, 0),
            new Vector2i(-1, 0)
    );

    private Node parent;
    private Vector2i vector;
    private int gCost = Integer.MAX_VALUE;
    private float fCost = Float.MAX_VALUE;

    Node(Vector2i vector) {
        this.vector = vector;
    }

    private Node(int x, int y) {
        this.vector = new Vector2i(x, y);
    }

    /**
     * 
     * @param node
     * @return 
     */
    public LinkedList<Vector2f> reconstructPath(Node node) {
        Node current = node;
        LinkedList<Vector2f> path
                = new LinkedList<>(singleton(IWorldService.gridUnproject(current.vector)));
        while (current.parent != null) {
            current = current.parent;
            path.addFirst(IWorldService.gridUnproject(current.vector));
        }
        return path;
    }

    public float heuristicCostEstimate(Node goalState) {
        Vector2f v0 = IWorldService.gridUnproject(this.vector);
        Vector2f v1 = IWorldService.gridUnproject(goalState.vector);
        return VectorUtils.distance(v0, v1) / World.GRID_CELL_SIZE;
    }

    public int manhattanDistance(Node b) {
        return Math.abs(vector.x - b.vector.x) + Math.abs(vector.y - b.vector.y);
    }

    public List<Node> neighbors(IWorldService world) {
        return DIRECTIONS.stream()
                .filter(d -> !world.blocked(vector.x + d.x, vector.y + d.y))
                .map(d -> new Node(vector.x + d.x, vector.y + d.y))
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return vector.equals(node.vector);
    }

    @Override
    public int hashCode() {
        return vector.hashCode();
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

    public float getfCost() {
        return fCost;
    }

    public void setfCost(float fCost) {
        this.fCost = fCost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
