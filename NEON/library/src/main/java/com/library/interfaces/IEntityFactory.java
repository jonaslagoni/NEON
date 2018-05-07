/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

/**
 * @author emil
 */
public interface IEntityFactory {
    /**
     * Creates a targeable entity
     * @returns a targeable entity
     */
    Targetable createEntity();

}
