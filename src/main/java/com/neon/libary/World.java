package com.neon.libary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.Vector2i;

import java.util.*;

public class World {

    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    public static final int MAX_WIDTH = WIDTH - 1;
    public static final int MAX_HEIGHT = HEIGHT - 1;
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;
    public static final Vector2f START = new Vector2f(WIDTH / 2, HEIGHT);
    public static final Vector2f END = new Vector2f(WIDTH / 2, 0);

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final Map<Class<?>, List<?>> cache = new HashMap<>();

    public static boolean isOutOfBounds(Vector2f v) {
        return v.x < 0 || v.x > WIDTH || v.y < 0 || v.y > HEIGHT;
    }

    public static Vector2i gridProject(Vector2f v) {
        return new Vector2i((int) v.x / GRID_CELL_SIZE, (int) v.y / GRID_CELL_SIZE);
    }

    public static Vector2f gridUnproject(Vector2i v) {
        return new Vector2f(
                v.x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2,
                v.y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2
        );
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

    public void setGridCell(Vector2f position, Drawable entity) {
        Vector2i v = gridProject(position);
        addEntity(entity);
        grid[v.x][v.y] = entity;
        entity.getSprite().setPosition(gridUnproject(v));
    }

    public boolean isValidPosition(Vector2f position) {
        setGridCell(position, () -> new Sprite(
                new Texture(Gdx.files.internal("images/laser-tower.png")),
                GRID_CELL_SIZE, GRID_CELL_SIZE
        ));
        Queue<Vector2f> path = PathFinder.findPath(getPositionGridCell(START), getPositionGridCell(END), this);
        Vector2i v = gridProject(position);
        removeEntity(grid[v.x][v.y]);
        grid[v.x][v.y] = null;
        return path != null;
    }

    public Entity getGridCell(Vector2f position) {
        Vector2i v = gridProject(position);
        return grid[v.x][v.y];
    }

    public Vector2i getPositionGridCell(Vector2f position) {
        Vector2i v = gridProject(position);
        return new Vector2i(v.x < 0 ? 0 : v.x, v.y < 0 ? 0 : v.y);
    }

    public void removeGridCell(int x, int y) {
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }

    public boolean blocked(int x, int y) {
        return x >= World.GRID_SPACES || y >= World.GRID_SPACES || x < 0 || y < 0 || grid[x][y] != null;
    }

}
