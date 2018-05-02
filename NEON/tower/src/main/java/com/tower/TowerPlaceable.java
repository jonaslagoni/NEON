/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tower;

import com.library.interfaces.IPlaceable;
import com.library.vectors.Vector2f;

/**
 *
 * @author emil
 */
public class TowerPlaceable implements IPlaceable {

    private final TowerType towerType;
    private final ILocalTowerService towerService;
    private final String title;

    TowerPlaceable(TowerType towerType,
                   ILocalTowerService towerService,
                   String title) {
        this.towerType = towerType;
        this.towerService = towerService;
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void place(Vector2f pos) {
        towerService.place(pos, towerType);
    }

}
