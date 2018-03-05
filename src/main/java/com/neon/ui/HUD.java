package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.neon.main.Game;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Entity;
import com.neon.player.Tower.Tower;

import java.util.Map;

public class HUD implements InputProcessor, Plugin {

    private final Stage hud;
    private Drawable selectedDrawable;
    private World world;

    public HUD() {
        this.hud = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    private static boolean isOutOfBounds(Vector2 v) {
        return v.x < 0 || v.x > World.WIDTH || v.y < 0 || v.y > World.HEIGHT;
    }

    @Override
    public void start(GameData gameData, World world) {
        this.world = world;

        hud = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);

        Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("./assets/assets.atlas")));

        Table table = new Table(skin);
        
        table.setFillParent(true);
        hud.addActor(table);

        gameData.addInputProcessor(hud);
        gameData.addInputProcessor(this);
        
        /*Create button for each placable item in gamedata*/
        for (Map.Entry<String, Drawable> entry : gameData.getPlaceables().entrySet()) {
            TextButton button = new TextButton(entry.getKey(), gameData.getSkin());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedDrawable = entry.getValue();
                }
            });
            table.bottom().right().add(button);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    public Stage getStage() {
        return hud;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 pos = Game.viewport.unproject(new Vector2(screenX, screenY));

        if(isOutOfBounds(pos)){
            return false;
        }
            
        Entity entity = world.getGridCell(pos);
        if(entity != null){
            if(entity instanceof Tower){
                Tower tower = (Tower)entity;
                System.out.println("Found tower");
                return true;
            }else{
                return false;
            }
        }else{
            if (selectedDrawable == null) return false;

            world.setGridCell(pos, selectedDrawable);
            selectedDrawable = null;
            return true;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
