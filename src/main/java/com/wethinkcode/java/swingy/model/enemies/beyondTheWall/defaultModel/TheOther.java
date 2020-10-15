package com.wethinkcode.java.swingy.model.enemies.beyondTheWall.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.beyondTheWall.Undead;

public class TheOther extends Undead {
    public TheOther() {
        super("TheOther");
        setLevel(0);
        setHitPoints(100);
        setAttack(15);
        setDefense(7);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/beyondTheWall/theOther.jpg");
    }
}
