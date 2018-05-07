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
public interface IInputService {

    /**
     * Checks if the mouse is clicked
     * @returns true if clicked
     */
    boolean isClicked();

    /**
     * Gets the coordinates on a vector
     * @returns a new vector with coordinates
     */
    Vector2f getCords();
}
