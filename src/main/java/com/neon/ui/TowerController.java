/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.neon.tower.Tower;

/**
 * @author Lagoni
 */
public class TowerController {
    private static TowerController instance;
    private Stage stage;

    @SuppressWarnings("WeakerAccess")
    public TowerController() {
        stage = new Stage();
    }

    public static TowerController getInstance() {
        if (instance == null) {
            instance = new TowerController();
        }
        return instance;
    }

    public void addTower(Tower tower) {
        stage.addActor(tower);
    }

    public void removeTower(Tower tower) {
        stage.getRoot().removeActor(tower);
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
