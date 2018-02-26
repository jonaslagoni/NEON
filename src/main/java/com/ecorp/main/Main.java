package com.ecorp.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        config.title = "MyGame";
        config.resizable = false;
        //config.fullscreen = true;
        config.useGL30 = true;
        new LwjglApplication(new Game(), config);
    }
}