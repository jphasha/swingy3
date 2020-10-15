package com.wethinkcode.java.swingy.model.enemies.jortunheim.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.jortunheim.Jortnar;

public class Jormungandr extends Jortnar {
    public Jormungandr() {
        super("Jormungandr");
        setLevel(3);
        setHitPoints(250);
        setAttack(32);
        setDefense(20);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/jortunheim/Jormungandr.jpg");
    }
}
