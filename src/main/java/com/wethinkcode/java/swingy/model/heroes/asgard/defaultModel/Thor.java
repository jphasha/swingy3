package com.wethinkcode.java.swingy.model.heroes.asgard.defaultModel;

import com.wethinkcode.java.swingy.model.heroes.asgard.Aesir;

public class Thor extends Aesir {
    public Thor() {
        super("Thor");
        setHeroLevel(3);
        setHeroHitPoints(260);
        setHeroAttack(30);
        setHeroDefence(20);
        setHeroXP(4800);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/asgard/thor.jpg");
    }
}
