package com.wethinkcode.java.swingy.model.heroes.westeros.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.westeros.Westerosi;

public class Balerion extends Westerosi {
    public Balerion() {
        super("Balerion");
        setHeroLevel(3);
        setHeroHitPoints(250);
        setHeroAttack(32);
        setHeroDefence(20);
        setHeroXP(4800);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/westeros/balerion.jpg");
    }
}
