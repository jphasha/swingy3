package com.wethinkcode.java.swingy.model.heroes.asgard.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.asgard.Aesir;

public class Odin extends Aesir {
    public Odin() {
        super("Odin");
        setHeroLevel(5);
        setHeroHitPoints(360);
        setHeroAttack(40);
        setHeroDefence(30);
        setHeroXP(12200);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/asgard/odin.jpg");
    }
}
