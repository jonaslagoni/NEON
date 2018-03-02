/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.neon.main.GameData;
import com.neon.tower.Tower;
import com.neon.tower.tower2.Tower2Plugin;

/**
 * @author Lagoni
 */
public class UiController implements IDeselectTower{

    private Stage stage;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas atlas;
    private GameData gameData;
    private TowerPlacementInputProcessor towerInput;
    
    public UiController(GameData gameData) {
        this.gameData = gameData;
        stage = new Stage();
        font = new BitmapFont();
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("./assets/assets.atlas"));
        skin.addRegions(atlas);
        towerInput = new TowerPlacementInputProcessor();
        towerInput.setDeselector(this);
        gameData.addInputProcessor(towerInput);
        createUI();
        gameData.addInputProcessor(stage);
    }

    private void createUI() {
        Tower2Plugin plugin1 = new Tower2Plugin();
        plugin1.addTower(stage, font, skin, towerInput);
        
    }

    public void draw() {
        stage.draw();
    }

    public void toggleUi() {
        stage.getRoot().setVisible(!stage.getRoot().isVisible());
    }
    

    @Override
    public void reset(Tower tower) {
        stage.getRoot().removeActor(tower);
    }
}
