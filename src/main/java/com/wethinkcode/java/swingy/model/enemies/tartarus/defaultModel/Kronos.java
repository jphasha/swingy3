package com.wethinkcode.java.swingy.model.enemies.tartarus.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.tartarus.Titan;

public class Kronos extends Titan {
    public Kronos() {
        super("Kronos");
        setLevel(4);
        setHitPoints(310);
        setAttack(35);
        setDefense(25);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/tartarus/kronos.jpg");
    }
}
