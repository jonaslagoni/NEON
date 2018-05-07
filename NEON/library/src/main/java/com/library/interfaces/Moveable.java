package com.library.interfaces;

import com.library.MoveAbility;

public interface Moveable extends Drawable {
    
    /**
     * Returns entity's moveability
     * @return moveability
     */
    MoveAbility getMoveAbility();
}
