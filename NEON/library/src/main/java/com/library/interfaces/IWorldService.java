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

    void addEntity(Entity entity);

    <E extends Entity> List<E> getEntities(final Class<E> type);

    void removeEntity(Entity player);

    boolean setGridCell(Vector2f position, Drawable entity);

    boolean isValidPosition(Vector2f position);

    Entity getGridCell(Vector2f position);

    boolean blocked(int x, int y);

    int getNumberOfTowers();

}
