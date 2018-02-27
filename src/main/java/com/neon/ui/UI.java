/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Lagoni
 */
public class UI {
    
    private Stage stage;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas atlas;
    
    public UI(){
        stage = new Stage();
        font = new BitmapFont();
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("./assets/assets.atlas"));
        skin.addRegions(atlas);
        createUI();
    }
    
    private void createUI(){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("up-button");
        textButtonStyle.down = skin.getDrawable("down-button");
        textButtonStyle.checked = skin.getDrawable("checked-button");
        TextButton button = new TextButton("Button1", textButtonStyle);
        button.setWidth(120);
        button.setHeight(25);
        stage.addActor(button);
    }
    
    public void draw(){
        stage.draw();
    }
    
    public void toggleUi(){
        stage.getRoot().setVisible(!stage.getRoot().isVisible());
    }
    
}
