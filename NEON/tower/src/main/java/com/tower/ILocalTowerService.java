/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tower;

import com.library.interfaces.ITowerService;
import com.library.vectors.Vector2f;

/**
 * @author emil
 */
public interface ILocalTowerService extends ITowerService {

    /**
     * Places a tower at the given position
     *
     * @param pos  Position for tower to be placed
     * @param type Type of the tower
     */
    void place(Vector2f pos, TowerType type);

}
