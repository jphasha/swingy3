package com.wethinkcode.java.swingy.model.enemies.tartarus.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.tartarus.Titan;

public class Atlas extends Titan {
    public Atlas() {
        super("Atlas");
        setLevel(2);
        setHitPoints(210);
        setAttack(25);
        setDefense(15);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/tartarus/atlas.jpg");
    }
}
