/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;

import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IWorldService {

    int WIDTH = 2048;
    int HEIGHT = 2048;
    int MAX_WIDTH = WIDTH - 1;
    int MAX_HEIGHT = HEIGHT - 1;
    int GRID_SPACES = 16;
    int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;

    Vector2f START = new Vector2f(WIDTH / 2, HEIGHT);
    Vector2f END = new Vector2f(WIDTH / 2, 0);

    static boolean isOutOfBounds(Vector2f v) {
        return v.getX() < 0 || v.getX() > WIDTH || v.getY() < 0 || v.getY() > HEIGHT;
    }

    static Vector2i gridProject(Vector2f v) {
        return new Vector2i((int) v.getX() / GRID_CELL_SIZE, (int) v.getY() / GRID_CELL_SIZE);
    }

    static Vector2f gridUnproject(Vector2i v) {
        return new Vector2f(v.x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2, v.y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2);
    }

    /**
     * Add entities to a list
     * @param entity
     */
    void addEntity(Entity entity);

    /**
     * Returns a list of type E extending entities
     * @param <E> generic type of entity
     * @param type class of wanted entities
     * @return entities 
     */
    <E extends Entity> List<E> getEntities(final Class<E> type);

    /**
     * Remove entity from entity list
     * @param player the entity to be removed
     */
    void removeEntity(Entity player);

    /**
     * Sets a drawable entity into the map at the vectors x and y
     * @param position is a gridcell
     * @param entity is a drawable entity
     * @return if successful
     */
    boolean setGridCell(Vector2f position, Drawable entity);
    
    /**
     * Returns true if position is valid
     * @param position x and y
     * @return true if valid and false if invalid
     */
    boolean isValidPosition(Vector2f position);

    /**
     * Returns entity on the given position
     * @param position at the entity
     * @return entity or null
     */
    Entity getGridCell(Vector2f position);

    /**
     * Checks if a coordinate is blocked by an entity or out of bounds
     * @param x coodinate
     * @param y coordinate
     * @return true if invalid
     */
    boolean blocked(int x, int y);

}
