/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.ui;

import com.neon.tower.Tower;

/**
 * @author Lagoni
 */
public interface IUiController {
    void hideTowerUpgrade();

    void showTowerUpgrade(Tower tower);

    void showTowerPlacement();

    void hideTowerPlacement();

    void deselectTowerPlacement(Tower tower);

    void addTowerButton(Tower tower);

    void addTowerToPlacement(Tower tower);

}
