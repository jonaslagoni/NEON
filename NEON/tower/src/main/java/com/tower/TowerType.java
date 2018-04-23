/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tower;

/**
 * @author Mathias
 */
public enum TowerType {
    LASER_TOWER("laser-tower"),
    MELEE_GLAIVE_TOWER("melee-glaive-tower"),
    PEA_SHOOTER("pea-shooter"),
    RANGE_POWERUP("range-powerup"),
    STRENGTH_POWERUP("strength-powerup"),
    RAILGUN_TOWER("railgun-tower"),
    ROCKET_TOWER("rocket-tower"),
    SPLASH_TOWER("splash-tower");

    private final String title;

    TowerType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
