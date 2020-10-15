package com.wethinkcode.java.swingy.model.enemies.jortunheim.defaultModel;

import com.wethinkcode.java.swingy.model.enemies.jortunheim.Jortnar;

public class Surtur extends Jortnar {
    public Surtur() {
        super("Surtur");
        setLevel(5);
        setHitPoints(350);
        setAttack(42);
        setDefense(30);
        setImage("src/main/java/com/wethinkcode/java/swingy/model/images/jortunheim/Surtur.jpg");
    }
}
