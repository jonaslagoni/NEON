package com.neon.main;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IdentityMap;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Entity;

public class World {

    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;

    private final Array<Entity> entities = new Array<>(false, 256);
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final IdentityMap<Class<?>, Array<?>> cache = new IdentityMap<>();

    public static boolean isOutOfBounds(Vector2 v) {
        return v.x < 0 || v.x > WIDTH || v.y < 0 || v.y > HEIGHT;
    }

    public Iterable<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        cache.clear();
        entities.add(entity);
    }

    @SuppressWarnings("unchecked")
    public <E extends Entity> Iterable<E> getEntities(Class<E> type) {

        if (cache.containsKey(type)) {
            return (Array<E>) cache.get(type);
        }

        Array<E> result = new Array<>();
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
        entities.removeValue(player, true);
    }

    public void setGridCell(Vector2 position, Drawable entity) {
        int x = MathUtils.floor(position.x / GRID_CELL_SIZE);
        int y = MathUtils.floor(position.y / GRID_CELL_SIZE);
        entities.add(entity);
        grid[x][y] = entity;
        entity.getSprite().setPosition(
                x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2,
                y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2)
        ;
    }

    public Entity getGridCell(Vector2 position) {
        int x = MathUtils.floor(position.x / GRID_CELL_SIZE);
        int y = MathUtils.floor(position.y / GRID_CELL_SIZE);
        return grid[x][y];
    }

    public void removeGridCell(int x, int y) {
        entities.removeValue(grid[x][y], true);
        grid[x][y] = null;
    }

}

