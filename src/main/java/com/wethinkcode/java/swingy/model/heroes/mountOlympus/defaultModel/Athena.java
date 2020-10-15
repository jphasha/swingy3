package com.wethinkcode.java.swingy.model.heroes.mountOlympus.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.mountOlympus.Olympian;

public class Athena extends Olympian {
    public Athena() {
        super("Athena");
        setHeroLevel(3);
        setHeroHitPoints(250);
        setHeroAttack(30);
        setHeroDefence(22);
        setHeroXP(4800);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/olympus/athena.jpg");
    }
}
