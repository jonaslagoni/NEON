package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.wave.Wave;
import javafx.scene.control.TextField;

import java.util.Map;

public class HUD implements InputProcessor, Plugin, Controller {

    private Stage hud;
    private Entity selectedEntity;
    private World world;
    private Batch batch;
    private Entity selectedTower;
    private Group placementGroup;
    private Group upgradeGroup;
    private Group statsGroup;
    private GameData gameData;
    private ITowerService towerService;
    private Label waveCounterLabel;
    private Label waveScoreLabel;
    private Label coinLabel;
    private IWaveService waveService;
    private INeonWallet neonWallet;


    public HUD(World world,
               GameData gameData,
               Batch batch) {
        this.gameData = gameData;
        this.world = world;
        this.batch = batch;

    }

    @Override
    public void start() {
        gameData.addController(this);

        this.towerService = gameData.getService(ITowerService.class);
        this.waveService = gameData.getService(IWaveService.class);
        this.neonWallet = gameData.getService(INeonWallet.class);
        this.hud = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);

        Table table = new Table(gameData.getSkin());
        table.setFillParent(true);
        hud.addActor(table);

        statsGroup = new Group();
        statsGroup.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        placementGroup = new Group();
        placementGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        upgradeGroup = new Group();
        upgradeGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        upgradeGroup.setVisible(false);

        Table statsTable = new Table(gameData.getSkin());
        statsTable.setFillParent(true);

        Table placementTable = new Table(gameData.getSkin());
        placementTable.setFillParent(true);

        Table upgradeTable = new Table(gameData.getSkin());
        upgradeTable.setFillParent(true);

        waveCounterLabel = new Label("", gameData.getSkin());
        waveScoreLabel = new Label("",gameData.getSkin());
        coinLabel = new Label("",gameData.getSkin());
        //statsTable.add(waveCounterLabel);
        //statsTable.add(waveScoreLabel);
        //statsTable.add(coinLabel);

        TextButton upgradeButton = new TextButton("Upgrade", gameData.getSkin(), "upgradeTower");
        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedTower != null) {
                    towerService.upgrade(selectedTower);
                }
            }
        });
        upgradeTable.bottom().right().add(upgradeButton).width(150).height(30);


        statsTable.top().left().add(waveCounterLabel).width(-1550).row();
        statsTable.left().add(waveScoreLabel).width(-1550).row();
        statsTable.left().add(coinLabel).width(-1550).row();

        statsGroup.addActor(statsTable);
        placementGroup.addActor(placementTable);
        upgradeGroup.addActor(upgradeTable);

        hud.addActor(statsGroup);
        hud.addActor(placementGroup);
        hud.addActor(upgradeGroup);

        gameData.addInputProcessor(hud);
        gameData.addInputProcessor(this);

        /*Create button for each placable item in gamedata*/
        for (Map.Entry<String, Factory> entry : gameData.getPlaceables().entrySet()) {
            TextButton button = new TextButton(entry.getKey(), gameData.getSkin());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedEntity = entry.getValue().build(entry.getKey());
                }
            });
            placementTable.bottom().right().add(button);
        }
    }

    @Override
    public void stop() {
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
        Vector2 pos = gameData.getViewport().unproject(new Vector2(screenX, screenY));

        upgradeGroup.setVisible(false);
        placementGroup.setVisible(true);

        /* Abandon if click is outside of the world */
        if (World.isOutOfBounds(pos)) {
            return false;
        }
        if(!world.isValidPosition(pos)){
            return false;
        }
        /* If a tower is selected, place it */
        if (selectedEntity != null && selectedEntity instanceof Drawable) {
            world.setGridCell(pos, (Drawable) selectedEntity);
            selectedEntity = null;
            return true;
        }

        /* Select an already placed tower */
        Entity entity = world.getGridCell(pos);
        if (entity != null) {
            selectedTower = entity;
            upgradeGroup.setVisible(true);
            placementGroup.setVisible(false);
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

    @Override
    public void update() {
        waveCounterLabel.setText("Wave: " + Integer.toString(waveService.getWaveCount()));
        waveScoreLabel.setText("EnemyScore: " + Integer.toString(waveService.getWaveScore()));
        coinLabel.setText("Neon Coins: " + Integer.toString(neonWallet.getCoins()));

    }
}
