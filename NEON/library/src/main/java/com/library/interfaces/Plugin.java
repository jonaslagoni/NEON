package com.library.interfaces;

/**
 *
 */
public interface Plugin {

    /**
     * Instantiation of plugins
     */
    void start();

    /**
     * When stopping the plugin
     */
    void stop();
}
