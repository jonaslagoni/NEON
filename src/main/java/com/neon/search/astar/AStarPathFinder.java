/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.search.astar;

import com.badlogic.gdx.math.Vector2;
import com.neon.common.search.Path;
import com.neon.search.heuristic.closest.ClosestHeuristic;
import com.neon.common.search.PathFinder;
import com.neon.common.search.Mover;
import com.neon.common.search.TileBasedMap;
import java.util.ArrayList;
import com.neon.common.search.HeuristicCost;

/**
 * A path finder implementation that uses the AStar heuristic based algorithm to
 * determine a path.
 *
 * @author Kevin Glass
 */
public class AStarPathFinder implements PathFinder {

    /**
     * The set of nodes that have been searched through
     */
    private ArrayList closed = new ArrayList();
    /**
     * The set of nodes that we do not yet consider fully searched
     */
    private CustomSortedList open = new CustomSortedList();

    /**
     * The map being searched
     */
    private TileBasedMap map;
    /**
     * The maximum depth of search we're willing to accept before giving up
     */
    private int maxSearchDistance;

    /**
     * The complete set of nodes across the map
     */
    private Node[][] nodes;
    /**
     * True if we allow diaganol movement
     */
    private boolean allowDiagMovement;
    /**
     * The heuristic we're applying to determine which nodes to search first
     */
    private HeuristicCost heuristic;

    /**
     * Create a path finder with the default heuristic - closest to target.
     *
     * @param map The map to be searched
     * @param maxSearchDistance The maximum depth we'll search before giving up
     * @param allowDiagMovement True if the search should try diaganol movement
     */
    public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement) {
        this(map, maxSearchDistance, allowDiagMovement, new ClosestHeuristic());
    }

    @Override
    public Path findPath(Mover mover, Vector2 start, Vector2 end) {
        return this.findPath(mover, (int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }

    /**
     * Create a path finder
     *
     * @param heuristic The heuristic used to determine the search order of the
     * map
     * @param map The map to be searched
     * @param maxSearchDistance The maximum depth we'll search before giving up
     * @param allowDiagMovement True if the search should try diaganol movement
     */
    public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement, HeuristicCost heuristic) {
        this.heuristic = heuristic;
        this.map = map;
        this.maxSearchDistance = maxSearchDistance;
        this.allowDiagMovement = allowDiagMovement;

        nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
        for (int x = 0; x < map.getWidthInTiles(); x++) {
            for (int y = 0; y < map.getHeightInTiles(); y++) {
                nodes[x][y] = new Node(x, y);
            }
        }
    }

    /**
     * @see PathFinder#findPath(Mover, int, int, int, int)
     */
    public Path findPath(Mover mover, int sx, int sy, int tx, int ty) {
        // easy first check, if the destination is blocked, we can't get there

        if (map.blocked(mover, tx, ty)) {
            return null;
        }

        // initial state for A*. The closed group is empty. Only the starting
        // tile is in the open list and it'e're already there
        nodes[sx][sy].cost = 0;
        nodes[sx][sy].depth = 0;
        closed.clear();
        open.clear();
        open.add(nodes[sx][sy]);

        nodes[tx][ty].parent = null;

        // while we haven'n't exceeded our max search depth
        int maxDepth = 0;
        while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
            // pull out the first node in our open list, this is determined to 
            // be the most likely to be the next step based on our heuristic

            Node current = getFirstInOpen();
            if (current == nodes[tx][ty]) {
                break;
            }

            removeFromOpen(current);
            addToClosed(current);

            // search through all the neighbours of the current node evaluating
            // them as next steps
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    // not a neighbour, its the current tile

                    if ((x == 0) && (y == 0)) {
                        continue;
                    }

                    // if we're not allowing diaganol movement then only 
                    // one of x or y can be set
                    if (!allowDiagMovement) {
                        if ((x != 0) && (y != 0)) {
                            continue;
                        }
                    }

                    // determine the location of the neighbour and evaluate it
                    int xp = x + current.x;
                    int yp = y + current.y;

                    if (isValidLocation(mover, sx, sy, xp, yp)) {
                        // the cost to get to this node is cost the current plus the movement

                        // cost to reach this node. Note that the heursitic value is only used
                        // in the sorted open list
                        float nextStepCost = current.cost + getMovementCost(mover, current.x, current.y, xp, yp);
                        Node neighbour = nodes[xp][yp];
                        map.pathFinderVisited(xp, yp);

                        // if the new cost we've determined for this node is lower than 
                        // it has been previously makes sure the node hasn'e've
                        // determined that there might have been a better path to get to
                        // this node so it needs to be re-evaluated
                        if (nextStepCost < neighbour.cost) {
                            if (inOpenList(neighbour)) {
                                removeFromOpen(neighbour);
                            }
                            if (inClosedList(neighbour)) {
                                removeFromClosed(neighbour);
                            }
                        }

                        // if the node hasn't already been processed and discarded then
                        // reset it's cost to our current cost and add it as a next possible
                        // step (i.e. to the open list)
                        if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
                            neighbour.cost = nextStepCost;
                            neighbour.heuristic = getHeuristicCost(mover, xp, yp, tx, ty);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                            addToOpen(neighbour);
                        }
                    }
                }
            }
        }

        // since we'e've run out of search 
        // there was no path. Just return null
        if (nodes[tx][ty].parent == null) {
            return null;
        }

        // At this point we've definitely found a path so we can use the parents
        // references of the nodes to find out way from the target location back
        // to the start recording the nodes on the way.
        Path path = new Path();
        Node target = nodes[tx][ty];
        while (target != nodes[sx][sy]) {
            path.prependStep(target.x, target.y);
            target = target.parent;
        }
        path.prependStep(sx, sy);

        // thats it, we have our path 
        return path;
    }

    /**
     * Get the first element from the open list. This is the next one to be
     * searched.
     *
     * @return The first element in the open list
     */
    protected Node getFirstInOpen() {
        return (Node) open.first();
    }

    /**
     * Add a node to the open list
     *
     * @param node The node to be added to the open list
     */
    protected void addToOpen(Node node) {
        open.add(node);
    }

    /**
     * Check if a node is in the open list
     *
     * @param node The node to check for
     * @return True if the node given is in the open list
     */
    protected boolean inOpenList(Node node) {
        return open.contains(node);
    }

    /**
     * Remove a node from the open list
     *
     * @param node The node to remove from the open list
     */
    protected void removeFromOpen(Node node) {
        open.remove(node);
    }

    /**
     * Add a node to the closed list
     *
     * @param node The node to add to the closed list
     */
    protected void addToClosed(Node node) {
        closed.add(node);
    }

    /**
     * Check if the node supplied is in the closed list
     *
     * @param node The node to search for
     * @return True if the node specified is in the closed list
     */
    protected boolean inClosedList(Node node) {
        return closed.contains(node);
    }

    /**
     * Remove a node from the closed list
     *
     * @param node The node to remove from the closed list
     */
    protected void removeFromClosed(Node node) {
        closed.remove(node);
    }

    /**
     * Check if a given location is valid for the supplied mover
     *
     * @param mover The mover that would hold a given location
     * @param sx The starting x coordinate
     * @param sy The starting y coordinate
     * @param x The x coordinate of the location to check
     * @param y The y coordinate of the location to check
     * @return True if the location is valid for the given mover
     */
    protected boolean isValidLocation(Mover mover, int sx, int sy, int x, int y) {
        boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());

        if ((!invalid) && ((sx != x) || (sy != y))) {
            invalid = map.blocked(mover, x, y);
        }

        return !invalid;
    }

    /**
     * Get the cost to move through a given location
     *
     * @param mover The entity that is being moved
     * @param sx The x coordinate of the tile whose cost is being determined
     * @param sy The y coordiante of the tile whose cost is being determined
     * @param tx The x coordinate of the target location
     * @param ty The y coordinate of the target location
     * @return The cost of movement through the given tile
     */
    public float getMovementCost(Mover mover, int sx, int sy, int tx, int ty) {
        return map.getCost(mover, sx, sy, tx, ty);
    }

    /**
     * Get the heuristic cost for the given location. This determines in which
     * order the locations are processed.
     *
     * @param mover The entity that is being moved
     * @param x The x coordinate of the tile whose cost is being determined
     * @param y The y coordiante of the tile whose cost is being determined
     * @param tx The x coordinate of the target location
     * @param ty The y coordinate of the target location
     * @return The heuristic cost assigned to the tile
     */
    public float getHeuristicCost(Mover mover, int x, int y, int tx, int ty) {
        return heuristic.getCost(map, mover, x, y, tx, ty);
    }

}
