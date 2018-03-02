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
import com.neon.main.GameData;
import com.neon.tower.Tower1;

/**
 * @author Lagoni
 */
public class UI implements Test{

    private Stage stage;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas atlas;
    private GameData gameData;
    private TowerPlacementInputProcessor towerInput;
    private Tower1 currentSelectedTower;
    
    public UI(GameData gameData) {
        this.gameData = gameData;
        stage = new Stage();
        font = new BitmapFont();
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("./assets/assets.atlas"));
        skin.addRegions(atlas);
        towerInput = new TowerPlacementInputProcessor();
        towerInput.setTest(this);
        gameData.addInputProcessor(towerInput);
        createUI();
        gameData.addInputProcessor(stage);
    }

    private void createUI() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("up-button");
        textButtonStyle.down = skin.getDrawable("down-button");
        textButtonStyle.checked = skin.getDrawable("checked-button");
        TextButton button = new TextButton("Button1", textButtonStyle);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Tower1 tower = new Tower1(font, skin, "up-button");
                if(currentSelectedTower != null){
                    stage.getRoot().removeActor(currentSelectedTower);
                }
                stage.getRoot().addActor(tower);
                currentSelectedTower = tower;
                towerInput.setTower(tower);
            }
        });
        button.setWidth(120);
        button.setHeight(25);
        stage.addActor(button);
        
    }

    public void draw() {
        stage.draw();
    }

    public void toggleUi() {
        stage.getRoot().setVisible(!stage.getRoot().isVisible());
    }
    
    public void setTower(){
        
    }

    @Override
    public void reset() {
        if(currentSelectedTower != null){
            stage.getRoot().removeActor(currentSelectedTower);
            currentSelectedTower = null;
        }
    }
}
