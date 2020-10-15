package com.wethinkcode.java.swingy.model.heroes.asgard.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.asgard.Aesir;

public class Baldur extends Aesir {
    public Baldur() {
        super("Baldur");
        setHeroLevel(1);
        setHeroHitPoints(160);
        setHeroAttack(20);
        setHeroDefence(10);
        setHeroXP(1000);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/asgard/baldur.jpg");
    }
}
