/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.libary.interfaces;

/**
 * @author Mathias
 */
public interface WeaponEntity extends Entity {

    void affectWeapon(float additiveDamage, float additiveRange, float additiveFireCooldown);

}
