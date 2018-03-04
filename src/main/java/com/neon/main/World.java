package com.neon.main;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IdentityMap;
import com.neon.main.entities.Entity;

public class World {

    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;


    private final Array<Entity> entities = new Array<>(false, 256);
    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];         // 2D array
    private final IdentityMap<Class<?>, Array<?>> cache = new IdentityMap<>();

    public Array<Entity> getEntities() {
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

    public int getGridLength() {
        return grid.length;
    }
}

