package com.weapon;

import com.library.World;
import com.library.interfaces.Controller;
import com.library.interfaces.Drawable;
import com.library.interfaces.ICollisionService;
import com.library.interfaces.IProjectileService;
import com.library.interfaces.ITargetingService;
import com.library.interfaces.Targetable;
import com.library.vectors.Vector2f;
import static com.library.vectors.VectorUtils.distanceSquare;

class WeaponController implements Controller {

    private World world;
    private ICollisionService collisionService = null;
    private IProjectileService projectileService = null;
    private ITargetingService targetingService = null;


    private static boolean isCloser(Vector2f source, Vector2f first, Vector2f second) {
        return distanceSquare(first, source) < distanceSquare(first, second);
    }


    @Override
    public void update(float dt) {
        world.getEntities(Weapon.class).forEach(weapon -> updateWeapon(weapon, dt));
    }

    private void updateWeapon(Weapon weapon, float dt) {

        weapon.fireCooldown += dt;

        if (weapon.fireCooldown < weapon.fireRate) return;

        Drawable closest = null;

        for (Drawable target : collisionService.getCollisions(weapon.position, weapon.range)) {

            if (closest == null || isCloser(weapon.position, target.getSprite().getPosition(), closest.getSprite().getPosition()))
                if (target instanceof Targetable)
                    closest = target;
        }

        if (closest != null) {
            Vector2f position = closest.getSprite().getPosition();

            projectileService.newProjectile(weapon.position, position, weapon.getShotType(), weapon.damage);
            weapon.fireCooldown = 0;
        }
    }

    private Vector2f findTarget(Drawable targetSprite, Weapon weapon) {
        return targetingService.calculateTargetVector(weapon.position, targetSprite.getSprite());

    }
    
    public void setCollisionService(ICollisionService ics){
        this.collisionService = ics;
    }
    
    public void removeCollisionService(){
        this.collisionService = null;
    }
    
    public void setProjectileService(IProjectileService ips){
        this.projectileService = ips;
    }
    
    public void removeProjectileService(){
        this.projectileService = null;
    }
    
    public void setTargetingService(ITargetingService its){
        this.targetingService = its;
    }
    
    public void removeTargetingService(){
        this.targetingService = null;
    }
}
    
