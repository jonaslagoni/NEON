/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

/**
 *
 * @author emil
 */
public interface IAssetManager {

    void loadAsset(String name, String path);

    void unloadAsset(String name);

}
