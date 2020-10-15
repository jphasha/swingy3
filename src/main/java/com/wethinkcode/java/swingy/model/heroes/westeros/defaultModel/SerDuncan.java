package com.wethinkcode.java.swingy.model.heroes.westeros.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.westeros.Westerosi;

public class SerDuncan extends Westerosi {
    public SerDuncan() {
        super("Ser Duncan");
        setHeroLevel(0);
        setHeroHitPoints(100);
        setHeroAttack(17);
        setHeroDefence(5);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/westeros/SerDuncan.jpg");
    }
}
