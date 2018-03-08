package com.neon.libary;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {

    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final Map<Class<?>, List<?>> cache = new HashMap<>();

    public static boolean isOutOfBounds(Vector2 v) {
        return v.x < 0 || v.x > WIDTH || v.y < 0 || v.y > HEIGHT;
    }

    public void addEntity(Entity entity) {
        cache.clear();
        entities.add(entity);
    }

    @SuppressWarnings("unchecked")
    public <E extends Entity> List<E> getEntities(Class<E> type) {

        if (cache.containsKey(type)) {
            return (List<E>) cache.get(type);
        }

        List<E> result = new ArrayList<>();
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                result.add((E) entity);
            }
        }
        cache.put(type, result);
        return result;
    }

    public void removeEntity(Entity player) {
        cache.clear();
        entities.remove(player);
    }

    public void setGridCell(Vector2 position, Drawable entity) {
        int x = (int) position.x / GRID_CELL_SIZE;
        int y = (int) position.y / GRID_CELL_SIZE;
        addEntity(entity);
        grid[x][y] = entity;
        entity.getSprite().setPosition(
                x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2,
                y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2
        );
    }

    public Entity getGridCell(Vector2 position) {
        int x = (int) position.x / GRID_CELL_SIZE;
        int y = (int) position.y / GRID_CELL_SIZE;
        return grid[x][y];
    }

    public void removeGridCell(int x, int y) {
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }
}
