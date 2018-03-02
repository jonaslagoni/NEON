/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.neon.main.GameData;
import com.neon.tower.Tower;
import com.neon.tower.tower2.Tower2Plugin;

/**
 * @author Lagoni
 */
public class UiController implements IUiController{

    private Stage stage;
    private Table towerButtonTable; //for all the towers buttons for creating towers
    private Group towerUpgradeGroup; //For the upgrading the group
    private Group towerPlacementGroup;
    
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
        towerInput.setUiController(this);
        gameData.addInputProcessor(towerInput);
        gameData.addInputProcessor(stage);
        
        towerButtonTable = new Table();
        towerButtonTable.setVisible(true);
        towerButtonTable.setPosition(Gdx.graphics.getWidth()/2-100, 0);
        towerButtonTable.setFillParent(true);
        
        towerUpgradeGroup = new Group();
        towerUpgradeGroup.setVisible(false);
        
        towerPlacementGroup = new Group();
        towerPlacementGroup.setVisible(true);
        
        stage.addActor(towerButtonTable);
        stage.addActor(towerUpgradeGroup);
        stage.addActor(towerPlacementGroup);
        
        
        createTowers();
        
    }

    private void createTowers() {
        Tower2Plugin plugin = new Tower2Plugin(font, skin, this);
        Tower2Plugin plugin2 = new Tower2Plugin(font, skin, this);
        
    }

    public void draw() {
        stage.draw();
    }
    
    @Override
    public void deselectTowerPlacement(Tower tower) {
        towerPlacementGroup.removeActor(tower);
    }
    
    @Override
    public void hideTowerUpgrade() {
        towerUpgradeGroup.setVisible(false);
    }

    @Override
    public void showTowerUpgrade(Tower tower) {
        towerUpgradeGroup.setVisible(true);
    }

    @Override
    public void showTowerPlacement() {
        towerButtonTable.setVisible(true);
    }

    @Override
    public void hideTowerPlacement() {
        towerButtonTable.setVisible(false);
    }
    @Override
    public void addTowerButton(Tower tower) {
        towerButtonTable.add(tower).width(tower.getWidth()).height(tower.getHeight()).row();
    }

    @Override
    public void addTowerToPlacement(Tower tower) {
        towerPlacementGroup.clearChildren();
        towerPlacementGroup.addActor(tower);
        towerInput.setSelectedTower(tower);
    }

}
