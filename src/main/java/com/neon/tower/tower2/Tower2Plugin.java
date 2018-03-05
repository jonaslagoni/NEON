/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.tower.tower2;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neon.tower.ITowerPlugin;
import com.neon.tower.Tower;
import com.neon.ui.IUiController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lagoni
 */
public class Tower2Plugin implements ITowerPlugin {
    @SuppressWarnings("FieldCanBeLocal")
    private Tower2Ui towerUi;
    private IUiController controller;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<Tower> towers = new ArrayList<>();

    public Tower2Plugin(BitmapFont font, Skin skin, IUiController controller) {
        this.setUiController(controller);
        this.addTower(font, skin);
    }

    @Override
    public void addTower(BitmapFont font, Skin skin) {
        towerUi = new Tower2Ui(font, skin, "button");
        towerUi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                controller.deselectTowerPlacement();
                Tower tower = new Tower2(font, skin, "tower2");
                tower.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        System.out.println("test : " + x + " | " + y);
                       
                    }
                });
                towers.add(tower);
                controller.addTowerToPlacement(tower);
            }
        });
        controller.addTowerButton(towerUi);
    }

    @Override
    public void setUiController(IUiController controller) {
        this.controller = controller;
    }

    @Override
    public void removeTower(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
