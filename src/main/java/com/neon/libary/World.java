package com.neon.libary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.Vector2i;
import com.neon.pathfinding.PathFinder;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class World {

    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    @SuppressWarnings("WeakerAccess")
    public static final int MAX_WIDTH = WIDTH - 1;
    @SuppressWarnings("WeakerAccess")
    public static final int MAX_HEIGHT = HEIGHT - 1;
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;
    @SuppressWarnings("WeakerAccess")
    public static final Vector2f START = new Vector2f(WIDTH / 2, HEIGHT);
    @SuppressWarnings("WeakerAccess")
    public static final Vector2f END = new Vector2f(WIDTH / 2, 0);

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private int numberOfTowers = 0;
    private final Map<Class<?>, List<?>> cache = new HashMap<>();

    public static boolean isOutOfBounds(Vector2f v) {
        return v.getX() < 0 || v.getX() > WIDTH || v.getY() < 0 || v.getY() > HEIGHT;
    }

    public static Vector2i gridProject(Vector2f v) {
        return new Vector2i((int) v.getX() / GRID_CELL_SIZE, (int) v.getY() / GRID_CELL_SIZE);
    }

    public static Vector2f gridUnproject(Vector2i v) {
        return new Vector2f(v.x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2, v.y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2);
    }

    public void addEntity(Entity entity) {
        cache.clear();
        if(entities.size() > 0){
            Entity e = entities.get(entities.size()-1);
            entities.set(entities.size()-1, entity);
            entities.add(e);
        }else{
            entities.add(entity);
        }
    }

    @SuppressWarnings("unchecked")
    public final <E extends Entity> List<E> getEntities(final Class<E> type) {
        if (cache.containsKey(type)) return (List<E>) cache.get(type);
        final List<E> result = entities.stream().filter(type::isInstance).map(type::cast).collect(toList());
        cache.put(type, result);
        return result;
    }

    public void removeEntity(Entity player) {
        cache.clear();
        entities.remove(player);
    }

    public boolean setGridCell(Vector2f position, Drawable entity) {
        Vector2i v = gridProject(position);
        if (grid[v.x][v.y] != null || entity == null) return false;
        grid[v.x][v.y] = entity;
        addEntity(entity);
        Vector2f v1 = gridUnproject(v);
        entity.getSprite().setPosition(v1);
        numberOfTowers++;
        return true;
    }

    public boolean isValidPosition(Vector2f position) {
        boolean b = setGridCell(position, () -> new Sprite(
                new Texture(Gdx.files.internal("images/laser-tower.png")),
                new Vector2f(MAX_WIDTH / 2, MAX_HEIGHT),
                new Vector2f(0, 200),
                GRID_CELL_SIZE,
                GRID_CELL_SIZE
        ));
        if (!b) return false;
        Queue<Vector2f> path = new PathFinder(this).findPath(getPositionGridCell(START), getPositionGridCell(END));
        Vector2i v = gridProject(position);
        removeGridCell(v.x, v.y);
        return path.size() > 0;
    }

    public Entity getGridCell(Vector2f position) {
        Vector2i v = gridProject(position);
        return grid[v.x][v.y];
    }

    private Vector2i getPositionGridCell(Vector2f position) {
        Vector2i v = gridProject(position);
        return new Vector2i(v.x < 0 ? 0 : v.x, v.y < 0 ? 0 : v.y);
    }

    public void removeGridCell(int x, int y) {
        numberOfTowers--;
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }

    public boolean blocked(int x, int y) {
        return x >= World.GRID_SPACES || y >= World.GRID_SPACES || x < 0 || y < 0 || grid[x][y] != null;
    }

    /**
     * @return the numberOfTowers
     */
    public int getNumberOfTowers() {
        return numberOfTowers;
    }
}
