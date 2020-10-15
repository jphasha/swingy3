package com.wethinkcode.java.swingy.model.enemies.tartarus.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.tartarus.Titan;

public class Oranos extends Titan {
    public Oranos() {
        super("Oranos");
        setLevel(3);
        setHitPoints(260);
        setAttack(30);
        setDefense(20);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/tartarus/oranoss.jpg");
    }
}
