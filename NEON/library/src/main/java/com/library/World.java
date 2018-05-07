package com.library;

import com.library.interfaces.Drawable;
import com.library.interfaces.Entity;
import com.library.interfaces.IPathFindingService;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.util.stream.Collectors.toList;

public class World implements IWorldService {

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final Map<Class<?>, List<?>> cache = new HashMap<>();
    private int numberOfTowers = 0;
    private IPathFindingService pathFinder;
    
    /**
     * Adds entities to a list
     * If there is already a list of entities, and entity to the existing list
     * Else add entity to a new list
     * @param entity is an object like a tower or player
     */
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
    
    /**
     * ?????????
     * @param <E>
     * @param type
     * @return 
     */
    @SuppressWarnings("unchecked")
    @Override
    public  <E extends Entity> List<E> getEntities(final Class<E> type) {
        if (cache.containsKey(type)) {
            return (List<E>) cache.get(type);
        }
        final List<E> result = entities.stream().filter(type::isInstance).map(type::cast).collect(toList());
        cache.put(type, result);
        return result;
    }
    
    /**
     * Removes player from entity list
     * @param player object
     */
    @Override
    public void removeEntity(Entity player) {
        cache.clear();
        entities.remove(player);
    }

    /**
     * Sets an entity (tower) into the map
     * If it is a empty gridcell and there is choosen an entity (tower),
     * add the entity with sprite into the map in the empty gridcell
     * incement number of towers and return true
     * @param position is a gridcell
     * @param entity is a tower object
     * @return boolean
     */
    @Override
    public boolean setGridCell(Vector2f position, Drawable entity) {
        Vector2i v = IWorldService.gridProject(position);
        if (grid[v.x][v.y] != null || entity == null) {
            return false;
        }
        grid[v.x][v.y] = entity;
        addEntity(entity);
        Vector2f v1 = IWorldService.gridUnproject(v);
        entity.getSprite().setPosition(v1);
        numberOfTowers++;
        return true;
    }

    @Override
    public boolean isValidPosition(Vector2f position) {
        if(pathFinder != null){
            boolean b = setGridCell(position, () -> new Sprite(
                "",
                new Vector2f(MAX_WIDTH / 2, MAX_HEIGHT),
                new Vector2f(0, 200),
                GRID_CELL_SIZE,
                GRID_CELL_SIZE
        ));
        if (!b) return false;
        Queue<Vector2f> path = pathFinder.findPath(getPositionGridCell(START), getPositionGridCell(END));
        Vector2i v = IWorldService.gridProject(position);
        removeGridCell(v.x, v.y);
        return path.size() > 0;
        }
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

    /**
     * Removes an entity (tower) and sets the gridcell empty
     * @param x coordinate
     * @param y coordinate
     */
    private void removeGridCell(int x, int y) {
        numberOfTowers--;
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }

    @Override
    public boolean blocked(int x, int y) {
        return x >= GRID_SPACES || y >= GRID_SPACES || x < 0 || y < 0 || grid[x][y] != null;
    }

    /**
     * @return the WIDTH
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return the HEIGHT
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @return the MAX_WIDTH
     */
    public int getMAX_WIDTH() {
        return MAX_WIDTH;
    }

    /**
     * @return the MAX_HEIGHT
     */
    public int getMAX_HEIGHT() {
        return MAX_HEIGHT;
    }

    /**
     * @return the GRID_SPACES
     */
    public int getGRID_SPACES() {
        return GRID_SPACES;
    }

    /**
     * @return the GRID_CELL_SIZE
     */
    public int getGRID_CELL_SIZE() {
        return GRID_CELL_SIZE;
    }

    /**
     * @return the START
     */
    public Vector2f getSTART() {
        return START;
    }

    /**
     * @return the END
     */
    public Vector2f getEND() {
        return END;
    }
    
    public void removePathFinder() {
        pathFinder = null;
    }

    public void setPathFinder(IPathFindingService pathFinder) {
        this.pathFinder = pathFinder;
    }
}
