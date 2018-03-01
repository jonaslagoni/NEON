package com.neon.main;

import com.neon.main.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[16][16];         // 2D array
    private final Map<Class<?>, List<?>> cache = new HashMap<>();

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        cache.clear();
        entities.add(entity);
    }

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

    public int getGridLength() {
        return grid.length;
    }
}

