package com.engine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void start(BundleContext context) throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        // config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        //config.height = 768;
        //config.width = 1024;
        config.resizable = false;
        new LwjglApplication(new Neon(), config);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
