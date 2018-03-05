package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.neon.main.*;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Entity;
import tower.Tower;

import java.util.Map;

public class HUD implements InputProcessor, Plugin {

    private final Stage hud;
    private Drawable selectedDrawable;
    private World world;
    private Tower selectedTower;
    private Group placement;
    private Group towerUI;

    public HUD(Batch batch) {
        this.hud = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void start(GameData gameData, World world) {
        this.world = world;

        Table table = new Table(gameData.getSkin());
        table.setFillParent(true);
        hud.addActor(table);

        placement = new Group();
        towerUI = new Group();
        towerUI.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        placement.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        towerUI.setVisible(false);

        Table placementTable = new Table(gameData.getSkin());
        placementTable.setFillParent(true);

        Table towerUITable = new Table(gameData.getSkin());
        towerUITable.setFillParent(true);
        TextButton upgrade = new TextButton("Upgrade", gameData.getSkin(), "upgradeTower");
        upgrade.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedTower != null) {
                    selectedTower.upgrade();
                }
            }
        });
        towerUITable.bottom().right().add(upgrade).width(150).height(30);

        towerUI.addActor(towerUITable);

        placement.addActor(placementTable);
        hud.addActor(placement);
        hud.addActor(towerUI);


        gameData.addInputProcessor(hud);
        gameData.addInputProcessor(this);

        /*Create button for each placable item in gamedata*/
        for (Map.Entry<String, Factory> entry : gameData.getPlaceables().entrySet()) {
            TextButton button = new TextButton(entry.getKey(), gameData.getSkin());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedDrawable = entry.getValue().create(entry.getKey());
                }
            });
            placementTable.bottom().right().add(button);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // TODO remove hud
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

        /* Convert screen coordinates to world coordinates */
        Vector2 pos = Game.viewport.unproject(new Vector2(screenX, screenY));

        towerUI.setVisible(false);
        placement.setVisible(true);

        /* Abandon if click is outside of the world */
        if (World.isOutOfBounds(pos)) {
            return false;
        }

        /* If a tower is selected, place it */
        if (selectedDrawable != null) {
            world.setGridCell(pos, selectedDrawable);
            selectedDrawable = null;
            return true;
        }

        /* Select an already placed tower */
        Entity entity = world.getGridCell(pos);
        if (entity != null && entity instanceof Tower) {
            selectedTower = (Tower) entity;
            towerUI.setVisible(true);
            placement.setVisible(false);
            return true;
        }

        return false;
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
