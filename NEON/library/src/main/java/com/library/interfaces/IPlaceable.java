/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import com.library.vectors.Vector2f;

/**
 *
 * @author emil
 */
public interface IPlaceable {

    String getTitle();

    void place(Vector2f pos);
}
