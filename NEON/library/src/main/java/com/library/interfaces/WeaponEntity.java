/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

/**
 * @author Mathias
 */
public interface WeaponEntity extends Entity {

    /**
     * CHanges the value for weaponentity
     * @param additiveDamage value to be added to damage
     * @param additiveRange value to be added to range
     * @param additiveFireCooldown value to firecooldown
     */
    void affectWeapon(float additiveDamage, float additiveRange, float additiveFireCooldown);

}
