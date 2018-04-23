package com.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.library.interfaces.*;
import com.library.vectors.Vector2f;

public class HUD implements InputProcessor, Controller, ViewObserver {

    private final BitmapFont font;
    private final Batch batch;

    private Stage stage;

    private IPlaceable selectedPlacable;
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

    private IWorldService world;
    private IGameData gameData;
    private IWaveService waveService;
    private INeonService neonService;
    private ITowerService towerService;
    private IEnemyService enemyService;
    private ILifeService lifeService;

    private Table placementTable;
    private TextButton.TextButtonStyle buttonStyle;

    public HUD(Batch batch, BitmapFont font, IWorldService world, IGameData gameData) {
        this.gameData = gameData;
        this.world = world;
        this.font = font;
        this.batch = batch;
    }

    public void start() {
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        statsGroup = new Group();
        statsGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        placementGroup = new Group();
        placementGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        upgradeGroup = new Group();
        upgradeGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        upgradeGroup.setVisible(false);

        Table statsTable = new Table();
        statsTable.setFillParent(true);

        placementTable = new Table();
        placementTable.padRight(Gdx.graphics.getWidth() / 100)
                .padBottom(Gdx.graphics.getHeight() / 100)
                .setFillParent(true);

        Table upgradeTable = new Table();
        upgradeTable.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        waveCounterLabel = new Label("", labelStyle);
        waveScoreLabel = new Label("", labelStyle);
        coinLabel = new Label("", labelStyle);
        towers = new Label("", labelStyle);
        waveCountdown = new Label("", labelStyle);
        lifeLabel = new Label("", labelStyle);

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        TextButton upgradeButton = new TextButton("Upgrade", buttonStyle);
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

        statsTable.add(new Label("Wave: ", labelStyle)).expandX().align(Align.left);
        statsTable.add(waveCounterLabel).expandX().align(Align.right).row();

        statsTable.add(new Label("Enemy Difficulty Value: ", labelStyle)).expandX().align(Align.left);
        statsTable.add(waveScoreLabel).expandX().align(Align.right).row();

        statsTable.add(new Label("", labelStyle)).row();
        statsTable.add(new Label("Next wave in: ", labelStyle)).expandX().align(Align.left);
        statsTable.add(waveCountdown).expandX().align(Align.right).row();

        statsTable.add(new Label("", labelStyle)).row();
        statsTable.add(new Label("Life: ", labelStyle)).expandX().align(Align.left);
        statsTable.add(lifeLabel).expandX().align(Align.right).row();

        statsTable.add(new Label("Neon Coins: ", labelStyle)).expandX().align(Align.left);
        statsTable.add(coinLabel).expandX().align(Align.right).row();

        statsTable.add(new Label("Towers: ", labelStyle)).expandX().align(Align.left);
        statsTable.add(towers).expandX().align(Align.right).row();

        statsTable.align(Align.right).align(Align.top)
                .padLeft(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 9 * 2)
                .padRight(Gdx.graphics.getWidth() / 100);

        statsGroup.addActor(statsTable);
        placementGroup.addActor(placementTable);
        upgradeGroup.addActor(upgradeTable);

        stage.addActor(statsGroup);
        stage.addActor(placementGroup);
        stage.addActor(upgradeGroup);
        /*Create button for each placable item in gamedata*/

        gameData.addObserver(this);

        updateView();
    }

    public Stage getStage() {
        return stage;
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
        if (IWorldService.isOutOfBounds(pos)) {
            return false;
        }
        /* If a tower is selected, place it */
        if (selectedPlacable != null && world.isValidPosition(pos)) {
            selectedPlacable.place(pos);
            selectedPlacable = null;
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
        towers.setText("" + (world == null ? "World not sat" : world.getNumberOfTowers()));
        waveCountdown.setText("" + enemyService.getWaveCountdown());
    }

    @Override
    public void updateView() {
        placementTable.clear();
        for (IPlaceable placeable : gameData.getPlaceables()) {
            TextButton button = new TextButton(placeable.getTitle(), buttonStyle);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedPlacable = placeable;
                }
            });
            placementTable.row();
            placementTable.bottom().right().add(button)
                    .width(IWorldService.GRID_CELL_SIZE / 2)
                    .height(IWorldService.GRID_CELL_SIZE / 2);
        }
    }
}
