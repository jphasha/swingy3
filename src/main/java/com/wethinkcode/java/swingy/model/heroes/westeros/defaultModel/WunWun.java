package com.wethinkcode.java.swingy.model.heroes.westeros.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.westeros.Westerosi;

public class WunWun extends Westerosi {
    public WunWun() {
        super("Wun Wun");
        setHeroLevel(1);
        setHeroHitPoints(150);
        setHeroAttack(22);
        setHeroDefence(10);
        setHeroXP(1000);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/westeros/wunWun.jpg");
    }
}
