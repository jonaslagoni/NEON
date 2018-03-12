package com.neon.libary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.neon.common.search.Mover;
import com.neon.common.search.Path;
import com.neon.common.search.PathFinder;
import com.neon.common.search.TileBasedMap;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.search.astar.AStarPathFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World implements TileBasedMap{

    public static final int WIDTH = 2048;
    public static final int HEIGHT = 2048;
    public static final int GRID_SPACES = 16;
    public static final int GRID_CELL_SIZE = HEIGHT / GRID_SPACES;
    public static final Vector2 START = new Vector2(WIDTH / 2, HEIGHT);
    public static final Vector2 END = new Vector2(WIDTH / 2, 0);
    
    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final Map<Class<?>, List<?>> cache = new HashMap<>();

    
    private final PathFinder finder = new AStarPathFinder(this, 500, false);
    
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

    public boolean isValidPosition(Vector2 position){
        setGridCell(position, new Drawable() {
            @Override
            public Sprite getSprite() {
                return new Sprite(
                        new Texture(Gdx.files.internal("images/laser-tower.png")),
                        GRID_CELL_SIZE,
                        GRID_CELL_SIZE
                );
            }
        });
        Path path = finder.findPath(null, getPositionGridCell(START), getPositionGridCell(END));
        
        int x = (int) position.x / GRID_CELL_SIZE;
        int y = (int) position.y / GRID_CELL_SIZE;
        removeEntity(grid[x][y]);
        grid[x][y] = null;
        if(path == null){
            return false;
        }
        return true;
    }
    public Entity getGridCell(Vector2 position) {
        int x = (int) position.x / GRID_CELL_SIZE;
        int y = (int) position.y / GRID_CELL_SIZE;
        return grid[x][y];
    }
    
    public Vector2 getPositionGridCell(Vector2 position){
        float x = (int)position.x / GRID_CELL_SIZE-1;
        float y = (int)position.y / GRID_CELL_SIZE-1;
        return new Vector2(x < 0 ? 0 : x, y < 0 ? 0 : y);
    }

    public void removeGridCell(int x, int y) {
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }

    @Override
    public int getWidthInTiles() {
        return GRID_SPACES;
    }

    @Override
    public int getHeightInTiles() {
        return GRID_SPACES;
    }

    @Override
    public void pathFinderVisited(int x, int y) {
        //Dont need to save it.
    }

    @Override
    public boolean blocked(Mover mover, int x, int y) {
        if(x >= World.GRID_SPACES || y >= World.GRID_SPACES){
            return true;
        }
        if(grid[x][y] != null){
            return true;
        }
        return false;
    }

    @Override
    public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
        return 1;
    }

    /**
     * @return the finder
     */
    public PathFinder getFinder() {
        return finder;
    }
}
