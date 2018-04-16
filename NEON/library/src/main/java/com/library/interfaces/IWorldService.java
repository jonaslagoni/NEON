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
public interface IWorldService{

    public void addEntity(Entity entity);

    public <E extends Entity> List<E> getEntities(final Class<E> type);

    public void removeEntity(Entity player);

    public boolean setGridCell(Vector2f position, Drawable entity);

    public boolean isValidPosition(Vector2f position);

    public Entity getGridCell(Vector2f position);

    public boolean blocked(int x, int y);

    public int getNumberOfTowers();
    
    public static boolean isOutOfBounds(Vector2f v) {
        return v.getX() < 0 || v.getX() > WIDTH || v.getY() < 0 || v.getY() > HEIGHT;
    }

    public static Vector2i gridProject(Vector2f v) {
        return new Vector2i((int) v.getX() / GRID_CELL_SIZE, (int) v.getY() / GRID_CELL_SIZE);
    }

    public static Vector2f gridUnproject(Vector2i v) {
        return new Vector2f(v.x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2, v.y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2);
    }
    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    @SuppressWarnings("WeakerAccess")
    public static final int MAX_WIDTH = WIDTH - 1;
    @SuppressWarnings("WeakerAccess")
    public static final int MAX_HEIGHT = HEIGHT - 1;
    @SuppressWarnings("WeakerAccess")
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;
    @SuppressWarnings("WeakerAccess")
    public static final Vector2f START = new Vector2f(WIDTH / 2, HEIGHT);
    @SuppressWarnings("WeakerAccess")
    public static final Vector2f END = new Vector2f(WIDTH / 2, 0);
}
