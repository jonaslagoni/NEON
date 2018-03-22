package com.neon.engine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        TexturePacker.process(settings, "./images", "./assets", "assets");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        // config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
         config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        //config.height = 768;
        //config.width = 1024;
        config.resizable = false;
        new LwjglApplication(new Game(), config);
    }
}