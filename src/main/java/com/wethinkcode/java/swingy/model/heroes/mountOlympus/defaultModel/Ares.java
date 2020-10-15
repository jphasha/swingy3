package com.wethinkcode.java.swingy.model.heroes.mountOlympus.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.mountOlympus.Olympian;

public class Ares extends Olympian {
    public Ares() {
        super("Ares");
        setHeroLevel(1);
        setHeroHitPoints(150);
        setHeroAttack(20);
        setHeroDefence(12);
        setHeroXP(1000);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/olympus/ares.jpg");
    }
}
