package com.wethinkcode.java.swingy.model.enemies.jortunheim.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.jortunheim.Jortnar;

public class Loki extends Jortnar {
    public Loki() {
        super("Loki");
        setLevel(1);
        setHitPoints(150);
        setAttack(22);
        setDefense(10);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/jortunheim/Loki.jpg");
    }
}
