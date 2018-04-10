package com.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.library.GameData;
import com.library.TowerType;
import com.library.World;
import com.library.interfaces.*;
import com.library.vectors.Vector2f;

public class HUD implements InputProcessor, Controller {

    private final Batch batch;
    private World world;
    private GameData gameData;
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
    private final Skin skin;

    HUD(Batch batch, Skin skin) {
        this.skin = skin;
        this.batch = batch;
    }

    public void start() {
        this.hud = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);

        Table table = new Table(skin);
        table.setFillParent(true);
        hud.addActor(table);

        statsGroup = new Group();
        statsGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        placementGroup = new Group();
        placementGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        upgradeGroup = new Group();
        upgradeGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        upgradeGroup.setVisible(false);

        Table statsTable = new Table(skin);
        statsTable.setFillParent(true);

        Table placementTable = new Table(skin);
        placementTable.padRight(Gdx.graphics.getWidth() / 100)
                .padBottom(Gdx.graphics.getHeight() / 100)
                .setFillParent(true);

        Table upgradeTable = new Table(skin);
        upgradeTable.setFillParent(true);

        waveCounterLabel = new Label("", skin);
        waveScoreLabel = new Label("", skin);
        coinLabel = new Label("", skin);
        towers = new Label("", skin);
        waveCountdown = new Label("", skin);
        lifeLabel = new Label("", skin);

        TextButton upgradeButton = new TextButton("Upgrade", skin, "upgradeTower");
        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedTower != null) {
                    towerService.upgrade(selectedTower);
                }
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

        statsTable.align(Align.right).align(Align.top)
                .padLeft(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 9 * 2)
                .padRight(Gdx.graphics.getWidth() / 100);

        statsGroup.addActor(statsTable);
        placementGroup.addActor(placementTable);
        upgradeGroup.addActor(upgradeTable);

        hud.addActor(statsGroup);
        hud.addActor(placementGroup);
        hud.addActor(upgradeGroup);

        /*Create button for each placable item in gamedata*/
        for (TowerType title : gameData.getPlaceables()) {
            TextButton button = new TextButton("", skin, title.toString());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedEntity = title;
                }
            });

            if (counter % 4 == 0) {
                placementTable.row();
            }
            placementTable.bottom().right().add(button)
                    .width(World.GRID_CELL_SIZE / 2)
                    .height(World.GRID_CELL_SIZE / 2);

            counter++;
        }
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
        Vector2 vector = GameScreen.VIEWPORT.unproject(new Vector2(screenX, screenY));
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
