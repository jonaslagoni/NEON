/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neon.tower.Tower;
import com.neon.tower.Tower1;

/**
 *
 * @author Lagoni
 */
public class TowerController {
    private static TowerController instance;
    public static TowerController getInstance(){
        if(instance == null){
            instance = new TowerController();
        }
        return instance;
    }
    private Stage stage;
    
    public TowerController(){
        stage = new Stage();
    }
    
    public void addTower(Tower tower){
        stage.addActor(tower);
    }
    
    public void removeTower(Tower tower){
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
