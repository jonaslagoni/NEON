package com.library;

import com.library.interfaces.Drawable;
import com.library.interfaces.Entity;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class World implements IWorldService {

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final Map<Class<?>, List<?>> cache = new HashMap<>();

    @Override
    public void addEntity(Entity entity) {
        cache.clear();
        if (entities.size() > 0) {
            Entity e = entities.get(entities.size() - 1);
            entities.set(entities.size() - 1, entity);
            entities.add(e);
        } else {
            entities.add(entity);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <E extends Entity> List<E> getEntities(final Class<E> type) {
        if (cache.containsKey(type)) {
            return (List<E>) cache.get(type);
        }
        final List<E> result = entities.stream().filter(type::isInstance).map(type::cast).collect(toList());
        cache.put(type, result);
        return result;
    }

    @Override
    public void removeEntity(Entity player) {
        cache.clear();
        entities.remove(player);
    }

    @Override
    public void setGridCell(Vector2f position, Drawable entity) {
        Vector2i v = IWorldService.gridProject(position);
        if (grid[v.x][v.y] != null || entity == null) {
            return;
        }
        grid[v.x][v.y] = entity;
        addEntity(entity);
        Vector2f v1 = IWorldService.gridUnproject(v);
        entity.getSprite().setPosition(v1);
    }

    @Override
    public boolean isValidPosition(Vector2f position) {
//        boolean b = setGridCell(position, () -> new Sprite(
//                new Texture(Gdx.files.internal("images/laser-tower.png")),
//                new Vector2f(MAX_WIDTH / 2, MAX_HEIGHT),
//                new Vector2f(0, 200),
//                GRID_CELL_SIZE,
//                GRID_CELL_SIZE
//        ));
//        if (!b) return false;
//        Queue<Vector2f> path = new PathFinder(this).findPath(getPositionGridCell(START), getPositionGridCell(END));
//        Vector2i v = gridProject(position);
//        removeGridCell(v.x, v.y);
//        return path.size() > 0;
        return true;
    }

    @Override
    public Entity getGridCell(Vector2f position) {
        Vector2i v = IWorldService.gridProject(position);
        return grid[v.x][v.y];
    }

    private Vector2i getPositionGridCell(Vector2f position) {
        Vector2i v = IWorldService.gridProject(position);
        return new Vector2i(v.x < 0 ? 0 : v.x, v.y < 0 ? 0 : v.y);
    }

    private void removeGridCell(int x, int y) {
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }

    @Override
    public boolean blocked(int x, int y) {
        return x >= GRID_SPACES || y >= GRID_SPACES || x < 0 || y < 0 || grid[x][y] != null;
    }
}
