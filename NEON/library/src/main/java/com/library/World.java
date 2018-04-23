package com.library;

import com.library.interfaces.Drawable;
import com.library.interfaces.Entity;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class World implements IWorldService {

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[GRID_SPACES][GRID_SPACES];
    private final Map<Class<?>, List<?>> cache = new HashMap<>();
    private int numberOfTowers = 0;
    

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
        numberOfTowers--;
        removeEntity(grid[x][y]);
        grid[x][y] = null;
    }

    @Override
    public boolean blocked(int x, int y) {
        return x >= GRID_SPACES || y >= GRID_SPACES || x < 0 || y < 0 || grid[x][y] != null;
    }

    /**
     * @return the numberOfTowers
     */
    @Override
    public int getNumberOfTowers() {
        return numberOfTowers;
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
}
