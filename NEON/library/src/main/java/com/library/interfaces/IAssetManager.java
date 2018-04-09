/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import java.io.File;

/**
 *
 * @author emil
 */
public interface IAssetManager {

    void loadAsset(String name, File file);

    void unloadAsset(String name);

}
