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
    public void hideTowerUpgrade();
    public void showTowerUpgrade(Tower tower);
    public void showTowerPlacement();
    public void hideTowerPlacement();
    public void deselectTowerPlacement();
    public void addTowerButton(Tower tower);
    public void addTowerToPlacement(Tower tower);
    
}
