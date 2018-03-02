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
import com.neon.ui.ITowerPlacementProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lagoni
 */
public class Tower2Plugin implements ITowerPlugin{
    private Tower2Ui towerUi;
    
    private List<Tower> towers = new ArrayList();
    
    @Override
    public void addTower(Stage stage, BitmapFont font, Skin skin, ITowerPlacementProcessor towerPlacement) {
        towerUi = new Tower2Ui(font, skin, "button");
        towerUi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Tower tower = new Tower2(font, skin, "tower2");
                towers.add(tower);
                stage.addActor(tower);
                towerPlacement.setSelectedTower(tower);
            }
        });
        stage.addActor(towerUi);
    }

    @Override
    public void removeTower(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
