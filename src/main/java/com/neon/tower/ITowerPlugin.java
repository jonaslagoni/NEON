/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.tower;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.neon.ui.ITowerPlacementProcessor;
import com.neon.ui.IUiController;

/**
 *
 * @author Lagoni
 */
public interface ITowerPlugin {
    public void setUiController(IUiController controller);
    public void addTower(BitmapFont font, Skin skin);
    public void removeTower(Stage stage);
}
