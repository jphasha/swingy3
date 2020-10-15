package com.wethinkcode.java.swingy.model.heroes.mountOlympus.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.mountOlympus.Olympian;

public class Zeus extends Olympian {
    public Zeus() {
        super("Zeus");
        setHeroLevel(4);
        setHeroHitPoints(300);
        setHeroAttack(35);
        setHeroDefence(27);
        setHeroXP(8050);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/olympus/zeus.jpg");
    }
}
