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
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.library.interfaces.*;
import com.library.vectors.Vector2f;

import java.util.HashMap;
import java.util.Map;

public class HUD implements InputProcessor, Controller, IObserver {

    private final BitmapFont font;
    private final Batch batch;
    private final Map<IStatusText, Label> statusTexts = new HashMap<>();
    private Stage stage;
    private IPlaceable selectedPlacable;
    private Entity selectedTower;
    private Group placementGroup;
    private Group upgradeGroup;
    private VerticalGroup statsGroup;
    private IWorldService world;
    private IGameData gameData;
    private Table placementTable;
    private TextButton.TextButtonStyle buttonStyle;
    private Label.LabelStyle labelStyle;

    public HUD(Batch batch, BitmapFont font, IWorldService world, IGameData gameData) {
        this.gameData = gameData;
        this.world = world;
        this.font = font;
        this.batch = batch;
    }

    /**
     * Adds visual elements to the game screen HUD
     */
    public void start() {
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        statsGroup = new VerticalGroup();
        statsGroup.setFillParent(true);
        statsGroup.align(Align.topRight);
        stage.addActor(statsGroup);

        placementGroup = new Group();
        placementGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        upgradeGroup = new Group();
        upgradeGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        upgradeGroup.setVisible(false);

        placementTable = new Table();
        placementTable.padRight(Gdx.graphics.getWidth() / 100)
                .padBottom(Gdx.graphics.getHeight() / 100)
                .setFillParent(true);
        placementGroup.addActor(placementTable);
        stage.addActor(placementGroup);

        Table upgradeTable = new Table();
        upgradeTable.setFillParent(true);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        TextButton upgradeButton = new TextButton("Upgrade", buttonStyle);
//        upgradeButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                if (selectedTower != null) {
//                   towerService.upgrade(selectedTower);
//                }
//            }
//        });
        upgradeTable.bottom().right().add(upgradeButton).width(150).height(30);
        upgradeGroup.addActor(upgradeTable);
        stage.addActor(upgradeGroup);

        gameData.addObserver(this);

        update();
    }

    Stage getStage() {
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
        if (world != null) {
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

        /* Update text on status labels */
        statusTexts.forEach((status, label) -> label.setText(status.update()));
    }

    @Override
    public void update() {

        /* Create a label for each status text object */
        statsGroup.clear();
        statusTexts.clear();
        for (IStatusText status : gameData.getStatusText()) {
            Label label = new Label(status.update(), labelStyle);
            statusTexts.put(status, label);
            statsGroup.addActor(label);
        }

        /* Create a place button for each placable object */
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
