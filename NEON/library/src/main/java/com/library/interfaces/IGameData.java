/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import com.library.TowerType;
import java.util.List;

/**
 *
 * @author emil
 */
public interface IGameData {
    public void addPlaceable(TowerType title);
    public List<TowerType> getPlaceables();
}
