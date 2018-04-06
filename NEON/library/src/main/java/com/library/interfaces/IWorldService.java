/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import static com.library.World.GRID_CELL_SIZE;
import static com.library.World.HEIGHT;
import static com.library.World.WIDTH;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IWorldService{

    public static boolean isOutOfBounds(Vector2f v){
         return v.getX() < 0 || v.getX() > WIDTH || v.getY() < 0 || v.getY() > HEIGHT;
    }

    public static Vector2i gridProject(Vector2f v){
        return new Vector2i((int) v.getX() / GRID_CELL_SIZE, (int) v.getY() / GRID_CELL_SIZE);
    }

    public static Vector2f gridUnproject(Vector2i v){
        return new Vector2f(v.x * GRID_CELL_SIZE + GRID_CELL_SIZE / 2, v.y * GRID_CELL_SIZE + GRID_CELL_SIZE / 2);
    }

    public void addEntity(Entity entity);

    public <E extends Entity> List<E> getEntities(final Class<E> type);

    public void removeEntity(Entity player);

    public boolean setGridCell(Vector2f position, Drawable entity);

    public boolean isValidPosition(Vector2f position);

    public Entity getGridCell(Vector2f position);

    public boolean blocked(int x, int y);

    public int getNumberOfTowers();

}
