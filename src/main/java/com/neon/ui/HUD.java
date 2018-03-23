package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.neon.libary.GameData;
import com.neon.libary.TowerType;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.libary.vectors.Vector2f;

public class HUD implements InputProcessor, Plugin, Controller {

    private final World world;
    private final Batch batch;
    private final GameData gameData;
    private Stage hud;
    private TowerType selectedEntity;
    private Entity selectedTower;
    private Group placementGroup;
    private Group upgradeGroup;
    private Group statsGroup;
    private Label waveCounterLabel;
    private Label waveScoreLabel;
    private Label coinLabel;
    private Label towers;
    private Label waveCountdown;
    private Label lifeLabel;
    private IWaveService waveService;
    private INeonService neonService;
    private ITowerService towerService;
    private IEnemyService enemyService;
    private ILifeService lifeService;
    private int counter;

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
        this.neonService = gameData.getService(INeonService.class);
        this.enemyService = gameData.getService(IEnemyService.class);
        this.lifeService = gameData.getService(ILifeService.class);
        this.hud = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);

        Table table = new Table(gameData.getSkin());
        table.setFillParent(true);
        hud.addActor(table);

        statsGroup = new Group();
        statsGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        placementGroup = new Group();
        placementGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        upgradeGroup = new Group();
        upgradeGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        upgradeGroup.setVisible(false);

        Table statsTable = new Table(gameData.getSkin());
        statsTable.setFillParent(true);

        Table placementTable = new Table(gameData.getSkin());
        placementTable.padRight(Gdx.graphics.getWidth() / 100).padBottom(Gdx.graphics.getHeight() / 100).setFillParent(true);

        Table upgradeTable = new Table(gameData.getSkin());
        upgradeTable.setFillParent(true);

        waveCounterLabel = new Label("", gameData.getSkin());
        waveScoreLabel = new Label("", gameData.getSkin());
        coinLabel = new Label("", gameData.getSkin());
        towers = new Label("", gameData.getSkin());
        waveCountdown = new Label("", gameData.getSkin());
        lifeLabel = new Label("", gameData.getSkin());

        TextButton upgradeButton = new TextButton("Upgrade", gameData.getSkin(), "upgradeTower");
        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedTower != null) towerService.upgrade(selectedTower);
            }
        });
        upgradeTable.bottom().right().add(upgradeButton).width(150).height(30);
        statsTable.top().right();


        statsTable.add("Wave: ").expandX().align(Align.left);
        statsTable.add(waveCounterLabel).expandX().align(Align.right).row();

        statsTable.add("Enemy Difficulty Value: ").expandX().align(Align.left);
        statsTable.add(waveScoreLabel).expandX().align(Align.right).row();

        statsTable.add("").row();

        statsTable.add("Next wave in: ").expandX().align(Align.left);
        statsTable.add(waveCountdown).expandX().align(Align.right).row();

        statsTable.add("").row();

        statsTable.add("Life: ").expandX().align(Align.left);
        statsTable.add(lifeLabel).expandX().align(Align.right).row();

        statsTable.add("Neon Coins: ").expandX().align(Align.left);
        statsTable.add(coinLabel).expandX().align(Align.right).row();

        statsTable.add("Towers: ").expandX().align(Align.left);
        statsTable.add(towers).expandX().align(Align.right).row();

        statsTable.align(Align.right).align(Align.top).padLeft(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 9 * 2).padRight(Gdx.graphics.getWidth() / 100);

        statsGroup.addActor(statsTable);
        placementGroup.addActor(placementTable);
        upgradeGroup.addActor(upgradeTable);

        hud.addActor(statsGroup);
        hud.addActor(placementGroup);
        hud.addActor(upgradeGroup);

        gameData.addInputProcessor(hud);
        gameData.addInputProcessor(this);

        /*Create button for each placable item in gamedata*/

        for (TowerType title : gameData.getPlaceables()) {
            TextButton button = new TextButton("", gameData.getSkin(), title.toString());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedEntity = title;
                }
            });

            if (counter % 4 == 0) {
                placementTable.row();
            }
            placementTable.bottom().right().add(button).width(World.GRID_CELL_SIZE / 2).height(World.GRID_CELL_SIZE / 2);
            counter++;
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
        Vector2 vector = gameData.getViewport().unproject(new Vector2(screenX, screenY));
        Vector2f pos = new Vector2f(vector.x, vector.y);

        upgradeGroup.setVisible(false);
        placementGroup.setVisible(true);

        /* Abandon if click is outside of the world */
        if (World.isOutOfBounds(pos)) {
            return false;
        }
        /* If a tower is selected, place it */
        if (selectedEntity != null) {
            if (!world.isValidPosition(pos)) {
                return false;
            }
            towerService.placeTower(pos, selectedEntity);
            selectedEntity = null;
            return false;
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
    public void update(float dt) {
        waveCounterLabel.setText("" + waveService.getWaveCount());
        waveScoreLabel.setText("" + waveService.getWaveScore());
        lifeLabel.setText("" + lifeService.getLife());
        coinLabel.setText("" + neonService.getCoins());
        towers.setText("" + world.getNumberOfTowers());
        waveCountdown.setText("" + enemyService.getWaveCountdown());
    }
}
