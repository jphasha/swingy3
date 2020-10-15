package com.wethinkcode.java.swingy.model.enemies.beyondTheWall.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.beyondTheWall.Undead;

public class WhiteWalker extends Undead {
    public WhiteWalker() {
        super("WhiteWalker");
        setLevel(4);
        setHitPoints(300);
        setAttack(35);
        setDefense(27);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/beyondTheWall/whiteWalker.jpg");
    }
}
