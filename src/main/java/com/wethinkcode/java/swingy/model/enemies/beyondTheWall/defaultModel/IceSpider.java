package com.wethinkcode.java.swingy.model.enemies.beyondTheWall.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.beyondTheWall.Undead;

public class IceSpider extends Undead {
    public IceSpider() {
        super("IceSpider");
        setLevel(2);
        setHitPoints(200);
        setAttack(25);
        setDefense(17);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/beyondTheWall/icespider.jpg");
    }
}
